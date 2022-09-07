package ex06_select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectOneMain2 {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";		// DB마다 url은 달라짐(Oracle XE 버전 기준)
			String user = "scott";		// 대소문자 상관없다.
			String password = "TIGER"; 	// 대소문자 구별한다.	
			con = DriverManager.getConnection(url, user, password);
			
			String sql = "SELECT COUNT(*) AS 총개수 FROM BOARD";
			
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				/*
				 	| 총개수 |
				 	|   3    |  <= rs.next() 호출로 인해 현재 rs 포인터의 위치
				 	
				 	1번째방법 (컬럼명으로 접근)
				 	rs.getInt("총개수"); 
				 	
				 	2번째방법 (열로 접근)
				 	rs.getInt(1);
				 	
				 	
				 	AS를 지우면 컬럼명이 집계함수 COUNT(*)가 되기 때문에
				 	rs.getInt("COUNT(*)");
				 */
				
			int count = rs.getInt("총개수");
			System.out.println(count);
				
			} // COUNT(*) 집계 함수의 결과는 else 처리할 필요가 없음
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}

}
