package com.qst.onlineVote.servlet;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qst.onlineVote.dao.UserDao;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 判断用户是否已注册
 *
 */
@WebServlet("/AjaxRegisterServlet")
public class AjaxRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
    	UserDao userDao=new UserDao();

        String name= request.getParameter("user_name");
        System.out.println("LRG："+name);
        try {
            boolean register = userDao.isRegister(name);
            if(register){
                System.out.println("用户名已被注册");
                response.getWriter().print("用户名已被注册");
            }else {
                response.getWriter().print("用户名可以使用");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}