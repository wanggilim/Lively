package com.quadcore.lively.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.quadcore.lively.model.MemberVO;
import com.quadcore.lively.util.OracleDBUtil;

/**
 * @author user
 *
 */
public class MemberDAO {


	//로그인 ID PWD 확인 로직
	public static MemberVO getUserAuth(String email, String userPass) {
		
		//int count = 0;
		MemberVO member = null;
		String sql = "select * from member"
				+ " where usermail = '" + email + "'"
				+ " and userpass = '" + userPass + "'";
		
		System.out.println("Member DAO로 입력값 들어 옴");
		Connection conn = OracleDBUtil.dbConnect();
		
		try {
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				MemberVO mem = new MemberVO(rs.getInt("userNo"), email, userPass, 
						rs.getInt("userLevel"), rs.getString("gender"), rs.getDate("birthday")) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		/*
		finally {
			OracleDBUtil.dbDisconnect(rs, st, conn);
		}
		*/
		return member;
	}
	
	
	public List<MemberVO> memberSelectAll() {
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from member";
		MemberVO member = null;
		List<MemberVO> memberlist = new ArrayList<>();
		
		conn = OracleDBUtil.dbConnect();
		System.out.println("오라클 접속 성공");

			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while(rs.next()) {
					member = makeMember(rs);
					memberlist.add(member);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				OracleDBUtil.dbDisconnect(rs, st, conn);
			}
			return memberlist;
			
	}
	
		//
	private MemberVO makeMember(ResultSet rs) {
		
		return null;
	}
	
}
