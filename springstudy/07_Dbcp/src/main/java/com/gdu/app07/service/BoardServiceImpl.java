package com.gdu.app07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gdu.app07.domain.BoardDTO;
import com.gdu.app07.repository.BoardDAO;

import lombok.AllArgsConstructor;

// field를 이용한 생성자를 만들어두면, 
// 생성자의 매개변수로 컨테이너의 Bean이 자동 주입(@Autowired)되므로,
// field에 @Autowired 처리할 필요가 없다.
@AllArgsConstructor // 생성자를 이용해 Bean 가져오기
public class BoardServiceImpl implements BoardService {

/*
	@Autowired 사용 방법
	
	1. 필드로 생성된 Bean 가져오기
		1) 필드마다 @Autowired를 추가한다. (필드가 10개면 @Autowired도 10번 작성)
		2) 필드가 많으면 사용하지 않는다.
	2. 생성자를 이용해 Bean 가져오기
		1) 생성자의 매개변수로 Bean을 가져온다.
		2) @Autowired를 작성할 필요가 없다.
	3. 메소드를 이용해 Bean 가져오기
		1) 메소드의 매개변수로 Bean을 가져온다.
		2) @Autowired를 작성해야 한다.
		3) 일반적으로 setter를 사용하지만 setter가 아니어도 상관 없다.
*/
	
	// Service는 DAO를 사용합니다.
	@Autowired // 컨테이너에 생성된 bean중에서 BoardDAO타입의 bean을 가져오시오.
	private BoardDAO dao;
	
	// 생성자의 매개변수로 Autowired 하여 Bean을 가져오는 방법
	// 위의 @AllArgsConstructor
//	public BoardServiceImpl(BoardDAO dao) {
//		super();
//		this.dao = dao;
//	}

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
