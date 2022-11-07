package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class FreeDetailService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 요청 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("freeNo"));
		long freeNo = Long.parseLong(opt.orElse("0"));
				
		// DB로 boardNo 보내고 해당 Board 받아오기
		Free free = FreeDAO.getInstance().selectFreeByNo(freeNo);
				
		// 요청 파라미터로 boardNo가 전달되지 않았거나 존재하지 않는 boardNo가 전달된 경우 목록보기로 이동
		if(free == null) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + freeNo + "번 게시글의 정보가 없습니다.')");
			out.println("location.href='" + request.getContextPath() + "/list.do'");
			out.println("</script>");
			out.close();
		}
				
		// request에 Board board 저장하기
		request.setAttribute("free", free);
		// detail.jsp로 포워딩
		ActionForward af = new ActionForward();
		af.setView("/free/detail.jsp");
		af.setRedirect(false);
		return af;
	
	}

}
