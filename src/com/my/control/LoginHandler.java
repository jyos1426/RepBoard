package com.my.control;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.dao.CustomerDAO;
import com.my.dao.CustomerDAOOracle;
import com.my.vo.Customer;

public class LoginHandler implements Handler {	
	private CustomerDAO dao;
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
	     try {
	 		dao = new CustomerDAOOracle();
	 		//클라이언트 전용 HttpSession 객체얻기
		    HttpSession session = request.getSession();
		    session.removeAttribute("loginInfo");
	 		
			String id = request.getParameter("id");
		    String pwd = request.getParameter("pwd");
		    //String id_save = request.getParameter("id_save");		    
		   /* if(id_save != null){//ID저장 선택한 경우 쿠키생성 <sevedId,아이디값>
		    	Cookie c = new Cookie("savedId",id);		    	
		    	response.addCookie(c);    	
		    }else{
		    	Cookie c = new Cookie("savedId",id);
		    	c.setMaxAge(0);
		    	response.addCookie(c);	
		    }*/
		    
		    System.out.println("로그인시도 : "+new Date()+" : "+id+" : "+pwd);
		    String result = "0";
		    Customer c = dao.selectById(id);

		    if( null != c  && pwd.equals(c.getPassword())){
		    		session.setAttribute("loginInfo",c);
					 result = "1";
		    }else result = "-1";   
		    
		    request.setAttribute("result",result);	
		} catch (Exception e) {
			e.printStackTrace();
		}	    

	     String forwardURL="result.do";	     
	     return forwardURL;
	}

}
