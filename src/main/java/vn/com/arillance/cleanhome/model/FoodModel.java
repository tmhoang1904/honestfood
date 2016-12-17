package vn.com.arillance.cleanhome.model;

public class FoodModel {
	/*
	 Nam: [ (13.397 x Trọng lượng kg) + (4.799 x Chiều cao cm) - (5.677 x Tuổi năm) + 88.362 ]

	Nữ  : [ (9.247 x Trọng lượng kg) + (3.098 x Chiều cao cm) - (4.330 x Tuổi năm) + 447.593 ]
	
	 Nhóm 1. Ít hoặc không vận động: BMR x 1.2
Nhóm 2. Vận động nhẹ: 1-3 lần/1 tuần: BMR x 1.375
Nhóm 3. Vận động vừa phải: 3-5 lần/ 1 tuần:  BMR x 1.55
Nhóm 4. Vận động nhiều: 6-7 lần/1 tuần: BMR x 1.725
Nhóm 5. Vận động nặng: Trên 7 lần 1 tuần: BMR x 1.9
	 
	 */
	private int foodId;
	private String foodName;
	private String describe;
	private double protein;
	private double fat;
	private double carbohydrate;	
	private int deleteFlag;
	private String note;
	private String imgUrl;
	private double price;
	private double calories;
	private int quantity;	
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public double getProtein() {
		return protein;
	}
	public void setProtein(double protein) {
		this.protein = protein;
	}
	public double getFat() {
		return fat;
	}
	public void setFat(double fat) {
		this.fat = fat;
	}
	public double getCarbohydrate() {
		return carbohydrate;
	}
	public void setCarbohydrate(double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getCalories() {
		return calories;
	}
	public void setCalories(double calories) {
		this.calories = calories;
	}	
	
}
