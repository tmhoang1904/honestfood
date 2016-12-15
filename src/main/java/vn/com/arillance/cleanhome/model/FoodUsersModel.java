package vn.com.arillance.cleanhome.model;

import java.sql.Timestamp;

public class FoodUsersModel {
	private int foodUsersId;
	private FoodModel food;
	private UserModel shop;
	private int quantity;
	private int statusFlag;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	public int getFoodUsersId() {
		return foodUsersId;
	}
	public void setFoodUsersId(int foodUsersId) {
		this.foodUsersId = foodUsersId;
	}
	public FoodModel getFood() {
		return food;
	}
	public void setFood(FoodModel food) {
		this.food = food;
	}
	public UserModel getShop() {
		return shop;
	}
	public void setShop(UserModel shop) {
		this.shop = shop;
	}	
	public int getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(int statusFlag) {
		this.statusFlag = statusFlag;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public Timestamp getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
