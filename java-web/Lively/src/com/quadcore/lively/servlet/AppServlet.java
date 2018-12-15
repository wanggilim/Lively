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
		
		int result=0;
    	//메일 중복 확인
		System.out.println(result);
        if (action.equals("/member/userMailDuplication")) {
			MemberController control = new MemberController();
			//ajax로 중복 확인
			String userMail = request.getParameter("userMail");
			System.out.println(userMail);
			result= control.registerCheck(userMail);
			System.out.println(result);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(result);
			// (하단 코드에 대한 역할은 signUp.js 에서 처리하도록 수정하였음. 하단 코드는 원본코드.)
			//response.getWriter().print(result==1?"사용할 수 없는 이메일입니다.":"사용할 수 있는 이메일입니다.");
			//return;
			
    	}
		
    			 
    	//1. 회원가입
		/**
		 * 변경사항 체크! (\/member\/signUp)+(\/member\/userMailDuplication)
		 * 
		 * 1. 코드 정리
		 * 2. jquery ajax 연동 완료 => getUserAuth 로 이동 로직 삭제
		 * 3. 회원가입 후 index.jsp (하단 주석 참고)
		 * 4. MemberService의 signUp 메서드로 직접 가입(signUp) 메서드 호출이 아닌
		 * MemberController 를 경유하여 이동하는 로직으로 변경 (진주 님과의 상의 필요!)
		 * 5. 이 메소드의 위에 있는 \/member\/userMailDuplication 메소드 주석 확인
		 * 6. sbirthday가 null이 아니고 ""가 아니어야만, Date 형변환 하는 로직 추가 (java.text.ParseException 예방)
		 * 7. signUp.html 대부분 조절했습니다. 자세한 것은 기존에 작성하신 코드를 확인해주시고,
		 * 구현 후 테스트를 시도했으나, signUp.html 의 form 태그에서의 onsubmit attr이 제대로 작동이 되지 않습니다.
		 * (onsubmit="return registerCheckFunction();" ... 이 부분 체크 부탁드립니다. -> signUp.js 참고)
		 * 
		 * @Date 2018.12.15
		 * @author wgl
		 */
    	if (action.equals("/member/signUp")) {
    	
    		System.out.println("가입하로 왔니");
    		MemberController control = new MemberController();
    		/*String suserNo = request.getParameter("userNo");
    		int userNo = Integer.parseInt(suserNo); 자동입력*/
    		String userMail = request.getParameter("userMail");
    		String userPass = request.getParameter("userPass");
    		/*String suserLevel = request.getParameter("userLevel");*/
    		/*int userLevel = Integer.parseInt(suserLevel); 관리자 선택*/
    		String gender = request.getParameter("gender");
    		String sbirthday = request.getParameter("birthday");
    		Date birthday = null;
    		if (sbirthday != null && !sbirthday.equals("")) {
    			birthday = DateUtil.stringToDate(sbirthday);
			}
    		MemberVO member = new MemberVO(userMail, userPass, gender, birthday);
    		//DB에 회원 등록
    		control.signUp(member);
    		
    		//회원가입 후 인덱스로 이동
    		//(이 주석은 확인 후 삭제) 추후에 index.jsp 로 넘어가거나 dashboard 화면으로 넘어갈지 결정해야함.
    		response.sendRedirect(path+"/index.jsp");
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


