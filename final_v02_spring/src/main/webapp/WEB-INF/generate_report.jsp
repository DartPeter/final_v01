<%@page import="jdbc.DBManager"%>
<%@page import="com.my.pet.spring.domain.Faculty"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<style><%@include file="/WEB-INF/css/style.css"%></style>
<head>
<meta charset="utf-8">
<title>Report</title>
</head>
<body>
<div>
<h1><fmt:message key="report.title"/></h1>
<%
Faculty f = new Faculty();
f.setId(Integer.parseInt(request.getParameter("id")));
List<List<String>> list = DBManager.generateReport(f);
request.setAttribute("list", list);
request.getSession().setAttribute("listSession", list);
request.getSession().setAttribute("bp", Integer.parseInt(request.getParameter("bp")));
request.getSession().setAttribute("tp", Integer.parseInt(request.getParameter("tp")));
%>
<h3><fmt:message key="report.bugget"/></h3>
<c:set var="count" value="0" scope="page" />
<c:forEach items="${list}" var="u">
<c:set var="count" value="${count + 1}" scope="page"/>
<c:if test="${count==param.bp+1}"><h3><fmt:message key="report.common"/></h3></c:if>
<c:if test="${count==param.tp+1}"><h3><fmt:message key="report.by"/></h3></c:if>
<p style="white-space: nowrap;">${u.get(1)}   ${u.get(2)}</p>
</c:forEach>
<br>
<div id="res">
</div>
<button type="button" onclick="sendEmails()"><fmt:message key="button.send.emails"/></button>

<script>
function sendEmails() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("res").innerHTML = this.responseText;
    }
  };
  xhttp.open("GET", "sendEmails", true);
  xhttp.send();
}
</script>
<br><br>
<a href="view_faculties"><fmt:message key="return"/></a>
</div>
</body>
</html>