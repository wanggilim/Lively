package com.quadcore.lively.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quadcore.lively.controller.MemberController;

//.do로 맵핑
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	//필요한 변수 위에서 설정 완료
	private static final long serialVersionUID = 1L;
    private String path = "";
    private String realpath = "";
    private String uri = "";
    private String url = "";
    private String action = ""; // uri에서 .do가 빠진, 실질적 액션
    private Map<String, Object> data;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		path = request.getContextPath();	
		realpath = request.getServletPath();
		uri = request.getRequestURI();	
		url = request.getRequestURL().toString();	
		action = uri.substring(path.length(), uri.length()-3);
		
		System.out.println("====================");
		System.out.println("action:" + action);
		System.out.println("====================");
		
		// 1. 로그인 (로그인 페이지 진입)
		if (action.equals("/member/signin")) {
			RequestDispatcher rd = request.getRequestDispatcher("/member/login.html");
			System.out.println("로그인 페이지 진입");
			//forward로 "입력값" 갖고 오기
			rd.forward(request, response);
			System.out.println("값을 @signin으로 보내기");
		}
		// 2. 로그아웃
		if (action.equals("/member/signout")) {
			System.out.println("로그 아웃!!");
			HttpSession session = request.getSession();
			//session.invalidate(); 현재 생성된 session을 무효화 시킴
			session.invalidate();
			//값 초기화, index.html로 페이지 전환
			response.sendRedirect(path+"/index.html");
		}
		
		//
		
	}//doGet끝

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		path = request.getContextPath();		
		realpath = request.getServletPath();
		uri = request.getRequestURI();		
		url = request.getRequestURL().toString();		
		action = uri.substring(path.length(), uri.length()-3);	

		
		//1. 회원 로그인 (입력 후, 로그인 인증)
		if (action.equals("/member/login")) {
			System.out.println("action 실행");
			//입력된 id, pass 가져오기
			MemberController user = new MemberController();
			
			String userMail = request.getParameter("userMail");
			System.out.println(userMail);
			String userPass = request.getParameter("userPass");
			int authUser = 1;
			
			System.out.println(userPass);
			//사용자가 Id값을 입력했을 때 
			if(userMail!=null) {
				System.out.println("입력값 받음1");
				authUser = user.getUserAuth(userMail,userPass);
				System.out.println("입력값 받음2");
			}
			//인증 완료
			if (authUser == 1) { //ID와 PWD 모두 맞을 때 1이 나옴
				HttpSession session = request.getSession();
				
				session.setAttribute("userMail", userMail);
				System.out.println("userMail:" + userMail + "/ userPass:" + userPass);
				
				//welcome page 전송
				response.sendRedirect(path + "/index.html");
			}
		}else {
			System.out.println("값을 못 받았을 때");
		}
		// null일 수도 있어서 String 안됨!! Object로 받아야함
		Object result = data.get("loginResult"); // 로그인일 경우, 기타
		// 로그인이 아닌 기타의 경우,


	}//doPost() 끝

}
