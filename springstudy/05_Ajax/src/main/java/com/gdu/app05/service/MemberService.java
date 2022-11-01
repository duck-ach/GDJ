package com.gdu.app05.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app05.domain.Member;

public interface MemberService {
	
	// 요청 파라미터
	public String execute1(HttpServletRequest request, HttpServletResponse response); // response를 사용할 때 Service가 최초가 아니라 Controller이다.
	public Member execute2(String id, String pw);
	public Map<String, Object> execute3(Member member);
	
	// 요청 JSON (Map 또는 Member 객체가 받을 수 있음)
	// Map을 JSON으로 변환하거나 JSON을 Map으로 변환하는것은 JACKSON이 여전히 해주고 있다.
	public Member execute4(Member member);
	
}
