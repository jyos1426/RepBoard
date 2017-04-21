package com.my.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.BoardDAOOracle;
import com.my.vo.RepBoard;

public class BoardModifyHandler implements Handler {
	private BoardDAOOracle dao;
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		// String result = "0";
		String no = "";
	     try {
	    	 System.out.println("모디파이");
	 		dao = new BoardDAOOracle();		

	 		if(null == request.getParameter("parent")) no ="0";
			else no = request.getParameter("parent");
	 		
			String subj = request.getParameter("subject");
			String pwd = request.getParameter("password");
		    String cont = request.getParameter("content");		
		    RepBoard board = new RepBoard(Integer.parseInt(no),0,subj,cont,pwd);
		    System.out.println(board);
		    dao.update(board);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}	  			
		 String forwardURL="boardlist.do";	     
		// RequestDispatcher rd = request.getRequestDispatcher(forwardURL);
		//	rd.forward(request, response);
		return forwardURL;
	}

}
