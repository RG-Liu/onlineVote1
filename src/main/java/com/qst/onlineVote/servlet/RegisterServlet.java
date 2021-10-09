package com.qst.onlineVote.servlet;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.qst.onlineVote.dao.UserDao;
import com.qst.onlineVote.pojo.User;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao userDao=new UserDao();
        int flag = 0;
        try {
        	 //md5加密
            password=DigestUtils.md5Hex(password);

            flag = userDao.register(username,password);
            if (flag > 0) {
                response.sendRedirect("admin/regS.jsp");
            }else{
                response.sendRedirect("admin/reg.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
