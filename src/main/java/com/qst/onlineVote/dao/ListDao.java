package com.qst.onlineVote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.qst.onlineVote.pojo.Listing;
import com.qst.onlineVote.util.DBUtil;



public class ListDao {
    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null,rs2=null;

    /**
     *显示投票列表
     * limit:
     * 第一个参数[取值为：（需要查看第几页-1）乘以第二个参数]是起始的位置，第二个参数是每页需要显示的条目数。
     *
     * 举例：商品表中有10条记录，现在需要进行分页显示，每页显示3条数据。现在需要查看第二页的数据。那么应该使用的sql语句是：
     * select * from product limit [(2-1)*3],3;
     * select * from product limit 3,3;
     * */
    public List<Listing> viewList(String title,int start,int end){
        List<Listing> viewList=new ArrayList<Listing>();
        Connection conn = DBUtil.getConn();
        if (title!=null){
            try {//模糊查询
                String sql="select title,COUNT(articleid) from article,optionss where article.id=articleid and title like '%"+title+"%' GROUP BY articleid";
                ps=conn.prepareStatement(sql);
               //ps.setString(1,title);
                rs=ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String sql="select title,COUNT(articleid) from article,optionss where article.id=articleid GROUP BY articleid limit ?,?";
            try {
                ps=conn.prepareStatement(sql);
                ps.setInt(1,start);
                ps.setInt(2,end);
                rs=ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String sql1="select title,COUNT(DISTINCT voterid) from article, vote where article.id=vote.articleid  GROUP BY articleid ORDER BY article.id  limit ?,?";
        try {
            ps=conn.prepareStatement(sql1);
            ps.setInt(1,start);
            ps.setInt(2,end);
            rs2=ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while(rs.next()){
                Listing list=new Listing();
                list.setTitle(rs.getString(1));
                list.setOptionNum(rs.getInt(2));
                viewList.add(list);
            }
            while (rs2.next()) {
                for (Listing lists : viewList) {
                    if(lists.getTitle().equals(rs2.getString(1))){
                        lists.setVoteNum(rs2.getInt(2));
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return viewList;
    }


    /**
    *统计一共有多少条投票
    */
    public int artlcleCount() {
        int count=0;
        connection=DBUtil.getConn();
        String sql="select count(id) from article";
        try {
        ps=connection.prepareStatement(sql);
        rs=ps.executeQuery();
        if(rs.next()){
            count=rs.getInt(1);
        }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        DBUtil.close();
        return count;
    }


    /**
     *根据投票标题查找ID
     */
    public int findTitleId(String title) {
        int r=0;
        connection=DBUtil.getConn();
        String sql="select id from article where title=?";


        try {
            ps=connection.prepareStatement(sql);
            ps.setString(1,title);
            rs=ps.executeQuery();
            if(rs.next()){
                r=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return r;
    }


    /**
    *新增投票,判断是否已经发表过
     */
    public boolean isReleaseVote(String title){
        boolean flag=false;
        connection=DBUtil.getConn();
        String sql="select id from article where title=?";
        try {
            ps=connection.prepareStatement(sql);
            ps.setString(1,title);
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


    /**
     * 添加投票的标题、结束时间、和多选状态,插入表中并根据自动增长获取它的ID
     */
    public int addTitle(String title,Timestamp endTime,int type) {
        int r=0;//id
        connection=DBUtil.getConn();
        String sql="insert into article(title,type,endtime) value(?,?,?)";
        try {
            ps=connection.prepareStatement(sql);
            ps.setString(1,title);
            ps.setInt(2,type);
            ps.setTimestamp(3,endTime);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql="select id from article where title=?";
        try {
            ps=connection.prepareStatement(sql);
            ps.setString(1,title);
            rs=ps.executeQuery();
            if(rs.next()){
                r=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return r;
    }


    /**
     * 添加投票信息选项
     */
    public void addOption(String value,int id){
        connection=DBUtil.getConn();
        String sql="insert into optionss(optionvalue,articleid) value(?,?)";
        try {
            ps=connection.prepareStatement(sql);
            ps.setString(1,value);
            ps.setInt(2,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close();
    }


    /**
     *根据投票标题ID查找对应选项内容
     */
    public List<String> findTitleOption(int id){
        List<String> optionList=new ArrayList<String>();
        connection= DBUtil.getConn();
        String sql="select optionvalue from optionss where articleid=?";
        try {
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                optionList.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return optionList;
    }


    /**
     *统计每个选项的票数
     */
    public int  optionNum(String title) {
        int sum=-1;
        connection=DBUtil.getConn();
        String sql="select count(optionid)  from  optionss,vote where optionid=optionss.id and optionvalue=?";
        try {
            ps=connection.prepareStatement(sql);
            ps.setString(1,title);
            rs=ps.executeQuery();
            if(rs.next()){
                sum=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return sum;
    }


    /**
     *计算该投票 的总票数
     */
    public int titleCount(int titleid){
        int sum=0;
        connection=DBUtil.getConn();
        String sql="select COUNT(articleid) from vote where articleid=?";
        try {
            ps=connection.prepareStatement(sql);
            ps.setInt(1,titleid);
            rs=ps.executeQuery();
            if(rs.next()){
                sum=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return sum;
    }


    /**
     *查看用户投票对应的选项ID
     */
    public List<Integer> findUserOption(int titleid,int userid) {
        List<Integer> inte=new ArrayList<Integer>();
        connection=DBUtil.getConn();
        String sql="select optionid  from vote where articleid=? and voterid=?";

        try {
            ps=connection.prepareStatement(sql);
            ps.setInt(1,titleid);
            ps.setInt(2,userid);
            rs=ps.executeQuery();
            while(rs.next()){
                inte.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return inte;
    }


    /**
     *根据选项ID查找 对应选项
     */
    public String findOption(int optionid){
        String option = null;
        List<Integer> inte=new ArrayList<Integer>();
        connection=DBUtil.getConn();
        String sql="select optionvalue from optionss where id=?";


        try {
            ps=connection.prepareStatement(sql);
            ps.setInt(1,optionid);
            rs=ps.executeQuery();
            if(rs.next()){
                option=rs.getString(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        DBUtil.close();
        return option;

    }


    /**
     *查看该投票的类型，单选或多选
     */
    public int  findTitleType(int id) {
        List<String> optionList=new ArrayList<String>();
        int type=-1;
        connection=DBUtil.getConn();
        String sql="select type from article where id=?";

        try {
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            if(rs.next()){
                type=rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return type;
    }


    /**
     *查找对应选项的ID
     */
    public int findOptionId(String option){
        int optionid=-1;
        connection=DBUtil.getConn();
        String sql="select id from optionss where optionvalue=?";
        try {
            ps=connection.prepareStatement(sql);
            ps.setString(1,option);
            rs=ps.executeQuery();
            if(rs.next()){
                optionid=rs.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close();
        return optionid;
    }


    /**
     *添加投票
     */
    public void addvote(int titleid,int optionid,int userid){
        connection = DBUtil.getConn();
        String  sql="insert into vote(articleid,optionid,voterid) value(?,?,?)";
        try {
            ps=connection.prepareStatement(sql);
            ps.setInt(1,titleid);
            ps.setInt(2,optionid);
            ps.setInt(3,userid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     *删除该投票，用户投的票
     */
    public void delVote(int id){
        connection=DBUtil.getConn();
        String sql="delete from vote where articleid=?";
        try {
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close();
    }


    /**
     *删除该投票对应的选项
     */
    public void delOption(int id){
        connection=DBUtil.getConn();
        String sql="delete from optionss  where articleid=?";
        try {
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close();
    }


    /**
     *删除投票
     */
    public void delArticle(int id){
        connection=DBUtil.getConn();
        String sql="delete from article where id=?";
        try {
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close();
    }


    /**
    *判断投票是否已经结束
    */
    public boolean timeIfEnd(int titleid){
        connection=DBUtil.getConn();
        Timestamp endVotetiem=null;
        boolean flag=false;
        String sql="select endtime from article where id=?";
        try {
            ps=connection.prepareStatement(sql);
            ps.setInt(1,titleid);
            rs=ps.executeQuery();
            if(rs.next()){
                endVotetiem=rs.getTimestamp("endtime");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        Timestamp d = new Timestamp(System.currentTimeMillis());
        if (endVotetiem.before(d)){
            flag=true;
        }
        return  flag;
    }
}
