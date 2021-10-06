package com.qst.onlineVote.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.qst.onlineVote.pojo.Listing;





public class ArticleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao userDao=new UserDao();
		ListDao listDao=new ListDao();
		request.setCharacterEncoding("utf-8");

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String flag = request.getParameter("flag");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		String hi=request.getParameter("hi");
		System.out.println(hi);
		if(hi!=null&&hi.equals("1")){
			//���hi����1���б�����ʾΪά��״̬
			request.setAttribute("del", "d");
		}


		int userid = userDao.findUserId(username);
		String del=request.getParameter("del");
		if(del!=null&&del.equals("d")){
			
			request.setAttribute("del", del);
		}

		//ҳ��
		int goPage=1;
		int count= listDao.artlcleCount();//��ȡ���ܼ�¼��ͶƱ��������
		int pageCount;//��ҳ��
		if (count%5!=0){
			pageCount = count/5+1;}
		else{
			pageCount = count/5;
		}

		String pagestr=request.getParameter("goPage");

		if(pagestr!=null&&!pagestr.equals("")){
			goPage=Integer.parseInt(pagestr);
		}
		
		if(goPage==0){
			goPage=1;
		}
		if(goPage>pageCount){
			goPage=pageCount;
		}
		request.setAttribute("goPage", goPage);
		
		
		if (flag == null || flag.equals("")) {
			List<Listing> list = new ArrayList<Listing>();
			list = listDao.viewList(null,(goPage-1)*5,5);
			for (Listing listing : list) {
				int titleid = listDao.findTitleId(listing.getTitle());
				boolean isvote = userDao.userIfVote(titleid, userid);
				boolean isEnd=listDao.timeIfEnd(titleid);
				listing.setIsEnd(isEnd);
				listing.setIsVote(isvote);

			}
			request.setAttribute("list", list);

			request.getRequestDispatcher("admin/index_files/tpList.jsp").forward(request, response);
		} else if (flag.equals("search")) {
			//������ģ����ѯ
			
			String title = request.getParameter("search");
			List<Listing> list = new ArrayList<Listing>();
			list = listDao.viewList(title,(goPage-1)*5,5);
			for (Listing listing : list) {
				int titleid = listDao.findTitleId(listing.getTitle());
				boolean isvote = userDao.userIfVote(titleid, userid);
				listing.setIsVote(isvote);
			}

			if (list.size() == 0) {
				//���û��������
				request.setAttribute("flag", 1);
				request.getRequestDispatcher("admin/index_files/tpList.jsp").forward(request, response);
			} else {
				request.setAttribute("list", list);
				request.getRequestDispatcher("admin/index_files/tpList.jsp").forward(request, response);
			}

		} else if (flag.equals("add")) {
			//����ͶƱ��
			String title = request.getParameter("title");
			boolean is=listDao.isReleaseVote(title);
			if(is){
				response.getWriter().print(
						"<script language='JavaScript'>alert('��ͶƱ�ѱ����������ʧ��');window.location.href='admin/addNewtp_files/addNewtp.jsp';</script>");
			}
			else{
				String type = request.getParameter("type");
				String endTimeStr=request.getParameter("end_time");

				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				format.setLenient(false);

				Timestamp ts = null;
				try {
					ts = new Timestamp(format.parse(endTimeStr).getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}


				int tp;//��ǣ���ѡ��0  ��ѡ��1
				if (type.equals("dan")) {
					tp = 0;
				} else {
					tp = 1;
				}
				//���ͶƱ�����Ϣ
				int id = listDao.addTitle(title,ts,tp);

				String[] option = request.getParameterValues("option");

				//���ѡ������Ϣ
				for (String string : option) {
					listDao.addOption(string, id);
				}
				response.getWriter().print(
						"<script language='JavaScript'>alert('��ӳɹ�');window.location.href='admin/index_files/tpList.jsp';</script>");
			}
			
		}

	}
}