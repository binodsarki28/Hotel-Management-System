<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>  
    <meta charset="UTF-8">
    <title>Booking Success</title>
    
    <!-- Redirect after 5 seconds -->
    <meta http-equiv="refresh" content="5;url=${pageContext.request.contextPath}/login.jsp" />

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e8f5e9;
            margin: 0;
            padding: 0;
            text-align: center;
        }
        .success-container {
            background-color: #fff;
            margin: 50px auto;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            max-width: 600px;
        }
        h2 {
            color: green;
        }
        p {
            font-size: 18px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="success-container">
        <h2>🎉 Booking Confirmed!</h2>
        <p>Your booking has been successfully completed.</p>
        <p>You will be redirected to the profile page shortly.</p>
    </div>

    <!-- Optional: JS redirect (fallback) -->
    <script>
        setTimeout(function() {
            window.location.href = "${pageContext.request.contextPath}/profile";
        }, 5000); // 5000ms = 5 seconds
    </script>
</body>
</html>
