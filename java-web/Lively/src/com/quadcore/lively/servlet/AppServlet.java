package com.quadcore.lively.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.quadcore.lively.controller.MemberController;
import com.quadcore.lively.model.MemberVO;
import com.quadcore.lively.service.MemberService;
import com.quadcore.lively.util.DateUtil;

/**
 * Servlet implementation class AppServlet
 */
@WebServlet("*.do")
public class AppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String path = "";
	private String realPath = "";
	private String uri = "";
	private String url = "";
	private String action = "";
	private Map<String, Object> data;

	public AppServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		path = request.getContextPath(); // /webprogramming
		realPath = request.getServletContext().getRealPath(".");
		uri = request.getRequestURI(); // /webprogramming/*.do
		url = request.getRequestURL().toString(); // http://localhost:9090/webprogramming/*.do
		action = uri.substring(path.length(), uri.length() - 3); // *

		response.setCharacterEncoding("utf-8");

		// 1. 로그인 (로그인 사이트 진입) (jinju)
		if (action.equals("/member/signIn")) {
			
			// 로그인 (또는 세션) 유무에 따라 진입 차단과 허용을 구분지음 (wgl/2018.12.15 수정)
			HttpSession session = request.getSession();
			MemberVO member = (MemberVO) session.getAttribute("member");
			
			// 로그인 상태라면
			if (member != null) {
				response.sendRedirect(path + "/dashboard.jsp");
			} else {
			// 로그인 상태가 아니라면 (= 로그인을 해야한다면)
				response.sendRedirect("/member/signIn.html");
			}
		}

		// 2. 로그아웃 (jinju)
		if (action.equals("/member/signOut")) {
			//세션 끊기 filter에서 진행할 예정 -> 반영완료 (wgl)
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(path + "/index.jsp"); // Lively Welcome page 이동하도록 재조정 (wgl)
		}
		// 1. 회원 삭제
		if (action.equals("/member/userDelete")) {
			MemberController control = new MemberController();
			String userMail = request.getParameter("userMail");
			String userPass = request.getParameter("userPass");
			control.deleteUserFromUserMail(userMail);

		}

		// 2. 회원 수정
		if (action.equals("/member/userUpdate")) {
			MemberController control = new MemberController();
			String userMail = request.getParameter("userMail");
			control.updateUserFromUserMail(userMail);
		}

		// 3. 회원 검색
		if (action.equals("/signIn/userInfo")) {
			MemberController control = new MemberController();

			String userMail = request.getParameter("userMail");

			control.searchUserFromUserMail(userMail);
		}

	} // end of doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("===>" + request.getParameter("userMail"));

		path = request.getContextPath();
		realPath = request.getServletContext().getRealPath(".");
		uri = request.getRequestURI();
		url = request.getRequestURL().toString();
		action = uri.substring(path.length(), uri.length() - 3);

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		// 1. 회원가입

		if (action.equals("/member/signUp")) {
			/**
			 *  code merge 할 때,
			 *  반드시 정표님과 협의 필요한 부분입니다.
			 *  1. member2 라는 변수명
			 *  2. 이어서 오는 로그인 유무에 따른 진입 차단과 허용 구분 로직
			 *  @author wgl
			 *  @Date 2018.12.15
			 */
			// 로그인 (또는 세션) 유무에 따라 진입 차단과 허용을 구분지음 (wgl/2018.12.15 수정)
			HttpSession session2 = request.getSession();
			MemberVO member2 = (MemberVO) session2.getAttribute("member");
			// 로그인 상태라면
			if (member2 != null) {
				response.sendRedirect(path + "/dashboard.jsp");
			}
			// 여기까지////////////////////////////////////////////////////////
			

			System.out.println("가입하로 왔니");
			MemberController control = new MemberController();
			MemberService service = new MemberService();
			/*
			 * String suserNo = request.getParameter("userNo"); int userNo =
			 * Integer.parseInt(suserNo); 자동입력
			 */
			String userMail = request.getParameter("userMail");
			String userPass = request.getParameter("userPass");
			/* String suserLevel = request.getParameter("userLevel"); */
			/* int userLevel = Integer.parseInt(suserLevel); 관리자 선택 */
			String gender = request.getParameter("gender");
			String sbirthday = request.getParameter("birthday");
			Date birthday = DateUtil.stringToDate(sbirthday);
			int authUser = 0;
			int userLevel = 0;
			int count = 0;

			// user 가입을 위해 기존 가입한 userMail 존재 확인
			count = control.getUserMail(userMail);
			if (count != 0) {
				response.sendRedirect(path + "/member/signUp.html");
			}

			MemberVO member = new MemberVO(userMail, userPass, gender, birthday);
			// DB에 회원 등록
			service.signUp(member);

			if (userMail != null) {
				authUser = control.getUserAuth(userMail, userPass);
			}

			// 인증
			if (authUser == 1) { // 아이디 비밀번호가 맞다면 1이 나와야함.

				HttpSession session = request.getSession();
				session.setAttribute("userMail", userMail);
				// welcome page 전송
				System.out.println("session을 가지고 왔니");
				response.sendRedirect(path + "/member/signIn.html");
			}

		}

		// 1. 회원 로그인 (입력 후, 로그인 인증) (jinju)
		if (action.equals("/member/signIn")) {
			// 입력된 id, pass 가져오기
			MemberController user = new MemberController();

			String userMail = request.getParameter("userMail");
			String userPass = request.getParameter("userPass");
			int authUser = user.getUserAuth(userMail, userPass);

			// 로그인 성공
			if (authUser == 1) { // 아이디 비밀번호가 맞다면 1이 나와야함.
				MemberController controller = new MemberController();
				HttpSession session = request.getSession();

				/**
				 * session에 저장 : MemberVO 
				 * @Date 2018.12.14
				 * @author wgl
				 * 
				 * (이하 원본)
				 * \ // session에 저장 : userMail , userLevel을
				 * int userLevel = controller.getUserLevel(userMail);
				 * session.setAttribute("userMail", userMail);
				 * session.setAttribute("userLevel", userLevel);
				 * System.out.println("userMail: " + (String) session.getAttribute("userMail"));
				 * System.out.println("userLevel: " + (int) session.getAttribute("userLevel"));
				 */
				MemberVO member = controller.getMember(userMail);
				session.setAttribute("member", member);
				System.out.println(member.toString());
				
				// welcome page 전송
				response.sendRedirect(path + "/dashboard.jsp");
			}
			// 로그인 실패
			else {
				// signIn2.html (인증 실패 메시지창 뜨는 로그인 페이지)
				response.sendRedirect(path + "/member/signIn2.html");
			}

		}
	} // end of doPost
}
