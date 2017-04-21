package com.my.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.BoardDAOOracle;
import com.my.vo.RepBoard;

public class BoardWriteHandler implements Handler {
	private BoardDAOOracle dao;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		// String result = "0";
	     try {
	 		dao = new BoardDAOOracle();		
	 		
	 		String parent = "";
			if(null == request.getParameter("parent")) parent ="0";
			else parent = request.getParameter("parent");
			
			int parentNum = Integer.parseInt(parent);			
			String subj = request.getParameter("subject");
			String pwd = request.getParameter("password");
		    String cont = request.getParameter("content");	
		    cont = cont +"<br><br>"+ request.getParameter("ip");
		    System.out.println(cont);
		    
		    dao.insert(new RepBoard(parentNum,subj,cont,pwd));
		    
		} catch (Exception e) {
		}	  			
		 String forwardURL="boardlist.do";	     
		// RequestDispatcher rd = request.getRequestDispatcher(forwardURL);
		//rd.forward(request, response);
	     return forwardURL;
	}

}
