package vn.com.arillance.honestfood.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vn.com.arillance.honestfood.exception.ErrorMessage;
import vn.com.arillance.honestfood.mapper.OrderMapper;
import vn.com.arillance.honestfood.mapper.UserMapper;
import vn.com.arillance.honestfood.model.CartModel;
import vn.com.arillance.honestfood.model.CategoryModel;
import vn.com.arillance.honestfood.model.CommentModel;
import vn.com.arillance.honestfood.model.DistrictModel;
import vn.com.arillance.honestfood.model.FeedbackModel;
import vn.com.arillance.honestfood.model.FoodCategoryModel;
import vn.com.arillance.honestfood.model.FoodModel;
import vn.com.arillance.honestfood.model.FoodUsersModel;
import vn.com.arillance.honestfood.model.OrderModel;
import vn.com.arillance.honestfood.model.ServiceModel;
import vn.com.arillance.honestfood.utils.HTTPUtils;

public class OrderDao {
	public List<OrderModel> getActiveOrder(int userId) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		List<OrderModel> orders = mapper.getActiveOrder(userId);
		session.close();
		return orders;
	}

	public ServiceModel getServiceById(int serviceId) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		ServiceModel model = mapper.getServiceById(serviceId);
		session.close();
		return model;
	}

	public ArrayList<CategoryModel> getListCategory() {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);

		ArrayList<CategoryModel> cats = mapper.getListCategory();
		session.close();
		return cats;
	}

	public ArrayList<FoodCategoryModel> getListFoodCategory(int catId) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);

		ArrayList<FoodCategoryModel> listFood = mapper.getListFoodCategory(catId);
		session.close();
		return listFood;
	}

	public ArrayList<FoodModel> getListFood(int foodCatId) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);

		ArrayList<FoodModel> listFood = mapper.getListFood(foodCatId);
		session.close();
		return listFood;
	}

	// public OrderModel addToCart(OrderModel order) {
	// SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
	// OrderMapper mapper = session.getMapper(OrderMapper.class);
	//
	//
	// session.close();
	//
	// }

	public OrderModel reqOrder(OrderModel order) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);

		UserMapper userMapper = session.getMapper(UserMapper.class);

		// caculate shift fee
		double shiftFee;
		if (order.getBillAmount() <= 200000) {
			shiftFee = 30000;
		} else {
			shiftFee = 0;
		}
		order.setShiftFee(shiftFee);

		mapper.requestOrder(order);

		String orderCode = "CH" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + ""
				+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "" + Calendar.getInstance().get(Calendar.YEAR)
				+ +order.getOrderId();

		order.setOrderCode(orderCode);

		mapper.updateOrderCode(order);

		if ((order.getOrderId() > 0) && (order.getFoodList().size() > 0)) {
			try {
				mapper.requestOrderFoodShop(order);

				session.commit();

			} catch (Exception e) {
				e.printStackTrace();
				mapper.removeOrder(order.getOrderId());
			}

//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					// Push notification for employee
//					if (order.getFoodList() != null) {
//						List<String> notiTokens = new ArrayList<String>();
//						UserDao userDao = new UserDao();
//						for (FoodModel food : order.getFoodList()) {
//							String notiToken = userDao.getNotiToken(food.getShop().getUserId());
//							notiTokens.add(notiToken);
//							HTTPUtils.pushNotficationToAllDevices(notiToken, "Bạn có đơn hàng mới",
//									"Bạn vừa nhận được đơn hàng mới!", HTTPUtils.NEW_ORDER);
//						}
//					}
//
//				}
//			}).start();
		}
		session.close();
		return order;
	}

	public ArrayList<OrderModel> getHistoryOrder(int ownerId) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);

		ArrayList<OrderModel> listHistoryOrder = mapper.getHistoryOrder(ownerId);
		session.close();
		return listHistoryOrder;
	}

	public FeedbackModel getFeedback(int employeeId) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);

		FeedbackModel feedback = mapper.getFeedback(employeeId);
		if (feedback == null) {
			mapper.createFeedback(employeeId);
			session.commit();

			feedback = new FeedbackModel();
			feedback.setEmployeeId(employeeId);
			feedback.setRateCount(0);
			feedback.setRateTotal(0);
		}
		session.close();
		return feedback;
	}

	public void updateRate(FeedbackModel feedback) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		mapper.updateRate(feedback);
		session.commit();

		session.close();
	}

	public void updateComment(FeedbackModel feedback) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		mapper.updateComment(feedback);
		session.commit();

		session.close();
	}

	public OrderModel getOrder(int orderId) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);

		return mapper.getOrder(orderId);
	}

	public void cusConfirmOrder(OrderModel order) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);

		mapper.cusConfirmOrder(order);

		session.commit();
		session.close();
	}

	public ArrayList<OrderModel> getOrderForEmployee(int districtId, int serviceId) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		HashMap<String, Integer> mapParam = new HashMap<String, Integer>();
		mapParam.put("districtId", districtId);
		mapParam.put("serviceId", serviceId);
		ArrayList<OrderModel> listOrderForEmployee = mapper.getOrderForEmployee(mapParam);
		session.close();
		return listOrderForEmployee;
	}

	public ArrayList<DistrictModel> getListDistrict(int serviceId) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);

		ArrayList<DistrictModel> listDistrict = mapper.getListDistrict(serviceId);
		session.close();
		return listDistrict;

	}

	public ArrayList<OrderModel> getOrderTakenService2(int employeeid, int serviceId) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		HashMap<String, Integer> mapParam = new HashMap<String, Integer>();
		mapParam.put("employeeId", employeeid);
		mapParam.put("serviceId", serviceId);
		ArrayList<OrderModel> listOrderTaken = mapper.getOrderTakenService2(mapParam);
		session.close();
		return listOrderTaken;
	}

	public ArrayList<OrderModel> getOrderTaken(int employeeid, int serviceId) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		HashMap<String, Integer> mapParam = new HashMap<String, Integer>();
		mapParam.put("employeeId", employeeid);
		mapParam.put("serviceId", serviceId);
		ArrayList<OrderModel> listOrderTaken = mapper.getOrderTaken(mapParam);
		session.close();
		return listOrderTaken;
	}

	public void takeOrder(OrderModel order) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		mapper.takeOrder(order);
		mapper.deleteTempAssigner(order);

		session.commit();

		session.close();

	}

	public void employeeCancelOrder(OrderModel order) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		mapper.employeeCancelOrder(order);
		session.commit();

		session.close();
	}

	public void employeeConfirmOrder(OrderModel order) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		mapper.employeeConfirmOrder(order);

		// Push notification for customer
		UserDao userDao = new UserDao();
		String notiToken = userDao.getNotiToken(order.getOwner().getUserId());
		HTTPUtils.pushNotficationToAllDevices(notiToken, "�?ơn hàng được xác nhận", "Bạn vừa có đơn hàng được xác nhận!",
				HTTPUtils.NEW_ORDER_CONFIRM);

		session.commit();

		session.close();
	}

	public ErrorMessage empCancelOrder(int orderId, int assignId, int userId, int reasonId) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		// HashMap<String, Integer> mapParam = new HashMap<String, Integer>();
		// mapParam.put("orderId", orderId);
		// mapParam.put("ownerId", ownerId);
		// mapParam.put("userId", userId);
		if (assignId == userId) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("orderId", orderId);
			map.put("cancel_reason_id", reasonId);
			int update = mapper.empCancelOrder(map);
			session.commit();
			session.close();
			if (update > 0) {
				return new ErrorMessage(1, "sucess");
			} else {
				return new ErrorMessage(0, "No update in Database");
			}
		} else {
			return new ErrorMessage(0, "ID not match");
		}
	}

	public void empRegOrder(OrderModel order) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		mapper.empRegOrder(order);
		session.commit();

		session.close();
	}

	public ArrayList<OrderModel> getOrderHistoryEmployeeService2(int employeeid, int serviceId) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		HashMap<String, Integer> mapParam = new HashMap<String, Integer>();
		mapParam.put("employeeId", employeeid);
		mapParam.put("serviceId", serviceId);
		ArrayList<OrderModel> listOrderForEmployee = mapper.getOrderHistoryEmployeeService2(mapParam);
		session.close();
		return listOrderForEmployee;
	}

	public ArrayList<OrderModel> getOrderHistoryEmployee(int employeeid, int serviceId) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		HashMap<String, Integer> mapParam = new HashMap<String, Integer>();
		mapParam.put("employeeId", employeeid);
		mapParam.put("serviceId", serviceId);
		ArrayList<OrderModel> listOrderForEmployee = mapper.getOrderHistoryEmployee(mapParam);
		session.close();
		return listOrderForEmployee;
	}

	public ArrayList<CommentModel> getComment(int employeeId) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);

		ArrayList<CommentModel> comments = mapper.getComment(employeeId);
		if (comments == null)
			comments = new ArrayList<>();
		session.close();

		return comments;
	}

	public String getNumberUnreadOrderCus(int userId, int roleType) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("userId", userId);
		map.put("roleType", roleType);
		int count = mapper.getNumberUnreadOrder(map);
		String json = "{\"counter\":" + count + "}";
		session.close();
		return json;
	}

	public ErrorMessage updateReadFlag(int orderId) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		int update = mapper.updateReadFlag(orderId);
		session.commit();
		session.close();
		if (update > 0) {
			return new ErrorMessage(1, "success");
		} else {
			return new ErrorMessage(0, "no row updated!");
		}
	}

	// public String generateCode() {
	// SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
	// OrderMapper mapper = session.getMapper(OrderMapper.class);
	// int seqNumber = mapper.getOrderMaxCode()+1;
	// mapper.generateOrderCode(seqNumber);
	// session.commit();
	//
	// String fullCode = "CH" +
	// Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "" +
	// (Calendar.getInstance().get(Calendar.MONTH) + 1) + "" +
	// Calendar.getInstance().get(Calendar.YEAR) + seqNumber;
	// return fullCode;
	// }

}
