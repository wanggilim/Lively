package com.quardcore.lively.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quardcore.lively.util.OracleDButil;






public class JoinDao{
	
	
	

	//멤버 추가
	public void insert(MemberVO m) {
		// TODO Auto-generated method stub
		
		String sql = "insert into member(userNo, userMail, userPass, gender, birthday) values(userNo.nextval,?,?,?,?)";
		PreparedStatement st = null;
		int result=0;
		
		try {
			Connection conn = OracleDButil.dbConnect();
			st = conn.prepareStatement(sql);
			
		
			st.setString(1, m.getUserMail());
			st.setString(2, m.getUserPass());
			st.setString(3, m.getGender());
			st.setDate(4, m.getBirthday());
			
			result=st.executeUpdate();
			System.out.println(result);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	//멤버조회
	public MemberVO select(String userMail) {
		// TODO Auto-generated method stub
	
		ResultSet rs = null;
		//MemberVO m = null;
		
		String sql = "select * from member where userMail =?";	
		PreparedStatement st = null;
		
		try {
			Connection conn = OracleDButil.dbConnect();
			st = conn.prepareStatement(sql);
			st.setString(1, userMail);
			rs= st.executeQuery();
			
			if(rs.next()) {
				return new MemberVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDate(6));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	//멤버 수정
	public void update(MemberVO m) {
		// TODO Auto-generated method stub
		
		String sql = "update member set userNo=?, userPass=?,userLevel=?, gender=?, birthday=sysdate where userMail=?";
		PreparedStatement st = null;
		
		try {
			Connection conn = OracleDButil.dbConnect();
			st=conn.prepareStatement(sql);
			st.setInt(1, m.getUserNo() );
			st.setString(6, m.getUserMail());
			st.setString(2, m.getUserPass());
			st.setInt(3, m.getUserLevel());
			st.setString(4, m.getGender());
			st.setDate(5, m.getBirthday());
			
			st.executeUpdate();
					
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//멤버삭제
	public void delete(String userMail) {
		// TODO Auto-generated method stub
		
		String sql = "delete member where userMail=?";
		PreparedStatement st = null;
		try {
			Connection conn = OracleDButil.dbConnect();
			st = conn.prepareStatement(sql);
			st.setString(1, userMail);
			st.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		}
	
	//user 승인을 위해 존재 확인
	public int getUserAuth(String userMail, String userPass) {
		int count = 0;
		
		String sql = "SELECT count(*) FROM member"
				+ " WHERE userMail = '" + userMail + "'" // 18
				+ " AND userPass = '" + userPass + "'"; // 160
		try {
			Connection conn = OracleDButil.dbConnect();
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1); // 0번은 count(*)
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	//sql 실행
	public Object st_execute(String sql) {

		Connection conn = null;
		Statement st =null;
		ResultSet rs =null;
		int result =0;	


		
		conn = OracleDButil.dbConnect();
		System.out.println("오라클 접속");
		System.out.println(sql);
		try {
			 st = conn.createStatement();
			 result = st.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDButil.dbDisconnect(rs, st, conn);
		}
				
		return result;
	}
	
	//userLevel 조회
	public MemberVO getUserLevel(String userMail) {
		ResultSet rs = null;
		//MemberVO m = null;
		
		String sql = "select * from member where usermail =?";	
		PreparedStatement st = null;
		
		try {
			Connection conn = OracleDButil.dbConnect();
			st = conn.prepareStatement(sql);
			st.setString(1, userMail);
			rs= st.executeQuery();
			
			if(rs.next()) {
				return new MemberVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDate(6));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	}


