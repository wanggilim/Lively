package com.quadcore.lively.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	//사용 보류
	public static HttpSession uSession() {
		//request를 null로 초기화는 로직이 이상함
		HttpServletRequest request = null;
		HttpSession session = request.getSession();
		return session;
		
	}
}
