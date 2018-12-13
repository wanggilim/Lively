package com.quadcore.lively.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class OracleDBUtil {
	

	// DB 연결
	public static Connection dbConnect() {
		Connection conn = null;
		
		String url = "jdbc:oracle:thin:@192.168.22.118:1521:xe";
		String user = "hr";
		String pw = "hr";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("DB 연결");
		
		return conn;
	}
	
	// Query 실행 -> ResultSet 반환
	public static ResultSet execute(Connection conn, Statement st, String sql) {
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public static int insert(Connection conn, PreparedStatement st, String sql) {
		int result = 0;
		try {
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	// DB 연결해제
	public static void dbDisconnect(
			ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)		rs.close();
			if (st != null)		st.close();
			if (conn != null)	conn.close();
			
			System.out.println("DB 해제");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
