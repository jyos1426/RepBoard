package com.my.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.BoardDAOOracle;
import com.my.vo.RepBoard;

public class CheckPasswordHandler implements Handler {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		 BoardDAOOracle dao;
		 String pwd = request.getParameter("pwd");
		 int no = Integer.parseInt(request.getParameter("no"));	
		 String result = "1";
		 
		 try {
		 		dao = new BoardDAOOracle();
		 		if(dao.chkPassword(no, pwd)){
		 			result="1";
		 		}else{
		 			result = "-1";
		 		}
		 
		 } catch (Exception e) {
				e.printStackTrace();
		}	

		request.setAttribute("result",result);	
		String forwardURL="result.do";		  
		return forwardURL;  
	}

}
