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
				<li><a href="${contextPath}/dashboard/userInfo"><span
						class="icon">👤</span> User Info</a></li>
				<li><a href="${contextPath}/dashboard/roomInfo"><span
						class="icon">🛏️</span> Room Info</a></li>
				<li><a href="${contextPath}/dashboard/menuInfo"><span
						class="icon">🍽️</span> Menu Info</a></li>
				<li><a href="${contextPath}/dashboard/bookingInfo"><span
						class="icon">🍽️</span> Booking Info</a></li>
				<li><a href="${contextPath}/dashboard/addRoom"><span
						class="icon">🍽️</span> Add Room</a>
				<li>
				<li><a href="${contextPath}/dashboard/addMenu"><span
						class="icon">🍽️</span> Add Menu</a>
				<li>
			</ul>
			<div class="logout">
				<form action="${pageContext.request.contextPath}/logout"
					method="post">
					<button type="submit" onclick="return confirm('Are you sure you want to logout?');">Logout</button>
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
