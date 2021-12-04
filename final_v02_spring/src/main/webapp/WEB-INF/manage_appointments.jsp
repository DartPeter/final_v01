<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="custom2" uri="http://custom.tags/custom2" %> 
<!DOCTYPE html>
<html lang="en">
<style><%@include file="/WEB-INF/css/style.css"%></style>
<head>
<meta charset="utf-8">
<title>View appointments</title>
</head>
<body>
	<div>
    <h1><fmt:message key="manage.appointments.list"/></h1>
    <c:forEach var="p" begin="${first}" end="${last}">
    	<a href="manage_appointments?page=${p}">${p}</a>
    </c:forEach>
    <table>
    <caption> <fmt:message key="manage.appointments.list"/> </caption>
    <tr>
    <th scope="col"><fmt:message key="manage.appointments.id"/></th>
    <th scope="col"><fmt:message key="manage.appointments.name"/></th>
    <th scope="col"><fmt:message key="manage.appointments.status"/></th>
    <th scope="col"><fmt:message key="manage.appointments.action"/></th>
    </tr>
    <c:forEach items="${list}" var="u">
    <tr><custom2:appointment appointment="${u}"/></tr>
    </c:forEach>
    </table>
    <br/><a href="home_user"><fmt:message key="return"/></a>
    </div>
</body>
</html>