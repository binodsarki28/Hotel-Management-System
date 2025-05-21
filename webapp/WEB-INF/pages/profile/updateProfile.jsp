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

<div class="register-container">
    <h1 class="register-title">Update Profile</h1>

    <!-- Display error or success messages -->
    <c:if test="${not empty error}">
        <div style="color: red; text-align: center; margin-bottom: 1rem;">
            ${error}
        </div>
    </c:if>
    <c:if test="${not empty success}">
        <div style="color: green; text-align: center; margin-bottom: 1rem;">
            ${success}
        </div>
    </c:if>

    <!-- Make sure user data is available -->
    <c:if test="${not empty loggedInUser}">
        <p style="text-align:center; color: gray;">[Debug] User ID: ${loggedInUser.userId}</p>

        <div class="form-container">
            <form action="${pageContext.request.contextPath}/updateProfile"
                  method="post" enctype="multipart/form-data">

                <input type="hidden" name="userId" value="${loggedInUser.userId}" />

                <div class="form-row">
                    <div class="form-field">
                        <label class="form-label">Full Name:</label>
                        <input type="text" class="form-input" name="fullName" value="${loggedInUser.fullName}" required />

                        <label class="form-label">Email:</label>
                        <input type="email" class="form-input" name="email" value="${loggedInUser.email}" required />
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-field">
                        <label class="form-label">Phone Number:</label>
                        <input type="text" class="form-input" name="phoneNumber" value="${loggedInUser.phoneNumber}" />

                        <label class="form-label">Gender:</label>
                        <select name="gender" class="form-input">
                            <option value="Male" ${loggedInUser.gender == 'Male' ? 'selected' : ''}>Male</option>
                            <option value="Female" ${loggedInUser.gender == 'Female' ? 'selected' : ''}>Female</option>
                            <option value="Other" ${loggedInUser.gender == 'Other' ? 'selected' : ''}>Other</option>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-field">
                        <label class="form-label">New Password:</label>
                        <input type="password" class="form-input" name="newPassword" placeholder="Enter new password" />

                        <label class="form-label">Current Profile Photo:</label><br />
                        <img src="${pageContext.request.contextPath}/uploads/${loggedInUser.profilePhoto}"
                             width="100px" alt="Profile Photo"/>
                    </div>
                </div>

                <div class="form-field" style="width: 100%;">
                    <br />
                    <label class="form-label">Upload New Photo:</label>
                    <input type="file" class="form-input" name="profilePhoto" accept="image/*" />
                </div>

                <input type="hidden" name="role" value="${loggedInUser.role}" />

                <button type="submit" class="submit-btn">Update</button>
            </form>
        </div>
    </c:if>

    <!-- Optional fallback if session is broken -->
    <c:if test="${empty loggedInUser}">
        <p style="color: red; text-align: center;">User session expired. Please log in again.</p>
    </c:if>
</div>
</body>
</html>
