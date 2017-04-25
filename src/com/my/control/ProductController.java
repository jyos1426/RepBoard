package com.my.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.dao.ProductDAO;
import com.my.vo.Product;

@Controller
public class ProductController {
	@Autowired
	private ProductDAO dao;
	
	@RequestMapping("/productlist.do")
	public String productlist(String searchItem, String searchValue, Model model){	
		List<Product> list = new ArrayList<>();
		System.out.println("상품검색됨/ "+searchItem+" : "+searchValue);
		
		try {
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
		
		model.addAttribute("result",list);		
		String forwardURL="result_productlist.jsp";
		return forwardURL;
	}
	
	@RequestMapping("/productdetail.do")
	public String productDetail(String no, Model model){
		Product p = null;
		try {
			p = dao.selectByNo(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("result",p);		
		String forwardURL="result_productdetail.jsp";
		return forwardURL;
	}
	
	
}
