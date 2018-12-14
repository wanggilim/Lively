package com.quadcore.lively.filter;

import java.io.IOException;
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

    /**
     * Default constructor. 
     */
    public SessionCheckFilter() {
    	System.out.println("SessionCheckFilter 생성자");
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
		System.out.println("SessionCheckFilter init");
	}

}
