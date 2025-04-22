<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main/login.css" />
</head>
<body style="background-color: white;">

	<jsp:include page="header.jsp" />

	<div class="login-container">
		<h1 class="login-title">Login Form</h1>
		<br>
		<div class="form-container">
			<!-- Error message section -->
			<c:if test="${not empty error}">
				<p style="color: red; text-align: center;">${error}</p>
			</c:if>
			<form action="${pageContext.request.contextPath}/login" method="post">
				<div class="form-field">
					<label for="email" class="form-label">Email:</label> <input
						type="text" id="email" name="email" class="form-input" required>
				</div>
				<div class="form-field">
					<label for="password" class="form-label">Password:</label> <input
						type="password" id="password" name="password" class="form-input"
						required>
				</div>
				<button type="submit" class="submit-btn">Login</button>
			</form>
		</div>
		<p class="register-info">
			Do not have an account? <br> <a
				href="${pageContext.request.contextPath}/register">Register
				First </a>
		</p>
	</div>
</body>
</html>
