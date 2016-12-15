package vn.com.arillance.cleanhome.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import vn.com.arillance.cleanhome.model.MailModel;

public interface MailMapper {
	
	int getCountUnreadMails();
	
	ArrayList<MailModel> getAllPopupMails();
	
	ArrayList<MailModel> getAllUnreadMails();
	
	ArrayList<MailModel> getAllMails();
	
	int removeMail(int mailId);
	
	int updateStatus(HashMap<String, Object> map);
	
	int insertMail(MailModel model);
	
}
