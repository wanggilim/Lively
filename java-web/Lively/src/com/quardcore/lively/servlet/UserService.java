package com.quardcore.lively.servlet;

import com.quardcore.lively.model.JoinDao;
import com.quardcore.lively.model.MemberVO;

public class UserService {

	private JoinDao dao;
	
		public UserService() {
			dao = new JoinDao();
		}

		public int getUserAuth(String userMail, String userPass) {
			return dao.getUserAuth(userMail, userPass);
		}
		
		public MemberVO getUserLevel(String userMail) {
			return dao.getUserLevel(userMail);
		}

		public Object deleteUserFromUserMail(String userMail) {
			String sql ="delete from member where usermail="+userMail;
			return dao.st_execute(sql);
		}

		public Object updateUserFromUserMail(String userMail) {
			return dao.st_execute("");
		}

		public Object searchUserFromUserMail(String userMail) {
			String sql ="select * from member where usermail="+userMail;
			return dao.st_execute(sql);
		}




}
