package com.quadcore.lively.servlet;

import com.quadcore.lively.model.MemberVO;

public class UserControl {

	private UserService service;
	
	public UserControl() {
		service = new UserService();
	}
	
	public int getUserAuth(String userMail, String userPass) {
		return service.getUserAuth(userMail, userPass);
	}
	

	public MemberVO getUserLevel(String userMail) {
		// TODO Auto-generated method stub
		return service.getUserLevel(userMail);
	}

	public Object deleteUserFromUserMail(String userMail) {
		return service.deleteUserFromUserMail(userMail);
		
	}

	public Object updateUserFromUserMail(String userMail) {
		return service.updateUserFromUserMail(userMail);
		
	}

	public Object searchUserFromUserMail(String userMail) {
		return service.searchUserFromUserMail(userMail);
		
	}

}
