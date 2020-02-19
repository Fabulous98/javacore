<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<div align="center">
		<table border="1" style="padding: 0; border-spacing: 0;">
			<caption>
				<h2>List of Users</h2>
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
	</div>
</body>
</html>

