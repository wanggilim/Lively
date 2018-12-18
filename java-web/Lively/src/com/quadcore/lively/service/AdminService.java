package com.quadcore.lively.service;

import com.quadcore.lively.model.AdminDAO;

public class AdminService {
	
	private AdminDAO dao;
	public AdminService() {
		dao = new AdminDAO();
	}
	//�뀒�씠釉붿“�쉶
	public Object selectByTableName(String tableName) {
		String sql ="select DISTINCT  * from " + tableName   ;
		return dao.selectAll(tableName, sql);
	}
	//admin議고쉶
	public Object selectByAdminNo(int adminNo) {
		String sql ="select * from admin where adminNo ="+adminNo;
		return dao.st_execute(sql);
	}
	//admin�닔�젙
	public Object updateByAdminNo(int adminNo) {
		return dao.st_execute("");
	}
	//admin�궘�젣
	public Object deleteByAdminNo(int adminNo) {
		String sql ="delete from admin where AdminNo="+adminNo;
		return dao.st_execute(sql);
	}
	
	public int insertAdmin(String adminEmail, String adminPass) {
		String sql = "insert into admin(adminno,adminEmail,adminpass) values(adminno.nextval,'"+adminEmail+"','"+adminPass+"')";
		System.out.println(sql);
		return (int)dao.st_execute(sql);
	}
	
	//admin 조건 조회
	public Object selectByLevelEmail(int level, String mail, String tableName) {
		String ifSql = null;
		String sql=null;
		if(tableName.equals("admin")) {
			sql = "select * from admin where 1=1";

			if(level != 0) {
				ifSql = " and adminLevel="+level;
				sql += ifSql;
			}
			if(mail != "") {
				ifSql = " and adminEmail like '%"+mail+"%'";
				sql += ifSql;
			}
			
		}else {
			 sql = "select * from member where 1=1";

			if(level != 0) {
				ifSql = " and userLevel="+level;
				sql += ifSql;
			}
			if(mail != "") {
				ifSql = " and userMail like '%"+mail+"%'";
				sql += ifSql;
			}
			
			System.out.println(sql);
		}
			
		return dao.selectAll(tableName, sql);
	}
	
	//B 보류
	public Object updateAdmin(int adminNo, String adminEmail, String adminPass, int adminLevel, int setAdminLevel) {
		String sql = "update admin set ";
		String sql2 = " where 1=1";
		String ifSql = null;

		
		if(setAdminLevel != 0) {
			ifSql = "adminLevel="+setAdminLevel;
			sql += ifSql;
		}
		if(adminNo !=0) {
			ifSql=" and adminNo="+adminNo;
			sql2 += ifSql;
		}
		if(adminEmail !=null) {
			ifSql=" and adminEmail='"+adminEmail+"'";
			sql2 += ifSql;
		}
		if(adminPass !=null) {
			ifSql=" and adminPass='"+adminPass+"'";
			sql2 += ifSql;
		}
		if(adminLevel !=0) {
			ifSql=" and adminLevel="+adminLevel;
			sql2 += ifSql;
		}
		System.out.println(sql+sql2);
		return dao.updateAdmin(sql+sql2);
	}
	public Object updateByMemberNo(int userNo, String userMail, String userPass, int userLevel, String gender,
			String birthday, int setMemberLevel) {
		String sql1 = "update member set userLevel="+ setMemberLevel;
		String sql2 = "where userNo="+userNo;
		
		
		return dao.updateAdmin(sql1+sql2);
	}

}
