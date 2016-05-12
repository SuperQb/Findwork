package com.cn.daoi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cn.dao.AcconDao;
import com.cn.dao.UserDao;
import com.cn.dao.WorkDao;
import com.cn.entities.Accon;
import com.cn.entities.Person;
import com.cn.entities.User;
import com.cn.entities.Work;
import com.cn.utils.NetConnection;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class WorkDaoi implements WorkDao{

	@Override
	public List<Work> queryAll() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from work";
	    try {
	    	conn = NetConnection.getConnection();
			st = (Statement) conn.createStatement();
			rs = st.executeQuery(sql);
			
			List<Work> works = new ArrayList<>();
			while(rs.next()){
				//int id = rs.getInt("id");
				String position = rs.getString("position");
				String pay = rs.getString("pay");
				String sex = rs.getString("sex");
				String location = rs.getString("location");
				String intrduce = rs.getString("intrduce");
				String content = rs.getString("content");
				String requid = rs.getString("requid");
				String callphone = rs.getString("callphone");
				String phone = rs.getString("phone");
				String type = rs.getString("type");
				String linkman=rs.getString("linkman");
				works.add(new Work( position,  pay,  sex,  location,  intrduce,  content,requid,  callphone,  phone,  type,linkman));
			}
			return works;
			
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
	public int addWork(Work work) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "insert into work values(null,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			conn = NetConnection.getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			
			preparedStatement.setString(1, work.getPosition());
			preparedStatement.setString(2, work.getPay());
			preparedStatement.setString(3, work.getSex());
			preparedStatement.setString(4, work.getLocation());
			preparedStatement.setString(5, work.getIntrduce());
			preparedStatement.setString(6, work.getContent());
			preparedStatement.setString(7, work.getRequid());
			preparedStatement.setString(8, work.getCallphone());
			preparedStatement.setString(9, work.getPhone());
			preparedStatement.setString(10, work.getType());
			
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

}
