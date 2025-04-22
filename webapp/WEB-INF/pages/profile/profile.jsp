<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/profile/profile.css" />
</head>
<body>

	<jsp:include page="../main/header.jsp" />

	<c:if test="${not empty loggedInUser}">
		<div class="profile-container">
			<img
				src="${pageContext.request.contextPath}/resources/logos/profile.png"
				alt="Profile Picture" class="profile-pic" />

			<div class="user-info">
				<h2>${loggedInUser.fullName}</h2>
				<p>Email: ${loggedInUser.email}</p>
				<p>Phone: ${loggedInUser.phoneNumber}</p>
				<p>Gender: ${loggedInUser.gender}</p>
			</div>

			<div class="actions">
				<a href="${pageContext.request.contextPath}/updateProfile">Update Profile</a>
				 <a href="/delete-profile">Delete Profile</a>
				<form action="${pageContext.request.contextPath}/logout"
					method="post" onsubmit="return confirmLogout()">
					<button type="submit">Logout</button>
				</form>

				<script>
					function confirmLogout() {
						return confirm("Are you sure you want to logout?");
					}
				</script>
			</div>
		</div>
	</c:if>

</body>
</html>
