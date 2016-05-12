package com.cn.daoi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cn.dao.AcconDao;
import com.cn.dao.UserDao;
import com.cn.entities.Accon;
import com.cn.entities.User;
import com.cn.utils.NetConnection;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class AcconDaoi implements AcconDao{

	@Override
	public List<Accon> queryAll() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from accon";
	    try {
	    	conn = NetConnection.getConnection();
			st = (Statement) conn.createStatement();
			rs = st.executeQuery(sql);
			
			List<Accon> accons = new ArrayList<>();
			while(rs.next()){
				String phone = rs.getString("phone");
				String key = rs.getString("key");
				accons.add(new Accon(phone, key));
			}
			return accons;
			
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
	@Override
	public int addAccon(Accon accon) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		//Statement st = null;
		//ResultSet rs = null;

		String sql = "insert into accon values(?,?)";

		try {
			
			conn = NetConnection.getConnection();
			//st = (Statement) conn.createStatement();
			//rs = st.executeQuery(sql);
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setString(1, accon.getPhone());
			preparedStatement.setString(2, accon.getKey());
			
			int reCode = preparedStatement.executeUpdate();
			
			return reCode;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			try {
				//rs.close();
				preparedStatement.close();
				//st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		return 0;
	}
	
	
	public int login(Accon accon) {
		
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		String sqllogin = "select * from"
				+ " accon "
				+ "where "
				+ "phone=? "
				+ "and "
				+ "keypass=?";

		try {
			
			conn = NetConnection.getConnection();
	
			preparedStatement = (PreparedStatement) conn.prepareStatement(sqllogin);
			preparedStatement.setString(1, accon.getPhone());
			preparedStatement.setString(2, accon.getKey());
			
			rs = preparedStatement.executeQuery();
			
			if(rs.first()){
//				
				//查找到
				return 1;
//				
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			try {
				//rs.close();
				preparedStatement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return 0;
		
	}
}
