package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ActionForward;
import domain.Member;
import repository.MemberDao;

public class MemberServiceImpl implements MemberService {

	@Override
	public ActionForward login(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		Member memeber = Member.builder()
				.id(id)
				.pw(pw)
				.build();
		
		// mapper에서 온 데이터가 여기로옴. 성공하면 멤버가 반환되고, 
		Member login = MemberDao.getInstance().login(memeber);
		
		if(login != null) { // 페이지가 이동되어도 정보가 저장되는 공간인 Session에 저장 (session은 request파라미터로 알아낼 수 있음)
			// 성공
			HttpSession session = request.getSession();
			session.setAttribute("login", login); // (저장하는이름(마음대로해도됨), 저장할 데이터)
			return new ActionForward("/index.jsp", false); // Forward로 처리 (로그인이 성공하면 index.jsp 로 이동)
		} else { //request.getContextPath() <이건 Redirect 처리할 때
			// 실패
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 실패');");
				out.println("history.back()"); // 메세지 alert를 만들고 나면 꼭 이동(history.back(), location.href)는 같이 다닌다.
				out.println("</script>");
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null; // 윙
		}

	}

	@Override
	public ActionForward logout(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		session.invalidate(); // 세션 초기화
		return new ActionForward(request.getContextPath(), true);

	}

	@Override
	public void register(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		Member member = Member.builder()
				.id(id)
				.pw(pw)
				.name(name)
				.email(email)
				.build();
		
		int result = MemberDao.getInstance().insertMember(member);
		
		try {
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if(result > 0) {
				// 회원가입하면 로그인 처리
				// 회원가입한 회원의 정보를 DB에서 가져온 뒤 session에 login이라는 이름으로 올리기
				HttpSession session = request.getSession();
				session.setAttribute("login", MemberDao.getInstance().login(member));
				out.println("alert('환영합니다');");
				out.println("location.href='" + request.getContextPath() + "';"); // contextPath로 이동하라는 것은 메인 홈페이지로 가라는 것과 같음
			} else {
				out.println("alert('회원 가입에 실패했습니다.');");
				out.println("history.back();");
			}
			out.println("</script>");
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void cancel(HttpServletRequest request, HttpServletResponse response) {
		
		// 세션에 있는 정보는 지워지지 않는 정보
		// 세션이 지워지는 시점 : (로그아웃눌렀을 때, 브라우저 닫혔을 때, 세팅된시간이 지났을 때)
		
		// session에 저장된 login 정보에서 탈퇴할 회원의 정보를 추출
		HttpSession session = request.getSession();
		Member login = (Member)session.getAttribute("login"); // 세션에서 꺼내쓸 때는 꼭 캐스팅할것. Member 꺼냈으면 Member로 캐스팅
		int memberNo = login.getMemberNo();
		
		int result = MemberDao.getInstance().deleteMember(memberNo);
		
		try {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if(result > 0) {
				// 탈퇴 성공하면 session 초기화
				session.invalidate(); // 세션초기화
				out.println("alert('추신. 당신을 꽤 좋아했어...');");
				out.println("location.href='" + request.getContextPath() + "';");
			} else {
				out.println("history.back();");
			}
			out.println("</script>");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		
	}

}
