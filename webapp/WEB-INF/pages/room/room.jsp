<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Available Rooms</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/room/room.css" />
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f6f8fa;
	margin: 0;
	padding: 0;
}

.search-container {
	background-color: #fff;
	padding: 20px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
	margin: 20px auto;
	border-radius: 10px;
	display: flex;
	gap: 20px;
	flex-wrap: wrap;
	align-items: center;
	justify-content: center;
	max-width: 900px;
}

.search-container input, .search-container select, .search-container button
	{
	padding: 10px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: 6px;
}

.search-container button {
	background-color: #007bff;
	color: white;
	cursor: pointer;
	transition: 0.3s;
}

.search-container button:hover {
	background-color: #0056b3;
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

.no-results {
	text-align: center;
	font-size: 18px;
	color: #888;
	margin-top: 40px;
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

</head>
<body>

	<jsp:include page="../main/header.jsp" />
	<br>
	<br>
	<br>

	<h1 style="text-align: center;">Our Rooms</h1>
	<h3 style="text-align: center;">Find the available room at the
		best price</h3>

	<!-- Search Filter -->
	<form action="${pageContext.request.contextPath}/search" method="get">
		<div class="search-container">
			<input type="date" name="checkin" placeholder="Check-in Date"
				value="${param.checkin}" /> <input type="date" name="checkout"
				placeholder="Check-out Date" value="${param.checkout}" /> <select
				name="category">
				<option value="">Select Category</option>
				<option value="Single"
					${param.category == 'Single' ? 'selected' : ''}>Single</option>
				<option value="Double"
					${param.category == 'Double' ? 'selected' : ''}>Double</option>
				<option value="Triple"
					${param.category == 'Triple' ? 'selected' : ''}>Triple</option>
				<option value="Cottage"
					${param.category == 'Cottage' ? 'selected' : ''}>Cottage</option>
			</select>

			<button type="submit">Search</button>
		</div>
	</form>

	<!-- Room Cards Grid -->
	<c:choose>
		<c:when test="${not empty roomList}">
			<div class="room-grid">
				<c:forEach var="room" items="${roomList}">
					<div class="room-card">
						<img
							src="${pageContext.request.contextPath}/uploads/${room.roomPhoto}"
							alt="Room Image">
						<div class="room-info">
							<h3>Room No: ${room.roomNo}</h3>
							
							<p>
								<strong>Type:</strong> ${room.roomType}
							</p>
							<p>
								<strong>Description:</strong> ${room.roomDescription}
							</p>
							<p>
								<strong>Price:</strong> Rs. ${room.pricePerDay}
							</p>
							<p>
								<strong>Status:</strong> ${room.status}
							</p>
							<c:choose>
								<c:when test="${not empty sessionScope.loggedInUser}">
									<a
										href="${pageContext.request.contextPath}/booking?roomId=${room.roomId}">Book
										Now</a>
								</c:when>
								<c:otherwise>
									<a href="${pageContext.request.contextPath}/login">Book Now</a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:when>
		<c:otherwise>
			<p class="no-results">No rooms found matching your criteria.</p>
		</c:otherwise>
	</c:choose>

</body>
</html>
