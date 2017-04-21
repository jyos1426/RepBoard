package com.my.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.vo.Product;

public class AddCartHandler implements Handler {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		   String no = request.getParameter("no");
		   String name = request.getParameter("name");
		   int price = Integer.parseInt(request.getParameter("price"));
		   int quantity = Integer.parseInt(request.getParameter("quantity"));   
		   System.out.println(no+name+price);
		   
		   Product p = new Product(no,name,price);
		   HashMap<Product,Integer> cart = (HashMap)session.getAttribute("cart");
		   if(null == cart){
			   cart = new HashMap<Product,Integer>();
			   session.setAttribute("cart", cart);			   
		   }
		   
		   Integer inCartQuantity = cart.get(p);
		   if(null == inCartQuantity){
		   }else{
			   quantity += inCartQuantity;
		   }
		   cart.put(p, quantity);
		   
		   System.out.println("장바구니 내용");
		   Set<Product> products = cart.keySet();
		   
		   for(Product inCartProduct: products){
			   int q = cart.get(inCartProduct);
			   System.out.println(inCartProduct.getNo()+" : "+inCartProduct.getName() +" : "+ q);
		   }
		   
		return null;
	}

}
