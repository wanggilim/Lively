package com.quadcore.lively.twitter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.quadcore.lively.twitter.model.TwitterStatus;
import com.quadcore.lively.twitter.util.OracleDBUtil;

public class StmtDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement ps;

	/**
	 * INSERT, UPDATE, DELETE 통합 (코드 진행중)
	 * @param sql
	 * @param statuses
	 * @return
	 */
	public int doDML(String sql, int cellebNo, List<TwitterStatus> statuses) {
		int result = 0;
		
		conn = OracleDBUtil.dbConnect();
		
		try {
			
			if (sql.toLowerCase().startsWith("insert into stmt values")) {
				
				for (TwitterStatus status : statuses) {
					ps = conn.prepareStatement(sql);
					
					ps.setString(1, status.getStmt());		// stmt
					ps.setLong(2, status.getStmtTs());		// stmtts
					ps.setString(3, status.getStmtType());	// stmttype
					ps.setString(4, status.getLocation());	// location
					ps.setInt(5, 0);						// likes
					ps.setInt(6, 0);						// sharing
					ps.setInt(7, cellebNo);					// cellebno
					ps.setString(8, status.getStmtURL());	// stmturl
					
					result += ps.executeUpdate();
				}
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
			
		return result;
	}

}
