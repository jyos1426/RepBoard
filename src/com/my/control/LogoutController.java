package com.my.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {
	@RequestMapping(value="/logout.do", method=RequestMethod.POST)
	public String execute(HttpServletRequest request,HttpSession session, Model model){
		session.removeAttribute("loginInfo");	//세션에서 loginInfo속성삭제
		model.addAttribute("result",request.getContextPath());	
		
		String forwardURL="/result.jsp";
		return forwardURL;
	}

}
