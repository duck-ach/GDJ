package com.gdu.app07.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gdu.app07.domain.BoardDTO;

/*
	Bean을 등록하는 방법
	1. Spring bean configuration file에 등록한다. (root-context)
	2. java에 @Configuration 애너테이션을 추가하고, @Bean 을 선언하여 생성한다.
	3. @Component를 활용해서 등록한다.
	
	DAO가 사용하는 annotation은 @Repository이다.
	@Repository 안에 @Component를 가지고 있다. (repository 전용 컴포넌트가 있으니 쓰자.)
	@component  == @configuration, @bean
 */

public class BoardDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// 커넥션을 만들어쓰지 않고 가져와서 쓴다.
	// Connection Pool을 관리하는 DataSource
	private DataSource dataSource;
	
	// BoardDAO가 생성되면
	// context.xml의 Resource를 읽어서 dataSource객체를 만든다.
	public BoardDAO() {
		// JNDI : Resource의 name을 이용해서 읽어 들이는 방식
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle11g");
		} catch (Exception e) { // NamingException
			e.printStackTrace();
		}
	}
	
	private void close() {
		try {
			if(rs != null) { rs.close(); }
			if(ps != null) { ps.close(); }
			if(con != null) { con.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<BoardDTO> selectAllBoards() {
		List<BoardDTO> boards = new ArrayList<BoardDTO>();
	      try {
	          con = dataSource.getConnection(); // Connection Pool에서 하나 얻어온다.
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
	      try {
	    	  con = dataSource.getConnection();
	    	  sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD WHERE BOARD_NO = ?";
	    	  ps = con.prepareStatement(sql);
	    	  ps.setInt(1, board_no);
	    	  rs = ps.executeQuery();
	    	  if(rs.next()) {
					board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			  }
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      } finally {
	    	  close();
	      }
	      return board;
	}

	
	public int insertBoard(BoardDTO board) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO BOARD(BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE)"
					+ " VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD'))";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getWriter());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	   
	public int updateBoard(BoardDTO board) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "UPDATE BOARD "
				+ "SET TITLE = ?, CONTENT = ?, MODIFY_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD') "
				+ "WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getBoard_no());
			result = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	   
	public int deleteBoard(int board_no) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	
	
}
