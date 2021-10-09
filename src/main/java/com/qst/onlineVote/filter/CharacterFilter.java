package com.qst.onlineVote.filter;


import javax.servlet.*;
import java.io.IOException;

public class CharacterFilter implements Filter {
	
	//初始化方法，对象创建后init方法开始执行，且只执行一次；主要功能初始化资源的
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) 
    		throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html; charset=UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    //容器删除该过滤器之前，会调用该方法，只调用一次
    public void destroy() {

    }
}
