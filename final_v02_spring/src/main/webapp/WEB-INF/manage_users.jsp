<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<style><%@include file="/WEB-INF/css/style.css"%></style>
<head>
<meta charset="utf-8">
<title>Manage users</title>
</head>
<body>
	<div>
	<h1><fmt:message key="manage.users.list"/></h1>
	<c:forEach var="p" begin="${first}" end="${last}">
    	<a href="manage_users?page=${p}">${p}</a>
    </c:forEach>
	<table>
		<caption><fmt:message key="manage.users.list"/></caption>
		<tr>
			<th scope="col"><fmt:message key="manage.users.id"/></th>
			<th scope="col"><fmt:message key="manage.users.full.name"/></th>
			<th scope="col"><fmt:message key="manage.users.type"/></th>
			<th scope="col"><fmt:message key="manage.users.is.blocked"/></th>
			<th scope="col"><fmt:message key="manage.users.blunbl"/></th>
		</tr>
		<c:forEach items="${list}" var="u">
			<tr>
				<td>${u.getId()}</td>
				<td>${u.getFullName()}</td>
				<td><fmt:message key="${u.getUserType().equals('admin')?'manage.users.admin':'manage.users.enrolee'}"/></td>
				<td><fmt:message key="${u.isBlocked()?'manage.users.yes':'manage.users.no'}"/></td>
				<td><c:if test="${!u.getUserType().equals('admin')&&u.isBlocked()}">
						<a href="processUser?id=${u.getId()}&method=unblock&page=${page}"><fmt:message key="manage.users.unblock"/></a>
					</c:if> <c:if test="${!u.getUserType().equals('admin')&&!u.isBlocked()}">
						<a href="processUser?id=${u.getId()}&method=block&page=${page}"><fmt:message key="manage.users.block"/></a>
					</c:if></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="home_admin"><fmt:message key="return"/></a>
	</div>
</body>
</html>