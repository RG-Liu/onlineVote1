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
     *��ʾͶƱ�б�
     * limit:
     * ��һ������[ȡֵΪ������Ҫ�鿴�ڼ�ҳ-1�����Եڶ�������]����ʼ��λ�ã��ڶ���������ÿҳ��Ҫ��ʾ����Ŀ����
     *
     * ��������Ʒ������10����¼��������Ҫ���з�ҳ��ʾ��ÿҳ��ʾ3�����ݡ�������Ҫ�鿴�ڶ�ҳ�����ݡ���ôӦ��ʹ�õ�sql����ǣ�
     * select * from product limit [(2-1)*3],3;
     * select * from product limit 3,3;
     * */
    public List<Listing> viewList(String title,int start,int end){
        List<Listing> viewList=new ArrayList<Listing>();
        Connection conn = DBUtil.getConn();
        if (title!=null){
            try {//ģ����ѯ
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
    *ͳ��һ���ж�����ͶƱ
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
     *����ͶƱ�������ID
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
    *����ͶƱ,�ж��Ƿ��Ѿ������
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
     * ���ͶƱ�ı��⡢����ʱ�䡢�Ͷ�ѡ״̬,������в������Զ�������ȡ����ID
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
     * ���ͶƱ��Ϣѡ��
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
     *����ͶƱ����ID���Ҷ�Ӧѡ������
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
     *ͳ��ÿ��ѡ���Ʊ��
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
     *�����ͶƱ ����Ʊ��
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
     *�鿴�û�ͶƱ��Ӧ��ѡ��ID
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
     *����ѡ��ID���� ��Ӧѡ��
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
     *�鿴��ͶƱ�����ͣ���ѡ���ѡ
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
     *���Ҷ�Ӧѡ���ID
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
     *���ͶƱ
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
     *ɾ����ͶƱ���û�Ͷ��Ʊ
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
     *ɾ����ͶƱ��Ӧ��ѡ��
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
     *ɾ��ͶƱ
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
    *�ж�ͶƱ�Ƿ��Ѿ�����
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
