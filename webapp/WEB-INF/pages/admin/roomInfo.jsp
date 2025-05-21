<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Room Info</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin/dashboard.css" />
</head>
<body>

	<div class="table-container">
		<!-- Display error message if available -->
		<c:if test="${not empty error}">
			<p class="error-message">${error}</p>
		</c:if>

		<!-- Display success message if available -->
		<c:if test="${not empty success}">
			<p class="success-message">${success}</p>
		</c:if>

		<h3>Room List</h3>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Room No</th>
					<th>Room Type</th>
					<th>Room Description</th>
					<th>Price</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<!-- Using JSTL forEach loop to display room data -->
				<c:forEach var="room" items="${roomList}">
					<tr>
						<td>${room.roomId}</td>
						<td>${room.roomNo}</td>
						<td>${room.roomType}</td>
						<td>${room.roomDescription}</td>
						<td>${room.pricePerDay}</td>
						<td>
							<form action="${contextPath}/dashboard/updateRoom" method="post"
								style="display: inline;">
								<input type="hidden" name="roomId" value="${room.roomId}">
								<input type="hidden" name="action" value="updateForm">
								<button class="action-btn" type="submit">Edit</button>
							</form>
							<form action="${contextPath}/dashboard/deleteRoom" method="post"
								style="display: inline;">
								<input type="hidden" name="roomId" value="${room.roomId}">
								<input type="hidden" name="action" value="delete">
								<button class="action-btn" type="submit"
									onclick="return confirm('Are you sure you want to delete this room?');">Delete</button>
							</form>

						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>
