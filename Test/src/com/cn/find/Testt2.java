package com.cn.find;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.dao.PersonDao;
import com.cn.dao.UserDao;
import com.cn.daoi.PersonDaoi;
import com.cn.daoi.UserDaoi;
import com.cn.entities.Person;
import com.cn.entities.User;
import com.google.gson.Gson;

/**
 * Servlet implementation class Testt2
 * 注册服务
 */
public class Testt2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PersonDao dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Testt2() {
        super();
        dao = new PersonDaoi();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("username");
		String password = request.getParameter("password");

		System.out.println("name = " + name);
		System.out.println("password = " + password);

		List<Person> persons = dao.queryAll();
		Gson gson = new Gson();
		String json = gson.toJson(persons);

		OutputStream os = null;
		os = response.getOutputStream();
		String st="OK";
		//os.write(json.getBytes("UTF-8"));
		os.write(st.getBytes("UTF-8"));

		//System.out.println(json);
	}

}
