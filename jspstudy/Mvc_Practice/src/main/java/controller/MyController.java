package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.CircleService;
import service.MyService;
import service.SquareService;
import service.TripleService;


@WebServlet("*.do")
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 응답, 요청 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 요청 확인
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);
		
		// MySercive 선언
		MyService myService = null;
		
		// ActionForward 선언
		ActionForward actionForward = null;
		
		// 요청에 따른 Model 선택
		switch(command) {
			// 비즈니스로직 필요한 경우
		case "square.do":
			myService = new SquareService();
			break;
		case "triple.do":
			myService = new TripleService();
			break;
		case "circle.do":
			myService = new CircleService();
			break;
		case "input.do":
			actionForward = new ActionForward();
			actionForward.setView("views/input.jsp");
			actionForward.setRedirect(false);
			break;
		}
		
		// 선택한 Model의 실행
		if(myService != null) {
			actionForward = myService.execute(request, response);
		}
		
		if(actionForward != null) {
			if(actionForward.isRedirect()) {
				response.sendRedirect(actionForward.getView());
			} else {
				request.getRequestDispatcher(actionForward.getView()).forward(request, response);
			}
		}
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
