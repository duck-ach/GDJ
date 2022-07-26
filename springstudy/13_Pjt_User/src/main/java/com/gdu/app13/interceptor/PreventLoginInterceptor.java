package com.gdu.app13.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/* 
	[ Bean 만드는 방법 ]
	xml의 <beans:bean>
	@Configuration 안의 @Bean 애너테이션
	@Component, @Service, @Controller
	
 
*/
@Component
public class PreventLoginInterceptor implements HandlerInterceptor {

	// 로그인이 완료된 사용자가
	// 로그인 페이지 이동, 약관페이지, 가입페이지 이동 등의 요청을 하면
	// 이를 막는 인터셉터
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(request.getSession().getAttribute("loginUser") != null) { // login이 되어있지 않으면?
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('해당 기능은 사용할 수 없습니다..');");
			out.println("location.href='" + request.getContextPath() + "';");
			out.println("</script>");
			out.close();
			
			return false; // 컨트롤러의 요청이 처리되지 않는다.
			
		} else {			
			return true; // 컨트롤러의 요청이 처리된다.
		}
		
	}
	
}
