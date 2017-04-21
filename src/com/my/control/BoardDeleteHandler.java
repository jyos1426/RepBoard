package com.my.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.BoardDAOOracle;

public class BoardDeleteHandler implements Handler {
	private BoardDAOOracle dao;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int no = 0;
		 String result = "0";
	     try {
	 		dao = new BoardDAOOracle();	
	 		no = Integer.parseInt(request.getParameter("no"));		    
		    if(dao.selectByNo(no).size()== 1){
		    	dao.delete(no);	
		    	 result = "1";  
		    }else result = "-1";   

		    request.setAttribute("result",result);	
		} catch (Exception e) {
			e.printStackTrace();
		}	  			
		 String forwardURL="result.do";	     
		// RequestDispatcher rd = request.getRequestDispatcher(forwardURL);
		//	rd.forward(request, response);
	     return forwardURL;
	}

}
