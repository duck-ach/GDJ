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

import domain.Post;

public class PostDao {
	
	// field
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// Connection Pool 관리 
	private DataSource dataSource;
	
	// singleton - pattern
	private static PostDao dao = new PostDao();
	private PostDao() {
		
		try {
			
			// DataSource 객체 생성 
			// content.xml에서 name="jdbc/oracle11g"인 Resource를 찾아서 생성(JNDI)
			Context ctx = new InitialContext();
			Context envCtx = (Context)ctx.lookup("java:comp/env");
			dataSource = (DataSource)envCtx.lookup("jdbc/oracle11g");
			
		} catch(NamingException e) { // 원하는 이름을 못 찾았을 
			e.printStackTrace();
		}
		
	}
	
	// method
	public static PostDao getInstance() {
		return dao;
	}
	
	// 1. 접속 자원 해제 
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(rs != null) { rs.close(); }
			if(ps != null) { ps.close(); }
			if(con != null) { con.close(); }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	  	Statement 클래스 
	  		- SQL 구문을 실행하는 역할
	  		- 스스로는 SQL 구문 이해 못함 
	  		- SQL 관리 O + 연결정보 X
	  		
	  	PrepareStatement 클래스 
	  		- Statement 클래스의 기능 향상
	  		- 인자와 관련된 작업에 특화매개변수)
	  		- 코드 안정성 높음. 가독성 높음.
	  		- 코드량이증가 (매개변수를 set해줘야해서)
	  		- 텍스트 형태의 sql 호출 
	  		
	  	ResultSet 클래스
	  		- set한 ps의 sql문을 실행시켜줌 
	  		- 첫번째 rs는 아무것도 가지고 있지 않아서 첫번째로 해야할게 rs.next(첫번째 행 선택(1행))
	  		- 그 다음 rs.getInt("board_no") 또는 rs.getInt(1) 해서 행을 가져온다.
	 */
	
	// 2. 목록 반환하기 
	public List<Post> selectAllBoard() {
		List<Post> posts = new ArrayList<Post>();
		try {
			con = dataSource.getConnection(); // CP로부터 Connection 대여 
			sql = "SELECT POST_NO, TITLE, WRITER, VIEWS, POST_DATE FROM POST ORDER BY POST_NO DESC";
			ps = con.prepareStatement(sql); // sql 호출
			rs = ps.executeQuery(); // SELECT 문은 executeQuery() 사용
			
			while(rs.next()) {
				Post post = new Post();
				post.setPost_no(rs.getInt(1));
				post.setTitle(rs.getString(2));
				post.setWriter(rs.getString(3));
				post.setViews(rs.getLong(4));
				post.setPost_date(rs.getDate(5));
				// 가져온 게시글을 리스트에 추가 
				posts.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return posts;
	}
	
	
}
