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

@WebFilter("/*")
public class SessionCheckFilter implements Filter {

	private List<String> whiteList;

	// 이미지 파일 처리를 위한 변수
	private List<String> staticResourceList;

	public SessionCheckFilter() {
		System.out.println("SessionCheckFilter 생성자");

		// whiteList : session 연결 X 페이지들 모음
		whiteList = new ArrayList<String>(1);
		// "/member/signIn" 인지 "/member/signIn.do"인지 체크!!
		whiteList.add("/Lively/index.html");
		whiteList.add("/Lively/member/signIn.html");
		whiteList.add("/Lively/member/signIn2.html");
		whiteList.add("/Lively/member/signUp.html");
		whiteList.add("/Lively/member/signIn.do");
		whiteList.add("/Lively/member/signIn2.do");
		whiteList.add("/Lively/member/signUp.do");

		// 다른 폴더("/resource/")(경로)에 있는 파일을 갖다쓸 경우 ex.이미지 파일 (현재 : 미사용)
		staticResourceList = new ArrayList<String>();
		staticResourceList.add("/resource/");
	}

	public void destroy() {
		System.out.println("SessionCheckFilter 소멸");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("SessionCheckFilter doFilter");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		String uri = req.getRequestURI();
		//whiteList(session 미연결 페이지) 리스트가 아니라면  ex) dashboard.html
		if (!whiteList.contains(uri)) {
			//세션 갖고 오기
			session = req.getSession();

			MemberVO member = (MemberVO) session.getAttribute("member");
			//session에 (MemberVO)member가 없다면 - index.html로 보내기
			if (member == null) {
				HttpServletResponse res = (HttpServletResponse) response;
				res.sendRedirect("/Lively/index.html");
				return;
			}//session에 (MemberVO)member가 있다면 - 기존 페이지 유지
		}

		else {//whiteList(session 미연결 페이지) 리스트라면
			MemberVO member = (MemberVO) session.getAttribute("member");
			//session에 (MemberVO) 있다면 - dashboard.html로 보내기
			if (member != null) {
//				session.invalidate();
				HttpServletResponse res = (HttpServletResponse) response;
				res.sendRedirect("/Lively/dashboard.html");
				return;
			}//session에 (MemberVO) 없다면 - 기존 페이지 유지
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// System.out.println("SessionCheckFilter init");
	}

}//class SessionCheckFilter 끝
