package com.cn.find;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.dao.AcconDao;
import com.cn.dao.UserDao;
import com.cn.daoi.AcconDaoi;
import com.cn.daoi.UserDaoi;
import com.cn.entities.Accon;
import com.cn.entities.User;
import com.google.gson.Gson;

/**
 * Servlet implementation class Testt
 * 登录服务
 */
public class Testt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AcconDao dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Testt() {
        super();
        dao = new AcconDaoi();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String userpass = request.getParameter("userpass");
		System.out.println("username = " +username);
		System.out.println("userpass = " + userpass);
		
		//查询数据库
		Accon accon = new Accon(username, userpass);
		int result=dao.login(accon);
		//List<User> users = dao.queryAll();
		//Gson gson = new Gson();
		//String json = gson.toJson(users);
				
		OutputStream os = null;
		os = response.getOutputStream();
		if(result==1)
		{
			String st="OK";	
			//os.write(json.getBytes("UTF-8"));
			os.write(st.getBytes("UTF-8"));
			
		}
		else{
			String st="NO";
			os.write(st.getBytes("UTF-8"));
		}
		
		os.flush();
		//System.out.println(json);
			
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
