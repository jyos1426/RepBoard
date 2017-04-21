package com.my.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.dao.OrderDAOOracle;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

public class AddOrderHandler implements Handler {
	private OrderDAOOracle dao;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		Customer c = (Customer)session.getAttribute("loginInfo");	//세션에서 loginInfo속성삭제
		String forwardURL="";
		
		if(null==c){	//비로그인
			forwardURL="loginform.jsp";
		}else{			//로그인					
			HashMap<Product,Integer> cart= (HashMap<Product,Integer>)session.getAttribute("cart");
			Set<Product> products = cart.keySet();
			
			dao = new OrderDAOOracle();
			List<OrderLine> lines= new ArrayList<>();

			for(Product p: products){
				int q = cart.get(p);
				lines.add(new OrderLine(1,p,q));
			}
			OrderInfo orderinfo= new OrderInfo(0,new Date(),c,lines);
			dao.insert(orderinfo);
			forwardURL="orderlist.do";
		}//end if		
		
		session.removeAttribute("cart");
		request.setAttribute("result",request.getContextPath());	
		return forwardURL;
	}

}
