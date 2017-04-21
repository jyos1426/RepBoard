package com.my.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.dao.OrderDAOOracle;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;

public class OrderListHandler implements Handler {
	private OrderDAOOracle dao;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		Customer c = (Customer)session.getAttribute("loginInfo");	//세션에서 loginInfo속성삭제
		String forwardURL="";
		List<OrderInfo> info = null;
		if(null==c){	//비로그인
			forwardURL="loginform.jsp";
		}else{			//로그인			
			dao = new OrderDAOOracle();
			info= dao.selectById(c.getId());
			forwardURL="result_orderlist.jsp";
			System.out.println("orderlistresult로 넘어감");
		}//end if
		request.setAttribute("result",info);	
		// RequestDispatcher rd = request.getRequestDispatcher(forwardURL);
		//	rd.forward(request, response);
		return forwardURL;
	}

}
