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
//@WebServlet("*.do")
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
		
		// 1. 로그인 
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
    	// 1. 유저 계정 삭제
    	if (action.equals("/admin/userDelete")) {
    		MemberController control = new MemberController();
    		String suserNo = request.getParameter("userNo");
    		int userNo = Integer.parseInt(suserNo);
    		String userPass = request.getParameter("userPass");
    		control.deleteUserFromUserMail(userNo);
    		
		}
    	
    
    	// 2. 유저 정보 수정
    	if (action.equals("/admin/userUpdate")) {
			MemberController control = new MemberController();
			String userMail = request.getParameter("userMail");
    		control.updateUserFromUserMail(userMail);
		}
    	
    	// 3. 유저 정보 검색
    	if (action.equals("/admin/userInfo")) {
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
    	//硫붿씪 以묐났 �솗�씤
		System.out.println(result);
        if (action.equals("/member/userMailDuplication")) {
			MemberController control = new MemberController();
			//ajax濡� 以묐났 �솗�씤
			String userMail = request.getParameter("userMail");
			System.out.println(userMail);
			result= control.registerCheck(userMail);
			System.out.println(result);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(result);
			// (�븯�떒 肄붾뱶�뿉 ���븳 �뿭�븷�� signUp.js �뿉�꽌 泥섎━�븯�룄濡� �닔�젙�븯���쓬. �븯�떒 肄붾뱶�뒗 �썝蹂몄퐫�뱶.)
			//response.getWriter().print(result==1?"�궗�슜�븷 �닔 �뾾�뒗 �씠硫붿씪�엯�땲�떎.":"�궗�슜�븷 �닔 �엳�뒗 �씠硫붿씪�엯�땲�떎.");
			//return;
			
    	}
		
    			 
    	//1. �쉶�썝媛��엯
		/**
		 * 蹂�寃쎌궗�빆 泥댄겕! (\/member\/signUp)+(\/member\/userMailDuplication)
		 * 
		 * 1. 肄붾뱶 �젙由�
		 * 2. jquery ajax �뿰�룞 �셿猷� => getUserAuth 濡� �씠�룞 濡쒖쭅 �궘�젣
		 * 3. �쉶�썝媛��엯 �썑 index.jsp (�븯�떒 二쇱꽍 李멸퀬)
		 * 4. MemberService�쓽 signUp 硫붿꽌�뱶濡� 吏곸젒 媛��엯(signUp) 硫붿꽌�뱶 �샇異쒖씠 �븘�땶
		 * MemberController 瑜� 寃쎌쑀�븯�뿬 �씠�룞�븯�뒗 濡쒖쭅�쑝濡� 蹂�寃� (吏꾩＜ �떂怨쇱쓽 �긽�쓽 �븘�슂!)
		 * 5. �씠 硫붿냼�뱶�쓽 �쐞�뿉 �엳�뒗 \/member\/userMailDuplication 硫붿냼�뱶 二쇱꽍 �솗�씤
		 * 6. sbirthday媛� null�씠 �븘�땲怨� ""媛� �븘�땲�뼱�빞留�, Date �삎蹂��솚 �븯�뒗 濡쒖쭅 異붽� (java.text.ParseException �삁諛�)
		 * 7. signUp.html ��遺�遺� 議곗젅�뻽�뒿�땲�떎. �옄�꽭�븳 寃껋� 湲곗〈�뿉 �옉�꽦�븯�떊 肄붾뱶瑜� �솗�씤�빐二쇱떆怨�,
		 * 援ы쁽 �썑 �뀒�뒪�듃瑜� �떆�룄�뻽�쑝�굹, signUp.html �쓽 form �깭洹몄뿉�꽌�쓽 onsubmit attr�씠 �젣��濡� �옉�룞�씠 �릺吏� �븡�뒿�땲�떎.
		 * (onsubmit="return registerCheckFunction();" ... �씠 遺�遺� 泥댄겕 遺��긽�뱶由쎈땲�떎. -> signUp.js 李멸퀬)
		 * 
		 * @Date 2018.12.15
		 * @author wgl
		 */
    	if (action.equals("/member/signUp")) {
    	
    		System.out.println("媛��엯�븯濡� �솕�땲");
    		MemberController control = new MemberController();
    		/*String suserNo = request.getParameter("userNo");
    		int userNo = Integer.parseInt(suserNo); �옄�룞�엯�젰*/
    		String userMail = request.getParameter("userMail");
    		String userPass = request.getParameter("userPass");
    		/*String suserLevel = request.getParameter("userLevel");*/
    		/*int userLevel = Integer.parseInt(suserLevel); 愿�由ъ옄 �꽑�깮*/
    		String gender = request.getParameter("gender");
    		String sbirthday = request.getParameter("birthday");
    		Date birthday = null;
    		if (sbirthday != null && !sbirthday.equals("")) {
    			birthday = DateUtil.stringToDate(sbirthday);
			}
    		MemberVO member = new MemberVO(userMail, userPass, gender, birthday);
    		//DB�뿉 �쉶�썝 �벑濡�
    		control.signUp(member);
    		
    		//�쉶�썝媛��엯 �썑 �씤�뜳�뒪濡� �씠�룞
    		//(�씠 二쇱꽍�� �솗�씤 �썑 �궘�젣) 異뷀썑�뿉 index.jsp 濡� �꽆�뼱媛�嫄곕굹 dashboard �솕硫댁쑝濡� �꽆�뼱媛덉� 寃곗젙�빐�빞�븿.
    		response.sendRedirect(path+"/index.jsp");
		}
    	
    	// 1. �쉶�썝 濡쒓렇�씤 (�엯�젰 �썑, 濡쒓렇�씤 �씤利�)
    	if (action.equals("/member/signIn")) {
    		// �엯�젰�맂 id, pass 媛��졇�삤湲�
    		MemberController user = new MemberController();
    		
    		String userMail = request.getParameter("userMail");
    		String userPass = request.getParameter("userPass");
    		int authUser = 0;
    		
    		if (userMail != null) {
    			authUser = user.getUserAuth(userMail, userPass);
			}
    		
    		// �씤利�
    		if (authUser == 1) { // �븘�씠�뵒 鍮꾨�踰덊샇媛� 留욌떎硫� 1�씠 �굹���빞�븿.
    			MemberController uControl = new MemberController();
    			HttpSession session = request.getSession();
    			session.setAttribute("userMail", userMail);
    			MemberVO member = uControl.getUserLevel(userMail);
    			session.setAttribute("userLevel", member.getUserLevel());
    			
    			// welcome page �쟾�넚
    			response.sendRedirect(path+"/index.jsp");
			}else {
				//�씪移섑븯吏� �븡�쓣 寃쎌슦 寃쎄퀬李쎌쓣 �쓣�슦怨� 濡쒓렇�씤�솕硫댁쑝濡� 蹂대깂
				//�겕濡ъ뿉�꽌 吏��썝�븯吏� �븡�쓬.
			/*	PrintWriter out = response.getWriter();
				response.setContentType("text/html; charset=utf-8");

				out.println("<script>alert('怨꾩젙�씠 �벑濡� �릺�뿀�뒿�땲�떎')</script>");
				 
				out.flush();*/			
				response.sendRedirect(path+"/member/signIn.html");
			}
    		
    		
		}
    	
    	if (action.equals("/memeber/signIn")) {

    		// �엯�젰�맂 id, pass 媛��졇�삤湲�
    		MemberController user = new MemberController();
    		
    		String userMail = request.getParameter("userMail");
    		String userPass = request.getParameter("userPass");
    		int authUser = 0;

    		if (userMail != null) {
    			authUser = user.getUserAuth(userMail, userPass);
			}
    		
    		// �븘�씠�뵒媛� �뾾�쑝硫� 濡쒓렇�씤�쑝濡� �씠�룞
    		if (authUser == 0) { // �븘�씠�뵒 鍮꾨�踰덊샇媛� 留욌떎硫� 1�씠 �굹���빞�븿.
    			HttpSession session = request.getSession();
    			session.setAttribute("userMail", userMail);
    			
    			response.sendRedirect(path+"/member/signIn.html");
			}
    		
    		
		}
    	

    	
    	
  

}

}


