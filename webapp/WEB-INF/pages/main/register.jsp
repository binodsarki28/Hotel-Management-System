<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Register</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main/register.css" />
</head>
<body style="background-color: white;">
	<jsp:include page="header.jsp" />

	<div class="register-container">
		<h1 class="register-title">Register</h1>

		<!-- Display validation or success message -->
		<c:if test="${not empty error}">
			<div style="color: red; text-align: center; margin-bottom: 1rem;">
				${error}</div>
		</c:if>
		<c:if test="${not empty success}">
			<div style="color: green; text-align: center; margin-bottom: 1rem;">
				${success}</div>
		</c:if>

		<div class="form-container">
			<form action="${pageContext.request.contextPath}/register"
				method="post" enctype="multipart/form-data">
				<div class="form-row">
					<div class="form-field">
						<label for="fullName" class="form-label">Full Name:</label> <input
							type="text" id="fullName" name="fullName" class="form-input"
							value="${fullName}" required>
					</div>
					<div class="form-field">
						<label for="email" class="form-label">Email:</label> <input
							type="email" id="email" name="email" class="form-input"
							value="${email}" required>
					</div>
				</div>

				<div class="form-row">
					<div class="form-field">
						<label for="phoneNumber" class="form-label">Phone No:</label> <input
							type="text" id="phoneNumber" name="phoneNumber"
							class="form-input" value="${phoneNumber}" required>
					</div>
					<div class="form-field">
						<label for="gender" class="form-label">Gender:</label> <select
							id="gender" name="gender" class="form-input" required>
							<option value="male" ${gender == 'male' ? 'selected' : ''}>Male</option>
							<option value="female" ${gender == 'female' ? 'selected' : ''}>Female</option>
						</select>
					</div>
				</div>

				<div class="form-row">
					<div class="form-field">
						<label for="password" class="form-label">Password:</label> <input
							type="password" id="password" name="password" class="form-input"
							required>
					</div>
					<div class="form-field">
						<label for="retypePassword" class="form-label">Retype
							Password:</label> <input type="password" id="retypePassword"
							name="retypePassword" class="form-input" required>
					</div>
				</div>
				
				<div class="form-row">
                    <div class="form-field" style="width: 100%;">
                        <label for="profilePhoto" class="form-label">Profile Photo:</label>
                        <input type="file" id="profilePhoto" name="profilePhoto"
                               class="form-input" required>
                    </div>
                </div>

				<input type="hidden" name="role" value="user" />

				<button type="submit" class="submit-btn">Register</button>
			</form>
		</div>

		<p class="login-info">
			Already have an account? <a
				href="${pageContext.request.contextPath}/login">Login</a>
		</p>
	</div>
</body>
</html>
