package com.hms.model;

public class RoomModel {
	
	private int roomId;
	private int roomNo;
	private String roomType;
	private String roomDescription;
	private float pricePerDay;
	private String status;
	private String roomPhoto;
	
	
	public RoomModel() {
		
	}

	public RoomModel(int roomId, int roomNo, String roomType, String roomDescription, float pricePerDay, String status) {
		super();
		this.roomId = roomId;
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.roomDescription = roomDescription;
		this.pricePerDay = pricePerDay;
		this.status = status;
	}
	
	public RoomModel(int roomNo, String roomType, String roomDescription, float pricePerDay, String status,
			String roomPhoto) {
		super();
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.roomDescription = roomDescription;
		this.pricePerDay = pricePerDay;
		this.status = status;
		this.roomPhoto = roomPhoto;
	}
	

	public RoomModel(int roomId, int roomNo, String roomType, String roomDescription, float pricePerDay, String status,
			String roomPhoto) {
		super();
		this.roomId = roomId;
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.roomDescription = roomDescription;
		this.pricePerDay = pricePerDay;
		this.status = status;
		this.roomPhoto = roomPhoto;
	}

	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getRoomDescription() {
		return roomDescription;
	}
	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}
	public float getPricePerDay() {
		return pricePerDay;
	}
	public void setPricePerDay(float pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRoomPhoto() {
		return roomPhoto;
	}
	public void setRoomPhoto(String roomPhoto) {
		this.roomPhoto = roomPhoto;
	}
	
}
