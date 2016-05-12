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
import com.cn.dao.WorkDao;
import com.cn.daoi.PersonDaoi;
import com.cn.daoi.WorkDaoi;
import com.cn.entities.Person;
import com.cn.entities.Work;
import com.google.gson.Gson;

/**
 * Servlet implementation class Getwork
 */
public class Getwork extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private WorkDao dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Getwork() {
        super();
        dao = new WorkDaoi();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String get = request.getParameter("getwork");
		System.out.println("get = " + get);
		

		List<Work> work = dao.queryAll();
		Gson gson = new Gson();
		String json = gson.toJson(work);

		OutputStream os = null;
		os = response.getOutputStream();
		//String st="OK";
		os.write(json.getBytes("UTF-8"));
		//os.write(st.getBytes("UTF-8"));
		
	}

}
