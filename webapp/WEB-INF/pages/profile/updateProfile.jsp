<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Profile</title>
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/css/profile/updateProfile.css" />
</head>
<body style="background-color: white;">
    <jsp:include page="../main/header.jsp" />

    <div class="update-container">
        <h1 class="update-title">Update Profile</h1>

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
            <form action="${pageContext.request.contextPath}/updateProfile"
                method="post">
                <div class="form-row">
                    <div class="form-field">
                        <label for="fullName" class="form-label">Full Name:</label>
                        <input type="text" id="fullName" name="fullName" class="form-input"
                            value="${user.fullName}" required>
                    </div>
                    <div class="form-field">
                        <label for="email" class="form-label">Email:</label>
                        <input type="email" id="email" name="email" class="form-input"
                            value="${user.email}" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-field">
                        <label for="phoneNumber" class="form-label">Phone No:</label>
                        <input type="text" id="phoneNumber" name="phoneNumber"
                            class="form-input" value="${user.phoneNumber}" required>
                    </div>
                    <div class="form-field">
                        <label for="gender" class="form-label">Gender:</label>
                        <select id="gender" name="gender" class="form-input" required>
                            <option value="male" ${user.gender == 'male' ? 'selected' : ''}>Male</option>
                            <option value="female" ${user.gender == 'female' ? 'selected' : ''}>Female</option>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-field">
                        <label for="oldPassword" class="form-label">Old Password:</label>
                        <input type="password" id="oldPassword" name="oldPassword" class="form-input"
                            required>
                    </div>
                    <div class="form-field">
                        <label for="newPassword" class="form-label">New Password:</label>
                        <input type="password" id="newPassword" name="newPassword" class="form-input"
                            required>
                    </div>
                </div>

                <input type="hidden" name="role" value="user" />

                <button type="submit" class="submit-btn">Update</button>
            </form>
        </div>
    </div>
</body>
</html>
