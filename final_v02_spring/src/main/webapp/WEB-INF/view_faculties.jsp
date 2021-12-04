<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html lang="en">
<style><%@include file="/WEB-INF/css/style.css"%></style>
<head>
<meta charset="utf-8">
<title>View faculties</title>
</head>
<body>
	<div>
    <h1><fmt:message key="view.faculties.list"/></h1>
    <%
    
    %>
    <a href="view_faculties?sortType=name&spage=${spage}"><fmt:message key="sort.faculties.by.name"/></a><br>
    <a href="view_faculties?sortType=budget&spage=${spage}"><fmt:message key="sort.faculties.by.budget"/></a><br>
    <a href="view_faculties?sortType=total&spage=${spage}"><fmt:message key="sort.faculties.by.total"/></a><br>
    <c:forEach var="p" begin="${first}" end="${last}">
    	<a href="view_faculties?sortType=${sortType}&spage=${p}">${p}</a>
    </c:forEach>
    <table>
    <caption> <fmt:message key="view.faculties.list"/> </caption>
    <tr>
    <th scope="col"><fmt:message key="view.faculties.id"/></th>
    <th scope="col"><fmt:message key="view.faculties.name"/></th>
    <th scope="col"><fmt:message key="view.faculties.budget.places"/></th>
    <th scope="col"><fmt:message key="view.faculties.total.places"/></th>
    <th scope="col"><fmt:message key="view.faculties.edit"/></th>
    <th scope="col"><fmt:message key="view.faculties.delete"/></th>
    <th scope="col"><fmt:message key="view.faculties.report"/></th>
    </tr>
    <c:forEach items="${list}" var="u">
    <tr>
    <td>${u.getId()}</td>
    <td>${u.getName()}</td>
    <td>${u.getBudgetPlaces()}</td>
    <td>${u.getTotalPlaces()}</td>
    <td><a href="add_edit_faculty?id=${u.getId()}&mode=edit&name=${URLEncoder.encode(u.getName(), "UTF-8")}&budgetPlaces=${u.getBudgetPlaces()}&totalPlaces=${u.getTotalPlaces()}"><fmt:message key="view.faculties.edit"/></a></td>
    <td><a href="processFaculty?id=${u.getId()}&mode=del&name=${URLEncoder.encode(u.getName(), "UTF-8")}&budgetPlaces=${u.getBudgetPlaces()}&totalPlaces=${u.getTotalPlaces()}"><fmt:message key="view.faculties.delete"/></a></td>
    <td><a href="generate_report?id=${u.getId()}&bp=${u.getBudgetPlaces()}&tp=${u.getTotalPlaces()}"><fmt:message key="view.faculties.generate"/></a>
    </tr>
    </c:forEach>
    </table>
    <br/><a href="add_edit_faculty?mode=new"><fmt:message key="view.faculties.add.new"/></a>
    <br/><a href="home_admin"><fmt:message key="return"/></a>
    </div>
</body>
</html>