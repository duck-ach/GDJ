package ex03_parameter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AnchorServlet
 */
@WebServlet("/AnchorServlet")
public class AnchorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AnchorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		// 요청 - client에 있는 문제
		String strA = request.getParameter("a");
		String strB = request.getParameter("b");
		
		int a = 0;
		int b = 0;
		if(strA != null && strB != null) {
			a = Integer.parseInt(strA);
			b = Integer.parseInt(strB);		
			
			
		}
		
		// 응답 - client에 있는 문제
		PrintWriter outPlus = response.getWriter();
		outPlus.println("<h1>" + a + "+" + b + "=" + (a+b) + "</h1>");
		System.out.println(a + "+" + b + "=" + (a+b));
		
		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h1>Hello World</h1>");
				
		out.flush(); // 생략가능
		out.close();
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
