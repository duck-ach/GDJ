package com.gdu.app13.service;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

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
	public Map<String, Object> isReduceId(String id) {
		// True이면 회원, False이면 비회원
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isUser", userMapper.selectUserById(id) != null); // null이 아닐 때. 조회되었으면 True
		result.put("isRetireUser", userMapper.selectRetireUserById(id) != null);
		return result;
	}
	
	@Override
	public Map<String, Object> isReduceEmail(String email) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isUser", userMapper.selectUserByEmail(email) != null); // null이 아닐 때. 조회되었으면 True
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
		result.put("authcode", authCode);
		
		return result;
	}
}
