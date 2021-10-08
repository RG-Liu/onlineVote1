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
import com.qst.onlineVote.pojo.Option;



/**
 *查看投票信息
 *
 */

@WebServlet("/lookVoteServlet")
public class LookVoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListDao listDao=new ListDao();
		request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
		//获取投票标题，选项数目，投票人数
		String title=request.getParameter("title");
		String optionNum=request.getParameter("optionNum");
		String voteNum=request.getParameter("voteNum");
		System.out.println("LRG:"+voteNum);

		//存储各个投票选项的集合
		List<Option> options=new ArrayList<Option>();

		//获得标题的ID
		int id=listDao.findTitleId(title);
		//根据投票标题ID，查找对应的选项列表。
		List<String> optionList=listDao.findTitleOption(id);
		for (String string : optionList) {
			Option op=new Option();
			op.setOption(string);
			//获取每个选项的票数
			int num=listDao.optionNum(string);
			op.setNum(num);
			options.add(op);
		}
		
		//获取该投票的所有票数
		int titleNum=listDao.titleCount(id);
		
		request.setAttribute("title", title);
		request.setAttribute("optionNum", optionNum);
		request.setAttribute("voteNum", voteNum);
		request.setAttribute("titleNum", titleNum);
		request.setAttribute("options", options);
		request.getRequestDispatcher("admin/cktp_files/cktp.jsp").forward(request, response);
		
	}

}
