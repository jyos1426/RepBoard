package com.my.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.ProductDAO;
import com.my.dao.ProductDAOOracle;
import com.my.vo.Product;

public class ProductDetailHandler implements Handler {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String no = request.getParameter("no");		
		Product p = new Product();
		ProductDAO dao;
		try {
			dao = new ProductDAOOracle();
			p = dao.selectByNo(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("result",p);		
		System.out.println(p);
		String forwardURL="result_productdetail.jsp";
		// RequestDispatcher rd = request.getRequestDispatcher(forwardURL);
		//	rd.forward(request, response);
		return forwardURL;
	}

}
