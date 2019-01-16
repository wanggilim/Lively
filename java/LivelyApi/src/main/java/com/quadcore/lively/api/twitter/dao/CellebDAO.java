package com.quadcore.lively.api.twitter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.quadcore.lively.api.twitter.model.TwitterStatus;
import com.quadcore.lively.api.twitter.util.OracleDBUtil;

public class CellebDAO {
	
	private Connection conn;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/**
	 * INSERT, UPDATE, DELETE 통합 (코드 진행중)
	 * @param sql
	 * @param status
	 * @return
	 */
	public int doDML(String sql, TwitterStatus status) {
		
		int result = 0;
		
		conn = OracleDBUtil.dbConnect();
		try {
			ps = conn.prepareStatement(sql);
			
			if (sql.toLowerCase().startsWith("insert into celleb")) {
				ps.setString(1, status.getName());				// cellebname
				ps.setString(2, "@"+status.getScreenName());	// accounts
			}
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public int getCellebNoByName(String sql) {
		int cellebNo = 0;
		
		conn = OracleDBUtil.dbConnect();
		try {
			st = conn.createStatement();
			
			rs = st.executeQuery(sql);
			while (rs.next()) {
				cellebNo = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cellebNo;
	}
	
	
	public String getCellebNameByNo(String sql) {
		String cellebName = "";
		
		conn = OracleDBUtil.dbConnect();
		try {
			st = conn.createStatement();
			
			rs = st.executeQuery(sql);
			while (rs.next()) {
				cellebName = rs.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cellebName;
	}

}
