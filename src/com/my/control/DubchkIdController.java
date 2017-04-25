package com.my.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.dao.CustomerDAO;
import com.my.vo.Customer;

@Controller
public class DubchkIdController{
	@Autowired
	private CustomerDAO dao;
	
	@RequestMapping(value="/dubchkid.do")
	public String execute(String id, Model model) {
		try {
			String result = "0";
			Customer c = dao.selectById(id);
			if (null == c && !"".equals(id)) {
				result = "1";
			} else if ("".equals(id)) {
				result = "-1";
			} else {
				result = "-2";
			}

			System.out.println(result);
			model.addAttribute("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String forwardURL = "/result.jsp";
		return forwardURL;
	}
}
