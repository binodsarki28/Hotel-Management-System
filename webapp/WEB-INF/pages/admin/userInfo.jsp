<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Info</title>
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
					<th>Full Name</th>
					<th>Email</th>
					<th>Phone Number</th>
					<th>Gender</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<!-- Using JSTL forEach loop to display room data -->
				<c:forEach var="user" items="${userList}">
					<tr>
						<td>${user.userId}</td>
						<td>${user.fullName}</td>
						<td>${user.email}</td>
						<td>${user.phoneNumber}</td>
						<td>${user.gender}</td>
						<td>
							<form action="${contextPath}/dashboard/deleteUser" method="post"
								style="display: inline;">
								<input type="hidden" name="userId" value="${user.userId}">
								<input type="hidden" name="action" value="delete">
								<button class="action-btn" type="submit"
									onclick="return confirm('Are you sure you want to delete this this?');">Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>
