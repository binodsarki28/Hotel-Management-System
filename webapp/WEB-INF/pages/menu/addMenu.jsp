<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Add Menu</title>
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/css/room/addRoom.css" />
</head>
<body style="background-color: white;">
    <jsp:include page="../main/header.jsp" />

    <div class="register-container">
        <h1 class="register-title">Add Menu</h1>

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
            <form action="${pageContext.request.contextPath}/addMenu"
                  method="post" enctype="multipart/form-data">
                <div class="form-row">
                    <div class="form-field">
                        <label for="foodMenu" class="form-label">Food Name:</label>
                        <input type="text" id="foodName" name="foodName"
                               class="form-input" value="${param.foodName}" required>
                    </div>
                    <div class="form-field">
                        <label for="roomType" class="form-label">Category:</label>
                        <input type="text" id="category" name="category"
                               class="form-input" value="${param.category}" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-field" style="width: 100%;">
                        <label for="foodDescription" class="form-label">Food Description:</label>
                        <textarea id="foodDescription" name="foodDescription"
                                  class="form-input" rows="4" required>${param.foodDescription}</textarea>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-field">
                        <label for="menuPrice" class="form-label">Price :</label>
                        <input type="number" step="0.01" id="menuPrice"
                               name="menuPrice" class="form-input" value="${param.menuPrice}" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-field" style="width: 100%;">
                        <label for="menuPhoto" class="form-label">Menu Photo:</label>
                        <input type="file" id="menuPhoto" name="menuPhoto"
                               class="form-input" required>
                    </div>
                </div>

                <button type="submit" class="submit-btn">Add Menu</button>
            </form>
        </div>
    </div>
</body>
</html>