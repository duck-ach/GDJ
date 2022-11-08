package com.gdu.app09.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdu.app09.domain.BoardDTO;

@Repository // serblet-context의 <context:component-scan base-package="com.gdu.app08" /> 가 인식하여 컴포넌트로 인식
public class BoardDAO {
	
	// SqlSessionTemplate
	// MyBatis에서 지원하는 매퍼 처리 클래스
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate; // JdbcTemplate라고 되어있던 것을 SqlSessionTemplate로 바꿔준다.
	
	public List<BoardDTO> selectAllBoards() {
		return sqlSessionTemplate.selectList("mybatis.mapper.board.selectAllBoards"); // mapper주소 + id
	}
	
	public BoardDTO selectBoardByNo(int boardNo) { // 해킹시도 이제 없을거니까 매개변수의 final도 지워준다.
		return sqlSessionTemplate.selectOne("mybatis.mapper.board.selectBoardByNo", boardNo); // mapper주소 + id, 전달해줄 값
	}

	public int insertBoard(BoardDTO board) {
		return sqlSessionTemplate.insert("mybatis.mapper.board.insertBoard", board);
	}
	   
	public int updateBoard(BoardDTO board) {
		return sqlSessionTemplate.update("mybatis.mapper.board.updateBoard", board);
	}
	   
	public int deleteBoard(int boardNo) {
		return sqlSessionTemplate.delete("mybatis.mapper.board.deleteBoard", boardNo);
	}

	
	
}
