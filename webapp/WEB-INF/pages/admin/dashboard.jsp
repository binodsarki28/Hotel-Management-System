<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/admin/dashboard.css" />
</head>
<body>

	<div class="container">
		<div class="sidebar">
			<ul class="nav">
				<li><a href="${contextPath}/dashboard"><span class="icon">🏠</span>
						Dashboard</a></li>
				<li><a href="${contextPath}/userInfo"><span class="icon">👤</span>
						User Info</a></li>
				<li><a href="${contextPath}/roomInfo"><span class="icon">🛏️</span>
						Room Info</a></li>
				<li><a href="${contextPath}/menuInfo"><span class="icon">🍽️</span>
						Menu Info</a></li>
			</ul>
			<div class="logout">
				<form action="${contextPath}/logout" method="post">
					<input type="submit" class="nav-button" value="Logout" />
				</form>
			</div>
		</div>

		<div class="content">
			<div class="header">
				<div class="info-box">
					<h3>Total User</h3>
					<p>${empty total ? 0 : total}</p>
				</div>
				<div class="info-box">
					<h3>Total Rooms Number</h3>
					<p>${empty room ? 0 : room}</p>
				</div>
				<div class="info-box">
					<h3>Total Bookings</h3>
					<p>${empty booking ? 0 : booking}</p>
				</div>
				<div class="info-box">
					<h3>Total Menus Items</h3>
					<p>${empty menu ? 0 : menu}</p>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
