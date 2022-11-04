package com.gdu.app08.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.gdu.app08.domain.BoardDTO;

@Repository // serblet-context의 <context:component-scan base-package="com.gdu.app08" /> 가 인식하여 컴포넌트로 인식
public class BoardDAO {
	
	// JdbcTemplate
	// Connection, PreparedStatement, ResultSet을 내부에서 사용하는 스프링 클래스
	// DriverManagerDataSource에 의해서 Connection Pool 방식으로 동작
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<BoardDTO> selectAllBoards() {
		String sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD ORDER BY BOARD_NO DESC";
		List<BoardDTO> boards = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BoardDTO.class)); //BoardDTO가 BeanProperty
		return boards;
	}
	
	public BoardDTO selectBoardByNo(final int board_no) {
		String sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD WHERE BOARD_NO = ?"; // 예전에 매개변수 해킹시도가 있었다. 그래서 final이 붙을 수 있다.
		BoardDTO board =  jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(BoardDTO.class), board_no); // object를 위한 쿼리 객체는 하나다.
	    return board;
	}

	
	public int insertBoard(final BoardDTO board) {
		String sql = "INSERT INTO BOARD(BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE)"
      + " VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD'))";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// ps안에 ? 3개 채우면 됨.(setter)
				ps.setString(1, board.getTitle());
				ps.setString(2, board.getContent());
				ps.setString(3, board.getWriter());
			}
		}); // 인터페이스는 new가 안되므로 여기서 바로 본문을 만드는 방식
		return result;
	}
	   
	public int updateBoard(final BoardDTO board) {
		String sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, MODIFY_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD') WHERE BOARD_NO = ?";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, board.getTitle());
				ps.setString(2, board.getContent());
				ps.setInt(3, board.getBoard_no());
			}
		});
		return result;
	}
	   
	public int deleteBoard(final int board_no) {
		String sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, board_no);
			}
		});
		return result;
	}

	
	
}
