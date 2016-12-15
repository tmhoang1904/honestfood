package vn.com.arillance.cleanhome.dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import vn.com.arillance.cleanhome.exception.ErrorMessage;
import vn.com.arillance.cleanhome.mapper.UserMapper;
import vn.com.arillance.cleanhome.model.Credentials;
import vn.com.arillance.cleanhome.model.UserModel;

@Component
public class UserDao {
	public UserModel login(String username, String password) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		HashMap<String, String> mapParam = new HashMap<String, String>();
		mapParam.put("userName", username);
		mapParam.put("password", password);
		

		UserModel user = mapper.login(mapParam);
		session.close();
		return user;
	}

	public UserModel loginFb(Credentials auth) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		UserModel user = mapper.loginFb(auth.getId());
		if (user == null) {
			// if (auth.getBirthday() != null) {
			// SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			// try {
			// Date date = new Date(df.parse(auth.getBirthday()).getTime());
			// user.setBirthDate(date);
			// } catch (ParseException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// }
			user = new UserModel();
			user.setUsername(auth.getFbId());
			user.setPassword(UUID.randomUUID().toString());
			user.setFirstName(auth.getFirstName());
			user.setLastName(auth.getLastName());
			user.setImgUrl(auth.getImgUrl());
			user.setCreatedTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
						
			user.setMobile("");

			try {
				user = createUser(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.close();
		return user;
	}

	public ErrorMessage updateNotiToken(int userId, String notiToken) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		HashMap<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("userId", userId);
		// mapParam.put("notiToken", notiToken);
		// Check if not contain token, then append it with seperator is ","
		String curToken = mapper.getNotiToken(userId);
		if (curToken == null || !curToken.contains(notiToken)) {
			if (curToken != null) {
				String[] parts = curToken.split(",");
				if (parts.length > 0) {
					curToken = parts[parts.length - 1];
					curToken += "," + notiToken;
				}
			} else {
				curToken = notiToken;
			}
			mapParam.put("notiToken", curToken);
			int update = mapper.updateNotiToken(mapParam);
			session.commit();
			if (update > 0) {
				session.close();
				return new ErrorMessage(1, "sucess");
			} else {
				session.close();
				return new ErrorMessage(0, "no updated row");
			}
		} else {
			session.close();
			return new ErrorMessage(1, "no token need to update");
		}
	}

	public String getNotiToken(int userId) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		String token = mapper.getNotiToken(userId);
		session.close();
		return token;
	}

	public ErrorMessage updateActiveFlag(int userId, int activeFlag) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		HashMap<String, Integer> mapParam = new HashMap<String, Integer>();
		mapParam.put("userId", userId);
		mapParam.put("activeFlag", activeFlag);
		int update = mapper.updateActiveFlag(mapParam);
		session.commit();
		session.close();
		if (update > 0) {
			return new ErrorMessage(1, "Update Active Flag Successfully");
		} else {
			return new ErrorMessage(0, "No update in Database");
		}
	}
	
	public List<UserModel> getListEmployeeAvailForOtherServices(int serviceId, int districtId, int additionalServiceId) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		HashMap<String, Integer> mapParam = new HashMap<String, Integer>();
		mapParam.put("serviceId", serviceId);
		mapParam.put("districtId", districtId);
		mapParam.put("additionalServiceId", additionalServiceId);
		List<UserModel> user = mapper.getListEmployeeAvailForOtherServices(mapParam);
		session.close();
		return user;
	}

	public Object updateInfo(UserModel userModel) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		UserModel user = null;
		int update;
		try {
			update = mapper.updateInfo(userModel);
			user = mapper.getUserByUserId(userModel.getUserId());
			session.commit();
		} catch (Exception err) {
			err.printStackTrace();
			session.close();
			return new ErrorMessage(0, "failed");
		}
		session.close();
		if (update > 0) {
			return user;
		}
		return new ErrorMessage(0, "No row updated with UserId : " + userModel.getUserId());
	}

	public UserModel checkUserExists(String userName) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		UserModel userModel = mapper.checkUserExists(userName);
		session.close();
		return userModel;
	}


	public UserModel createUser(UserModel userModel) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);

		userModel.setCreatedTime(new Timestamp(Calendar.getInstance().getTime().getTime()));		

		try {
			mapper.createUser(userModel);
//			int userId = mapper.getUserIdByUsername(userModel.getUsername());
//			userModel.setUserId(userId);
			
			session.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return userModel;
	}

	// public void updateAccount(UserModel user) {
	// SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
	// UserMapper mapper = session.getMapper(UserMapper.class);
	// mapper.updateAccount(user);
	// session.commit();
	//
	// session.close();
	// }
	//
	// public void changePassword(UserModel user) {
	// SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
	// UserMapper mapper = session.getMapper(UserMapper.class);
	// mapper.changePassword(user);
	// session.commit();
	// session.close();
	// }
	// public void register(UserModel user) {
	// SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
	// UserMapper mapper = session.getMapper(UserMapper.class);
	// mapper.register(user);
	// // mapper.createUserSkill(user);
	// session.commit();
	// session.close();
	// }
	//
	// public void createProfile(ProfileModel profile) {
	// SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
	// UserMapper mapper = session.getMapper(UserMapper.class);
	// mapper.createProfile(profile);
	// mapper.createUserSkill(profile);
	// session.commit();
	// session.close();
	// }
	//
	// public ArrayList<ProfileModel> search(SearchModel search) {
	// SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
	// UserMapper mapper = session.getMapper(UserMapper.class);
	// ArrayList<ProfileModel> listUser = mapper.searchUser(search);
	// session.close();
	// return listUser;
	// }
	//
	// public ProfileModel getuser(String userId) {
	// SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
	// UserMapper mapper = session.getMapper(UserMapper.class);
	// ProfileModel user = mapper.getuser(userId);
	// session.close();
	// return user;
	// }
	//
	// public int updatePosition(SearchModel user) {
	// SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
	// UserMapper mapper = session.getMapper(UserMapper.class);
	// HashMap<String, String> mapParam = new HashMap<String, String>();
	// mapParam.put("username", user.getUserId());
	// mapParam.put("password", user.getPassword());
	// UserModel userModel = mapper.login(mapParam);
	// if (userModel != null) {
	// mapper.updatePosition(user);
	// session.commit();
	// session.close();
	// return 1;
	// } else {
	// return 0;
	// }
	// }
	//
	// public boolean checkEmail(String email) {
	// SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
	// UserMapper mapper = session.getMapper(UserMapper.class);
	// String rtn = mapper.checkEmail(email);
	// if (rtn != null) {
	// return true;
	// }
	// session.close();
	// return false;
	// }
	//
	// public ArrayList<ProfileModel> getListProfile(String userId) {
	// SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
	// UserMapper mapper = session.getMapper(UserMapper.class);
	// ArrayList<ProfileModel> listProfile = mapper.getListProfile(userId);
	// session.close();
	// return listProfile;
	//
	// }
	//
	// public int updateFlag(SearchModel user) {
	// SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
	// UserMapper mapper = session.getMapper(UserMapper.class);
	// HashMap<String, String> mapParam = new HashMap<String, String>();
	// mapParam.put("username", user.getUserId());
	// mapParam.put("password", user.getPassword());
	// UserModel userModel = mapper.login(mapParam);
	// if (userModel != null) {
	// mapper.updateFlag(user);
	// session.commit();
	// session.close();
	// return 1;
	// } else {
	// return 0;
	// }
	// }
	//
	// public UserModel loginSocial(UserModel user) {
	// SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
	// UserMapper mapper = session.getMapper(UserMapper.class);
	//
	// if (!mapper.getLoginSocial(user.getUserId())) {
	// mapper.register(
	// new UserModel(user.getUserId(), user.getName(),
	// UUID.randomUUID().toString(), true, user.getImgUrl(), "", false, "", "",
	// "", "", ""));
	// session.commit();
	// }
	// UserModel userSocial = mapper.loginSocial(user.getUserId());
	// session.close();
	// return userSocial;
	// }
}
