package com.my.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.BoardDAOOracle;
import com.my.vo.RepBoard;

public class BoardDetailHandler implements Handler {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String no = request.getParameter("no");	
		int noInt = 0;
		if(!no.equals("")|| no!=null){
			noInt = Integer.parseInt(no);
		}
			List<RepBoard> boardlist = null;
			BoardDAOOracle dao;
			try {
				dao = new BoardDAOOracle();
				System.out.println(noInt);
				boardlist = dao.selectByNo(noInt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
		request.setAttribute("result",boardlist);	
		String forwardURL="result_board_detail.jsp";
		// RequestDispatcher rd = request.getRequestDispatcher(forwardURL);
		//	rd.forward(request, response);
	     return forwardURL;
	}

}
