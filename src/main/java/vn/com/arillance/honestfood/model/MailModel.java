package vn.com.arillance.honestfood.model;

import java.sql.Timestamp;

public class MailModel {
	private int mailId;
	private String title;
	private String content;
	private String url;
	private int status;
	private int mailType;
	private int deleteFlag;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	private String shareLink;
	public int getMailId() {
		return mailId;
	}
	public void setMailId(int mailId) {
		this.mailId = mailId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getMailType() {
		return mailType;
	}
	public void setMailType(int mailType) {
		this.mailType = mailType;
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
	public String getShareLink() {
		return shareLink;
	}
	public void setShareLink(String shareLink) {
		this.shareLink = shareLink;
	}	
}
