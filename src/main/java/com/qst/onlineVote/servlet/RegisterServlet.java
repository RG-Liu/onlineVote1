package com.qst.onlineVote.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qst.onlineVote.dao.UserDao;





public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao userDao=new UserDao();
        int flag = 0;
        try {
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
