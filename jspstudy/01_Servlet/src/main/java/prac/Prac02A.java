package prac;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Prac02A
 */
@WebServlet("/Prac02A")
public class Prac02A extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String name = request.getParameter("name");
		String strAge = request.getParameter("age");
		
		int age = 0;
		if(strAge != null) {
			age = Integer.parseInt(strAge);
		}
		
		// 요청으로 받은 정보를 다시 Prac02B으로 보냄
		response.sendRedirect("/01_Servlet/Prac02B?name=" + URLEncoder.encode(name) + "&age=" + age);
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
