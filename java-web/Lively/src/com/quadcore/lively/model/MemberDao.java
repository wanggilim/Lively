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






public class MemberDao{
	
	
	

	//멤버 추가
	public void insert(MemberVO member) {
		// TODO Auto-generated method stub
		
		String sql = "insert into member(userNo, userMail, userPass, gender, birthday) values(userNo.nextval,?,?,?,?)";
		PreparedStatement st = null;
		int result =0;
		
		try {
			Connection conn = OracleDBUtil.dbConnect();
			st = conn.prepareStatement(sql);
		
			st.setString(1, member.getUserMail());
			st.setString(2, member.getUserPass());
			st.setString(3, member.getGender());
			st.setDate(4, member.getBirthday());
	
			
			result=st.executeUpdate();

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
			Connection conn = OracleDBUtil.dbConnect();
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
	public void update(MemberVO member) {
		// TODO Auto-generated method stub
		
		String sql = "update member set userNo=?, userPass=?,userLevel=?, gender=?, birthday=sysdate where userMail=?";
		PreparedStatement st = null;
		
		try {
			Connection conn = OracleDBUtil.dbConnect();
			st=conn.prepareStatement(sql);
			st.setInt(1, member.getUserNo() );
			st.setString(6, member.getUserMail());
			st.setString(2, member.getUserPass());
			st.setInt(3, member.getUserLevel());
			st.setString(4, member.getGender());
			st.setDate(5, member.getBirthday());
			
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
			Connection conn = OracleDBUtil.dbConnect();
			st = conn.prepareStatement(sql);
			st.setString(1, userMail);
			st.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		}
	//user 가입을 위해 기존 가입한 userMail 존재 확인
		public int getUserMail(String userMail) {
			int count = 0;
			
			String sql = "SELECT count(*) FROM member"
					+ " WHERE userMail = '" + userMail + "'";
			try {
				Connection conn = OracleDBUtil.dbConnect();
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
	//user 로그인 승인을 위해 존재 확인
	public int getUserAuth(String userMail, String userPass) {
		int count = 0;
		
		String sql = "SELECT count(*) FROM member"
				+ " WHERE userMail = '" + userMail + "'" // 18
				+ " AND userPass = '" + userPass + "'"; // 160
		try {
			Connection conn = OracleDBUtil.dbConnect();
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


		
		conn = OracleDBUtil.dbConnect();
		System.out.println("오라클 접속");
		System.out.println(sql);
		try {
			 st = conn.createStatement();
			 result = st.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDBUtil.dbDisconnect(rs, st, conn);
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
			Connection conn = OracleDBUtil.dbConnect();
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


