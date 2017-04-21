package com.my.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.ProductDAO;
import com.my.dao.ProductDAOOracle;
import com.my.vo.Product;

public class ProductListHandler implements Handler {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		//요청 전달데이터 searchItem값 찾기
		String searchItem = request.getParameter("searchItem");
		String searchValue = request.getParameter("searchValue");			
		List<Product> list = new ArrayList<>();
		System.out.println("상품검색됨/ "+searchItem+" : "+searchValue);
		try {
			ProductDAO dao = new ProductDAOOracle();
		
			if( null==searchItem || searchItem.equals("")|| null==searchValue||searchValue.equals("") ){
				list = dao.selectAll();			
				
			}else if("name".equals(searchItem) && !"".equals(searchValue)){
				list = dao.selectByName(searchValue);	
			}else if("no".equals(searchItem) && !"".equals(searchValue)){
				Product p = dao.selectByNo(searchValue);	
				if(p!=null){
					list.add(p);	
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("result",list);		
		System.out.println(list);
		String forwardURL="result_productlist.jsp";
		// RequestDispatcher rd = request.getRequestDispatcher(forwardURL);
		//	rd.forward(request, response);
		return forwardURL;
	}

}
