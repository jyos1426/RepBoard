	package com.my.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.BoardDAOOracle;
import com.my.vo.Product;
import com.my.vo.RepBoard;

public class BoardListHandler implements Handler {
    private final static int pageSize = 6;		  //페이지 당 글 갯수
    private final static int pageGroupSize = 3;   //보여질 페이지 갯수
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		List<RepBoard> list = new ArrayList<>();
		List<RepBoard> articleList = new ArrayList<>();	
		
		String searchItem = request.getParameter("searchItem");
		String searchValue = request.getParameter("searchValue");
		String pageNum = request.getParameter("pageNum");//페이지 번호		 
        if (pageNum == null) {
            pageNum = "1";
        }
         
        int currentPage = Integer.parseInt(pageNum);
        int startRow = (currentPage - 1) * pageSize + 1;//한 페이지의 시작글 번호
        int endRow = currentPage * pageSize;//한 페이지의 마지막 글번호
        int count = 0;
        int number=0;

		try {
	 		BoardDAOOracle dao = new BoardDAOOracle();	 	
	 		list = dao.selectAll();		
	 		if( null==searchItem || searchItem.equals("")|| null==searchValue||searchValue.equals("") ){
				list = dao.selectAll();	
				
			}else if("name".equals(searchItem) && !"".equals(searchValue)){
				list = dao.selectByCont(searchValue);	
				
			}else if("no".equals(searchItem) && !"".equals(searchValue)){
				list = dao.selectByNo(Integer.parseInt(searchValue));	
			}
	 		
	 		/* Paging */
	 		count = list.size();
	 		
	 		if ( count > 0 ) {  
                if( endRow > count )  endRow = count;              
            	for(int i = startRow-1 ; i < endRow ; i++){
            		articleList.add(list.get(i));
            	}                          
	        } else {
	            articleList = null;
	        }	 	
	 		number=count-(currentPage-1)*pageSize;		//글목록에 표시할 글번호            
            
            int pageGroupCount = count/(pageSize*pageGroupSize)+( count % (pageSize*pageGroupSize) == 0 ? 0 : 1);	//페이지 그룹 갯수
            int numPageGroup = (int) Math.ceil((double)currentPage/pageGroupSize);	//페이지 그룹 순서

	        //해당 뷰에서 사용할 속성
	        request.setAttribute("currentPage", new Integer(currentPage));
	        request.setAttribute("startRow", new Integer(startRow));
	        request.setAttribute("endRow", new Integer(endRow));
	        request.setAttribute("count", new Integer(count));
	        request.setAttribute("pageSize", new Integer(pageSize));

            request.setAttribute("number", new Integer(number));
	        request.setAttribute("pageGroupSize", new Integer(pageGroupSize));
	        request.setAttribute("numPageGroup", new Integer(numPageGroup));
	        request.setAttribute("pageGroupCount", new Integer(pageGroupCount));
	        request.setAttribute("result", articleList);
	 		
		} catch (Exception e) {
			e.printStackTrace();
		}	    


		String forwardURL="result_board_list.jsp";
		return forwardURL;
	}

}
