package com.my.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartListHandler implements Handler {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String forwardURL="result_cartlist.jsp";
//		RequestDispatcher rd = request.getRequestDispatcher(forwardURL);
//		rd.forward(request, response);
		return forwardURL;
	}

}
