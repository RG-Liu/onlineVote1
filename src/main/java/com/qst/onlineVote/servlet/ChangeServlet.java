package com.qst.onlineVote.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qst.onlineVote.dao.ListDao;




@WebServlet("/changeServlet")
public class ChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
		ListDao listDao=new ListDao();

		String title=request.getParameter("title");
		int titleid=listDao.findTitleId(title);
		List<String> optionList=listDao.findTitleOption(titleid);
		List<String> yList=new ArrayList<String>();
		for (int i = 2; i < optionList.size(); i++) {
			yList.add(optionList.get(i));
		}
		request.setAttribute("title", title);
		request.setAttribute("list", optionList);
		request.setAttribute("ylist", yList);
		request.getRequestDispatcher("admin/change_files/change.jsp").forward(request, response);
	}

}
