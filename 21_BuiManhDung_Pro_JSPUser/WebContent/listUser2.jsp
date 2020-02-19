<%@page import="common.User"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2 align="center" >List User Type 2</h2>
	<table align="center" border="1" cellspacing="0" cellpadding="0" width="40%">
		<tr>
			<th>ID</th> 
			<th>Name</th>
			<th>Date</th>
			<th>Birth place</th>
		</tr>
		
		<%
			ArrayList<User> listUser = (ArrayList<User>) request.getAttribute("listUser2");
			for (User user : listUser) {
		%>
		
		<tr>
			<td><%=user.getId() %></td> 
			<td><%=user.getName() %></td>
			<td><%=user.getBirthday() %></td>
			<td><%=user.getBirthplace() %></td>
		</tr>
		
		<%
			}
		%>
	
	</table>
	
	<br><br>
	
	
	
	

</body>
</html>