<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!DOCTYPE html>
<title>result_orderlist</title>
<link rel = "stylesheet" type="text/css" href="style/gray_table.css">

<body>
<table class="tb_product" style="margin:auto;">
<tr> <th>주문번호</th> <th>주문일자</th> <th>상품번호</th> <th>상품명</th> <th>상품가격</th> <th>수량</th> <th>금액</th> </tr>
	
<c:forEach var="in" items="${requestScope.result}">
	<c:set var="cnt" value="0"/>
	<tr>	
		<td rowspan="${fn:length(in.lines)}">${in.info_no}</td>  <td rowspan= "${fn:length(in.lines)}">${in.info_date}</td> 		
				
		<c:forEach var="l" items="${in.lines}">	
			<c:set var="p" value="${l.line_p}"/>	
			<c:if test="${cnt}!=0">		<tr>	</c:if>			
				<td>${p.no}</td> <td>${p.name}</td> <td>${p.price}</td> 
				<td>${l.line_quantity}</td> <td>${l.line_quantity*p.price}</td>
			
			<c:set var="cnt" value="${cnt+1}" />
				</tr>	
		</c:forEach>		
</c:forEach>

</table>
</body>