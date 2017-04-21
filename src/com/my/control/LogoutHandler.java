package com.my.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutHandler implements Handler {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");	//세션에서 loginInfo속성삭제
		request.setAttribute("result",request.getContextPath());	
		String forwardURL="result.do";
		// RequestDispatcher rd = request.getRequestDispatcher(forwardURL);
		//	rd.forward(request, response);
		return forwardURL;
	}

}
