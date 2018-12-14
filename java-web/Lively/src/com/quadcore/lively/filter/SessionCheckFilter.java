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

/**
 * Servlet Filter implementation class SessionCheckFilter
 */
@WebFilter("/*")
public class SessionCheckFilter implements Filter {

	private List<String> whiteList;
	
	// 이미지 파일 처리를 위한 변수
	private List<String> staticResourceList;
	
    /**
     * Default constructor. 
     */
    public SessionCheckFilter() {
    	System.out.println("SessionCheckFilter 생성자");
    	
    	// whiteList : session 연결 X 페이지들 모음
		whiteList = new ArrayList<String>(1);
		whiteList.add("/index.jsp");
		// "/member/signIn" 인지 "/member/signIn.do"인지 체크!!
		whiteList.add("/member/signIn.html");
		whiteList.add("/member/signIn2.html");
		whiteList.add("/member/signUp.do");
		whiteList.add("/member/signUp.html");
 
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
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//System.out.println("SessionCheckFilter init");
	}

}
