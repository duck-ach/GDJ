package com.gdu.app06.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdu.app06.domain.BoardDTO;

/*
	Bean을 등록하는 방법
	1. Spring bean configuration file에 등록한다. (root-context)
	2. java에 @Configuration 애너테이션을 추가하고, @Bean 을 선언하여 생성한다.
	3. @Component를 활용해서 등록한다.
	
	DAO가 사용하는 annotation은 @Repository이다.
	@Repository 안에 @Component를 가지고 있다. (repository 전용 컴포넌트가 있으니 쓰자.)
	@component  == @configuration, @bean
 */
@Repository // Service가 사용하는 @Component
public class BoardDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	private Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT", "TIGER"); // 호스트경로 / 사용자이름 / 비밀번호
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	private void close() {
		try {
			if(rs != null) { rs.close(); }
			if(ps != null) { ps.close(); }
			if(con != null) { con.close(); }
		} catch (Exception e) {
			
		}
	}
	
	public List<BoardDTO> selectAllBoards() {
		List<BoardDTO> boards = new ArrayList<BoardDTO>();
		return boards;
	}
	
	public BoardDTO selectBoardByNo(int board_no) {
	      BoardDTO board = null;
	      return board;
	}

	
	public int insertBoard(BoardDTO board) {
		int result = 0;
	      
		return result;
	}
	   
	public int updateBoard(BoardDTO board) {
		int result = 0;
	      
		return result;
	}
	   
	public int deleteBoard(int board_no) {
		int result = 0;
		return result;
	}

	
	
}
