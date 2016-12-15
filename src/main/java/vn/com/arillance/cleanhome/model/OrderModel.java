package vn.com.arillance.cleanhome.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {
	private int orderId;	
	private UserModel owner;
	private String startTime;
	private String customerName;		
	private CategoryModel category;
	private double longitude;
	private String orderCode;
	private double latitude;
	private String address;	
	private String note;	
	private String mobile;	
	private boolean deleteFlag;
	private int statusFlag;
	private int confirmFlag;
	private int readFlag;
	private DistrictModel district;
	private Timestamp createTime;
	private int thisWeek;
	private double billAmount;	
	private double shiftFee;
	private List<FoodModel> foodList;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}	
	public UserModel getOwner() {
		return owner;
	}
	public void setOwner(UserModel owner) {
		this.owner = owner;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}	
	public CategoryModel getCategory() {
		return category;
	}
	public void setCategory(CategoryModel category) {
		this.category = category;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public int getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(int statusFlag) {
		this.statusFlag = statusFlag;
	}
	public int getConfirmFlag() {
		return confirmFlag;
	}
	public void setConfirmFlag(int confirmFlag) {
		this.confirmFlag = confirmFlag;
	}
	public int getReadFlag() {
		return readFlag;
	}
	public void setReadFlag(int readFlag) {
		this.readFlag = readFlag;
	}
	public DistrictModel getDistrict() {
		return district;
	}
	public void setDistrict(DistrictModel district) {
		this.district = district;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public int getThisWeek() {
		return thisWeek;
	}
	public void setThisWeek(int thisWeek) {
		this.thisWeek = thisWeek;
	}
	public double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}
	public double getShiftFee() {
		return shiftFee;
	}
	public void setShiftFee(double shiftFee) {
		this.shiftFee = shiftFee;
	}
	public List<FoodModel> getFoodList() {
		return foodList;
	}
	public void setFoodList(List<FoodModel> foodList) {
		this.foodList = foodList;
	}
	

}
