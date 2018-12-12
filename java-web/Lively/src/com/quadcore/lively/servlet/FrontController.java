package com.quadcore.lively.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quadcore.lively.controller.MemberController;
import com.quadcore.lively.model.MemberVO;

//.do로 맵핑

// WAS가 실행될 때,프론트 컨트롤러는 한 번만 실행됨
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	// 필요한 변수 위에서 설정 완료
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "";
		String realpath = "";
		String uri = "";
		String url = "";
		String action = ""; // uri에서 .do가 빠진, 실질적 액션

		System.out.println("Test");


		// 각각의 사용자들이 변수를 사용 -> 새로운 사람이 로그인하면 -> 다른 사용자의
		Map<String, Object> data;

		path = request.getContextPath();
		realpath = request.getServletPath();
		uri = request.getRequestURI();
		url = request.getRequestURL().toString();
		action = uri.substring(path.length(), uri.length() - 3);
		System.out.println("====================");
		System.out.println("action: " + action);
		System.out.println("====================");

		HttpSession session = request.getSession();
		Object sessionObj = session.getAttribute("member");
		if (!action.equals("/member/login") && sessionObj == null) {
			response.sendRedirect(path + "/member/login.do");

			return;
		}

		String method = request.getMethod().toLowerCase();
		
		switch (action) {

		case "/member/logout":
			// 2. 로그아웃
			System.out.println("로그 아웃!!");
			// session.invalidate(); 현재 생성된 session을 무효화 시킴
			session.invalidate();
			// 값 초기화, index.jsp로 페이지 전환
			response.sendRedirect(path + "/index.jsp");
			break;

		case "/member/login":
			System.out.println("/member/login!!!!!!");
			if (method.equals("post")) {

				System.out.println("action login 실행");
				// 입력된 id, pass 가져오기
				MemberController user = new MemberController();

				String userMail = request.getParameter("userMail");
				String userPass = request.getParameter("userPass");

				System.out.println("userMail: " + userMail);
				System.out.println("userPass: " + userPass);


//---------------수정해야함----------------------				
				MemberVO member = new MemberVO(); 
				
//				request.getAttribute(member);

				
				// 1.사용자가 Id값 pwd값을 입력했을 때

				System.out.println("입력값 받음 //다른 사항 확인 전...");

				// 1.1 ID에 email형식으로 들어왔는지 확인

				// 1.1.1 ID PWD 인증 성공일 경우
				if (member != null) { // ID와 PWD 모두 맞을 때 1이 나옴
					session.setAttribute("member", member);

					session.setAttribute("userMail", userMail);
					System.out.println("userMail:" + userMail + ".......   userPass:" + userPass);

					// welcome page 전송

					response.sendRedirect(path + "/index.jsp");
					return;

					// 1.1.2 ID PWD 인증 실패일 경우
				} else {
					System.out.println("인증이 안 됐을 때");
					session.setAttribute("member", member);
					session.setAttribute("signMessage", "ID 또는 PASSWORD가  틀립니다");
					response.sendRedirect("login.do");
					return;
				}

			} else {
				System.out.println("get!!!!!");
				// 1. 로그인 (로그인 페이지 진입)

				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				System.out.println("Get 로그인 페이지 진입!!!!!");
				// forward로 "입력값" 보내기
				rd.forward(request, response);
				System.out.println("값을 @login으로 보내기");

			}
			break;
		}
	}
}

// 1. 회원 로그인 (입력 후, 로그인 인증)
