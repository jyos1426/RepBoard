package com.my.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.control.Handler;

public class ResultHandler implements Handler {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String mime = "text/plain; charset=UTF-8";
		response.setContentType(mime);	//응답 형식 설정
		String result = (String)request.getAttribute("result"); 
		PrintWriter out= response.getWriter();
		out.print(result);
		return null;
	}

}
