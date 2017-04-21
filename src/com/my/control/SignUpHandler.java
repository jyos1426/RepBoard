package com.my.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.CustomerDAO;
import com.my.dao.CustomerDAOOracle;
import com.my.vo.Customer;

public class SignUpHandler implements Handler {
	private CustomerDAO dao;       

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		   String result = "0";
		     try {
		 		dao = new CustomerDAOOracle();
		 		
				String id = request.getParameter("id");
			    String pwd = request.getParameter("pwd");
			    String name = request.getParameter("name");
				Customer c = new Customer(id, pwd, name);	
				dao.insert(c);		
				result = "1";
			 
			} catch (Exception e) {
				result = e.getMessage();
			}	  
		     request.setAttribute("result",result);	
		     String forwardURL="result.do";
		return forwardURL;
	}

}
