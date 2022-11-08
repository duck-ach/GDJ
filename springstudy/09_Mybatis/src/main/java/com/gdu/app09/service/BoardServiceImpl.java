package com.gdu.app09.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app09.domain.BoardDTO;
import com.gdu.app09.repository.BoardDAO;

@Service // 컴포넌트로 등록
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO dao; // Bean에서 dao를 가져옴

	@Override
	public List<BoardDTO> findAllBoards() {
		return dao.selectAllBoards();
	}

	@Override
	public BoardDTO findBoardByNo(int board_no) {
		return dao.selectBoardByNo(board_no);
	}

	@Override
	public int saveBoard(BoardDTO board) {
		return dao.insertBoard(board);
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		return dao.updateBoard(board);
	}

	@Override
	public int removeBoard(int board_no) {
		return dao.deleteBoard(board_no);
	}
	

}
