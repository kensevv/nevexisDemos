package com.kensev.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

//OncePerRequestFilter

@WebFilter("/*")
public class AuthorizationFilter implements Filter {
	
	private static final List<String> excludedUrls = Arrays.asList("/home.jsp", "/login.jsp", "/register.jsp", "/AccessDenied.jsp", "/LoginServlet", "/RegisterServlet");
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String path = httpRequest.getServletPath();
		String dispatcherForwardPath = "../AccessDenied.jsp";
		if(httpRequest.getPathInfo() == null) {
			dispatcherForwardPath = "/AccessDenied.jsp";
		}
		
		if ( null == httpRequest.getSession().getAttribute("account") && !excludedUrls.contains(path)) {
			request.setAttribute("errorMessage", "Un-Authorized session. Please Log-in first!");
			RequestDispatcher dispatcher = request.getRequestDispatcher(dispatcherForwardPath);
			dispatcher.forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}


	@Override
	public void destroy() {
	}
}
