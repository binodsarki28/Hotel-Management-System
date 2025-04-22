<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact Us</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main/aboutAndContact.css" />
</head>
<body>

	<jsp:include page="header.jsp" />

	<main>
		<section class="hero">
			<h1>Contact Us</h1>
		</section>

		<section class="contact-intro">
			<p>"We Don’t Just Manage Hotels — We Transform Hospitality. From
				Front Desk to Housekeeping, Our All-in-One Solution Enhances Every
				Guest Touchpoint, Ensuring a Smoother, Smarter, and More Memorable
				Experience."</p>
		</section>

		<section class="contact-content">
			<div class="contact-form-container">
				<h2>Send Us a Message</h2>
				<form class="contact-form">
					<div class="form-group">
						<label for="name">Full Name:</label> <input type="text" id="name"
							name="name" required>
					</div>
					<div class="form-group">
						<label for="email">Email Address:</label> <input type="email"
							id="email" name="email" required>
					</div>
					<div class="form-group">
						<label for="phone">Phone Number:</label> <input type="tel"
							id="phone" name="phone" required>
					</div>
					
					<div class="form-group">
						<label for="message">Message:</label>
						<textarea id="message" name="message" rows="6" required></textarea>
					</div>
					
					<button type="submit" class="submit-btn">Send Message</button>
				</form>
			</div>

			<div class="contact-info">
				<h2>Our Contact Information</h2>
				<div class="info-item">
					<i class="fas fa-map-marker-alt"></i>
					<div>
						<h3>Address</h3>
						<p>
							Kathmandu Bagmati<br>New Baneshwor 44300.<br>Nepal
						</p>
					</div>
				</div>
				<div class="info-item">
					<i class="fas fa-phone"></i>
					<div>
						<h3>Phone</h3>
						<p>+977 9814790008</p>
					</div>
				</div>
				<div class="info-item">
					<i class="fas fa-envelope"></i>
					<div>
						<h3>Email</h3>
						<p>sanskritikghar@nepalhotel.com.np</p>
					</div>
				</div>
				<div class="info-item">
					<i class="fas fa-clock"></i>
					<div>
						<h3>Business Hours</h3>
						<p>
							Monday - Friday: 9:00 AM - 6:00 PM<br>Saturday & Sunday:
							Closed
						</p>
					</div>
				</div>
			</div>
		</section>
		
		<section class="map-section">
			<h2>Find Us Here</h2>
			<div class="map-container">
				<iframe
					src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d4996.1903922440615!2d85.33946925024051!3d27.691336345794255!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x39eb199a06c2eaf9%3A0xc5670a9173e161de!2sNew%20Baneshwor%2C%20Kathmandu%2044600!5e0!3m2!1sen!2snp!4v1744721084695!5m2!1sen!2snp"
				    class="map-iframe">
				</iframe>
			</div>
		</section>
	</main>
</body>
</html>