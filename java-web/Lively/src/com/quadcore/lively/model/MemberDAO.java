package com.quadcore.lively.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quadcore.lively.util.OracleDBUtil;

public class MemberDAO{
	
	//멤버 추가
	public void insert(MemberVO member) {
		// TODO Auto-generated method stub
		
		String sql = "insert into member(userNo, userMail, userPass, gender, birthday) values(userNo.nextval,?,?,?,?)";
		PreparedStatement st = null;
		Connection conn = null;
		ResultSet rs = null;
	    int result =0;
		
		try {
			conn = OracleDBUtil.dbConnect();
			conn.setAutoCommit(false);
			st = conn.prepareStatement(sql);
		
			st.setString(1, member.getUserMail());
			st.setString(2, member.getUserPass());
			st.setString(3, member.getGender());
			st.setDate(4, member.getBirthday());
			
			result=st.executeUpdate();
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDBUtil.dbDisconnect(rs, st, conn);
		}
		
	}
	
	//멤버조회
	public MemberVO select(String userMail) {
		// TODO Auto-generated method stub
	
		MemberVO member = null;
		
		String sql = "select * from member where userMail =?";	
		PreparedStatement st = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = OracleDBUtil.dbConnect();
			conn.setAutoCommit(false);
			st = conn.prepareStatement(sql);
			st.setString(1, userMail);
			rs= st.executeQuery();
			
			if(rs.next()) {
				member = new MemberVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDate(6));
			}
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDBUtil.dbDisconnect(rs, st, conn);
		}
		
		return member;
	}
	
	//멤버 수정
	public void update(MemberVO member) {
		// TODO Auto-generated method stub
		
		String sql = "update member set userNo=?, userPass=?,userLevel=?, gender=?, birthday=sysdate where userMail=?";
		PreparedStatement st = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = OracleDBUtil.dbConnect();
			conn.setAutoCommit(false);
			st=conn.prepareStatement(sql);
			st.setInt(1, member.getUserNo() );
			st.setString(2, member.getUserPass());
			st.setInt(3, member.getUserLevel());
			st.setString(4, member.getGender());
			st.setDate(5, member.getBirthday());
			st.setString(6, member.getUserMail());
			
			st.executeUpdate();
			conn.commit();
					
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDBUtil.dbDisconnect(rs, st, conn);
		}
	}

	//멤버삭제
	public void delete(String userMail) {
		// TODO Auto-generated method stub
		
		String sql = "delete member where userMail=?";
		PreparedStatement st = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = OracleDBUtil.dbConnect();
			conn.setAutoCommit(false);
			st = conn.prepareStatement(sql);
			st.setString(1, userMail);
			st.executeUpdate();
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDBUtil.dbDisconnect(rs, st, conn);
		}
		
	}
	
	//user 가입을 위해 기존 가입한 userMail 존재 확인
	public int getUserMail(String userMail) {
		int count = 0;
		
		String sql = "SELECT count(*) FROM member"
				+ " WHERE userMail = '" + userMail + "'";
		Connection conn=null;
		Statement st = null;
		ResultSet rs = null;
		
		
		try {
			conn = OracleDBUtil.dbConnect();
			conn.setAutoCommit(false);
			st = conn.createStatement();
		    rs = st.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1); // 0번은 count(*)
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDBUtil.dbDisconnect(rs, st, conn);
		}
		
		return count;
	}
		
		
	//user 로그인 승인을 위해 존재 확인
	public int getUserAuth(String userMail, String userPass) {
		int count = 0;
		
		String sql = "SELECT count(*) FROM member"
				+ " WHERE userMail = '" + userMail + "'" // 18
				+ " AND userPass = '" + userPass + "'"; // 160
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {			
			conn = OracleDBUtil.dbConnect();
			conn.setAutoCommit(false);
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
		
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
	
	//sql 실행
	public Object st_execute(String sql) {

		Connection conn = null;
		Statement st =null;
		ResultSet rs =null;
		int result =0;	
		
		System.out.println("오라클 접속");
		System.out.println(sql);
		try {
			conn = OracleDBUtil.dbConnect();
			conn.setAutoCommit(false);
			st = conn.createStatement();
			result = st.executeUpdate(sql);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDBUtil.dbDisconnect(rs, st, conn);
		}
				
		return result;
	}
	
	//userLevel 조회
	public MemberVO getUserLevel(String userMail) {
		MemberVO member = null;
		
		String sql = "select * from member where usermail =?";	
		PreparedStatement st = null;
		Connection conn = null;
		ResultSet rs = null;
		try {			
			conn = OracleDBUtil.dbConnect();
			conn.setAutoCommit(false);
			st = conn.prepareStatement(sql);
			st.setString(1, userMail);
			rs = st.executeQuery(sql);
			
			if(rs.next()) {
				member = new MemberVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDate(6));
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDBUtil.dbDisconnect(rs, st, conn);
		}
			
		return member;
	}
	
}


