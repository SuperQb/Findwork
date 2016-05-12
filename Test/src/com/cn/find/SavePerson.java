package com.cn.find;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.dao.AcconDao;
import com.cn.dao.PersonDao;
import com.cn.daoi.AcconDaoi;
import com.cn.daoi.PersonDaoi;
import com.cn.entities.Accon;
import com.cn.entities.Person;

/**
 * Servlet implementation class SavePerson
 */
public class SavePerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PersonDao perDao = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SavePerson() {
        super();
        perDao = new PersonDaoi();
        // TODO Auto-generated constructor stub
    }

 protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	
    	String registerPhone = request.getParameter("registerPhone");
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String icon = request.getParameter("icon");
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		
		
		
		System.out.println("registerPhone = " + registerPhone);
		System.out.println("phone = " + phone);
		System.out.println("name = " + name);
		System.out.println("icon = " + icon);
		System.out.println("age = " + age);
		System.out.println("sex = " + sex);
		
		Person person = new Person( registerPhone,  phone,  name,  icon,  age,  sex);
		//int addPerson = perDao.addPerson(person);
		int updataPerson = perDao.updataPerson(person);
		System.out.println("updataPerson = " + updataPerson+"");
		
		
    }
}
