package ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormServlet
 */
@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 요청
		request.setCharacterEncoding("UTF-8");
		
		// 변수
		String id = request.getParameter("id");
		// 맞는 문자열 검사문 (반드시 Null 체크를 먼저 해줘야한다.)
		if(id == null || id.isEmpty()) {
			id = "빈 아이디";
		}
		// 틀린 문자열검사문 (Null체크를했으나 NullPointException)
		if(id.isEmpty() || id == null) {
			id = "빈 아이디";
		}
		String pwd = request.getParameter("pwd");
		if(pwd.isEmpty()) {
			pwd = "빈 비밀번호";
		}
		
		String gender = request.getParameter("gender");
		// gender 검사
		if(gender == null) {
			gender = "빈 성별";
		}
		
		String city = request.getParameter("city");
		// city 검사
		if(city.isEmpty()) {
			city = "빈 도시";
		}
		
		// 배열(파라미터)
		String[] phone = request.getParameterValues("phone");
		for(int i = 0; i < phone.length; i++) {
			if(phone[i].isEmpty()) {
				phone[i] = (i+1) + "번째 자리 빈 전화번호";
			}			
		}
		String strPhone = phone[0] + "-" + phone[1] + "-" + phone[2];
		
		String[] agree = request.getParameterValues("agree");
		
		String emailId = request.getParameter("email_id");
		String domain = request.getParameter("domain");
		
		// 응답
		response.setContentType("text/html; charset=UTF-8"); // 가장먼저!
		
		PrintWriter out = response.getWriter();
		out.println("<h3> 아이디 : " + id + "</h3>");
		out.println("<h3> 비밀번호 : " + pwd + "</h3>");
		out.println("<h3> 성별 : " + gender + "</h3>");
		out.println("<h3> 거주도시 : " + city + "</h3>");
		out.println("<h3> 연락처 : " + strPhone + "</h3>");
		out.println("<h3> 이메일 : " + emailId + "@" + domain);
		
		out.println("<h3> 동의한내역 : " + Arrays.toString(agree) + "</h3>");			
		List<String> list = Arrays.asList(agree); // 문자열배열이 리스트로 변환(for문을 안돌리고 검사를 할수있다.)
		if(list.contains("terms")) {
			out.println("<h3>이용약관에 동의하셨습니다.</h3>");
		}
		if(list.contains("service")) {
			out.println("<h3>서비스에 동의하셨습니다.</h3>");
		}
		if(list.contains("marketing")) {
			out.println("<h3>마케팅에 동의하셨습니다.</h3>");
		}
		
		
		
		
		out.close();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
