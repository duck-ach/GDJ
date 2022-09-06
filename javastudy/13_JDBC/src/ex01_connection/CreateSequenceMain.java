package ex01_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateSequenceMain {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Oracle Driver 로드
			Class.forName("oracle.jdbc.OracleDriver");
			// Connection 생성
			String url = "jdbc:oracle:thin:@localhost:1521:xe";		// DB마다 url은 달라짐(Oracle XE 버전 기준)
			String user = "scott";		// 대소문자 상관없다.
			String password = "TIGER"; 	// 대소문자 구별한다.	
			con = DriverManager.getConnection(url, user, password);
			// 쿼리문 작성
			String sql = "CREATE SEQUENCE BOARD_SEQ NOCACHE";
			ps = con.prepareStatement(sql);
			
			// 쿼리문 실행
			ps.execute();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		try {
			if(con != null)
				con.close();
			if(ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
