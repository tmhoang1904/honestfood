package vn.com.arillance.honestfood.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import vn.com.arillance.honestfood.model.UserModel;

public interface UserMapper {

	UserModel login(HashMap<String, String> mapParam);
	
	UserModel loginFb(String id);
	
	UserModel checkUserExists(String userName);
	
	int updateNotiToken(HashMap<String, Object> mapParam);
	
	String getNotiToken(int userId);
	
	String getUserOrderStatus(int userId);
	
	void updatePromotionAccount(HashMap<String, Object> mapParam);
	
	int updateUserOrderStatus(HashMap<String, Object> map);
	
	UserModel getPromotionAccount(int userId);
	
	UserModel getUserByUserId(int userId);
	
	int updateInfo(UserModel userModel);
	
	int updateActiveFlag(HashMap<String, Integer> mapParam);

	void register(UserModel user);
	
	void createUser(UserModel userModel);
	
	int getUserIdByUsername(String userName);	

	String checkEmail(String email);

	void updateAccount(UserModel user);

	void changePassword(UserModel user);

	UserModel loginSocial(String userId);

	boolean getLoginSocial(String userId);
	
	void requestUsersDistrict(UserModel userModel);
	
	List<UserModel> getAllEmployeesByHour();

//	ArrayList<UserModel> getListEmployeeAvail(@Param("weekdayShift") HashMap<String, String> weekdayShift);
	ArrayList<UserModel> getListEmployeeAvail(HashMap<String, Object> mapParam);
	
	List<UserModel> getListEmployeeAvailForOtherServices(HashMap<String, Integer> mapParam);
	

}
