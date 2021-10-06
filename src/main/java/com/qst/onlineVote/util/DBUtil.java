package com.qst.onlineVote.util;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	static Properties pros = new Properties();
	static String driver;
	static String url;
	static String username;
	static String password;
	static Connection connection=null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;

	static {
		try {
			pros.load(DBUtil.class.getClassLoader().getResourceAsStream(
					"db.properties"));
			driver = pros.getProperty("driver");
			url = pros.getProperty("url");
			username = pros.getProperty("username");
			password = pros.getProperty("password");
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// 加载驱动
			Class.forName(driver);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获取连接对象
	public static Connection getConn() {
		Connection connection = null;
		try {

			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	// 关闭数据库连接，释放资源
	public static void close() {
	
		 try {
	            if(connection!=null){
	                connection.close();
	            }
	            if(ps!=null){
	                ps.close();
	            }
	            if(rs!=null){
	                rs.close();
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	    }

	public static void main(String[] args) {
		Connection con = DBUtil.getConn();
		System.out.println(con);

	}

}
