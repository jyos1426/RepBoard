package com.my.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.BoardDAOOracle;
import com.my.vo.RepBoard;

public class BoardGetHandler implements Handler {


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		int no = Integer.parseInt(request.getParameter("no"));	
		BoardDAOOracle dao;
		RepBoard b = null;
		try {
			dao = new BoardDAOOracle();
			b = dao.selectByNo(no).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("result",b);
		String forwardURL="result_board.jsp";
		return forwardURL;
	}

}
