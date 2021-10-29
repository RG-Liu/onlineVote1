package com.qst.onlineVote.filter;

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

public class SessionCheckFilter implements Filter {

	public SessionCheckFilter() {

	}

	// 销毁时调用
	public void destroy() {
	}

	// 过滤方法 主要是对request和response进行一些处理，然后交给下一个过滤器或Servlet处理
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException { // 执行过滤
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String path = req.getRequestURI();
		//System.out.println(path);
		if (session.getAttribute("username") != null) {
			chain.doFilter(request, response);// 交给下一个过滤器或servlet处理
		} else {
			boolean flag = path.equals("/onlineVote/LoginPage.jsp")
					|| path.equals("/onlineVote/code")
					|| path.equals("/onlineVote/admin/reg.jsp")
					|| path.equals("/onlineVote/admin/regS.jsp")
					|| path.equals("/onlineVote/AjaxRegisterServlet")
					|| path.equals("/onlineVote/registerServlet")
					|| path.equals("/onlineVote/login_files/login.css")
					|| path.equals("/onlineVote/admin/reg_files/reg.css")
					|| path.equals("/onlineVote/admin/reg_files/logo.gif")
					|| path.equals("/onlineVote/admin/reg_files/jquery-1.x.js.%E4%B8%8B%E8%BD%BD")
					|| path.equals("/onlineVote/registerServlet")
					|| path.equals("/onlineVote/admin/regS.jsp")
					|| path.equals("/onlineVote/admin/regS_files/reg.css")
					|| path.equals("/onlineVote/admin/regS_files/logo.gif")
					|| path.equals("/onlineVote/LoginServlet");
			if (flag) {
				chain.doFilter(request, response);
			} else {
				response.getWriter()
						.print("<script language='JavaScript'>alert('请先登录!');window.location.href='/onlineVote/LoginPage.jsp';</script>");
			}			
		}
		//

	}

	// 初始化方法，对象创建后init方法开始执行，且只执行一次；主要功能初始化资源的
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
