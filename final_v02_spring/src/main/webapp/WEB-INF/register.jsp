<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<style><%@include file="/WEB-INF/css/style.css"%></style>
<head>
<meta charset="utf-8">
<title>Registration Form</title>
</head>
<body>
	<div>
	
	<p>
	<br><br><br>
	${param.type} <br>
	${type} <br>
	${qwe} <br>
	<br><br><br>
	</p>
	
	<c:if test="${message!=null&&!message.isBlank()}">
	<p><fmt:message key="${message}"/></p>
	</c:if>
	<h1><fmt:message key="register.form"/></h1>
	<form id="registerForm" action="registerProc" method="post">
		<table>
		<caption><fmt:message key="register.form"/></caption>
			<tr style="display: none;"><th scope="col">f</th><th scope="col">v</th><th scope="col">e</th></tr>
			<tr>
				<td><fmt:message key="register.full.name"/></td>
				<td><input type="text" name="full_name" maxlength="75" required /></td><td class="validity"></td>
			</tr>
			<tr>
				<td><fmt:message key="register.user.type"/></td>
				<td><input type="text" name="user_type" value="${param.type}" readonly /></td><td class="validity""></td>
			</tr>
			<tr>
				<td><fmt:message key="register.login"/></td>
				<td><input type="text" name="login" maxlength="20" required /></td><td class="validity""></td>
			</tr>
			<tr>
				<td><fmt:message key="register.password"/></td>
				<td><input type="password" name="password" maxlength="20" required /></td><td class="validity""></td>
			</tr>
			<tr>
				<td><fmt:message key="register.email"/></td>
				<td><input type="text" name="email" maxlength="20" required /></td><td class="validity""></td>
			</tr>

			<c:if test="${(param.type).equals('enrolee')}">
				<tr>
					<td><fmt:message key="register.city"/></td>
					<td><input type="text" name="city" maxlength="30" required /></td>
				</tr>
				<tr>
					<td><fmt:message key="register.region"/></td>
					<td><input type="text" name="region" maxlength="30" required /></td>
				</tr>
				<tr>
					<td><fmt:message key="register.institution.name"/></td>
					<td><input type="text" name="inst_name" maxlength="80" required /></td>
				</tr>
			</c:if>
			</table>
            <input type="hidden" name="type" value="${param.type}" />
            <br>
			<input type="submit" value="<fmt:message key='submit'/>"/>
			 <input type="hidden" name="key_reg" value="${System.currentTimeMillis()}" />
		</form>		
		
		<br>
		<a href="login"><fmt:message key="return"/></a>
	</div>
</body>
</html>