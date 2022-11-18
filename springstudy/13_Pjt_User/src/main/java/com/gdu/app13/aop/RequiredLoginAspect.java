package com.gdu.app13.aop;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
// aop가 돌아가기 위해서는 컴포넌트를 동작시키는 @EnableAspectJAutoProxy가 있어야한다. (DBConfig에 있음)
//aop가 동작하기 위해서는 만들고 @컴포넌트 등록하고 해당 컴포넌트 동작시키는 @EnableAspcetJAutoProxy가 필요하다
@EnableAspectJAutoProxy // DB Config에서 지우고 여기에 등록해줬다.
@Component // Bean으로 등록하기위해 Component를 등록해준다.
@Aspect
public class RequiredLoginAspect {
	// controller의 모든 메소드를 Aspect는 Join Point라고 부른다.
	// 그중 각각을 PointCut이라고 한다.
													// UserContoller라고 하지않고 *Contoller라고 해서 모든 컨트롤러를 타겟으로 한다.
	@Pointcut("execution(* com.gdu.app13.controller.*Controller.requiredLogin_*(..))") // pointcut 표현식. requiredLogin_로 시작하는 모든 메소드는 (..)<어떤것이든 상관없다.
	public void requiredLogin() {}
	
	@Before("requiredLogin()") // 동작하는 타이밍 @Before / PointCut 실행 전에 requiredLogin() 메소드 수행 // Before처리할때는 Pointcut지정밖에 못함
	public void requiredLoginHandler(JoinPoint joinPoint) throws Throwable {
		
		// 로그인이 되어 있는지 확인하기 위해서 session이 필요하므로 request가 필요하다.
		ServletRequestAttributes servletRequestAttr = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		
		HttpServletRequest request = servletRequestAttr.getRequest();
		HttpServletResponse response = servletRequestAttr.getResponse();
		
		// 세션
		HttpSession session = request.getSession();
		
		// 로그인 여부 확인
		if(session.getAttribute("loginUser") == null) {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
				
			out.println("<script>");
			out.println("if(confirm('로그인이 필요한 기능입니다. 로그인 하시겠습니까?')) {");
			out.println("location.href='"+ request.getContextPath() +"/user/login/form';");
			out.println("} else {");
			out.println("history.back();");
			out.println("}");
			out.println("</script>");
			out.close();
			
		}
		
		
	}
	
	
	
}
