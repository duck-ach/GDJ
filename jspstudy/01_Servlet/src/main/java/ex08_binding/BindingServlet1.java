package ex08_binding;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/BindingServlet1")
public class BindingServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// StateLess
		// 1. 상태 없음
		// 2. 웹 페이지 간 이동은 StateLess
		// 3. 현재 페이지는 이전 페이지에서 어떤정보가 어떻게 돌아갔는지 정보가 전혀 없음
		//
		
		// Binding
		// 1. 속성(Attribute)에 정보를 저장하고 사용하는 것
		// 2. 3개 영역을 사용
		// 		1) ServletContext 	  : 애플리케이션(프로젝트) 내부 어디서든 종료 전까지 접근해서 사용 가능 (ex. 방문자 수 등 서비스망할때까지 사라지지 않는다.)
		// 		2) HttpSession		  : 실행중인 웹 브라우저 종료 전까지 접근해서 사용 가능
		//		 (모든 서블릿 부모)
		// 		3) HttpServletRequest : 응답전까지 접근해서 사용가능 (한번의 요청은 한번의 응답으로 끝)
		// 3. 사용 메소드
		// 		1) getAttribute('속성') 	: 해당 속성 값 가져오기
		//		2) setAttribute('속성', 값) : 속성에 값 저장하기 (값은 Object타입으로 저장. getter에서 값 가져올 때캐스팅해서 사용하면된다.)
		//		3) removeAttribute('속성')  : 해당 속성 삭제하기
		// 로그인정보는 브라우저를 끄면 로그인이 풀리게 되어 있으므로 Session에 저장.
		
		// ServletContext
		ServletContext ctx = getServletContext(); // 또는 request.getServletContext();
		ctx.setAttribute("a", 1);
		
		// HttpSession(굉장히 중요 모르면 평생 일 못하고 칼바람 나락감)
		HttpSession session = request.getSession();
		session.setAttribute("b", 2);
	
		// HttpServletRequest
		request.setAttribute("c", 3);
		
		// 응답
//		response.setContentType("text/html; charset=UTF-8");
//		
//		PrintWriter out = response.getWriter();
//		out.println("<a href=\"/01_Servlet/BindingServlet2\">이동</a>");
//		out.close();
		
		// 포워드
		// 1. request를 전달함
		// 2. 서버 내부 이동이므로 경로 작성 시 컨텍스트 패스는 작성하지 않음
//		request.getRequestDispatcher("/BindingServlet2").forward(request, response);
		// 포워드는 request를 살려서 한번 더 쓸 수 있게 그대로 전달해주는 것
		
		// 리다이렉트(a링크, location으로 이동하는 것)
		// 1. request를 전달하지 않음
		// 2. 클라이언트 -> 서버로 이동하므로 컨텍스트 패스를 작성해야함
		response.sendRedirect("/01_Servlet/BindingServlet2");
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
