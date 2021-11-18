<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<style><%@include file="/WEB-INF/css/style.css"%></style>
<head>
<meta charset="utf-8">
<title>User Panel</title>
</head>
<body>
    <div style="text-align: center">
        <h1><fmt:message key="home.user.title"/></h1>
        <strong>${user.fullName} (${user.email})</strong>
        <br><br>
        <a href="manage_appointments"><fmt:message key="home.user.manage.appointments"/></a>
        <br><br>
        <a href="view_marks"><fmt:message key="home.user.manage.marks"/></a>
        <br><br>
        <a href="logout"><fmt:message key="logout"/></a>
        <br><br>
        <form action="loadFile" method="post" enctype="multipart/form-data">
			<input type="file" name="imageFile" >
			<br><br>
			<input type="submit" value="<fmt:message key="home.user.upload"/>">
		</form>
		<br>
		<img src="loadImage" alt="not loaded">
		
    </div>
</body>
</html>