package com.gdu.app13.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app13.domain.SleepUserDTO;
import com.gdu.app13.domain.UserDTO;

public interface UserService {
	public Map<String, Object> isReduceId(Map<String, Object> map);
	public Map<String, Object> isReduceEmail(Map<String, Object> map);
	public Map<String, Object> sendAuthCode(String email);
	public void join(HttpServletRequest request, HttpServletResponse response); // 반환타입이 없다는건 어떤 jsp로 넘길것도없고, response를 여기서 만들겠다는 의미
	public void retire(HttpServletRequest request, HttpServletResponse response);
	public void login(HttpServletRequest request, HttpServletResponse response);
	public void keepLogin(HttpServletRequest request, HttpServletResponse response);
	// KeepLoginInterceptor에서 호출(원래 서비스는 컨트롤러가 호출하는데 요건 인터셉터가 호출) 요청 들어가기 전에 인터셉터를 동작시켜야해서
	public void logout(HttpServletRequest request, HttpServletResponse response);
	public UserDTO getUserBySessionId(Map<String, Object> map); // keepLoginInterceptor에서 호출
	public Map<String, Object> confirmPassword(HttpServletRequest request); // json을 만들기 위해
	public void modifyPassword(HttpServletRequest request, HttpServletResponse response);
	public void sleepUserHandle(); // sleepUserScheduler에서 호출
	public SleepUserDTO getSleepUserById(String id);
	public void restoreUser(HttpServletRequest request, HttpServletResponse response);
	public String getNaverLoginApiURL(HttpServletRequest request); // session이 필요하므로 request 넘겨줌
	public UserDTO getNaverLoginTokenNProfile(HttpServletRequest request);
}
