package com.sdkj.util.context;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckLoginFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filter)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
//		String url = request.getRequestURL().toString();
//		url = url.substring(0, url.lastIndexOf("/"));
		// 判断用户是否超时
		if (!"login".equals(request.getParameter("method")))
			if (session.getAttribute("valid_user") == null || session.isNew()) {
				// response.sendRedirect(url+"/index.jsp");
				if(request.getHeader("x-requested-with") != null && 
						request.getHeader("x-requested-with")     
							.equalsIgnoreCase("XMLHttpRequest")) {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);     
				}else{
					request.getRequestDispatcher("/timeout.jsp").forward(
							request, response);
				}
			} else {
				filter.doFilter(servletRequest, servletResponse);
			}
		else
			filter.doFilter(servletRequest, servletResponse);
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
