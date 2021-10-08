package com.qst.onlineVote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qst.onlineVote.pojo.User;
import com.qst.onlineVote.util.DBUtil;



public class UserDao{
    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    //查询全部用户信息
    public List<User> getAllUser() throws SQLException{
        connection= DBUtil.getConn();
        List<User> list=new ArrayList<User>();
        String sql="select * from user";
        ps=connection.prepareStatement(sql);
        rs=ps.executeQuery();

        while(rs.next()){
            int id=rs.getInt("id");
            String username=rs.getString("username");
            int status=rs.getInt("status");
            User user=new User();
            user.setId(id);
            user.setUsername(username);
            user.setStatus(status);
            list.add(user);
        }
        DBUtil.close();
        return list;

    }


    /**
    *用户登录
     * */
    public int UserLogin(User user) throws SQLException{
        connection= DBUtil.getConn();
        int id=0;
        String sql="select * from user where username=? and password=? /*and status=1*/";
        ps=connection.prepareStatement(sql);
        ps.setString(1,user.getUsername());
        ps.setString(2,user.getPassword());
        rs=ps.executeQuery();
        if(rs.next()){
            id=rs.getInt("id");
            return id;
        }

        DBUtil.close();
        return id;
    }



    /**
     *用户注册
     */
    public int register(String username,String password) throws SQLException{
        connection=DBUtil.getConn();
        String sql="insert into user(username,password) values(?,?)";
        ps=connection.prepareStatement(sql);
        ps.setString(1,username);
        ps.setString(2,password);
        int i = ps.executeUpdate();

        return i;
    }

    /**
     * 判断用户是否已注册
     */
    public int isRegister(String name) throws SQLException {
        connection=DBUtil.getConn();
        String sql = "select * from user where username=?";
        ps=connection.prepareStatement(sql);
        ps.setString(1,name);
        rs=ps.executeQuery();
        if(rs.next()){
            return 1;
        }
        DBUtil.close();
        return 0;
    }

    /**
    *根据名字查找ID
     */
    public int findUserId(String name){
        int id=-1;
        connection=DBUtil.getConn();
        try {
        String sql="select id from user where username=?";
        ps=connection.prepareStatement(sql);
        ps.setString(1,name);
        rs=ps.executeQuery();
        if(rs.next()){
            id=rs.getInt(1);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return id;
    }

    /**
    *根据投票标题ID和用户ID查找是否投票
    * */
    public boolean userIfVote(int titleid,int userid) {
        boolean flag=false;
        connection=DBUtil.getConn();
        String sql="select * from vote where articleid=? and voterid=?";
        try {
            ps=connection.prepareStatement(sql);
            ps.setInt(1,titleid);
            ps.setInt(2,userid);
            rs=ps.executeQuery();
            if(rs.next()){
                flag=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return flag;
    }
}




