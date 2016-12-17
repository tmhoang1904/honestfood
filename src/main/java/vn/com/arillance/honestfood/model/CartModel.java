package vn.com.arillance.honestfood.model;

import java.sql.Timestamp;
import java.util.List;

public class CartModel {
	private int cartId;
	private UserModel cartOwner;	
	private double cartAmount;
	private int deleteFlag;
	private int statusFlag;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	private List<FoodUsersModel> foodShopList;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public UserModel getCartOwner() {
		return cartOwner;
	}
	public void setCartOwner(UserModel cartOwner) {
		this.cartOwner = cartOwner;
	}	
	public double getCartAmount() {		
		return cartAmount;
	}
	public void setCartAmount(double cartAmount) {
		this.cartAmount = cartAmount;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
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
	public List<FoodUsersModel> getFoodShopList() {
		return foodShopList;
	}
	public void setFoodShopList(List<FoodUsersModel> foodShopList) {
		this.foodShopList = foodShopList;
	}
	
	
	public int getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(int statusFlag) {
		this.statusFlag = statusFlag;
	}
//	public double caculateCartAmount(){
//		if (foodShopList != null){
//			double cartAmount = 0;
//			for (FoodUsersModel foodShop : foodShopList){
//				cartAmount += foodShop.getPrice() * foodShop.getQuantity();
//			}
//			return cartAmount;
//		}
//		return 0;
//	}
}
