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
    	if (action.equals("/member/signIn")) {
    		
			RequestDispatcher rd = request.getRequestDispatcher("/member/signIn.html");
			rd.forward(request, response);
		}
    	
    	// 2. 로그아웃
    	if (action.equals("/member/signOut")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(path + "/member/signIn.html");
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

System.out.println(action);
    	//메일 중복 확인
		int result=0;
    			if(action.equals("/member/userMailDuplication")) {
    				MemberController control = new MemberController();
    				//ajax로 중복 확인
    				String userMail = request.getParameter("userMail");
    				result= control.registerCheck(userMail);
    				System.out.println(result);
    				response.setContentType("text/html;charset=utf-8");
    				response.getWriter().print(result==1?"사용할 수 없는 이메일입니다.":"사용할 수 있는 이메일입니다.");
System.out.println(result);
    				return;
    			}
    	
    	//1. 회원가입
		
    	if (action.equals("/member/signUp")) {
    	
    		System.out.println("가입하로 왔니");
    		MemberController control = new MemberController();
    		MemberService service = new MemberService();
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
    		int count=0;
    		
    		//user 가입을 위해 기존 가입한 userMail 존재 확인
    		count = control.getUserMail(userMail);
    		if(count!=0) {
    			response.sendRedirect(path+"/member/signUp.html");
    		}
    		
    		MemberVO member = new MemberVO(userMail, userPass, gender, birthday);
    		//DB에 회원 등록
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
    			response.sendRedirect(path+"/member/signIn.html");
			}
    		
    		
		}
    	
    	// 1. 회원 로그인 (입력 후, 로그인 인증)
    	if (action.equals("/member/signIn")) {
    		// 입력된 id, pass 가져오기
    		MemberController user = new MemberController();
    		
    		String userMail = request.getParameter("userMail");
    		String userPass = request.getParameter("userPass");
    		int authUser = 0;
    		
    		if (userMail != null) {
    			authUser = user.getUserAuth(userMail, userPass);
			}
    		
    		// 인증
    		if (authUser == 1) { // 아이디 비밀번호가 맞다면 1이 나와야함.
    			MemberController uControl = new MemberController();
    			HttpSession session = request.getSession();
    			session.setAttribute("userMail", userMail);
    			MemberVO member = uControl.getUserLevel(userMail);
    			session.setAttribute("userLevel", member.getUserLevel());
    			
    			// welcome page 전송
    			response.sendRedirect(path+"/index.jsp");
			}else {
				//일치하지 않을 경우 경고창을 띄우고 로그인화면으로 보냄
				//크롬에서 지원하지 않음.
			/*	PrintWriter out = response.getWriter();
				response.setContentType("text/html; charset=utf-8");

				out.println("<script>alert('계정이 등록 되었습니다')</script>");
				 
				out.flush();*/			
				response.sendRedirect(path+"/member/signIn.html");
			}
    		
    		
		}
    	
    	if (action.equals("/memeber/signIn")) {

    		// 입력된 id, pass 가져오기
    		MemberController user = new MemberController();
    		
    		String userMail = request.getParameter("userMail");
    		String userPass = request.getParameter("userPass");
    		int authUser = 0;
System.out.println("여기에는 오나요");
    		if (userMail != null) {
    			authUser = user.getUserAuth(userMail, userPass);
			}
    		
    		// 아이디가 없으면 로그인으로 이동
    		if (authUser == 0) { // 아이디 비밀번호가 맞다면 1이 나와야함.
    			HttpSession session = request.getSession();
    			session.setAttribute("userMail", userMail);
    			
    			response.sendRedirect(path+"/member/signIn.html");
			}
    		
    		
		}
    	

    	
    	
  

}
}
