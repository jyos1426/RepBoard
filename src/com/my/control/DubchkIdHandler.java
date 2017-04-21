package com.my.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.CustomerDAO;
import com.my.dao.CustomerDAOOracle;
import com.my.vo.Customer;

public class DubchkIdHandler implements Handler {
	private CustomerDAO dao; 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		 try {
		 		dao = new CustomerDAOOracle();
		 		
				String id = request.getParameter("id");
			    String pwd = request.getParameter("pwd");
			    System.out.println(id+" : "+pwd);
			    String result = "0";
			    Customer c = dao.selectById(id);

			    if( null == c && !"".equals(id)){
					result = "1";
			    }else if("".equals(id)){
			    	result = "-1";   
			    }else{
			    	result = "-2";   
			    }

		    	System.out.println(result);
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
