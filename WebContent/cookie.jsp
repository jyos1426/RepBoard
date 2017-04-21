<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>쿠키목록</h3>
<%
	//요청 헤더에 실려온 쿠키찾기
	Cookie[] cs = request.getCookies();
	if(cs != null){
		for(Cookie c : cs){
%>		<%=c.getName() %>:<%=c.getValue() %><br>
<%		}
	}//end if
%>
<%
	//쿠키 생성, 응답해더에 추가
	String name = request.getParameter("name");
	String value = request.getParameter("value");
	if(name != null && !name.equals("")&& value != null && !value.equals("")){
		Cookie c = new Cookie(name, value);
		c.setMaxAge(60);
		response.addCookie(c);		
	}
%>
<a href="cookie.jsp">쿠키보기</a>
<form method="get" action="cookie.jsp">
	쿠키이름 : <input type="text" name="name"><br>
	쿠키값 : <input type="text" name="value"><br>
	<input type="submit" value="쿠키추가">
</form>
</body>
</html>