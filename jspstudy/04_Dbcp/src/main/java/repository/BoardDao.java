package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDao {
	
	// field
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
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
//		Context envCtx = (Context)ctx.lookup("java:comp/env");
//		dataSource = (DataSource)envCtx.lookup("jdbc/oracle11g");
			dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle11g");			
		} catch (NamingException e) { // 원하는 이름을 못 찾았을 때
			e.printStackTrace();
		}
	}
	public static BoardDao getInstance() {
		return dao;
	}
	
}
