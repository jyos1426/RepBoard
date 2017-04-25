package com.my.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.dao.CustomerDAO;
import com.my.vo.Customer;

@Controller
public class SignUpController {
	@Autowired
	private CustomerDAO dao;       
	
	@RequestMapping(value="/signup.do")
	public String execute(String id,
							@RequestParam("pwd") String pwd, 
							String name, Model model){
		
		
	    String result = "0";
	    try {
			Customer c = new Customer(id, pwd, name);	
			dao.insert(c);		
			result = "1";
		 
		} catch (Exception e) {
			result = e.getMessage();
		}	  
	    model.addAttribute("result",result);	
	    String forwardURL="result.jsp";
		return forwardURL;
	}

}
