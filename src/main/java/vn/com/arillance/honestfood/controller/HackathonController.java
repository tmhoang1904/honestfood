package vn.com.arillance.honestfood.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import vn.com.arillance.honestfood.dao.MailDao;
import vn.com.arillance.honestfood.dao.OrderDao;
import vn.com.arillance.honestfood.dao.UserDao;
import vn.com.arillance.honestfood.exception.ErrorMessage;
import vn.com.arillance.honestfood.model.CategoryModel;
import vn.com.arillance.honestfood.model.CommentModel;
import vn.com.arillance.honestfood.model.Credentials;
import vn.com.arillance.honestfood.model.DistrictModel;
import vn.com.arillance.honestfood.model.FeedbackModel;
import vn.com.arillance.honestfood.model.FoodCategoryModel;
import vn.com.arillance.honestfood.model.FoodModel;
import vn.com.arillance.honestfood.model.MailModel;
import vn.com.arillance.honestfood.model.OrderModel;
import vn.com.arillance.honestfood.model.UserModel;
import vn.com.arillance.honestfood.utils.HTTPUtils;

@Component
@Path("/")
public class HackathonController {
	@POST
	@Path("/loginfb")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object loginFb(Credentials auth) throws Exception {

		if (auth.getAccessToken() == null) {
			return new ErrorMessage(0, "login failed ");
		}
		String fbId = HTTPUtils.checkAccessToken(auth.getAccessToken());
		if (!fbId.equals("")) {
			auth.setFbId(fbId);
			UserDao userDao = new UserDao();
			UserModel user = userDao.loginFb(auth);
			if (user != null) {
				return user;
			} else {
				return new ErrorMessage(0, "login failed ");
			}
		} else {
			return new ErrorMessage(0, "login failed ");
		}
	}
	
	@GET
	@Path("/getactiveorder")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderModel> getActiveOrder(@QueryParam("ownerid") int ownerid){
		OrderDao orderDao = new OrderDao();
		return orderDao.getActiveOrder(ownerid);
	}
	
	@POST
	@Path("/createuser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object createUser(UserModel userModel) {
		UserDao userDao = new UserDao();
		Object obj = isSignupDataValid(userModel);
		if (obj instanceof ErrorMessage){
			return (ErrorMessage) obj;
		}		
		return userDao.createUser(userModel);
	}

	@POST
	@Path("updateinfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object updateInfo(UserModel userModel) {
		UserDao userDao = new UserDao();
		return userDao.updateInfo(userModel);
	}

	@POST
	@Path("/updateactiveflag")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object updateActiveFlag(String json) {		
		JSONObject jsonObj = new JSONObject(json);
		int userId = jsonObj.getInt("userid");
		int activeFlag = jsonObj.getInt("activeflag");
		if (userId > 0) {
			UserDao userDao = new UserDao();
			return userDao.updateActiveFlag(userId, activeFlag);
		} else {
			return new ErrorMessage(0, "Invalid User Id");
		}
	}

	@GET
	@Path("/getlistcategory")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<CategoryModel> getListCategory() throws Exception {
		OrderDao orderDao = new OrderDao();
		return orderDao.getListCategory();
	}
	
	@GET
	@Path("/getlistfoodcat")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<FoodCategoryModel> getListFoodCat(@QueryParam("catid") int catId){
		OrderDao orderDao = new OrderDao();
		return orderDao.getListFoodCategory(catId);
	}
	
	@GET
	@Path("/getlistfood")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<FoodModel> getListFood(@QueryParam("foodcatid") int foodCatId){
		OrderDao orderDao = new OrderDao();
		return orderDao.getListFood(foodCatId);
	}
	
	@POST
	@Path("/requestorder")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OrderModel addToCart(OrderModel order){
		OrderDao orderDao = new OrderDao();		
		return orderDao.reqOrder(order);
	}

//	@POST
//	@Path("/requestorder")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public OrderModel requestOrder(OrderModel order) throws Exception {
//		OrderDao orderDao = new OrderDao();
//		order.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
//		return orderDao.reqOrder(order);
//	}

	@GET
	@Path("/gethistoryorder")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<OrderModel> getHistoryOrder(@QueryParam("ownerid") int ownerId) throws Exception {
		OrderDao orderDao = new OrderDao();
		return orderDao.getHistoryOrder(ownerId);

	}

	@GET
	@Path("/getlistemployeeavail")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserModel> getListEmployeeAvailForOtherServices(@QueryParam("serviceid") int serviceId,
			@QueryParam("districtid") int districtId, @QueryParam("additionalserviceid") int additionalServiceId) throws Exception {
		UserDao userDao = new UserDao();
		return userDao.getListEmployeeAvailForOtherServices(serviceId, districtId, additionalServiceId);
	}

	@GET
	@Path("/getfeedback")
	@Produces(MediaType.APPLICATION_JSON)
	public FeedbackModel getFeedback(@QueryParam("employeeid") int employeeId) throws Exception {
		OrderDao orderDao = new OrderDao();
		FeedbackModel feedback = orderDao.getFeedback(employeeId);
		return feedback;

	}

	@GET
	@Path("/getcomment")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<CommentModel> getComment(@QueryParam("employeeid") int employeeId) throws Exception {
		OrderDao orderDao = new OrderDao();
		ArrayList<CommentModel> comments = orderDao.getComment(employeeId);
		return comments;
	}

	@GET
	@Path("/getorder")
	@Produces(MediaType.APPLICATION_JSON)
	public OrderModel getOrder(@QueryParam("orderod") int orderId) throws Exception {
		OrderDao orderDao = new OrderDao();
		OrderModel order = orderDao.getOrder(orderId);
		return order;

	}
	
	@POST
	@Path("/updatenotitoken")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ErrorMessage updateNotiToken(String json){
		System.out.println("Json : " + json);
		JSONObject jsonObj = new JSONObject(json);
		int userId = jsonObj.getInt("userid");
		String notiToken = jsonObj.getString("notitoken");
		UserDao userDao = new UserDao();
		return userDao.updateNotiToken(userId, notiToken);
	}

	@POST
	@Path("/updatefeedback")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ErrorMessage updateFeedback(FeedbackModel feedback) throws Exception {
		OrderDao orderDao = new OrderDao();
		if (feedback.getEmployeeId() > 0) {
			orderDao.updateRate(feedback);
		}
		if ((feedback.getComment() != null) && (!feedback.getComment().isEmpty())) {
			orderDao.updateComment(feedback);

		}
		return new ErrorMessage(1, "success");

	}

	@POST
	@Path("/cusconfirmorder")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ErrorMessage updateFeedback(OrderModel order) throws Exception {
		OrderDao orderDao = new OrderDao();
		orderDao.cusConfirmOrder(order);
		return new ErrorMessage(1, "success");
	}
	
	@GET
	@Path("/getorderforemployee")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<OrderModel> getOrderForEmployee(@QueryParam("districtid") int districtId,
			@QueryParam("serviceid") int serviceId) throws Exception {
		OrderDao orderDao = new OrderDao();
		return orderDao.getOrderForEmployee(districtId, serviceId);
	}

	@GET
	@Path("/getordertakenemployeeservice2")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<OrderModel> getOrderTakenService2(@QueryParam("employeeid") int employeeid,
			@QueryParam("serviceid") int serviceId) throws Exception {
		OrderDao orderDao = new OrderDao();
		return orderDao.getOrderTakenService2(employeeid, serviceId);
	}

	@GET
	@Path("/getordertakenemployee")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<OrderModel> getOrderTaken(@QueryParam("employeeid") int employeeid,
			@QueryParam("serviceid") int serviceId) throws Exception {
		OrderDao orderDao = new OrderDao();
		return orderDao.getOrderTaken(employeeid, serviceId);
	}

	@GET
	@Path("/getorderhistoryemployeeservice2")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<OrderModel> getOrderHistoryEmployeeService2(@QueryParam("employeeid") int employeeid,
			@QueryParam("serviceid") int serviceId) throws Exception {
		OrderDao orderDao = new OrderDao();
		return orderDao.getOrderHistoryEmployeeService2(employeeid, serviceId);
	}

	@GET
	@Path("/getorderhistoryemployee")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<OrderModel> getOrderHistoryEmployee(@QueryParam("employeeid") int employeeid,
			@QueryParam("serviceid") int serviceId) throws Exception {
		OrderDao orderDao = new OrderDao();
		return orderDao.getOrderHistoryEmployee(employeeid, serviceId);
	}

	@POST
	@Path("/empregorder")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ErrorMessage empRegOrder(OrderModel order) throws Exception {
		OrderDao orderDao = new OrderDao();
		orderDao.empRegOrder(order);
		return new ErrorMessage(1, "success");
	}

	@POST
	@Path("/assignorder")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ErrorMessage takeOrder(OrderModel order) throws Exception {
		OrderDao orderDao = new OrderDao();
		orderDao.takeOrder(order);
		return new ErrorMessage(1, "success");
	}

	@GET
	@Path("/getlistdistrict")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<DistrictModel> getlistDistrict(@QueryParam("serviceid") int serviceId) throws Exception {
		OrderDao orderDao = new OrderDao();
		return orderDao.getListDistrict(serviceId);
	}
	
	@GET
	@Path("/getnumberunreanorder")
	@Produces(MediaType.APPLICATION_JSON)
	public String getNumberUnreadOrder(@QueryParam("userid") int userId, @QueryParam("roletype") int roleType){
		OrderDao orderDao = new OrderDao();
		return orderDao.getNumberUnreadOrderCus(userId, roleType);
	}
	
	@POST
	@Path("/updatereadflag")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ErrorMessage updateReadFlag(OrderModel orderModel){
		OrderDao orderDao = new OrderDao();
		return orderDao.updateReadFlag(orderModel.getOrderId());
	}

//	@POST
//	@Path("/cuscancelorder")	
//	@Produces(MediaType.APPLICATION_JSON)
//	public ErrorMessage cusCancelOrder(@QueryParam("orderid") int orderId, @QueryParam("ownerid") int ownerId,
//			@QueryParam("userid") int userId) {
//		OrderDao orderDao = new OrderDao();
//		return orderDao.cusCancelOrder(orderId, ownerId, userId);		
//	}
	@POST
	@Path("/empcancelorder")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ErrorMessage empCancelOrder(String json) {
		OrderDao orderDao = new OrderDao();
		JSONObject jsonObj = new JSONObject(json);
		int orderId = jsonObj.getInt("orderid");
		int userId = jsonObj.getInt("userid");
		int ownerId = jsonObj.getInt("assignid");	
		int reasonId = jsonObj.getInt("cancel_reason_id");
		return orderDao.empCancelOrder(orderId, ownerId, userId, reasonId);			
	}

	@POST
	@Path("/employeecancelorder")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ErrorMessage employeeCancelOrder(OrderModel order) throws Exception {
		OrderDao orderDao = new OrderDao();
		orderDao.employeeCancelOrder(order);
		return new ErrorMessage(1, "success");
	}

	@POST
	@Path("/employeecfmorder")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ErrorMessage employeeConfirmOrder(OrderModel order) throws Exception {
		OrderDao orderDao = new OrderDao();
		orderDao.employeeConfirmOrder(order);
		return new ErrorMessage(1, "success");
	}

	
	private Object isSignupDataValid(UserModel userModel){
		UserDao userDao = new UserDao();
		if (userDao.checkUserExists(userModel.getUsername()) != null){
			return new ErrorMessage(0, "Tên đăng nhập đã tồn tại!");
		}
		if (userModel.getPassword().length() < 6){
			return new ErrorMessage(0, "Mật khẩu quá ngắn!");
		}
		if (userModel.getFirstName().trim().equals("") || userModel.getLastName().trim().equals("")){
			return new ErrorMessage(0, "H�? tên không được b�? trống");
		}
		return true;
	}	
	
	@GET
	@Path("/getcountunreadmails")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getCountUnreadMails(){
		MailDao mailDao = new MailDao();
		return mailDao.getCountUnreadMails();
	}
	
	@GET
	@Path("/getallmails")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<MailModel> getAllMails(){
		MailDao mailDao = new MailDao();
		return mailDao.getAllMails();
	}
	
	@GET
	@Path("/getallpopupmails")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<MailModel> getAllPopupMails(){
		MailDao mailDao = new MailDao();
		return mailDao.getAllPopupMails();
	}
	
	@GET
	@Path("/getallunreadmails")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<MailModel> getAllUnreadMails(){
		MailDao mailDao = new MailDao();
		return mailDao.getAllUnreadMails();
	}	
}
