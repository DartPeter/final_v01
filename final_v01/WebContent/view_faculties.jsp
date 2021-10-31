<%@page import="jdbc.DBManager"%>
<%@page import="entity.Faculty"%>
<%@page import="java.util.List"%>
<%@page import="java.net.URLEncoder"%>
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
    final int PAGE_SIZE = 10;
    String spage = request.getParameter("spage");
    int currentPage = 1;
    try {
    	currentPage = Integer.parseInt(spage);
    } catch(NumberFormatException nfe) {
    	// do nothing
    }
    
    List<Faculty> list = DBManager.getAllFaculties();
    
	// sort staff
    String sortType = request.getParameter("sortType");
	if(sortType == null) sortType = "";
    switch (sortType) {
	    case "name" :
	    	list.sort((f1, f2) -> f1.getName().compareTo(f2.getName()));
	    	break;
	    case "budget" :
	    	list.sort((f1, f2) -> Integer.compare(f1.getBudgetPlaces(), f2.getBudgetPlaces()));
	    	break;
	    case "total" :
	    	list.sort((f1, f2) -> Integer.compare(f1.getTotalPlaces(), f2.getTotalPlaces()));
	    	break;
	   	default :
	   		break;	
    }
    
    List<Faculty> list2 = list.subList((currentPage - 1) * PAGE_SIZE, Math.min(currentPage * PAGE_SIZE, list.size()));
    request.setAttribute("list", list2);
    int size = list.size();
    int pagesTotal = size / PAGE_SIZE + (size % PAGE_SIZE == 0 ? 0 : 1);
    int first = 1;
    int last = pagesTotal;
    request.setAttribute("first", first);
    request.setAttribute("last", last);
    request.setAttribute("sortType", sortType);
    request.setAttribute("spage", spage);
    %>
    <a href="view_faculties.jsp?sortType=name&spage=${spage}"><fmt:message key="sort.faculties.by.name"/></a><br>
    <a href="view_faculties.jsp?sortType=budget&spage=${spage}"><fmt:message key="sort.faculties.by.budget"/></a><br>
    <a href="view_faculties.jsp?sortType=total&spage=${spage}"><fmt:message key="sort.faculties.by.total"/></a><br>
    <c:forEach var="p" begin="${first}" end="${last}">
    	<a href="view_faculties.jsp?sortType=${sortType}&spage=${p}">${p}</a>
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
    <td><a href="add_edit_faculty.jsp?id=${u.getId()}&mode=edit&name=${URLEncoder.encode(u.getName(), "UTF-8")}&budgetPlaces=${u.getBudgetPlaces()}&totalPlaces=${u.getTotalPlaces()}"><fmt:message key="view.faculties.edit"/></a></td>
    <td><a href="processFaculty?id=${u.getId()}&mode=del&name=${URLEncoder.encode(u.getName(), "UTF-8")}&budgetPlaces=${u.getBudgetPlaces()}&totalPlaces=${u.getTotalPlaces()}"><fmt:message key="view.faculties.delete"/></a></td>
    <td><a href="generate_report.jsp?id=${u.getId()}&bp=${u.getBudgetPlaces()}&tp=${u.getTotalPlaces()}"><fmt:message key="view.faculties.generate"/></a>
    </tr>
    </c:forEach>
    </table>
    <br/><a href="add_edit_faculty.jsp?mode=new"><fmt:message key="view.faculties.add.new"/></a>
    <br/><a href="home_admin.jsp"><fmt:message key="return"/></a>
    </div>
</body>
</html>