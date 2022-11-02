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

/*
	@Repository
	안녕. 난 DAO에 추가하는 @Component이야.
	servlet-context.xml에 등록된 <context:component-scan> 태그에 의해서 bean으로 검색되지
	root-context.xml이나 @Configuration에 @Bean으로 등록하지 않아도 Container에 만들어 져
*/
/*  singleton을 안만드는 이유 : Spring은 Container를 전부 Singleton으로 생성한다.  */

@Repository // Service가 사용하는 @Component로 트랜잭션 기능이 추가되어 있어.
public class BoardDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// private 메소드
	// 이 메소드는 BoardDAO에서만 사용한다.
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
	      try {
	          con = getConnection();
	          sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD ORDER BY BOARD_NO DESC";
	          ps  = con.prepareStatement(sql);
	          rs = ps.executeQuery();
	          while(rs.next()) {
	        	  BoardDTO board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)  );
	        	  boards.add(board);
	          }
	      }catch(Exception e) {
	          e.printStackTrace();
	      } finally {
	    	  close();
	      }
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
