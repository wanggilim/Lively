package com.quardcore.lively.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quardcore.lively.controller.JoinControl;
import com.quardcore.lively.model.MemberVO;
import com.quardcore.lively.service.JoinService;
import com.quardcore.lively.util.DateUtil;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		path = request.getContextPath();	// /webprogramming
    	realPath = request.getServletContext().getRealPath(".");
    	uri = request.getRequestURI();		// /webprogramming/*.do
    	url = request.getRequestURL().toString(); // http://localhost:9090/webprogramming/*.do
    	action = uri.substring(path.length(), uri.length()-3); // *
    	
		response.setCharacterEncoding("utf-8");
		
		// 1. 로그인 (로그인 사이트 진입)
    	if (action.equals("/member/login")) {
    		
			RequestDispatcher rd = request.getRequestDispatcher("/member/login.html");
			rd.forward(request, response);
		}
    	
    	// 2. 로그아웃
    	if (action.equals("/member/logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(path + "/member/login.html");
		}
    	// 1. 회원 삭제
    	if (action.equals("/member/userDelete")) {
    		UserControl control = new UserControl();
    		String userMail = request.getParameter("userMail");
    		String userPass = request.getParameter("userPass");
    		control.deleteUserFromUserMail(userMail);
    		
		}
    	
    
    	// 2. 회원 수정
    	if (action.equals("/member/userUpdate")) {
			UserControl control = new UserControl();
			String userMail = request.getParameter("userMail");
    		control.updateUserFromUserMail(userMail);
		}
    	
    	// 3. 회원 검색
    	if (action.equals("/logIn/userInfo")) {
    		UserControl control = new UserControl();
    		
    		String userMail = request.getParameter("userMail");

    		
    		control.searchUserFromUserMail(userMail);
		}
    
    	
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("===>" + request.getParameter("userMail"));
		
		path = request.getContextPath();
    	realPath = request.getServletContext().getRealPath(".");
    	uri = request.getRequestURI();
    	url = request.getRequestURL().toString();
    	action = uri.substring(path.length(), uri.length()-3);
    	
    	request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
    	
    	//1. 회원가입
		
    	if (action.equals("/member/register")) {
    	
    		
    		JoinControl control = new JoinControl();
    		JoinService service = new JoinService();
    		/*String suserNo = request.getParameter("userNo");
    		int userNo = Integer.parseInt(suserNo); 자동입력*/
    		String userMail = request.getParameter("userMail");
    		String userPass = request.getParameter("userPass");
    		/*String suserLevel = request.getParameter("userLevel");*/
    		/*int userLevel = Integer.parseInt(suserLevel); 관리자 선택*/
    		String gender = request.getParameter("gender");
    		String sbirthday = request.getParameter("birthday");
    		Date birthday = DateUtil.stringToDate(sbirthday);
    		int authUser = 0;
    		int userLevel= 0;
    		
    		MemberVO m = new MemberVO(userMail, userPass, gender, birthday);
    		//DB에 회원 등록
    		service.join(m);
    		
    		if (userMail != null) {
    			authUser = control.getUserAuth(userMail, userPass);
			}
    		
    		// 인증
    		if (authUser == 1) { // 아이디 비밀번호가 맞다면 1이 나와야함.

    			HttpSession session = request.getSession();
    			session.setAttribute("userMail", userMail);
    			// welcome page 전송
    			response.sendRedirect("/webprogramming/member/login.html");
			}
    		
    		
		}
    	
    	// 1. 회원 로그인 (입력 후, 로그인 인증)
    	if (action.equals("/member/login")) {
    		// 입력된 id, pass 가져오기
    		UserControl user = new UserControl();
    		
    		String userMail = request.getParameter("userMail");
    		String userPass = request.getParameter("userPass");
    		int authUser = 0;
    		
    		if (userMail != null) {
    			authUser = user.getUserAuth(userMail, userPass);
			}
    		
    		// 인증
    		if (authUser == 1) { // 아이디 비밀번호가 맞다면 1이 나와야함.
    			UserControl uControl = new UserControl();
    			HttpSession session = request.getSession();
    			session.setAttribute("userMail", userMail);
    			MemberVO m = uControl.getUserLevel(userMail);
    			session.setAttribute("userLevel", m.getUserLevel());
    			
    			// welcome page 전송
    			response.sendRedirect("/webprogramming/index.jsp");
			}
    		
    		
		}
    	
    	if (action.equals("/memeber/login")) {
    		// 입력된 id, pass 가져오기
    		UserControl user = new UserControl();
    		
    		String userMail = request.getParameter("userMail");
    		String userPass = request.getParameter("userPass");
    		int authUser = 0;
    		
    		if (userMail != null) {
    			authUser = user.getUserAuth(userMail, userPass);
			}
    		
    		// 아이디가 없으면 로그인으로 이동
    		if (authUser == 0) { // 아이디 비밀번호가 맞다면 1이 나와야함.
    			HttpSession session = request.getSession();
    			session.setAttribute("userMail", userMail);
    			
    			response.sendRedirect("/webprogramming/member/login.html");
			}
    		
    		
		}
    	

    	
    	
  

}
}
