package com.hms.model;

public class MenuModel {
	private int menuId;
	private String foodName;
	private String category;
	private String foodDescription;
	private float menuPrice;
	private String menuPhoto;
	
	
	public MenuModel() {
	
	}
	
	public MenuModel(int menuId, String foodName, String category, String foodDescription, float menuPrice,
			String menuPhoto) {
		super();
		this.menuId = menuId;
		this.foodName = foodName;
		this.category = category;
		this.foodDescription = foodDescription;
		this.menuPrice = menuPrice;
		this.menuPhoto = menuPhoto;
	}
	public MenuModel(String foodName, String category, String foodDescription, float menuPrice, String menuPhoto) {
		super();
		this.foodName = foodName;
		this.category = category;
		this.foodDescription = foodDescription;
		this.menuPrice = menuPrice;
		this.menuPhoto = menuPhoto;
	}

	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFoodDescription() {
		return foodDescription;
	}
	public void setFoodDescription(String foodDescription) {
		this.foodDescription = foodDescription;
	}
	public float getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(float menuPrice) {
		this.menuPrice = menuPrice;
	}
	public String getMenuPhoto() {
		return menuPhoto;
	}
	public void setMenuPhoto(String menuPhoto) {
		this.menuPhoto = menuPhoto;
	}
		
}
