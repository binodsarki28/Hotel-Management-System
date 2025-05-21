<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking Info</title>
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

		<h3>Booking List</h3>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Check In Date</th>
					<th>Check Out Date</th>
					<th>NO of Guest</th>
					<th>Total Amount</th>
					<th>Total Amount</th>
					<th>User Id</th>
					<th>Room Id</th>
				</tr>
			</thead>
			<tbody>
				<!-- Using JSTL forEach loop to display room data -->
				<c:forEach var="booking" items="${bookingList}">
					<tr>
						<td>${booking.bookingId}</td>
						<td>${booking.checkInDate}</td>
						<td>${booking.checkOutDate}</td>
						<td>${booking.noOfGuest}</td>
						<td>${booking.totalAmount}</td>
						<td>${booking.userId}</td>
						<td>${booking.roomId}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>