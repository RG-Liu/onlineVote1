package com.qst.onlineVote.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qst.onlineVote.dao.ListDao;





public class ChangeSuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListDao listDao=new ListDao();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		String title = request.getParameter("title");
		int titleid=listDao.findTitleId(title);
		//ɾ��ͶƱ�����Ϣ
		listDao.delVote(titleid);
		//ɾ��ѡ������Ϣ
		listDao.delOption(titleid);
		//ɾ����������Ϣ��
		listDao.delArticle(titleid);
		
		String type=request.getParameter("type");
		String endTimeStr=request.getParameter("end_time");
		int tp;
		if (type.equals("dan")) {
			tp = 0;
		} else {
			tp = 1;
		}

		//
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);

		Timestamp ts = null;
		try {
			ts = new Timestamp(format.parse(endTimeStr).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}



		//���ͶƱ�����Ϣ��

		int id = listDao.addTitle(title,ts,tp);

		String[] option = request.getParameterValues("option");
		//���ѡ������Ϣ��
		for (String string : option) {
			listDao.addOption(string, id);
		}
		response.getWriter().print("<script language='JavaScript'>alert('�޸ĳɹ�');window.location.href='admin/index_files/tpList.jsp';</script>");
	}

}
