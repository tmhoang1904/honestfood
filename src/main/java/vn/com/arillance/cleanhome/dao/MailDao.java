package vn.com.arillance.cleanhome.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import vn.com.arillance.cleanhome.exception.ErrorMessage;
import vn.com.arillance.cleanhome.mapper.MailMapper;
import vn.com.arillance.cleanhome.model.MailModel;

public class MailDao {

	public HashMap<String, Object> getCountUnreadMails() {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		MailMapper mapper = session.getMapper(MailMapper.class);
		int count = 0;
		try {
			count = mapper.getCountUnreadMails();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("unreadMailsCount", count);
		return map;
	}

	public ArrayList<MailModel> getAllPopupMails() {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		MailMapper mapper = session.getMapper(MailMapper.class);
		ArrayList<MailModel> mails = new ArrayList<MailModel>();
		try {
			mails = mapper.getAllPopupMails();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return mails;
	}

	public ArrayList<MailModel> getAllUnreadMails() {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		MailMapper mapper = session.getMapper(MailMapper.class);
		ArrayList<MailModel> mails = new ArrayList<MailModel>();
		try {
			mails = mapper.getAllUnreadMails();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return mails;
	}

	public ArrayList<MailModel> getAllMails() {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		MailMapper mapper = session.getMapper(MailMapper.class);
		ArrayList<MailModel> mails = new ArrayList<MailModel>();
		try {
			mails = mapper.getAllMails();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return mails;
	}

	public ErrorMessage updateStatus(int mailId, int status) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		MailMapper mapper = session.getMapper(MailMapper.class);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mailId", mailId);
		map.put("status", status);
		int update = 0;
		try {
			update = mapper.updateStatus(map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		if (update > 0) {
			return new ErrorMessage(1, "success");
		} else {
			return new ErrorMessage(0, "no row updated!");
		}
	}

	public ErrorMessage insertMail(MailModel mail) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		MailMapper mapper = session.getMapper(MailMapper.class);
		int update = 0;
		try {
			update = mapper.insertMail(mail);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		if (update > 0) {
			return new ErrorMessage(1, "success");
		} else {
			return new ErrorMessage(0, "no row inserted!");
		}
	}
}
