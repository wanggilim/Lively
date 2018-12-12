package com.quardcore.lively.controller;

import com.quardcore.lively.model.MemberVO;
import com.quardcore.lively.service.JoinService;



public class JoinControl {

		
		private JoinService service;
		
		public JoinControl() {
			service = new  JoinService();
		}
		// TODO Auto-generated method stub
	
		public int getUserAuth(String usermail, String userpass) {
			return service.getUserAuth(usermail, userpass);
}

	

	
	

}
