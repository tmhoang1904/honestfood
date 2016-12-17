package vn.com.arillance.honestfood.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import vn.com.arillance.honestfood.model.MailModel;

public interface MailMapper {
	
	int getCountUnreadMails();
	
	ArrayList<MailModel> getAllPopupMails();
	
	ArrayList<MailModel> getAllUnreadMails();
	
	ArrayList<MailModel> getAllMails();
	
	int removeMail(int mailId);
	
	int updateStatus(HashMap<String, Object> map);
	
	int insertMail(MailModel model);
	
}
