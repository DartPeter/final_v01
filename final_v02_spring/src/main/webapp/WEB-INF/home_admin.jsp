<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<style><%@include file="/WEB-INF/css/style.css"%></style>
<head>
<meta charset="utf-8">
<title>Admin Panel</title>
</head>
<body>
    <div style="text-align: center">
        <h1><fmt:message key="home.admin.title"/></h1>
        <a href="view_faculties"><fmt:message key="home.admin.manage.faculties"/></a>
        <br><br>
        <a href="manage_users"><fmt:message key="home.admin.manage.users"/></a>
        <br><br>
        <a href="http://localhost:8080/final_v01/logout"><fmt:message key="logout"/></a>
    </div>
</body>
</html>