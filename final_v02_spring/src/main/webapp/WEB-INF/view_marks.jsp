<%@page import="jdbc.DBManager"%>
<%@page import="com.my.pet.spring.domain.SysUser"%>
<%@page import="com.my.pet.spring.domain.Mark"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags/custom" %>
<!DOCTYPE html>
<html lang="en">
<style><%@include file="/WEB-INF/css/style.css"%></style>
<head>
<meta charset="utf-8">
<title>User Marks panel</title>
</head>
<body>
<div>
	<h1><fmt:message key="view.marks.title"/></h1>
	<%
	    SysUser su = new SysUser();
	    su.setId(((SysUser)request.getSession().getAttribute("user")).getId());
	    List<Mark> list = DBManager.getUserMarks(su);
	    request.setAttribute("list", list);
	%>
	<form action="updateMark" method="post">
	<table>
	<caption> <fmt:message key="view.marks.title"/> </caption>
		<tr>
			<th scope="col"><fmt:message key="view.marks.subj.name"/></th>
			<th scope="col"><fmt:message key="view.marks.mark"/></th>
		</tr>
		<tr><td colspan="2"><fmt:message key="view.marks.subjects"/></td></tr>
		<c:set var="count" value="0" scope="page" />
		<c:forEach items="${list}" var="u">
			<c:if test="${count==19}">
			<tr><td colspan="2"><fmt:message key="view.marks.exams"/></td></tr>
			</c:if>
			<c:set var="count" value="${count + 1}" scope="page"/>
			<tr><custom:subj_mark item="${u}"></custom:subj_mark></tr>
		</c:forEach>
	</table>
    <br/>
	
	<input type="submit" value="<fmt:message key='view.marks.update'/>">
	<input type="hidden" name="key_vm" value="${System.currentTimeMillis()}" />
	</form>
	<br/><a href="home_user"><fmt:message key="return"/></a>
</div>
</body>
</html>