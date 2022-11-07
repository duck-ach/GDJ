package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class FreeModifyService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 요청 파라미터
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				long freeNo = Integer.parseInt(request.getParameter("freeNo"));
				
				// DB로 보낼 Board board 생성
				Free free = new Free();
				free.setTitle(title);
				free.setContent(content);
				free.setFreeNo(freeNo);
				
				// DB로 Board board 보내기 (수정)
				int result = FreeDAO.getInstance().updateFree(free);
				// 수정 성공 / 실패
				PrintWriter out = response.getWriter();
				if(result > 0) {
					out.println("<script>");
					out.println("alert('수정 성공')");
					out.println("location.href='" + request.getContextPath() + "/list.do'");
					out.println("</script>");
				} else {
					out.println("<script>");
					out.println("alert('게시글 수정이 실패했습니다.')");
					out.println("history.back()");
					out.println("</script>");
				}
				out.close();
				
				return null; 
			}

}
