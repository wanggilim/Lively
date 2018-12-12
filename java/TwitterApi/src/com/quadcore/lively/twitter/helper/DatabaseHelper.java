package com.quadcore.lively.twitter.helper;

import java.io.BufferedReader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.quadcore.lively.twitter.util.OracleDBUtil;
import com.quadcore.lively.twitter.util.UtilDateToSqlDate;

public class DatabaseHelper {
	private Connection conn;
	private Statement st;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	public DatabaseHelper() {
		
	}
	
	public String openCheck() {
		String result = "";
		
		String sql = "SELECT TO_CHAR(SYSDATE, 'MM-DD-YYYY HH24:MI:SS') from dual";

		try {
			conn = OracleDBUtil.dbConnect();
			st = conn.createStatement();
			
			rs = OracleDBUtil.execute(conn, st, sql);
			
			while (rs.next()) {
				result += rs.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int insertTwitterCelleb(String cellebName) {
		return this.insertTwitterCelleb(cellebName, "", "",
				UtilDateToSqlDate.change(new java.util.Date(System.currentTimeMillis())));
	}
	
	public int insertTwitterCelleb(String cellebName, String cellebNameKr) {
		return this.insertTwitterCelleb(cellebName, cellebNameKr, "",
				UtilDateToSqlDate.change(new java.util.Date(System.currentTimeMillis())));
	}
	
	public int insertTwitterCelleb(String cellebName, String cellebNameKr, String gender) {
		return this.insertTwitterCelleb(cellebName, cellebNameKr, gender,
				UtilDateToSqlDate.change(new java.util.Date(System.currentTimeMillis())));
	}
	
	public int insertTwitterCelleb(String cellebName, String cellebNameKr, String gender, Date birthday) {
		int result = 0;
		String sql = "insert into celleb values(cellebno.NEXTVAL,?,?,?,?)";
		
		try {
			conn = OracleDBUtil.dbConnect();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, cellebName);		// cellebName
			psmt.setString(2, cellebNameKr);	// cellebNameKr
			psmt.setString(3, gender);			// gender
			psmt.setDate(4, birthday);			// birthday
			
			result = OracleDBUtil.insert(conn, psmt, sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int insertTwitterStatement(String statementContent, Date stmtDate,
			String stmtType, String location, int likesCnt, int sharingCnt, int cellebNo) {
		int result = 0;
		String sql = "insert into stmt values(stmtno.NEXTVAL,?,?,?,?,?,?,?)";
		
		try {
			conn = OracleDBUtil.dbConnect();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, statementContent);// stmt
			psmt.setDate(2, stmtDate);			// stmtdate
			psmt.setString(3, stmtType);		// stmttype
			psmt.setString(4, location);		// location
			psmt.setInt(5, likesCnt);			// likes
			psmt.setInt(6, sharingCnt);			// sharing
			psmt.setInt(7, cellebNo);			// cellebno
				
			
			result = OracleDBUtil.insert(conn, psmt, sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int selectNameOfTwitterCelleb(String cellebName) {
		int result = 0; // DB 속 Twitter Celleb Id
		
		String sql = "select cellebno from celleb"
				+ " where cellebname = '" + cellebName + "'";
		
		try {
			conn = OracleDBUtil.dbConnect();
			st = conn.createStatement();
			
			ResultSet rs = OracleDBUtil.execute(conn, st, sql);
			
			while (rs.next()) {
				result = rs.getInt(1); // DB 속 cellebno
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
