package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class CircleService implements MyService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		double radius = Double.parseDouble(request.getParameter("radius"));
		
		double result = Math.pow(radius, radius)* Math.PI;
		
		// 뷰로 전달할 데이터는 request에 속성으로 저장
		request.setAttribute("radius", radius);		
		request.setAttribute("result", result);
		request.setAttribute("shape", "circle");
		
		ActionForward actionForward = new ActionForward();
		actionForward.setView("views/output.jsp");
		actionForward.setRedirect(false); // 포워드 하겠다요!
		
		return actionForward;
	}

}
