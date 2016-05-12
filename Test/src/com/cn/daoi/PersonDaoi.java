package com.cn.daoi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cn.dao.AcconDao;
import com.cn.dao.PersonDao;
import com.cn.dao.UserDao;
import com.cn.entities.Accon;
import com.cn.entities.Person;
import com.cn.entities.User;
import com.cn.utils.NetConnection;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class PersonDaoi implements PersonDao{

	@Override
	public List<Person> queryAll() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from person";
	    try {
	    	conn = NetConnection.getConnection();
			st = (Statement) conn.createStatement();
			rs = st.executeQuery(sql);
			
			List<Person> persons = new ArrayList<>();
			while(rs.next()){
				String phone = rs.getString("phone");
				String name = rs.getString("name");
				String icon = rs.getString("icon");
				String age = rs.getString("age");
				String sex = rs.getString("sex");
				String registerPhone = rs.getString("registerPhone");
				persons.add(new Person( registerPhone,phone,name,icon,age,sex));
			}
			return persons;
			
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
	public int addPerson(Person person) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		//Statement st = null;
		//ResultSet rs = null;

		String sql = "insert into person values(?,?,?,?,?,?)";
		//String sql = "update person set phone=?,name=?,icon=?,age=?,sex=? where registerPhone=?";

		try {
			conn = NetConnection.getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			
			preparedStatement.setString(1, person.getPhone());
			preparedStatement.setString(2, person.getName());
			preparedStatement.setString(3, person.getIcon());
			preparedStatement.setString(4, person.getAge());
			preparedStatement.setString(5, person.getSex());
			preparedStatement.setString(6, person.getRegisterPhone());
			
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
	
	@Override
	public int updataPerson(Person person) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		//Statement st = null;
		//ResultSet rs = null;

		//String sql = "insert into person values(?,?,?,?,?,?)";
		String sql = "update person set phone=?,name=?,icon=?,age=?,sex=? where registerPhone=?";

		try {
			conn = NetConnection.getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			
			preparedStatement.setString(1, person.getPhone());
			preparedStatement.setString(2, person.getName());
			preparedStatement.setString(3, person.getIcon());
			preparedStatement.setString(4, person.getAge());
			preparedStatement.setString(5, person.getSex());
			preparedStatement.setString(6, person.getRegisterPhone());
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
