package com.my.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		String mime = "text/plain; charset=UTF-8";
		response.setContentType(mime);	//응답 형식 설정
		String result = (String)request.getAttribute("result"); 
		PrintWriter out= response.getWriter();
		out.print(result);
	}

}
