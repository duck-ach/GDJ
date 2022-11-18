package com.gdu.app13.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import com.gdu.app13.domain.UserDTO;
import com.gdu.app13.service.UserService;

public class KeepLoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;
	
	// Controller의 모든 요청 이전에 KeepLoginInterceptor가 개입
	// Controller의 모든 요청 이전에 개입한다는 코드를 servlet-context.xml에 작성해 둔다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 로그인이 되어 있지 않은 경우 + 쿠키에 keepLogin이 있는 경우 => 로그인 유지 동작
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			
			// spring framework는 cookie사용을 도와준다. (ex. 예를들면 쿠키리스트 죄다 가져와서 for문 돌리기를 안해도된다.)
			Cookie cookie = WebUtils.getCookie(request, "keepLogin"); // 특정 Cookie 가지고오는 방법
			if(cookie != null) {
				
				String sessionId = cookie.getValue();
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("sessionId", sessionId);
				
				UserDTO loginUser = userService.getUserBySessionId(map);
				if(loginUser != null) {
					session.setAttribute("loginUser", loginUser); // 로그인 처리
				}
			}
		}
		return true;
	}
	
}
