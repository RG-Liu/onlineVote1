package com.qst.onlineVote.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qst.onlineVote.dao.ListDao;
import com.qst.onlineVote.dao.UserDao;



/**
 *如果已投票，则看看该用户的的投票信息
 */
@WebServlet("/whetherVoteServlet")
public class WhetherVoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public WhetherVoteServlet() {
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
		ListDao listDao=new ListDao();
		UserDao userDao=new UserDao();

    	String title=request.getParameter("title");
		String optionNum=request.getParameter("optionNum");
		String voteNum=request.getParameter("voteNum");
		System.out.println(voteNum+"nishirenhzengdema");
		
		int titleid=listDao.findTitleId(title);
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("username");
		int userid=userDao.findUserId(username);
		
		//获取该投票的所有选项
		List<String> slist=listDao.findTitleOption(titleid);
		
		//获取用户已选择的选项
		List<Integer> inte=listDao.findUserOption(titleid, userid);
		List<String>  sinte=new ArrayList<String>();
		for (int i : inte) {
			sinte.add(listDao.findOption(i));
		}
		
		/*for (String string : sinte) {
			System.out.println(string);
		}*/
		request.setAttribute("title", title);
		request.setAttribute("optionNum", optionNum);
		request.setAttribute("voteNum", voteNum);
		request.setAttribute("list", slist);
		request.setAttribute("sinte", sinte);
		request.getRequestDispatcher("admin/yesVote_files/yesVote.jsp").forward(request, response);
	}

}
