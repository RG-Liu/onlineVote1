package com.qst.onlineVote.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qst.onlineVote.dao.ListDao;
import com.qst.onlineVote.dao.UserDao;





public class AttendVoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		ListDao listDao=new ListDao();
		UserDao userDao=new UserDao();
		String types=request.getParameter("type");
		if(types==null||types.equals("")){
			//参与投票
			String title=request.getParameter("title");
			String optionNum=request.getParameter("optionNum");
			String voteNum=request.getParameter("voteNum");
			int id=listDao.findTitleId(title);
			List<String> optionList=listDao.findTitleOption(id);
			int type=listDao.findTitleType(id);
			request.setAttribute("title", title);
			request.setAttribute("optionNum", optionNum);
			request.setAttribute("voteNum", voteNum);
			request.setAttribute("type", type);
			request.setAttribute("optionList", optionList);		
			request.getRequestDispatcher("admin/cytp_files/cytp.jsp").forward(request, response);
		}else{
			
			//添加新投票。
			HttpSession session=request.getSession();			
			String name=(String) session.getAttribute("username");
			int userId=userDao.findUserId(name);
			String title = request.getParameter("title");
			int titleId=listDao.findTitleId(title);
			String type=request.getParameter("type");
			if(type.equals("0")){
				String option=request.getParameter("radio");
				int optionid=listDao.findOptionId(option);
				listDao.addvote(titleId, optionid, userId);
			}else{
				String option[]=request.getParameterValues("chbox");
				for (String string : option) {
					System.out.println(string);
					int optionid=listDao.findOptionId(string);
					listDao.addvote(titleId, optionid, userId);
				}	
			}
			response.getWriter().print("<script language='JavaScript'>alert('投票成功');window.location.href='admin/index_files/tpList.jsp';</script>");
		}
		
	}

}
