package com.qst.onlineVote.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionCheckFilter implements Filter {

	public SessionCheckFilter() {

	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		if (session.getAttribute("username") != null) {
			chain.doFilter(request, response);
		} else {
			response.getWriter().print("<script language='JavaScript'>alert('请先登录');window.location.href='../LoginPage.jsp';</script>");
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
