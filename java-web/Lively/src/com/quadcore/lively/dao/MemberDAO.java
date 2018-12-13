package com.quadcore.lively.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.quadcore.lively.util.OracleDBUtil;

public class MemberDAO {
	
	public int getUserAuth(String userMail, String userPass) {
		
		int count = 0;
		
		String sql = "SELECT count(*) FROM member"
				+ " WHERE usermail = '" + userMail + "'" // aa@gmail.com
				+ " AND userpass = '" + userPass + "'"; // a123
		
		
		System.out.println("UserDao userMail : " + userMail);
		System.out.println("UserDao userPass : " + userPass);
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {			
			conn = OracleDBUtil.dbConnect();
			conn.setAutoCommit(false);
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				//이부분 이해 안 감!!!!!
				count = rs.getInt(1); // 0번은 count(*)
			}
			conn.commit();
			
		} catch (SQLException e) {
			if(conn!=null) 
				try {conn.rollback();} 
				catch(SQLException e2) {}
			
		} finally {
			OracleDBUtil.dbDisconnect(rs, st, conn);
		}
		
		return count;
	}

}
