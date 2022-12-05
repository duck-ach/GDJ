package com.gdu.app01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app01.domain.BoardDTO;
import com.gdu.app01.mapper.BoardMapper;

@Service // 컴포넌트로 등록
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;

	@Override
	public List<BoardDTO> findAllBoards() {
		return mapper.selectAllBoards();
	}

	@Override
	public BoardDTO findBoardByNo(int boardNo) {
		return mapper.selectBoardByNo(boardNo);
	}

	@Override
	public int saveBoard(BoardDTO board) {
		return mapper.insertBoard(board);
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		System.out.println(board);
		return mapper.updateBoard(board);
	}

	@Override
	public int removeBoard(int boardNo) {
		return mapper.deleteBoard(boardNo);
	}
	

}
