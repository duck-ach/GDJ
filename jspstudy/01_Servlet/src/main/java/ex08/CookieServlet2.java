package ex08;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CookieServlet2")
public class CookieServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 1. 응답 타입 및 응답 스트림
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
				
		// 2. 전체 쿠키 가져오기
		// 클라이언트는 하나의 쿠키만 보낼 수는 없고 모든 쿠키를 보내야 하기 때문에 배열로 처리
			Cookie[] cookies = request.getCookies();
				
		// 3. 전체 쿠키 확인
			if(cookies != null) {
				for(int i = 0; i < cookies.length; i++) {
					out.println("<h1>쿠키이름 : " + cookies[i].getName() + "</h1>");
					out.println("<h1>쿠키값 : " + URLDecoder.decode(cookies[i].getValue(), "UTF-8") + "</h1>");
				}
			}
				
		// 4. 특정 쿠키만 확인
			if(cookies != null) {
				for(int i = 0; i < cookies.length; i++) {
					if(cookies[i].getName().equals("name")) {
						out.println("<h1>쿠키이름 : " + cookies[i].getName() + "</h1>");
						out.println("<h1>쿠키값 : " + URLDecoder.decode(cookies[i].getValue(), "UTF-8") + "</h1>");				
					}
				}
			}
				
		// 5. 응답
			out.println("<a href=\"/01_Servlet/CookieServlet3\">이동</a>");
			out.close();
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
