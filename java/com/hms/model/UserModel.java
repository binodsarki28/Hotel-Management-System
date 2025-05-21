
package com.hms.model;

/**
 * @author Nischal Tharu 
 */

public class UserModel {
	private int userId;
	private String fullName;
	private String email;
	private String phoneNumber;
	private String gender;
	private String password;
	private String profilePhoto;
	private String role;
	
	public UserModel(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public UserModel(int userId, String fullName, String email, String phoneNumber, String gender, String password, String profilePhoto, String role) {
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.password = password;
		this.profilePhoto = profilePhoto;
		this.role = role;
	}
	
	

	

	public UserModel(int userId, String fullName, String email, String phoneNumber, String gender, String password,
			String profilePhoto) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.password = password;
		this.profilePhoto = profilePhoto;
	}

	public UserModel(String fullName, String email, String phoneNumber, String gender, String password,
			String profilePhoto, String role) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.password = password;
		this.profilePhoto = profilePhoto;
		this.role = role;
	}

	public UserModel(int userId, String fullName, String email, String phoneNumber, String gender) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
