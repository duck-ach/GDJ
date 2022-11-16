package com.gdu.app13.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
	public Map<String, Object> isReduceId(Map<String, Object> map);
	
	public Map<String, Object> isReduceEmail(Map<String, Object> map);
	public Map<String, Object> sendAuthCode(String email);
	public void join(HttpServletRequest request, HttpServletResponse response); // 반환타입이 없다는건 어떤 jsp로 넘길것도없고, response를 여기서 만들겠다는 의미
	public void retire(HttpServletRequest request, HttpServletResponse response);
	public void login(HttpServletRequest request, HttpServletResponse response);
}