package com.qst.onlineVote.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qst.onlineVote.dao.ListDao;





public class DelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListDao listDao=new ListDao();


		String title =request.getParameter("title");
		int titleid=listDao.findTitleId(title);
		//删除投票表的信息
		listDao.delVote(titleid);
		//删除选项表的信息
		listDao.delOption(titleid);
		//删除标题表的信息。
		listDao.delArticle(titleid);
		response.sendRedirect("admin/index_files/tpList.jsp");
		
	}

}
