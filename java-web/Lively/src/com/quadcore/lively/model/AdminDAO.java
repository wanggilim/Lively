package com.quadcore.lively.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.quadcore.lively.util.OracleDBUtil;

public class AdminDAO {
	
	//�뀒�씠釉붿“�쉶
	public Object selectAll(String tableName, String sql) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
	 
		MemberVO member = null;
		AdminVO admin = null;
		List<MemberVO> memberList = new ArrayList<>();
		List<AdminVO> adminList = new ArrayList<>();
		List list = new ArrayList<>();

		conn = OracleDBUtil.dbConnect();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (tableName.equals("member")) {
				while (rs.next()) {
					member = makeMember(rs);
					memberList.add(member);
				}
				list = memberList;
			} else {
				while (rs.next()) {
					admin = makeAdmin(rs);
					adminList.add(admin);
				}
				list = adminList;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleDBUtil.dbDisconnect(rs, st, conn);
		}
		
		return list;
			
	}
	
	//admin議고쉶
		public AdminVO select(String adminMail) {
			// TODO Auto-generated method stub
		
			AdminVO admin = null;
			
			String sql = "select * from admin where adminMail = ?";	
			PreparedStatement st = null;
			Connection conn = null;
			ResultSet rs = null;
			
			try {
				conn = OracleDBUtil.dbConnect();
				conn.setAutoCommit(false);
				st = conn.prepareStatement(sql);
				rs= st.executeQuery();
				
				if(rs.next()) {
					admin= new AdminVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				}
				conn.commit();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				OracleDBUtil.dbDisconnect(rs, st, conn);
			}
			
			return admin;
		}
		
		//admin �닔�젙
		public Object updateAdmin(String sql) {
			// TODO Auto-generated method stub
		
			PreparedStatement st = null;
			Connection conn = null;
			ResultSet rs = null;
			
			try {
				conn = OracleDBUtil.dbConnect();
				conn.setAutoCommit(false);
				st=conn.prepareStatement(sql);				
				st.executeUpdate();
				conn.commit();
						
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				OracleDBUtil.dbDisconnect(rs, st, conn);
			}
			return null;
		}

		//admin�궘�젣
		public void delete(String adminMail) {
			// TODO Auto-generated method stub
			
			String sql = "delete from member where adminNo=?";
			PreparedStatement st = null;
			Connection conn = null;
			ResultSet rs = null;
			
			try {
				conn = OracleDBUtil.dbConnect();
				conn.setAutoCommit(false);
				st = conn.prepareStatement(sql);
				st.setString(1, adminMail);
				st.executeUpdate();
				conn.commit();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				OracleDBUtil.dbDisconnect(rs, st, conn);
			}
			
		}
		
		//admin�깮�꽦
		private AdminVO makeAdmin(ResultSet rs) throws SQLException {
			int adminNo = rs.getInt("adminNO");
			String adminMail = rs.getString("adminEMail");
			String adminPass = rs.getString("adminPass");
			int adminLevel = rs.getInt("adminLevel");
			AdminVO admin = new AdminVO(adminNo, adminMail, adminPass, adminLevel);
			return admin;
		}
		
		
		//member�깮�꽦
		private MemberVO makeMember(ResultSet rs) throws SQLException {
			int userNo = rs.getInt("userNo");
			String userMail = rs.getString("userMail");
			String userPass = rs.getString("userPass");
			int userLevel = rs.getInt("userLevel");
			String gender = rs.getString("gender");
			Date birthday = rs.getDate("birthday");
			
			MemberVO member = new MemberVO(userNo, userMail, userPass, userLevel, gender, birthday);
			return member;
		}
		//sql�떎�뻾
		public Object st_execute(String sql) {

			Connection conn = null;
			Statement st =null;
			ResultSet rs =null;
			int result =0;	
			
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

	
		

}
