package vn.com.arillance.honestfood.model;

import java.util.List;

public class FoodCategoryModel {
	private int foodCategoryId;
	private String foodCategoryName;
	private String note;
	private int deleteFlag;	
	private List<FoodModel> foodList;
	private String imgUrl;
	public int getFoodCategoryId() {
		return foodCategoryId;
	}
	public void setFoodCategoryId(int foodCategoryId) {
		this.foodCategoryId = foodCategoryId;
	}
	public String getFoodCategoryName() {
		return foodCategoryName;
	}
	public void setFoodCategoryName(String foodCategoryName) {
		this.foodCategoryName = foodCategoryName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public List<FoodModel> getFoodList() {
		return foodList;
	}
	public void setFoodList(List<FoodModel> foodList) {
		this.foodList = foodList;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}	
	
	
}
