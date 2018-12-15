package com.quadcore.lively.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleDBUtil {
	//반복되는 업무에 대한 유틸을 제작
	//util 패키지를 별도로 만듦
	//DB연결
	public static Connection dbConnect() {
		Connection conn = null;//잘못되면 null
		String url ="jdbc:oracle:thin:@192.168.22.126:1522:xe"; // data source explorer에서 connection url에서 복사
		String user="lively", password="1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;//연결되면 conn가 리턴
	}
	
	//DB해제
	
	public static void dbDisconnect(ResultSet rs, Statement st, Connection conn) {
		
		try {
			if(rs!=null) rs.close();//안쪽부터 닫아 준다.
			if(st!=null) st.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
