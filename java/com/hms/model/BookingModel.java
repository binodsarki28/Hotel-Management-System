package com.hms.model;

import java.sql.Date;

public class BookingModel {
	
	private int bookingId;
	
	private Date checkInDate;
	
	private Date checkOutDate;
	
	private int noOfGuest;
	
	private float totalAmount;
	
	private String status;
	
	private int userId;
	
	private int roomId;
	

	public BookingModel() {
		super();
	}

	public BookingModel(int bookingId, Date checkInDate, Date checkOutDate, int noOfGuest, float totalAmount, String status, int userId,
			int roomId) {
		super();
		this.bookingId = bookingId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.noOfGuest = noOfGuest;
		this.totalAmount = totalAmount;
		this.status = status;
		this.userId = userId;
		this.roomId = roomId;
	}

	public BookingModel(Date checkInDate, Date checkOutDate, int noOfGuest, float totalAmount, String status, int userId,
			int roomId) {
		super();
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.noOfGuest = noOfGuest;
		this.totalAmount = totalAmount;
		this.status = status;
		this.userId = userId;
		this.roomId = roomId;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public int getNoOfGuest() {
		return noOfGuest;
	}

	public void setNoOfGuest(int noOfGuest) {
		this.noOfGuest = noOfGuest;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}	
	
	
	
	
}
