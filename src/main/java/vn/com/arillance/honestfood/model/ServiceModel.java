package vn.com.arillance.honestfood.model;

public class ServiceModel {
	public static final int SERVICE_BY_HOUR = 1;
    public static final int SERVICE_LAUNDRY = 2;
    public static final int SERVICE_CLEANING = 3;
    public static final int SERVICE_SHOES = 4;
	
	public static final double PROMOTION_BY_HOUR = 0;
    public static final double PROMOTION_LAUNDRY = 30000;
    public static final double PROMOTION_CLEANING = 0;
    public static final double PROMOTION_SHOES = 8000;
    public static final double SHIFT_FEE = 30000;
    
	private int serviceId;
	private String serviceName;
	private String iconUrl;
	private String note;
	private int deleteFlag;
	private double shiftFee;
	private double shiftFeeThreshold;
	private String shiftFeeNote;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
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

	public double getShiftFee() {
		return shiftFee;
	}

	public void setShiftFee(double shiftFee) {
		this.shiftFee = shiftFee;
	}

	public double getShiftFeeThreshold() {
		return shiftFeeThreshold;
	}

	public void setShiftFeeThreshold(double shiftFeeThreshold) {
		this.shiftFeeThreshold = shiftFeeThreshold;
	}

	public String getShiftFeeNote() {
		return shiftFeeNote;
	}

	public void setShiftFeeNote(String shiftFeeNote) {
		this.shiftFeeNote = shiftFeeNote;
	}
	
}
