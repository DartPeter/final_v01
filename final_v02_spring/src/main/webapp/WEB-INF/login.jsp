<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<style><%@include file="/WEB-INF/css/style.css"%></style>
<head>
<meta charset="utf-8">
<title>Selection Committee Website</title>

</head>
<body>
	<div style="text-align: right">
		<form action="changeLocale.jsp" method="post">
			<fmt:message key="settings_jsp.label.set_locale" />
			: <select name="locale">
				<c:forEach items="${applicationScope.locales}" var="locale">
					<c:set var="selected"
						value="${locale.key == currentLocale ? 'selected' : '' }" />
					<option value="${locale.key}" ${selected}>${locale.value}</option>
				</c:forEach>
			</select> <input type="submit"
				value="<fmt:message key='settings_jsp.form.submit_save_locale'/>">
		</form>

	</div>

	<div style="text-align: center">
		<h1><fmt:message key="login.page"/></h1>
		<form action="loginProc" method="post">
			<label for="username"><fmt:message key="login.login"/>:</label> 
			<input name="username" id="username" size="75" required /> 
			<br> <br> 
			<label for="password"><fmt:message key="login.password"/>:</label>
			<input type="password" name="password" id="password" size="75" required />
			<br> <br> 
			<c:if test="${message!=null&&!message.isBlank()}">
			<p style="color: red;"><fmt:message key="${message}"/></p>
			</c:if>
			<button type="submit"><fmt:message key="login.button"/></button>
		</form>
		<br>
		<form action="register">
			<input type="hidden" name="type" value="admin" />
			<button type="submit"><fmt:message key="login.register.admin"/></button>
		</form>
		<br>
		<form action="register">
			<input type="hidden" name="type" value="enrolee" />
			<button type="submit"><fmt:message key="login.register.enrolee"/></button>
		</form>
	</div>

</body>
</html>