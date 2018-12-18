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
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter("/*")
public class SessionFilter implements Filter {

	private List<String> whiteList;

	// 이미지 파일 처리를 위한 변수
	private List<String> staticResourceList;

	public SessionFilter() {
		System.out.println("SessionFilter 생성자");

		// whiteList : session 연결 X 페이지들 모음
		whiteList = new ArrayList<String>(1);
		// "/member/signIn" 인지 "/member/signIn.do"인지 체크!!
		whiteList.add("/Lively/");
		whiteList.add("/Lively/index.html");
		whiteList.add("/Lively/member/signIn.html");
		whiteList.add("/Lively/member/signIn2.html");
		whiteList.add("/Lively/member/signUp.html");
		whiteList.add("/Lively/member/signIn.do");
		whiteList.add("/Lively/member/signIn2.do");
		whiteList.add("/Lively/member/signUp.do");

		// 다른 폴더("/resource/")(경로)에 있는 파일을 갖다쓸 경우 ex.이미지 파일
		staticResourceList = new ArrayList<String>();
		staticResourceList.add("/resources/");
	}

	public void destroy() {
		System.out.println("SessionFilter 소멸");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("SessionFilter doFilter");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		String uri = req.getRequestURI();
		System.out.println(uri);
		// !whiteList (session O 페이지 라면) ex) dashboard.html, 단어장, detail.html
		if (!whiteList.contains(uri)) {
			System.out.println("!whiteList-> " + uri);

			// 리소스 파일 접근
			boolean isURIResourceFile = false;

			for (String staticResource : staticResourceList) {
				if (uri.startsWith(staticResource)) {
					isURIResourceFile = true;
					break;
				}

			}

			if (isURIResourceFile == true) {
				// 세션 갖고 오기
				session = req.getSession();

				MemberVO member = (MemberVO) session.getAttribute("member");
				// session에 (MemberVO)member가 없다면 - index.html로 보내기
				if (member == null) {
					System.out.println("!whiteList&& member=null");

					if (uri.startsWith("/Lively/index")) {
						System.out.println("index 들어 왔어");
						return;
					} else {
						HttpServletResponse res = (HttpServletResponse) response;
						res.sendRedirect("/Lively/index.html");
						System.out.println("세션에 member 정보가 없어, 강제로 index.html 이동");
						return;
					}
				}
				// session에 (MemberVO)member가 있고 && dashboard라면
				else  {
					System.out.println("!whiteList&& member!=null");
					if (uri.startsWith("/Lively/dashboard")) {
					System.out.println("dashboard 들어 왔어");
					return;
					}
				}

				// session에 (MemberVO)member가 있고 && dashboard가 아니면 - 기존 페이지 유지
			}

			else {// whiteList(session X 페이지) 리스트라면
				System.out.println("whiteList->"+uri);
				MemberVO member = (MemberVO) session.getAttribute("member");
				// session에 (MemberVO)member 있다면 - dashboard.html로 보내기

				if (uri.startsWith("/Lively/index")) {
					return;
				} else if (member != null) {

					if (uri.startsWith("/Lively/dashboard")) {
						return;
					} else {
						HttpServletResponse res = (HttpServletResponse) response;
						res.sendRedirect("/Lively/dashboard.html");
						System.out.println("세션에 member 정보가 있어, 강제로 dashboard.html 이동");
					}
					return;
				}
				// session에 (MemberVO) 있고 + index.html이라면, dashboard.html로 보내기

				// session에 (MemberVO) 없다면 - 기존 페이지 유지
			} // whiteList리스트가 아니라면(session 0 member 0면) ex) dashboard.html 끝
		
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// System.out.println("SessionFilter init");
	}

}
