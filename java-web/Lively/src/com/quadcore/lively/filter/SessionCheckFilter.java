package com.quadcore.lively.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quadcore.lively.model.MemberVO;

/**
 * Servlet Filter implementation class SessionCheckFilter
 */
@WebFilter("/*")
public class SessionCheckFilter implements Filter {

	private List<String> whiteList;
	private String[][] whiteAddresses = { //화이트 리스트에 넣을 주소 목록 (jinju->wgl)
		// index
		{"/index.jsp"},
		
		// member
		{"/member/signIn.do",
    		"/member/signIn.html",
    		"/member/signIn2.do",
    		"/member/signIn2.html",
    		"/member/signUp.do",
    		"/member/signUp.html",
    		"/member/signOut.do",
    		"/member/signOut.html"}
	};
	
	
	// 이미지 파일 처리를 위한 변수
	private List<String> staticResourceList;
	
    /**
     * Default constructor. 
     */
    public SessionCheckFilter() {
    	System.out.println("SessionCheckFilter 생성자");
    	// whiteList : session 연결 X 페이지들 모음
    	whiteList = new ArrayList<String>(1);
    	
    	// type (index, member, ...)
    	for (int i = 0; i < whiteAddresses.length; i++) {
    		
    		// address of a type
    		for (int j = 0; j < whiteAddresses[i].length; j++) {
				whiteList.add(whiteAddresses[i][j]);
			}
		}
    	
		// 다른 폴더("/resource/")(경로)에 있는 파일을 갖다쓸 경우 ex.이미지 파일 (현재 : 미사용)
		staticResourceList = new ArrayList<String>();
		staticResourceList.add("/resource/");
    }
    
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("SessionCheckFilter 소멸");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("SessionCheckFilter doFilter");
		
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getServletContext().getContextPath();
		String uri = req.getRequestURI();
		System.out.println(path);
		System.out.println(uri);
 
		// Session Filter 확인 테스트
		System.out.println("sessionFilter checking : " + uri.substring(path.length()));
		
		// whiteList(session 연결 X)가 아닐 때
		if (!whiteList.contains(uri.substring(path.length()))) {
 
			HttpSession session = req.getSession();
 
			//userMail::String 에서 member::MemberVO 타입으로 변경 (wgl)
			MemberVO member = (MemberVO) session.getAttribute("member");
 
			if (member == null) {
				HttpServletResponse res = (HttpServletResponse) response;
				res.sendRedirect(path+ "/index.jsp");
				return;
			}
 
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//System.out.println("SessionCheckFilter init");
	}

}
