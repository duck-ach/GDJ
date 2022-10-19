package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.Board;

public class BoardDao {
	
	// field
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs; // cursor라고도 부름
	private String sql;
	
	// Connection Pool 관리
	private DataSource dataSource;
	
	
	// singleton - pattern
	private static BoardDao dao = new BoardDao();
	private BoardDao() {
		try {
			// DataSource 객체 생성
			// context.xml에서 name="jdbc/oracle11g"인 Resource를 찾아서 생성(JNDI)
			Context ctx = new InitialContext();
			
			// 주석처리된 위 문장 2개가 밑에 1문장
			Context envCtx = (Context)ctx.lookup("java:comp/env");
			dataSource = (DataSource)envCtx.lookup("jdbc/oracle11g");
//			dataSource = (DataSource)envCtx.lookup("java:comp/env/jdbc/oracle11g");			
		} catch (NamingException e) { // 원하는 이름을 못 찾았을 때
			e.printStackTrace();
		}
	}
	
	// method
	public static BoardDao getInstance() {
		return dao;
	}
	
	// 1. 접속 자원 해제
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(rs != null) { rs.close(); }
			if(ps != null) { ps.close(); }
			if(con != null) { con.close(); } // Connection Pool의 close()는 Connection 종료가 아닌 Connection 반환으로 동작
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 2. 목록 반환하기
	public List<Board> selectAllBoard() {
		List<Board> boards = new ArrayList<Board>();
		try {
			con = dataSource.getConnection(); // CP로부터 Connection 대여
			sql = "SELECT BOARD_NO, TITLE, CONTENT, CREATE_DATE FROM BOARD ORDER BY BOARD_NO DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(); // SELECT문은 executeQuery() 사용
			// 첫번째 rs는 아무것도 가지고 있지않아서 첫번째로 해야할 게 rs.next(첫번째 행 선택(1행))
			// 그 다음 rs.getInt("board_no") 또는 rs.getInt(1) 해서 행을 가져온다.
			while(rs.next()) { // 목록 보기는 while문 처리
				Board board = new Board();
				board.setBoard_no(rs.getInt(1)); 	 // rs.getInt("BOARD_NO")
				board.setTitle(rs.getString(2)); 	 // rs.getString("TITLE")
				board.setContent(rs.getString(3)); 	 // rs.getString("CONTENT")
				board.setCreate_date(rs.getDate(4)); // rs.getDate("CREATE_DATE")
				// 가져온 게시글을 리스트에 추가
				boards.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return boards;
	}
	
	// 3. 상세보기
	public Board selectBoardByNo(int board_no) {
		Board board = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT BOARD_NO, TITLE, CONTENT, CREATE_DATE FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no); // 1번째 물음표(?)에 board_no 전달하기
			rs = ps.executeQuery(); // SELECT문은 executeQuery() 사용
			if(rs.next()) { 		// 상세보기는 if문
				board = new Board();
				board.setBoard_no(rs.getInt(1));
				board.setTitle(rs.getString(2));
				board.setContent(rs.getString(3));
				board.setCreate_date(rs.getDate(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return board;
	}
	
	// 4. 게시글 삽입
	public int insertBoard(Board board) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			result = ps.executeUpdate(); // INSERT문은 executeUpdate() 메소드 사용
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null); // rs는 사용하지 않아서 null값 삽입
		}
		return result;
	}
	
	// 5. 게시글 수정
	public int updateBoard(Board board) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getBoard_no());
			result = ps.executeUpdate(); // UPDATE문은 executeUpdate() 메소드 사용
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	// 6. 게시글 삭제
	public int deleteBoard(int board_no) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql); // 쿼리문 전달
			ps.setInt(1, board_no);
			result = ps.executeUpdate(); // DELETE문도 executeUpdate() 메소드를 사용한다.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	
}
