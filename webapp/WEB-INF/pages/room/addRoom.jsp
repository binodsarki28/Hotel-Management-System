<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Add Room</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/room/addRoom.css" />
</head>
<body style="background-color: white;">

	<div class="register-container">
		<h1 class="register-title">Add Room</h1>

		<!-- Display validation or success message -->
		<c:if test="${not empty error}">
			<div style="color: red; text-align: center; margin-bottom: 1rem;">
				${error}</div>
		</c:if>
		<c:if test="${not empty success}">
			<div style="color: green; text-align: center; margin-bottom: 1rem;">
				${success}</div>
		</c:if>

		<div class="form-container">
			<form action="${pageContext.request.contextPath}/addRoom"
				method="post" enctype="multipart/form-data">
				<div class="form-row">
					<div class="form-field">
						<label for="roomNo" class="form-label">Room No:</label> <input
							type="number" id="roomNo" name="roomNo" class="form-input"
							value="${param.roomNo}" required>
					</div>
					<div class="form-field">
						<label for="roomType" class="form-label">Room Type:</label> <select
							id="roomType" name="roomType" class="form-input" required>
							<option value="">Select Room Type</option>
							<option value="Single"
								${param.roomType == 'Single' ? 'selected' : ''}>Single</option>
							<option value="Double"
								${param.roomType == 'Double' ? 'selected' : ''}>Double</option>
							<option value="Triple"
								${param.roomType == 'Triple' ? 'selected' : ''}>Triple</option>
							<option value="Cottage"
								${param.roomType == 'Cottage' ? 'selected' : ''}>Cottage</option>
						</select>
					</div>

				</div>

				<div class="form-row">
					<div class="form-field" style="width: 100%;">
						<label for="roomDescription" class="form-label">Room
							Description:</label>
						<textarea id="roomDescription" name="roomDescription"
							class="form-input" rows="4" required>${param.roomDescription}</textarea>
					</div>
				</div>

				<div class="form-row">
					<div class="form-field">
						<label for="pricePerDay" class="form-label">Price Per Day:</label>
						<input type="number" step="0.01" id="pricePerDay"
							name="pricePerDay" class="form-input"
							value="${param.pricePerDay}" required>
					</div>
					<div class="form-field">
						<label for="status" class="form-label">Status:</label> <select
							id="status" name="status" class="form-input" required>
							<option value="available"
								${param.status == 'available' ? 'selected' : ''}>Available</option>
							<option value="unavailable"
								${param.status == 'unavailable' ? 'selected' : ''}>Unavailable</option>
						</select>
					</div>
				</div>

				<div class="form-row">
					<div class="form-field" style="width: 100%;">
						<label for="roomPhoto" class="form-label">Room Photo:</label> <input
							type="file" id="roomPhoto" name="roomPhoto" class="form-input"
							required>
					</div>
				</div>

				<button type="submit" class="submit-btn">Add Room</button>
			</form>
		</div>
	</div>
</body>
</html>