package prac;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Prac03A
 */
@WebServlet("/Prac03A")
public class Prac03A extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청
				request.setCharacterEncoding("UTF-8");
				
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String content = request.getParameter("content");
				
				// 파일명 (날짜-작성자.txt)
				String filename = new Date(System.currentTimeMillis()) + "-" + from + ".txt";
				
				// 디렉터리생성 servletContext(이 프로그램의). realPath(진짜경로)
				// C:\GDJ\jspstudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\01_Servlet\storage\2022-10-07-히랭쓰.txt
				File dir = new File(request.getServletContext().getRealPath("storage"), filename);
				if(dir.exists() == false) {
					dir.mkdirs();
				}
				
				// 파일 객체
				File file = new File(dir, filename);
				
				// Writer 객체
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				
				bw.write("To." + to + "\n");
				bw.write(content + "\n");
				bw.write("From. " + from);
				bw.close();
				
				// 이동(Redirect, Forward 둘다 상관없으나 신규생성하는걸 전달하므로 Redirect로 해보겠따)
				response.sendRedirect("/01_Servlet/Prac03B?filename=" + URLEncoder.encode(filename));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
