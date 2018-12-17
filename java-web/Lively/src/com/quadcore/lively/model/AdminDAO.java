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
	
	//테이블조회
	public Object selectAll(String tableName) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from user_tables where Table_name = '"+ tableName +"'";
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
	
	//admin조회
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
		
		//admin 수정
		public void update(AdminVO admin) {
			// TODO Auto-generated method stub
			
			String sql = "update admin set adminNo=?, adminEmail=?, adminPass=?, adminLevel=?";
			PreparedStatement st = null;
			Connection conn = null;
			ResultSet rs = null;
			
			try {
				conn = OracleDBUtil.dbConnect();
				conn.setAutoCommit(false);
				st=conn.prepareStatement(sql);
				st.setInt(1, admin.getAdminNo() );
				st.setString(2, admin.getAdminMail());
				st.setString(3, admin.getAdminPass());
				st.setInt(4, admin.getAdminLevel());
				
				st.executeUpdate();
				conn.commit();
						
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				OracleDBUtil.dbDisconnect(rs, st, conn);
			}
		}

		//admin삭제
		public void delete(String adminMail) {
			// TODO Auto-generated method stub
			
			String sql = "delete member where adminMail=?";
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
		
		//admin생성
		private AdminVO makeAdmin(ResultSet rs) throws SQLException {
			int adminNo = rs.getInt("adminNO");
			String adminMail = rs.getString("adminMail");
			String adminPass = rs.getString("adminPass");
			int adminLevel = rs.getInt("adminLevel");
			AdminVO admin = new AdminVO(adminNo, adminMail, adminPass, adminLevel);
			return admin;
		}
		
		
		//member생성
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
		//sql실행
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


}
