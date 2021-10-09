package com.qst.onlineVote.servlet;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import com.qst.onlineVote.dao.UserDao;
import com.qst.onlineVote.pojo.User;



@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
        UserDao userDao=new UserDao();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code=request.getParameter("vcode");
        HttpSession se=request.getSession();
       String vcode=(String)se.getAttribute("vcode");
       
        try {
        	 //md5加密
            password=DigestUtils.md5Hex(password);

            int id=userDao.UserLogin(new User(username,password));
            if(id!=0&&code.equalsIgnoreCase(vcode)){
                HttpSession session=request.getSession();
                session.setAttribute("username",username);
                response.sendRedirect("admin/index.jsp");
            }else{
            request.setAttribute("msg","用户名或密码错误");
                request.getRequestDispatcher("LoginPage.jsp").forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
       

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
