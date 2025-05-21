<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.hms.model.MenuModel, com.hms.service.AddMenuService, java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
AddMenuService menuService = new AddMenuService();
List<MenuModel> menuList = menuService.getAllMenus();
request.setAttribute("menuList", menuList);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menus</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/room/room.css" />
</head>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f6f8fa;
	margin: 0;
	padding: 0;
}

.room-grid {
	display: grid;
	grid-template-columns: repeat(4, 1fr); /* Fixed 4 columns */
	gap: 20px;
	padding: 20px;
	max-width: 1200px;
	margin: 0 auto 40px auto;
}

.room-card {
	background-color: white;
	border-radius: 10px;
	overflow: hidden;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	transition: transform 0.2s;
	height: 100%; /* Make card heights uniform */
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}

.room-card:hover {
	transform: translateY(-5px);
}

.room-card img {
	width: 100%;
	height: 160px;
	object-fit: cover;
}

.room-info {
	padding: 15px;
	flex-grow: 1;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}

.room-info h3 {
	margin: 10px 0 5px;
	font-size: 20px;
}

.room-info p {
	margin: 5px 0;
	color: #555;
	font-size: 14px;
}

.room-info a {
	text-align: center;
	margin-top: 10px;
	text-decoration: none;
	background-color: #28a745;
	color: white;
	padding: 8px 12px;
	border-radius: 5px;
	transition: background-color 0.3s;
}

.room-info a:hover {
	background-color: #218838;
}

@media screen and (max-width: 1000px) {
	.room-grid {
		grid-template-columns: repeat(2, 1fr);
	}
}

@media screen and (max-width: 600px) {
	.room-grid {
		grid-template-columns: 1fr;
	}
}
</style>
<body>

	<jsp:include page="../main/header.jsp" />
	<br>
	<br>
	<br>

	<h1 style="text-align: center;">Our Menu</h1>
	<h3 style="text-align: center;">Find the best food at
		best price</h3>
		<br> <br>

	<div class="room-grid">
		<c:forEach var="menu" items="${menuList}">
			<div class="room-card">
				<img
					src="${pageContext.request.contextPath}/uploads/${menu.menuPhoto}"
					alt="Menu Image">
				<div class="room-info">
					<h3>Food Name: ${menu.foodName}</h3>
					<p>
						<strong>Category:</strong> ${menu.category}
					</p>
					<p>
						<strong>Description:</strong> ${menu.foodDescription}
					</p>
					s
					<p>
						<strong>Price:</strong> Rs. ${menu.menuPrice}
					</p>
				</div>
			</div>
		</c:forEach>
	</div>

</body>
</html>
