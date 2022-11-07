package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.FreeDAO;

public class FreeRemoveService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 요청 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("freeNo"));
		long freeNo = Long.parseLong(opt.orElse("0"));
				
		// DB로 boardNo 보내기(삭제)
		int result = FreeDAO.getInstance().deleteFree(freeNo);
				
		// 삭제 성공/실패
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('게시글이 삭제되었습니다.')");
			out.println("location.href='" + request.getContextPath() + "/list.do'");
			out.println("</script>");
		} 
		out.close();
						
		return null;
	}

}
