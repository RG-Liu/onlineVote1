package com.qst.onlineVote.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qst.onlineVote.dao.UserDao;
import com.qst.onlineVote.pojo.User;





public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDao userDao=new UserDao();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            int id=userDao.UserLogin(new User(username,password));
            if(id!=0){
                HttpSession session=request.getSession();
                session.setAttribute("username",username);
                response.sendRedirect("admin/index.jsp");
            }else{
                request.setAttribute("msg","用户名或密码错误");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
