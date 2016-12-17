package vn.com.arillance.honestfood.model;

import java.util.List;

public class CategoryModel {
	private int categoryId;
	private String categoryName;
	private int deleteFlag;
	private List<FoodCategoryModel> foodCategories;
	private String imgUrl;
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public List<FoodCategoryModel> getFoodCategories() {
		return foodCategories;
	}
	public void setFoodCategories(List<FoodCategoryModel> foodCategories) {
		this.foodCategories = foodCategories;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}
