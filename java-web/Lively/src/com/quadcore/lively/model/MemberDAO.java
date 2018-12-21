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

public class MemberDAO{
	
	//멤버 추가
	/**
	 * 변경사항
	 * 1. SQL 문장
	 * 
	 * @Date 2018.12.15
	 * @author wgl
	 */
	public void insert(MemberVO member) {
		// TODO Auto-generated method stub
		
		String sql = "insert into member(userno, usermail, userpass, gender, birthday) values(userno.nextval,?,?,?,?)";
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
			
			while(rs.next()) {
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
	public void delete(int userNo) {
		// TODO Auto-generated method stub
		
		String sql = "delete from member where userNo=?";
		PreparedStatement st = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = OracleDBUtil.dbConnect();
			conn.setAutoCommit(false);
			st = conn.prepareStatement(sql);
			st.setInt(1, userNo);
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
		
		System.out.println("sql: "+ sql);
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {			
			conn = OracleDBUtil.dbConnect();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
		
				count = rs.getInt(1); // 0번은 count(*)
				
				System.out.println("count: " + count);
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
	public int st_execute(String sql) {

		Connection conn = null;
		Statement st =null;
		ResultSet rs =null;
		int result =0;	
		

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
	
	//userMail로 (MemberVO)member 조회
	public MemberVO getUserLevel(String userMail) {
		MemberVO member = null;
		
		String sql = "select * from member where usermail =?";	
		PreparedStatement st = null;
		Connection conn = null;
		ResultSet rs = null;
		try {			
			conn = OracleDBUtil.dbConnect();
			st = conn.prepareStatement(sql);
			st.setString(1, userMail);
			rs = st.executeQuery();
			
			while(rs.next()) {
				member = new MemberVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDate(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDBUtil.dbDisconnect(rs, st, conn);
		}
			
		return member;
	}
	//멤버레벨얻기


	//관리자 페이지 선택
	public List<MemberVO> selectByUserLevelMail( int userLevel, String userMail) {
		MemberVO member = null;
		List<MemberVO> memberList = new ArrayList<>();
		String sql=null;
		sql = "select * from member where 1=1";
			if(userLevel != 0) {
				String sqlIf =" and userLevel="+userLevel;
				sql += sqlIf;
			}
			if(userMail != "") {
				String sqlIf = " and userMail like '%"+userMail+"%'";
				sql += sqlIf;
			}
			System.out.println(sql);
		
		PreparedStatement st = null;
		Connection conn = null;
		ResultSet rs = null;
		try {			
			conn = OracleDBUtil.dbConnect();
			conn.setAutoCommit(false);
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			
			while(rs.next()) {
				member = new MemberVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDate(6));
				memberList.add(member);
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDBUtil.dbDisconnect(rs, st, conn);
		}	
		
		return memberList;
	}
	// 관리자 페이지 권한 업데이트
	public void updateByMemberNo(MemberVO member, int setMemberLevel) {
		
		String sql ="update member set userLevel="+setMemberLevel
					+" where userNo="+member.getUserNo();
		PreparedStatement st = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			
			conn = OracleDBUtil.dbConnect();
			conn.setAutoCommit(false);
			st = conn.prepareStatement(sql);
			
			rs = st.executeQuery();

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDBUtil.dbDisconnect(rs, st, conn);
		}
	}

	public void insertAdmin(MemberVO member) {
		
		String sql = "insert into member(userno, usermail, userpass, userLevel, gender, birthday) values(userno.nextval,?,?,?,?,?)";
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
			st.setInt(3, member.getUserLevel());
			st.setString(4, member.getGender());
			st.setDate(5, member.getBirthday());
			
			result=st.executeUpdate();
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDBUtil.dbDisconnect(rs, st, conn);
		}
		
	}

	public Object updateMyInfo(
			MemberVO member,String setUserPass, String setUserGender, Date setUserBirthday) {
		String setSql="update member set ";
		String sqlIf=" where userNo="+member.getUserNo();
		String sqlSelecet = "select * from member where userNo="+member.getUserNo();
		if(setUserPass != "") {
			setSql += "userPass=" + "'"+setUserPass+"'";
			if(setUserGender != ""  || setUserBirthday != null) {
				setSql += ",";
			}
		}
		
		if(setUserGender != "") {
			setSql += "gender=" + "'"+setUserGender+"'";
			if(setUserBirthday != null) {
				setSql += ",";
			}
		}
		if(setUserBirthday != null) {
			setSql += "birthday=" + "'"+setUserBirthday+"'";
		}
		String sql = setSql + sqlIf;
		System.out.println(sql);
		
		
		PreparedStatement st = null;
		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = OracleDBUtil.dbConnect();
			conn.setAutoCommit(false);
			st = conn.prepareStatement(sql);
			
			st.executeUpdate();
			conn.commit();
			
			st = conn.prepareStatement(sqlSelecet);
			rs = st.executeQuery();
			while(rs.next()) {
				member = new MemberVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDate(6));
			}
			st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDBUtil.dbDisconnect(rs, st, conn);
		}
		return member;
		
	}
		
		
	}
	