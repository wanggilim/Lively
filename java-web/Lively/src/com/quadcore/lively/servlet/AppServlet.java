package com.quadcore.lively.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quadcore.lively.controller.DashboardController;
import com.quadcore.lively.controller.MemberController;
import com.quadcore.lively.model.MemberVO;
import com.quadcore.lively.model.StmtVO;
import com.quadcore.lively.model.WordVO;
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
			System.out.println("session이 끊겼습니다.");
			response.sendRedirect(path + "/");
		}

		// 1. 회원 삭제
		if (action.equals("/member/userDelete")) {
			MemberController control = new MemberController();
			String userMail = request.getParameter("userMail");
			String userPass = request.getParameter("userPass");
			control.deleteUserFromUserMail(userMail);

		}


		// 2. 회원 검색
		if (action.equals("/signIn/userInfo")) {
			MemberController control = new MemberController();

			String userMail = request.getParameter("userMail");

			control.searchUserFromUserMail(userMail);
		}


		/**
		 * 대쉬보드
		 *
		 * @author wgl
		 * @Date 2018.12.18
		 */
		if (action.equals("/dashboard")) {
			DashboardController controller = new DashboardController();
			String word = request.getParameter("word"); // dashboard.js 에서 넘어옴.

			// 대쉬보드 접속
			if (word == null) {
				System.out.println("word 가 null 입니다.");
				response.sendRedirect(path + "/dashboard.jsp");
			}

			// 대쉬보드 내에서 검색
			else {
				List<WordVO> wordVOList = controller.searchWords(word);
				List<StmtVO> stmtVOList = controller.searchStatements(word);
				List<String> perhapsKeywords = controller.searchPerhapsWords(word, wordVOList);
				String means = ""; // 입력한 단어에 대한 의미(뜻)들

				RequestDispatcher rd = request.getRequestDispatcher("dashboard_result.jsp");
				request.setAttribute("word", word);		// 검색 키워드 (dashboard.js 파라미터)
				//request.setAttribute("means", means);	// 검색 키워드에 대한 단어 뜻
				request.setAttribute("wordVOList", wordVOList); // 검색 키워드에 대한 결과 :: List<WordVO>
				request.setAttribute("stmtVOList", stmtVOList); // 검색 키워드에 대한 결과 :: List<StmtVO>
				request.setAttribute("perhapsKeywords", perhapsKeywords); // 사용자의 입력 오타인지 확인해주기 위한 단어 리스트
				rd.forward(request, response);
			}
		}

	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("===>" + request.getParameter("userMail"));

		path = request.getContextPath();
		realPath = request.getServletContext().getRealPath(".");
		uri = request.getRequestURI();
		url = request.getRequestURL().toString();
		action = uri.substring(path.length(), uri.length() - 3);

		int result = 0;
		// 메일 중복 확인

    	// 1. 유저 계정 삭제
    	if (action.equals("/admin/userDelete")) {
    		MemberController control = new MemberController();
    		String userMail = request.getParameter("userMail");
    		control.deleteUserFromUserMail(userMail);
    		response.sendRedirect(path + "/admin/admin.jsp");
		}

    	
    	// 2. 유저 정보 수정
    	if (action.equals("/member/userUpdate")) {
    		MemberVO member = new MemberVO();
    		MemberController control = new MemberController();
			HttpSession session = request.getSession();
			member = (MemberVO)session.getAttribute("member");
			System.out.println(member.toString());
			String setUserPass = request.getParameter("setUserPass");
			String setUserGender = request.getParameter("setUserGender");
			String ssetUserBirthday = request.getParameter("setUserBirthday");

			Date setUserBirthday=null;
			if(ssetUserBirthday != "") {
			   setUserBirthday = DateUtil.stringToDate(ssetUserBirthday);
			}

			member = (MemberVO)control.updateMyInfo(member, setUserPass, setUserGender,setUserBirthday);
			System.out.println("변경된 세션: "+member.toString());
			session.setAttribute("member", member);

			//대쉬보드로 보내야한다
			String page ="/dashboard.jsp";
			response.sendRedirect(path+page);
    	}
    	
    	// 2. 탈퇴
    	if(action.equals("/member/userWithdrawal")) {
    		MemberVO member = new MemberVO();
    		HttpSession session = request.getSession();
    		MemberController control = new MemberController();
    		member = (MemberVO)session.getAttribute("member");
    		control.deleteUserFromUserMail(member.getUserMail());
    		session.invalidate();
    		response.sendRedirect(path+"/index.html");
    	}
    	

    	// 3. 유저 정보 검색
    	if (action.equals("/admin/userInfo")) {
    		HttpSession session = request.getSession();
    		MemberController control = new MemberController();
    		String userMail = request.getParameter("userMail");
    		MemberVO member = new MemberVO();
    		member = (MemberVO)control.searchUserFromUserMail(userMail);
    		session.setAttribute("member", member);
			response.sendRedirect(path+"/member/userUpdateInfo");
		}


		//4. admin 페이지 조건 검색
		if(action.equals("/admin/adminInfo")) {
			System.out.println("adminInfo");
			MemberController control = new MemberController();
			List<MemberVO> memberList = new ArrayList<>();
			String userMail = request.getParameter("userMail");
			int userLevel = Integer.parseInt(request.getParameter("userLevel"));
			
			memberList= (List<MemberVO>)control.selectByLevelMail(userLevel,userMail);
			HttpSession session = request.getSession();
			session.setAttribute("memberList", memberList);
			response.sendRedirect(path+"/admin/adminRef.jsp");
		}
		
		//5 admin.jsp 업데이트
		if(action.equals("/admin/adminUpdate")) {
			System.out.println("-----------------------------------");
			HttpSession session = request.getSession();
			int userNo = Integer.parseInt(request.getParameter("userNo"));
			String userPass = request.getParameter("userPass");
			String userMail = request.getParameter("userMail");
			int userLevel = Integer.parseInt(request.getParameter("userLevel"));
			String gender = request.getParameter("gender");
			String sbirthday = request.getParameter("birthday");
			Date birthday=null;
			if(sbirthday != "") {
			birthday = DateUtil.stringToDate(sbirthday);
			}
			MemberVO member = new MemberVO(userNo,userPass,userMail,userLevel,gender,birthday);
			session.setAttribute("member", member);
			String page="/admin/adminUpdate.jsp";
			
			if(request.getParameter("setMemberLevel") != null) {

				int setMemberLevel =Integer.parseInt(request.getParameter("setMemberLevel"));
		
				MemberController control = new MemberController();
				System.out.println("setMemberLevel= "+request.getParameter("setMemberLevel"));
				control.updateByMemberNo(member, setMemberLevel);
	
				page="/admin/admin.jsp";
			}
			response.sendRedirect(path+page);
		}

    	if(action.equals("/member/userUpdateInfo")) {

				MemberController uControl = new MemberController();
				HttpSession session = request.getSession();
				String userMail = (String)session.getAttribute("userMail");
				MemberVO member = uControl.getUserInfo(userMail);
				session.setAttribute("member", member);
				System.out.println(member.toString());
    			response.sendRedirect(path+"/member/userUpdateInfo.jsp");
    	}
   
		if (action.equals("/member/userMailDuplication")) {
			System.out.println("/member/userMailDuplication");
			MemberController control = new MemberController();
			// ajax로 중복 확인
			String userMail = request.getParameter("userMail");
			System.out.println(userMail);
			result = control.registerCheck(userMail);
			System.out.println(result);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(result);
			// (하단 코드에 대한 역할은 signUp.js 에서 처리하도록 수정하였음. 하단 코드는 원본코드.)
			//response.getWriter().print(result==1?"사용할 수 없는 이메일입니다.":"사용할 수 있는 이메일입니다.");
			//return;
		}

    	// 1. 회원가입
		/**
		 * 변경사항 체크! (\/member\/signUp)+(\/member\/userMailDuplication)
		 *
		 * 1. 코드 정리 2. jquery ajax 연동 완료 => getUserAuth 로 이동 로직 삭제 3. 회원가입 후 index.jsp
		 * (하단 주석 참고) 4. MemberService의 signUp 메서드로 직접 가입(signUp) 메서드 호출이 아닌
		 * MemberController 를 경유하여 이동하는 로직으로 변경 (진주 님과의 상의 필요!) 5. 이 메소드의 위에 있는
		 * \/member\/userMailDuplication 메소드 주석 확인 6. sbirthday가 null이 아니고 ""가 아니어야만,
		 * Date 형변환 하는 로직 추가 (java.text.ParseException 예방) 7. signUp.html 대부분 조절했습니다.
		 * 자세한 것은 기존에 작성하신 코드를 확인해주시고, 구현 후 테스트를 시도했으나, signUp.html 의 form 태그에서의
		 * onsubmit attr이 제대로 작동이 되지 않습니다. (onsubmit="return registerCheckFunction();"
		 * ... 이 부분 체크 부탁드립니다. -> signUp.js 참고)
		 *
		 * @Date 2018.12.15
		 * @author wgl
		 */
		if (action.equals("/member/signUp")) {
			MemberController control = new MemberController();
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
			Date birthday = null;
			if (sbirthday != null && !sbirthday.equals("")) {
				birthday = DateUtil.stringToDate(sbirthday);
			}
			MemberVO member = new MemberVO(userMail, userPass, gender, birthday);
			System.out.println("/member/signUp > \n" + member);
			// DB에 회원 등록
			control.signUp(member);

			response.sendRedirect(path + "/index.html");
		}

		// 1. 회원 로그인 (입력 후, 로그인 인증)
		if (action.equals("/member/signIn")) {
			// 입력된 id, pass 가져오기
			MemberController user = new MemberController();

			String userMail = request.getParameter("userMail");
			String userPass = request.getParameter("userPass");
			int authUser = user.getUserAuth(userMail, userPass);


			// 인증
			if (authUser == 1) { // 아이디 비밀번호가 맞다면 1이 나와야함.
				MemberController control = new MemberController();
				HttpSession session = request.getSession();
				session.setAttribute("userMail", userMail);
				MemberVO member = control.getUserInfo(userMail);
				session.setAttribute("member", member);
				int userLevel = member.getUserLevel();
				if(userLevel >= 4 ) {
				// welcome page 전송
				response.sendRedirect(path+"/dashboard.do");
				}
				else {
				response.sendRedirect(path+"/admin/admin.jsp");
				}

			}else {
				//일치하지 않을 경우 경고창을 띄우고 로그인화면으로 보냄
				//크롬에서 지원하지 않음.
			/*	PrintWriter out = response.getWriter();
				response.setContentType("text/html; charset=utf-8");
	
				out.println("<script>alert('계정이 등록 되었습니다')</script>");
	
				out.flush();*/
				response.sendRedirect(path+"/member/signIn2.html");
			}
		}


		// 관리자 등록
		if (action.equals("/member/adminInsert")) {

			MemberController control = new MemberController();
			/*String suserNo = request.getParameter("userNo");
			int userNo = Integer.parseInt(suserNo); 자동입력*/
			String userMail = request.getParameter("userMail");
			String userPass = request.getParameter("userPass");
			String suserLevel = request.getParameter("userLevel");
			int userLevel = Integer.parseInt(suserLevel);
			String gender = request.getParameter("gender");
			String sbirthday = request.getParameter("birthday");
			Date birthday = null;
			if (sbirthday != null && !sbirthday.equals("")) {
				birthday = DateUtil.stringToDate(sbirthday);
			}
			MemberVO member = new MemberVO(userMail, userPass, userLevel, gender, birthday);
			//DB에 회원 등록
			control.insertAdmin(member);
			String page="/admin/admin.jsp";
			//회원가입 후 인덱스로 이동
			//(이 주석은 확인 후 삭제) 추후에 index.jsp 로 넘어가거나 dashboard 화면으로 넘어갈지 결정해야함.
			response.sendRedirect(path+page);
		}
	}
}
