package ex02_create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTableMain {

	public static void main(String[] args) {
		
		// Connection 생성
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";		// DB마다 url은 달라짐(Oracle XE 버전 기준)
			String user = "scott";		// 대소문자 상관없다.
			String password = "TIGER"; 	// 대소문자 구별한다.	
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("OracleDriver 로드 실패");
		} catch (SQLException e) {
			System.out.println("DB접속정보 오류");
		}

		// CREATE TABLE 실행
		PreparedStatement ps = null;
		try {
			
			// String타입으로 쿼리문 작성
			// 쿼리문의 마지막 세미콜론(;)은 JDBC에서 사용할 수 없다. (쿼리문 자체만 전달)
			StringBuilder sb = new StringBuilder();
			sb.append("CREATE TABLE BOARD(");
			sb.append("BOARD_NO NUMBER NOT NULL CONSTRAINT PK_BOARD PRIMARY KEY,");
			sb.append("TITLE VARCHAR2(100 BYTE) NOT NULL,");
			sb.append("CONTENT VARCHAR2(100 BYTE) NULL,");
			sb.append("HIT NUMBER NOT NULL,");
			sb.append("CREATE_DATE DATE NOT NULL)");
			
			String sql = sb.toString();
			// PreparedStatement 객체 생성
			// Prepated : 미리 준비하라
			// Statement : 쿼리문
			// 역할 : 쿼리문 실행을 담당
			ps = con.prepareStatement(sql); 
			
			// 쿼리문 실행
			// prepare statement 클래스에 소속된 execute()를 실행해야함
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Connection, PreparedStatement 닫기
		try {
			if(con != null) con.close();
			if(ps != null) ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
