package com.qst.onlineVote.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qst.onlineVote.dao.UserDao;



/**
 * �ж��û��Ƿ���ע��
 *
 */

public class AjaxRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao=new UserDao();

        String name= request.getParameter("user_name");
        System.out.println("LRG��"+name);
        try {
            int register = userDao.isRegister(name);
            if(register==1){
                System.out.println("�û����ѱ�ע��");
                response.getWriter().print("�û����ѱ�ע��");
            }else {
                response.getWriter().print("�û�������ʹ��");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}