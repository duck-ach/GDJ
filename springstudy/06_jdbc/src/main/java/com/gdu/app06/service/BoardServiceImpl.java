package com.gdu.app06.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gdu.app06.domain.BoardDTO;
/*
	@Service
	안녕. 난 Service에 추가하는 @Component이야.
	servlet-context.xml에 등록된 <context:component-scan> 태그에 의해서 bean으로 검색되지
	root-context.xml이나 @Configuration에 @Bean으로 등록하지 않아도 Container에 만들어 져
*/

@Service
public class BoardServiceImpl implements BoardService {

	@Override
	public List<BoardDTO> findAllBoards() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDTO findBoardByNo(int board_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveBoard(BoardDTO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeBoard(int board_no) {
		// TODO Auto-generated method stub
		return 0;
	}

}
