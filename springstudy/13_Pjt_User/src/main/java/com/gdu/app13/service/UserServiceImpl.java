package com.gdu.app13.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.app13.domain.RetireUserDTO;
import com.gdu.app13.domain.UserDTO;
import com.gdu.app13.mapper.UserMapper;
import com.gdu.app13.util.SecurityUtil;

@PropertySource(value = {"classpath:email.properties"})
@Service
public class UserServiceImpl implements UserService {
	// 이메일을 보내는 사용자 정보
	@Value(value = "${mail.username}")
	private String username;  // 본인 지메일 주소
	@Value(value="${mail.password}")
	private String password;  // 발급 받은 앱 비밀번호
	
	// field값
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private SecurityUtil securityUtil;
	
	
	@Override
	public Map<String, Object> isReduceId(Map<String, Object> map) {
		// True이면 회원, False이면 비회원
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isUser", userMapper.selectUserByMap(map) != null); // null이 아닐 때. 조회되었으면 True
		result.put("isRetireUser", userMapper.selectRetireUserById(map) != null);
		return result;
	}
	
	@Override
	public Map<String, Object> isReduceEmail(Map<String, Object> map) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isUser", userMapper.selectUserByMap(map) != null); // null이 아닐 때. 조회되었으면 True
		return result;
	}

	@Override
	public Map<String, Object> sendAuthCode(String email) {
		/*
			
		*/
		// 인증코드 만들기
		String authCode = securityUtil.getAuthCode(6); // 첫번째 방법 (수동으로 우리가 만든것)
//		String authCode = securityUtil.generateRandomString(6); // 두번째 방법 (스프링이 해주는 것)
		System.out.println("발송된 인증코드 : " + authCode);
		
		// 이메일 전송을 위한 필수 속성을 Property 객체로 생성
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");  // 구글 메일로 보냄(보내는 메일은 구글 메일만 가능) API자체가 그람,, 받는건 상관없음
		properties.put("mail.smtp.port", "587"); 			 // 구글 메일로 보내는 포트번호
		properties.put("mail.smtp.auth", "true"); 			 // 인증된 메일
		properties.put("mail.smtp.starttls.enable", "true"); // TLS 허용
		
		/*
		 	이메일 보내기 API 사용을 위한 사전 작업
		 	
		 	1. 구글 로그인
		 	2. Google 계정 - 보안
		 		1) 2단계 인증 - 사용
		 		2) 앱 비밀번호
		 			(1) 앱 선택 : 기타
		 			(2) 기기 선택 : Windows 컴퓨터
		 			(3) 생성 버튼 :   16자리 앱 비밀번호를 생성해 줌(이 비밀번호를 이메일 보낼 때 사용)
		*/
		
		
		// 사용자 정보를 javax.mail.Session에 저장
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override // protected라서 자동으로 안들어와서 import 해줌
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		// 이메일 작성 및 전송
		try {
			Message message = new MimeMessage(session);
			// 보내는사람, 받는사람
			message.setFrom(new InternetAddress(username, "인증코드관리자")); // 보내는 사람
			message.setRecipient(Message.RecipientType.TO , new InternetAddress(email)); // 받는사람
			// Recipients (s가 들어가면 여러사람한테 보냄) CC : 받는사람여러명(참조), BCC : 블라인드CC
			
			// 제목
			message.setSubject("[Application]인증 요청 메일입니다.");
			// 본문
			message.setContent("인증번호는<strong>" + authCode + "</strong>입니다.", "text/html; charset=UTF-8"); // MIME-TYPE을 적어준다.
			
			// 보내기
			Transport.send(message); // 이메일 전송
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// join.jsp로 반환할 데이터
		// 생성한 인증코드를 보내줘야 함
		// 그래야 사용자가 입력한 인증코드와 비교할 수 있음
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("authCode", authCode);
		
		return result;
	}

	@Transactional
	@Override
	public void join(HttpServletRequest request, HttpServletResponse response) {
		
		// 파라미터 
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String mobile = request.getParameter("mobile");
		String birthyear = request.getParameter("birthyear");
		String birthmonth = request.getParameter("birthmonth");
		String birthdate = request.getParameter("birthdate");
		String postcode = request.getParameter("postcode");
		String roadAddress = request.getParameter("roadAddress");
		String jibunAddress = request.getParameter("jibunAddress");
		String detailAddress = request.getParameter("detailAddress");
		String extraAddress = request.getParameter("extraAddress");
		String email = request.getParameter("email");
		String location = request.getParameter("location");
		String promotion = request.getParameter("promotion");
		
		// 일부 파라미터는 DB에 넣을 수 있도록 가공
		pw = securityUtil.sha256(pw); // 사용자가 보낸 데이터가 가공되서 암호화
		name = securityUtil.preventXSS(name); // name은 공백검사만 거쳐 들어오기 때문에 스크립트 공격이 들어올 수있으므로
		String birthday = birthmonth + birthdate; // DB에 넣을 수 있도록 가공 (연계데이터 분석)
		detailAddress = securityUtil.preventXSS(detailAddress);
		int agreeCode = 0; // 필수 동의(필수동의 안되어있는 사람은 없을테니 초기화로 0을 적음)
		if(!location.isEmpty() && promotion.isEmpty()) { // 파라미터로 받아올 때는 null이 아닌 on 또는 '' 공백이다.
			agreeCode = 1; // 필수 + 위치					
		} else if(location.isEmpty() && !promotion.isEmpty()) {
			agreeCode = 2; // 필수 + 프로모션
		} else if(!location.isEmpty() && !promotion.isEmpty()) {
			agreeCode = 3; // 필수 + 위치 + 프로모션
		}
		
		// DB로 보낼 UserDTO 만들기
		UserDTO user = UserDTO.builder()
				.id(id)
				.pw(pw)
				.name(name)
				.gender(gender)
				.email(email)
				.mobile(mobile)
				.birthyear(birthyear)
				.birthday(birthday)
				.postcode(postcode)
				.roadAddress(roadAddress)
				.jibunAddress(jibunAddress)
				.detailAddress(detailAddress)
				.extraAddress(extraAddress)
				.agreeCode(agreeCode)
				.build(); // 지어라. 하며 마침표찍는 것
		
		// 회원가입처리
		int result = userMapper.insertUser(user);
		
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("id", id);
		
		// 응답
		try {
			// 로그인 처리를 위해서 session에 로그인 된 사용자 정보를 올려둠
			// 모든 페이지 위에 session이 있기 때문에 (기본30분)동안 모든 페이지에서 세션정보를 뽑아서 사용할 수 있다.
			request.getSession().setAttribute("loginUser", userMapper.selectUserByMap(userMap));
			
			// 로그인 기록 남기기
			int updateResult = userMapper.updateAccessLog(id);
			if(updateResult == 0) { // update 실패 시.
				userMapper.insertAccessLog(id); // 로그인 때마다 항상 하고싶으면 188,189조건없이 해당코드만 적으면 됨.
			}
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(result > 0) {
				out.println("<script>");
				out.println("alert('회원 가입이 완료되었습니다.')");
				out.println("location.href='"+ request.getContextPath() +"';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원 가입에 실패하셨습니다..')");
				out.println("history.go(-2);"); // 2칸 전으로 돌아가는 것 (history.back은 go(-1)과 같음)
				out.println("</script>");
			}
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional // insert, delete, update가 2개이상이면 Transactional 처리해주어야한다.
	@Override
	public void retire(HttpServletRequest request, HttpServletResponse response) {
		
		// 탈퇴할 회원의 userNo, id, joinDate는 session의 loginUser에 저장되어 있다.
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
		
		// 탈퇴할 회원 RetireUserDTO 생성
		RetireUserDTO retireUser = RetireUserDTO.builder()
									.userNo(loginUser.getUserNo())
									.id(loginUser.getId())
									.joinDate(loginUser.getJoinDate())
									.build();
		
		// 탈퇴처리
		int deleteResult = userMapper.deleteUser(loginUser.getUserNo()); // 세션에 저장되어있는 userNo 넘겨주기.
		int insertResult = userMapper.insertRetireUser(retireUser); 	 // retireUser 전달
		
		// 응답
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(deleteResult > 0 && insertResult > 0) {
				
				// session 초기화(로그인 사용자 loginUser 삭제를 위해)
				session.invalidate(); // 안하면 회원탈퇴를 했는데도 로그인이 되어있는상태가 되어있을 수 있음(DB는 삭제된상태)
				
				out.println("<script>");
				out.println("alert('회원 탈퇴되었습니다.')");
				out.println("location.href='"+ request.getContextPath() +"';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원탈퇴처리에 실패하셨습니다.')");
				out.println("history.go(-1);"); // 2칸 전으로 돌아가는 것 (history.back은 go(-1)과 같음)
				out.println("</script>");
			}
			out.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void login(HttpServletRequest request, HttpServletResponse response) {
		//자기 스스로 이동할 수 있는 코드 영역이 있기 때문에 반환타입을 void로 처리

		// 파라미터
		String url = request.getParameter("url");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// pw는 DB에 저장된 데이터와 동일한 형태로 가공
		pw = securityUtil.sha256(pw);
		
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("id", id);
		userMap.put("pw", pw);
	
		// id, pw가 일치하는 회원을 DB에서 조회하기
		UserDTO loginUser = userMapper.selectUserByMap(userMap);
		
		// id, pw가 일치하는 회원이 있다 : 로그인 기록 남기기 + session에 loginUser 저장하기
		if(loginUser != null) {
			
			// 로그인 유지 처리는 keepLogin 메소드가 따로 처리함
			keepLogin(request, response);
			
			// 로그인 처리를 위해서 session에 로그인 된 사용자 정보를 올려둠
			request.getSession().setAttribute("loginUser", loginUser);
			
			// 로그인 기록 남기기
			int updateResult = userMapper.updateAccessLog(id);
			if(updateResult == 0) {
				userMapper.insertAccessLog(id);
			}
			
			
			// 이동 (로그인페이지 이전 페이지로 되돌아가기)
			try {
				response.sendRedirect(url);
			} catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		// id, pw가 일치하는 회원이 없다 : 로그인 페이지로 돌려 보내기
		else {
			// 응답
			try {
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('일치하는 회원 정보가 없습니다.');");
				out.println("location.href='" + request.getContextPath() + "/user/login/form';");
				out.println("</script>");
				out.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void keepLogin(HttpServletRequest request, HttpServletResponse response) {
		/*
			로그인 유지를 체크한 경우
			
			1. session_id를 쿠키에 저장해둔다.
				(쿠키명 : keepLogin)
			2. session_id를 DB에 저장해 둔다.
				(SESSION_ID 칼럼에 session_id를 저장하고, 
				 SESSION_LIMIT_DATE 칼럼에 로그인유지가 언제까지 유효한지(세션 만료일)를 (나는 15일 후 날짜)저장
			
			로그인 유지를 체크하지 않은 경우
			1. 쿠키 또는 DB중 하나만 삭제하면 된다. (둘다 매칭해서 같은 경우 저장된것을 꺼내오는 시스템이라서)
			   편의상 쿠키명 keepLogin을 제거하는 것으로 처리한다. 
			
		*/
		String id = request.getParameter("id");
		String keepLogin = request.getParameter("keepLogin");
		
		if(keepLogin != null) { // 로그인 유지를 체크한 경우 : keep
			
			// session_id
			String sessionId = request.getSession().getId();
			
			// 1. session_id를 쿠키에 저장하기
			Cookie cookie = new Cookie("keepLogin", sessionId); // session_id 값
			cookie.setMaxAge(60 * 60 * 24 * 15); // 60초(1분), 60분(1시간), 24시간(1일), 15일 // 총 15일
			cookie.setPath(request.getContextPath()); // localhost:9090/app13이라는 contextPath를 사용하는 모든 페이지에서 쿠키를 사용할수있다.
			response.addCookie(cookie); // 쿠키를 클라이언트에게 보내는 것은 응답이므로 response에 담는다.
			
			// 2. session_id를 DB에 저장하기
			UserDTO user = UserDTO.builder()
					.id(id)
					.sessionId(sessionId)		// timeStamp 1/1000 이므로, timeStamp에 15일을 더한 값
					.sessionLimitDate(new Date(System.currentTimeMillis() + (60 * 60 * 24 * 15) * 1000)) 
					.build();
			
			userMapper.updateSessionInfo(user);
			
		} else { // 로그인 유지를 체크하지 않은 경우 : Null
			
			// keepLogin 쿠키 제거하기
			Cookie cookie = new Cookie("keepLogin", ""); // keepLogin이라는 값을 빈 문자열로 만듦
			cookie.setMaxAge(0); // 쿠키 유지 시간이 0이면 삭제를 의미함
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie); // 응답에 쿠키 정보를 담음
			
		}
		
	}
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		// 로그아웃 하려면 세션 초기화. (자동로그인을 풀려면 쿠키 한쪽 제거해줘야함)
		
		// 로그아웃 처리
		HttpSession session = request.getSession(); // 세션
		if(session.getAttribute("loginUser") != null) {
			session.invalidate(); // 세션 초기화			
		}
		// 위 코드를 request.getSession().invalidate()로 줄일 수 있음
		
		// 로그인 유지 풀기
		// cookie의 이름 중 "keepLogin"이 있다면 // 원래 점검코드가 있었는데 있으나 없으나 덮어쓰기함
		Cookie cookie = new Cookie("keepLogin", ""); // keepLogin이라는 값을 빈 문자열로 만듦
		cookie.setMaxAge(0); // 쿠키 유지 시간이 0이면 삭제를 의미함
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie); // 응답에 쿠키정보를 담음
		
		
		
	}
	
	@Override
	public UserDTO getUserBySessionId(Map<String, Object> map) {
		return userMapper.selectUserByMap(map);
	}
	
	@Override
	public Map<String, Object> confirmPassword(HttpServletRequest request) {
		
		// 파라미터 pw + SHA-256 처리
		String pw = securityUtil.sha256(request.getParameter("pw"));
		
		
		// id
		HttpSession session = request.getSession(); // Session에 저장되어있는 아이디를 빼온다.
		String id = ((UserDTO)session.getAttribute("loginUser")).getId(); // 로그인 된 사람의 Id
		
		// 조회 조건으로 사용할 Map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pw", pw);
		
		// id, pw가 일치하는 회원 조회
		UserDTO user = userMapper.selectUserByMap(map);
		
		// 결과 반환(결과로 사용할 맵을 따로 만듦)
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isUser", user != null); // true, false로 처리
		
		return result;
	}
	
	@Override
	public void modifyPassword(HttpServletRequest request, HttpServletResponse response) {
		
		// 현재 로그인 된 사용자
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");

		// 파라미터
		String pw = securityUtil.sha256(request.getParameter("pw"));
		
		// 동일한 비밀번호로 변경 금지
		if(pw.equals(loginUser.getPw())) {
			try {
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('현재 비밀번호와 동일한 비밀번호로 변경할 수 없습니다.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		// 사용자 번호 (세션에 들어있음)
		int userNo = loginUser.getUserNo();
		
		// DB로 보낼 UserDTO
		UserDTO user = UserDTO.builder()
				.userNo(userNo)
				.pw(pw)
				.build();
		System.out.println(user.toString());
		
		// 비밀번호 수정
		int result = userMapper.updateUserPassword(user);
		// 응답
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(result > 0) {
				
				// session에 저장된 loginUser 업데이트
				loginUser.setPw(pw);
				
				out.println("<script>");
				out.println("alert('비밀번호가 수정되었습니다.')");
				out.println("location.href='"+ request.getContextPath() +"/user/mypage';");
				out.println("</script>");
				
			} else {
				
				out.println("<script>");
				out.println("alert('비밀번호가 수정되지 않았습니다.')");
				out.println("history.back();");
				out.println("</script>");
				
			}
			
			out.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		} // DB는 내용이 바뀌었지만 Session에는 갱신해주지 않아서 옛날 정보가 들어가있다.
		
		
		
	}
	
	// 하루에 한번 스케줄러를 통해 휴면계정 처리
	@Transactional // Insert와 Delete가 동시에 진행되기 때문
	@Override
	public void sleepUserHandle() {
		int insertCount = userMapper.insertSleepUser(); // 휴면계정 등록
		if(insertCount > 0) {
			userMapper.deleteUserForSleep(); // 유저목록에서 휴면계정 삭제
		}
	}
	
}
