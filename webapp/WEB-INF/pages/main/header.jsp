<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main/header.css" />
</head>
<body>
	<header class="site-header">
		<div class="container">
			<div class="logo-wrapper">
				<h1 class="logo">
					<a href=""><img
						src="${pageContext.request.contextPath}/resources/logos/hotelLogo.jpg"
						alt="Hotel Logo" /></a>
				</h1>
				<h1 class="hotel-name">Sanskritik Ghar</h1>
			</div>
			<nav class="nav">
				<ul>
					<!-- Show this whole block ONLY if admin is NOT logged in -->
					<c:if test="${empty admin}">
						<li><a class="active-link"
							href="${pageContext.request.contextPath}/home">Home</a></li>
						<li><a class="active-link"
							href="${pageContext.request.contextPath}/room">Room</a></li>
						<li><a class="active-link"
							href="${pageContext.request.contextPath}/menu">Menu</a></li>
						<li><a class="active-link"
							href="${pageContext.request.contextPath}/aboutUs">About Us</a></li>
						<li><a class="active-link"
							href="${pageContext.request.contextPath}/contactUs">Contact
								Us</a></li>

						<!-- Show Login if no user is logged in -->
						<c:if test="${empty loggedInUser}">
							<li><a class="active-link"
								href="${pageContext.request.contextPath}/login">Login</a></li>
							<li><a class="active-link"
								href="${pageContext.request.contextPath}/register">Register</a></li>
						</c:if>

						<!-- Show Profile if user logged in (and no admin) -->
						<c:if test="${not empty loggedInUser}">
							<li><a class="active-link"
								href="${pageContext.request.contextPath}/profile">Profile</a></li>
						</c:if>
					</c:if>

					<!-- Show ONLY Admin Dashboard if admin is logged in -->
					<c:if test="${not empty admin}">
						<li><a class="active-link"
							href="${pageContext.request.contextPath}/dashboard">Admin
								Dashboard</a></li>
					</c:if>
				</ul>
			</nav>


		</div>
		s
	</header>
</body>
</html>
