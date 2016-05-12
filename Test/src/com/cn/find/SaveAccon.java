package com.cn.find;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.dao.AcconDao;
import com.cn.daoi.AcconDaoi;
import com.cn.entities.Accon;

/**
 * Servlet implementation class Saveperson
 */
public class SaveAccon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AcconDao aDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveAccon() {
        super();
        aDao = new AcconDaoi();
        // TODO Auto-generated constructor stub
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	
    	String username = request.getParameter("username");
		String userpass = request.getParameter("userpass");
		System.out.println("username = " + username);
		System.out.println("userpass = " + userpass);
		Accon accon = new Accon(username, userpass);
		int addAccon = aDao.addAccon(accon);
		System.out.println("addAccon = " + addAccon+"");
		
		
		
    }
	
	
	

}
