package com.cn.find;

import java.io.IOException;
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

/**
 * Servlet implementation class SaveWork
 */
public class SaveWork extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WorkDao workDao = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveWork() {
        super();
        workDao=new WorkDaoi();
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
		
		String position = request.getParameter("position");
		String pay = request.getParameter("pay");
		String sex = request.getParameter("sex");
		String location = request.getParameter("location");
		String intrduce = request.getParameter("introduce");
		String content = request.getParameter("content");
		String requid = request.getParameter("required");
		String callphone = request.getParameter("callphone");
		String phone = request.getParameter("phone");
		String type = request.getParameter("type");
		String linkman = request.getParameter("linkman");
		
		System.out.println("position = " + position);
		System.out.println("pay = " + pay);
		System.out.println("sex = " + sex);
		System.out.println("location = " + location);
		System.out.println("intrduce = " + intrduce);
		System.out.println("content = " + content);
		System.out.println("requid = " + requid);
		System.out.println("callphone = " + callphone);
		System.out.println("phone = " + phone);
		System.out.println("type = " + type);
		System.out.println("linkman = " + linkman);
		Work work = new Work( position, pay, sex,location,intrduce,content,requid,callphone,phone,type,linkman );
		//int addPerson = perDao.addPerson(person);
		int addWork = workDao.addWork(work);
		System.out.println("addWork = " + addWork+"");
		
		
	}

}
