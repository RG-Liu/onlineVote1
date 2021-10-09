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
	 //  销毁时调用
	public void destroy() {
	}
	
	 //过滤方法 主要是对request和response进行一些处理，然后交给下一个过滤器或Servlet处理
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException { //执行过滤
		request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
//		 String path=req.getRequestURI();	
//	 System.out.println(path);
//		 boolean flag= path.equals("/onlineVote/LoginPage.jsp")||path.equals("/onlineVote/code")
//			 ||path.equals("/onlineVote/admin/reg.jsp")||path.equals("/onlineVote/admin/regS.jsp")
//			  ||path.equals("/onlineVote/AjaxRegisterServlet")||path.equals("/onlineVote/registerServlet");
//
//		  if(flag){
//			  
//		   chain.doFilter(request, response);
// }	 else{
//	 Object obj=req.getSession().getAttribute("useruser");
//	 if(obj==null){
//		 response.getWriter().print("<script language='JavaScript'>alert('请先登录!');window.location.href='../LoginPage.jsp';</script>");
//		
//	 }else{
//		 chain.doFilter(request, response);
//	 }
//   }
		
		 	  if (session.getAttribute("username") != null) {
				chain.doFilter(request, response);//交给下一个过滤器或servlet处理
			} else {
				
				response.getWriter().print("<script language='JavaScript'>alert('请先登录!');window.location.href='../LoginPage.jsp';</script>");
			
			}
		  	

	}
	//初始化方法，对象创建后init方法开始执行，且只执行一次；主要功能初始化资源的
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
