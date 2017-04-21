<%@page import="com.my.vo.Customer"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%-- <%
//request의 속성중 "customer" 값 얻기, 반환형은 com.my.vo.Customer
Customer customer = (Customer)request.getAttribute("customer");

//속성값이 null인 경우 객체생성 후 속성으로 추가(속성명: "customer");
if(null==customer){
	request.setAttribute("customer", new Customer());
}
//속성객체에 id값을 설정한다 ("id1");
customer.setId("id1");
customer.setId("p1");

//scriptlet을 열지 ㅇ낳고 
%> --%>
<jsp:useBean id="customer" class="com.my.vo.Customer" scope="request" ></jsp:useBean>
<jsp:setProperty name ="customer" property="id" value="id1"/>
<jsp:setProperty name ="customer" property="password" value="ip1"/>
<jsp:setProperty name ="customer" property="id"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>