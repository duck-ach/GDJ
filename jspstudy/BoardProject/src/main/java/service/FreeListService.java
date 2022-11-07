package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.FreeDAO;

public class FreeListService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// request에 게시글 목록 저장
		request.setAttribute("frees", FreeDAO.getInstance().selectAllFrees());
		
		// list.jsp로 포워딩
		return new ActionForward("/free/list.jsp", false);
	}

}
