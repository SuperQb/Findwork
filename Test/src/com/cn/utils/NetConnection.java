package com.cn.utils;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class NetConnection {
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		
		Connection conn = null;
		try {
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/user", "root", "123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	

}
