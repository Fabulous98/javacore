<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="common.User"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>My Application</title>

</head>
<body>
	<center>
		<h1>User Management</h1>

	</center>
	
	 <form method="get" name="timkiem" action="searchUser">
      <table border="0" align="center">
        <tr><td colspan=2 style="font-size:12pt;" align="center">
        <h3>Search User</h3></td></tr>
        <tr><td ><b>User Name</b></td>
        
          <td>: <input type="text" name="searchValue" id="searchValue" value=''/>
        </td></tr>        
        <tr><td colspan=2 align="center">
        <input  type="submit" name="submit" value="Search"></td></tr>
      </table>
    </form>
	
		<table align="center" border="1" width="40%"
		style="padding: 0; border-spacing: 0;">
		<caption>
			<h2>Dùng thẻ Jstl</h2>
		</caption>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Birthday</th>
			<th>Birth Place</th>
		</tr>
		<c:forEach var="user" items="${listUser}">
			<tr>
				<td><c:out value="${user.id}" /></td>
				<td><c:out value="${user.name}" /></td>
				<td><c:out value="${user.birthday}" /></td>
				<td><c:out value="${user.birthplace}" /></td>

			</tr>
		</c:forEach>
	</table>
	<br>
	<br>

	<table align="center" border="1" cellspacing="0" cellpadding="0"
		width="40%">
		<caption>
			<h2>Dùng Java code</h2>
		</caption>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Date</th>
			<th>Birth place</th>
		</tr>

		<%
			ArrayList<User> listUser = (ArrayList<User>) session.getAttribute("listUser");
			for (User user : listUser) {
		%>

		<tr>
			<td><%=user.getId()%></td>
			<td><%=user.getName()%></td>
			<td><%=user.getBirthday()%></td>
			<td><%=user.getBirthplace()%></td>
		</tr>

		<%
			}
		%>

	</table>

</body>
</html>

