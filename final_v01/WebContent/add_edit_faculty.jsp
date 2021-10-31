<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.net.URLDecoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>  
<html lang="en">  
<style><%@include file="/WEB-INF/css/style.css"%></style>
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
<title>Add Edit Faculty Form</title>  
</head>  
<body>   
<div>
<c:if test="${param.mode.equals('new')}"><h2><fmt:message key="add.edit.faculty.add"/></h2></c:if>
<c:if test="${param.mode.equals('edit')}"><h2><fmt:message key="add.edit.faculty.edit"/></h2></c:if>
<c:if test="${errors!=null&&!errors.isBlank()}">
<p><fmt:message key="${errors}"/></p>
</c:if>
<form action="processFaculty?mode=${param.mode}" method="post">
<table>
<caption>
<c:if test="${param.mode.equals('new')}"><fmt:message key="add.edit.faculty.add"/></c:if>
<c:if test="${param.mode.equals('edit')}"><fmt:message key="add.edit.faculty.edit"/></c:if>
</caption>
<tr style="display: none;"><th scope="col">f</th><th scope="col">v</th></tr>
<tr><td><fmt:message key="add.edit.faculty.id"/>:</td><td><input type="text" name="id" value="${param.id != null ? param.id : faculty.id}" readonly></td></tr>
<tr><td><fmt:message key="add.edit.faculty.name"/>:</td><td><input type="text" name="name" value="${param.name != null ? param.name : faculty.name}" required></td></tr>
<tr><td><fmt:message key="add.edit.faculty.budget.places"/>:</td><td><input type="number" min=0 name="budgetPlaces" value="${param.budgetPlaces != null ? param.budgetPlaces : faculty.budgetPlaces}" required></td></tr>
<tr><td><fmt:message key="add.edit.faculty.total.places"/>:</td><td><input type="number" min=0 name="totalPlaces" value="${param.totalPlaces != null ? param.totalPlaces : faculty.totalPlaces}" required></td></tr>
<tr><td colspan="2">
<c:if test="${param.mode.equals('new')}"><input type="submit" value="<fmt:message key="add.edit.faculty.add.button"/>"/></c:if>
<c:if test="${param.mode.equals('edit')}"><input type="submit" value="<fmt:message key="add.edit.faculty.edit.button"/>"/></c:if>
</td>
</table>
<input type="hidden" name="key_aef" value="${System.currentTimeMillis()}" />
</form>
<a href="view_faculties.jsp"><fmt:message key="add.edit.faculty.list"/></a><br/>
</div>
</body>  
</html>  