<%@page import="jdbc.DBManager"%>
<%@page import="com.entity.Appointment"%>
<%@page import="com.entity.Faculty"%>
<%@page import="com.entity.SysUser"%>
<%@page import="java.util.List"%>
<%@page import="java.net.URLEncoder"%>
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
    <%
    final int PAGE_SIZE = 10;
    String spage = request.getParameter("page");
    int currentPage = 1;
    try {
    	currentPage = Integer.parseInt(spage);
    } catch(NumberFormatException nfe) {
    	// do nothing
    }
    request.setAttribute("page", currentPage);
	request.getSession().setAttribute("lapp", Integer.toString(currentPage));
    List<Appointment> list = DBManager.getUserAppointments((SysUser)(session.getAttribute("user")));
    List<Appointment> list2 = list.subList((currentPage - 1) * PAGE_SIZE, Math.min(currentPage * PAGE_SIZE, list.size()));
    request.setAttribute("list", list2);
    int size = list.size();
    int pagesTotal = size / PAGE_SIZE + (size % PAGE_SIZE == 0 ? 0 : 1);
    int first = 1;
    int last = pagesTotal;
    request.setAttribute("first", first);
    request.setAttribute("last", last);
    %>
    <c:forEach var="p" begin="${first}" end="${last}">
    	<a href="manage_appointments.jsp?page=${p}">${p}</a>
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
    <br/><a href="home_user.jsp"><fmt:message key="return"/></a>
    </div>
</body>
</html>