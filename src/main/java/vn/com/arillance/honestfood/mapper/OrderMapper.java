package vn.com.arillance.honestfood.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import vn.com.arillance.honestfood.model.CartModel;
import vn.com.arillance.honestfood.model.CategoryModel;
import vn.com.arillance.honestfood.model.CommentModel;
import vn.com.arillance.honestfood.model.DistrictModel;
import vn.com.arillance.honestfood.model.FeedbackModel;
import vn.com.arillance.honestfood.model.FoodCategoryModel;
import vn.com.arillance.honestfood.model.FoodModel;
import vn.com.arillance.honestfood.model.OrderModel;
import vn.com.arillance.honestfood.model.ServiceModel;

public interface OrderMapper {
	List<OrderModel> getActiveOrder(int userId);

	ArrayList<CategoryModel> getListCategory();
	
	ArrayList<FoodCategoryModel> getListFoodCategory(int catId);
	
	ArrayList<FoodModel> getListFood(int foodCatId);
	
	int getCartIdOfUser(int ownerId);
	
	int checkCartExists(int cartId);
	
	int updateCartAmount(CartModel cart);
	
	int addNewCart(CartModel cart);
	
	int requestCartFoodShop(List<HashMap<String, Integer>> maps);

	void requestOrder(OrderModel order);

	ArrayList<OrderModel> getHistoryOrder(int ownerId);

	FeedbackModel getFeedback(int employeeId);

	void updateRate(FeedbackModel feedback);

	void createFeedback(int employeeId);

	void updateComment(FeedbackModel feedback);

	OrderModel getOrder(int orderId);

	void cusConfirmOrder(OrderModel order);

	int empCancelOrder(HashMap<String, Integer> map);

	int getNumberUnreadOrder(HashMap<String, Integer> map);

	int updateReadFlag(int orderId);

	ArrayList<OrderModel> getOrderForEmployee(HashMap<String, Integer> mapParam);

	ArrayList<DistrictModel> getListDistrict(int serviceId);

	ArrayList<OrderModel> getOrderTakenService2(HashMap<String, Integer> mapParam);

	ArrayList<OrderModel> getOrderTaken(HashMap<String, Integer> mapParam);

	void takeOrder(OrderModel order);

	void employeeCancelOrder(OrderModel order);

	void employeeConfirmOrder(OrderModel order);

	void requestOrderFoodShop(OrderModel order);

	void removeOrder(int orderId);

	void deleteTempAssigner(OrderModel order);

	void empRegOrder(OrderModel order);

	void generateOrderCode(int seqNumber);

	int getOrderMaxCode();

	int getShiftsAvail(HashMap<String, Object> map);

	void updateOrderCode(OrderModel order);

	void updateOrderPaymentContent(OrderModel order);

	ArrayList<OrderModel> getOrderHistoryEmployee(HashMap<String, Integer> mapParam);

	ArrayList<OrderModel> getOrderHistoryEmployeeService2(HashMap<String, Integer> mapParam);

	void updateWeekdayProgress(OrderModel order);

	ArrayList<CommentModel> getComment(int employeeId);

	ArrayList<OrderModel> getListOrderOfEmployee(int employeeId);

	ServiceModel getServiceById(int serviceId);
}
