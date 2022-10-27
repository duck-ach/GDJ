package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDao;

public class BoardListService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("boardNo"));
		int boardNo = Integer.parseInt(opt.orElse("0"));
		
		// request에 게시글 목록 저장
		request.setAttribute("boards", BoardDao.getInstance().selectAllBoards());
		request.setAttribute("board", BoardDao.getInstance().selectBoardByNo(boardNo));
		
		int count = BoardDao.getInstance().getBoardCount();
		request.setAttribute("count", count);
		
		// list.jsp로 포워딩
		ActionForward af = new ActionForward();
		af.setView("/board/list.jsp");
		af.setRedirect(false);
		return af;
		
	}

}
