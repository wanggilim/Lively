package com.quadcore.lively.controller;

import java.util.List;

import com.quadcore.lively.model.MemberVO;
import com.quadcore.lively.service.MemberService;




public class MemberController {

		
		private MemberService service;
		
		public MemberController() {
			service = new  MemberService();
		}
		// TODO Auto-generated method stub
		//濡쒓렇�씤 �듅�씤
		public int getUserAuth(String userMail, String userPass) {
			return service.getUserAuth(userMail, userPass);
}
		//媛��엯�떆 湲곗〈 �쑀裕� �솗�씤
		public int getUserMail(String userMail) {
			return service.getUserMail(userMail);
		}
		
		//硫ㅻ쾭 �젅踰� �뼸湲�
		public MemberVO getUserLevel(String userMail) {
			// TODO Auto-generated method stub
			return service.getUserLevel(userMail);
		}
		//硫ㅻ쾭 �궘�젣
		public Object deleteUserFromUserMail(int userNo) {
			return service.deleteUserFromUserMail(userNo);
			
		}
		//硫ㅻ쾭 �닔�젙
		public Object updateUserFromUserMail(String userMail) {
			return service.updateUserFromUserMail(userMail);
			
		}
		//硫ㅻ쾭 議고쉶
		public Object searchUserFromUserMail(String userMail) {
			return service.searchUserFromUserMail(userMail);
			
		}
		public int registerCheck(String userMail) {
			// TODO Auto-generated method stub
			return service.registerCheck(userMail);
		}
		
		/**
		 * �쉶�썝 媛��엯
		 * @Date 2018.12.15
		 * @author wgl
		 * @param member::MemberVO 媛��엯�븳 硫ㅻ쾭�쓽 �젙蹂�
		 */
		public void signUp(MemberVO member) {
			service.signUp(member);
			
		}
	

	
	

}
