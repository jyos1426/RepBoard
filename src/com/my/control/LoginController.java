package com.my.control;

import java.util.Date;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.my.dao.CustomerDAO;
import com.my.vo.Customer;

@Controller
public class LoginController{	
	@Autowired
	private CustomerDAO dao;
	//자동주입을 받겠다
		 
	@RequestMapping(value="/login.do", method=RequestMethod.POST)		
	public String execute(String id, 
							@Param("pwd") String pwd, HttpSession session, Model model){		
	     try {
		    session.removeAttribute("loginInfo");	 
		    System.out.println("로그인시도 : "+new Date()+" : "+id+" : "+pwd);
		    String result = "";
		    Customer c = dao.selectById(id);

		    if( null != c  && pwd.equals(c.getPassword())){
	    		session.setAttribute("loginInfo",c);
				result = "1";
		    }else result = "-1";   
		    
		    model.addAttribute("result",result);	
		} catch (Exception e) {
			e.printStackTrace();
		}	    

	     String forwardURL="/result.jsp";	     
	     return forwardURL;
	}		 
}
