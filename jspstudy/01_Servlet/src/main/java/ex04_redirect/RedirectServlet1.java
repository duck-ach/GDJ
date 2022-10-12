package ex04_redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RedirectServlet1
 */
@WebServlet("/RedirectServlet1")
public class RedirectServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/*
	 	Redirect
	 		1. 기존 request 를 유지하지 않는 이동 방식
	 		2. response를 이용해서 서버가 클라이언트에게 이동할 장소를 알려주는 방식
	 		response.sendRedirect(이동할 경로)
	 		3. 이동할 경로는 ContextPath를 포함한 전체 경로로 작성해야 함
	 		4. 클라이언트가 직접 이동하는 방식이기 때문에 URL을 이용해서 redirect 경로 확인이 가능함
	 		5. DB가 변경되는 작업 이후에는 Redirect를 진행(INSERT, UPDATE, DELETE 이후에는 Redirect)
	 */
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// redirect
		response.sendRedirect("/01_Servlet/RedirectServlet2");
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
