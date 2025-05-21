<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Profile</title>
    <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/profile/profile.css" />
</head>
<body>

    <jsp:include page="../main/header.jsp" />

    <c:if test="${not empty loggedInUser}">
        <!-- Profile Card -->
        <div class="profile-container">
            <div class="profile-header">
                <img
                    src="${pageContext.request.contextPath}/uploads/${loggedInUser.profilePhoto}"
                    alt="Profile Picture" class="profile-pic" />
                <h2>${loggedInUser.fullName}</h2>
                <p>${loggedInUser.email}</p>
                <p>${loggedInUser.phoneNumber}</p>
                <p>${loggedInUser.gender}</p>
            </div>

            <div class="actions">
                <a href="${pageContext.request.contextPath}/updateProfile">Update Profile</a>
                <a href="${pageContext.request.contextPath}/bookingHistory">View Booking History</a>
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <button type="submit"
                        onclick="return confirm('Are you sure you want to logout?');">Logout</button>
                </form>
            </div>
        
          </div>
    </c:if>

</body>
</html>
