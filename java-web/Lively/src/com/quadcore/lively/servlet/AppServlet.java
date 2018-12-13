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
import javax.servlet.jsp.PageContext;

import com.quadcore.lively.controller.MemberController;


@WebServlet("*.do")
public class AppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String path = "";
	private String realPath = "";
	private String uri = "";
	private String url = "";
	private String action = "";
	
	public AppServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		path = request.getContextPath(); // /WGLProject1207
		realPath = request.getServletContext().getRealPath(".");
		uri = request.getRequestURI(); // /WGLProject1207/*.do
		url = request.getRequestURL().toString(); // http://localhost:9090/WGLProject1207/*.do
		action = uri.substring(path.length(), uri.length() - 3); // *
		System.out.println("====================");
		// System.out.println(path);
		System.out.println(realPath);
		// System.out.println(uri);
		// System.out.println(url);
		System.out.println(action);
		System.out.println("====================");

		/**
		 * 회원
		 */
		// 1. 로그인 (로그인 사이트 진입)
		if (action.equals("/member/signIn")) {
			RequestDispatcher rd = request.getRequestDispatcher("signIn.html");
			rd.forward(request, response);
		}

		// 2. 로그아웃
		if (action.equals("/member/signOut")) {
			System.out.println("도달");
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(path + "/index.jsp");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		path = request.getContextPath();
		realPath = request.getServletContext().getRealPath(".");
		uri = request.getRequestURI();
		url = request.getRequestURL().toString();
		action = uri.substring(path.length(), uri.length() - 3);
		System.out.println("====================");
		System.out.println(action);
		System.out.println("====================");

		
		/**
		 * 회원
		 */
		
		// 1. 회원 로그인 (입력 후, 로그인 인증)
		if (action.equals("/member/signIn")) {
			// 입력된 id, pass 가져오기
			
			
			System.out.println("action : /member/signIn 들어옴 ");
			
			MemberController user = new MemberController();

			String userMail = request.getParameter("userMail");
			String userPass = request.getParameter("userPass");
			
			System.out.println("userMail : " + userMail);
			System.out.println("userPass : " + userPass);
			
			int authUser = user.getUserAuth(userMail, userPass);

			
			// 1.1 인증
			// 1.1.1 인증 됐을 때
			if (authUser == 1) { // 아이디 비밀번호가 맞다면 1이 나와야함.
				HttpSession session = request.getSession();
				session.setAttribute("userMail", userMail);	//session에 ID를 저장한다

				// welcome page 전송
				response.sendRedirect(path + "/index.jsp");
			}
			// 1.1.2 인증 안됐을 때
			else {
	//////////////////// 추가사항///////////////////////////////			
				System.out.println("인증 실패");
				//1. 알림 메시지 출력(아이디 또는 패스워드가 틀립니다.)
				
				//2. 사용자 입력값을 지우고, signIn.do가 보여진다
				response.sendRedirect(path + "/member/signIn2.html");
				
				return;
				
			}
		}

	}

}
