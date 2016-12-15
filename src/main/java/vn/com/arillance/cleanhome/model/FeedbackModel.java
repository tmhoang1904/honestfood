package vn.com.arillance.cleanhome.model;

public class FeedbackModel {
	private int feedbackId;
	private int orderId;

	private int employeeId;
	private int rateTotal;
	private int rateCount;
	private int newRate;
	private String comment;
	private int custId;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getRateTotal() {
		return rateTotal;
	}

	public void setRateTotal(int rateTotal) {
		this.rateTotal = rateTotal;
	}

	public int getRateCount() {
		return rateCount;
	}

	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}

	public int getNewRate() {
		return newRate;
	}

	public void setNewRate(int newRate) {
		this.newRate = newRate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

}
