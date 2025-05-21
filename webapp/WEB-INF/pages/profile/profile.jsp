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
				<a href="${pageContext.request.contextPath}/updateProfile">Update
					Profile</a>
				<form action="${pageContext.request.contextPath}/logout"
					method="post">
					<button type="submit"
						onclick="return confirm('Are you sure you want to logout?');">Logout</button>
				</form>

			</div>

			<div class="booking-history">
				<h3>📜 My Booking History</h3>
				<table>
					<thead>
						<tr>						
							<th>Room ID</th>
							<!-- using roomId from booking table -->
							<th>Check-in</th>
							<th>Check-out</th>
							<th>Total Price</th>
							<th>Status</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="booking" items="${bookings}">
							<tr>
								<td>${booking.roomId}</td>
								<!-- roomNo is not in booking, so show roomId -->
								<td>${booking.checkInDate}</td>
								<td>${booking.checkOutDate}</td>
								<td>Rs.${booking.totalAmount}</td>
								<td>${booking.status}</td>
								<td>
									<form action="${pageContext.request.contextPath}/checkout"
										method="post">
										<input type="hidden" name="bookingId"
											value="${booking.bookingId}" />
										<button type="submit" class="checkout-btn">Checkout</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
	</c:if>

	<script>
		function confirmLogout() {
			return confirm("Are you sure you want to logout?");
		}
	</script>

</body>
</html>
