package com.cn.daoi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cn.dao.UserDao;
import com.cn.entities.User;
import com.cn.utils.NetConnection;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class UserDaoi implements UserDao{

	@Override
	public List<User> queryAll() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from user";
	    try {
	    	conn = NetConnection.getConnection();
			st = (Statement) conn.createStatement();
			rs = st.executeQuery(sql);
			
			List<User> users = new ArrayList<>();
			while(rs.next()){
				int userId = rs.getInt("userid");
				String username = rs.getString("username");
				
				users.add(new User(userId, username));
			}
			return users;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}

}
