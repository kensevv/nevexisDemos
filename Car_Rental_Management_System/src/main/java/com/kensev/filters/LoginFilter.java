package com.kensev.filters;

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

@WebFilter("/home.jsp")
public class LoginFilter implements Filter {
	
	private List<String> excludedUrls = new ArrayList<String>();
	
	public void init(FilterConfig fConfig) throws ServletException {
		excludedUrls.add("/login.jsp");
		excludedUrls.add("/register.jsp");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String path = httpRequest.getServletPath();
		
		if (httpRequest.getSession().getAttribute("account") == null && !excludedUrls.contains(path)) {
			httpResponse.sendRedirect("login.jsp");
		} else {
			chain.doFilter(request, response);
		}
	}
	
	public void destroy() {
	}
}
