package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Order;
import bean.OrderGood;
import bean.UserInfo;
import dao.OrderDao;
import dao.OrderGoodDao;
import db.DataBaseUtil;

public class OrderDaoImpl implements OrderDao{

	@Override
	public Order addOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		Order orders = null;
		try {
			String sqlStr = "insert into myorder (business_id,user_id,o_remarks,o_pay,o_creattime)"
					+ "values(?,?,?,?,?)";
			int num = DataBaseUtil.executeUpdate(
					sqlStr,new Object[] {order.getBusiness_id(),order.getUser_id(),order.getO_remarks(),order.getO_pay(),order.getO_creattime()});
			if (num > 0) {
				List<Order> userInfoList = findAllOrderByUserID(order.getUser_id());
				for (Order userDB : userInfoList) {
					if (userDB.getBusiness_id()==order.getBusiness_id()
							&&userDB.getO_creattime().equals(order.getO_creattime())) {
								orders = userDB;
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConn();
		}

		return orders;
	}

	@Override
	public Order updataOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		Order userInfo = null;
		try {
		
		String sqlStr = "update myorder set business_id=?,user_id=?,o_remarks=?,o_pay=?,o_creattime=? where order_id=?";
		
		int num = DataBaseUtil.executeUpdate(
					sqlStr,
					new Object[] {order.getBusiness_id(),order.getUser_id(),order.getO_remarks(),order.getO_pay(),order.getO_creattime(),order.getOrder_id() });
		if(num>0){    
		userInfo = findOrderByID(order.getOrder_id());
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataBaseUtil.closeConn();
		}
				
		return userInfo;
	}
	
	

	private Order findOrderByID(int order_id) {
		// TODO Auto-generated method stub
		Order user = null;
		
		try {
			String sqlStrid = "select * from myorder where order_id=?";
			ResultSet rs = DataBaseUtil.executeQuery(sqlStrid,
					new Object[] { order_id });
			while (rs.next()) {
				user = new Order();
				user.setOrder_id(rs.getInt("order_id"));
				user.setBusiness_id(rs.getInt("business_id"));
				user.setUser_id(rs.getInt("user_id"));
				user.setO_remarks(rs.getString("o_remarks"));
				user.setO_pay(rs.getInt("o_pay"));
				user.setO_creattime(rs.getString("o_creattime"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				DataBaseUtil.closeConn();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public List<Order> findAllOrderByUserID(int user_id) throws Exception {
		// TODO Auto-generated method stub
		List<Order> usersList = new ArrayList<Order>();
		try {
			String sqlStr = "select * from myorder where user_id=?";
			ResultSet rs = DataBaseUtil.executeQuery(sqlStr, new Object[]{user_id});

			while (rs.next()) {
				
				Order user = new Order();
				
				user.setOrder_id(rs.getInt("order_id"));
				user.setBusiness_id(rs.getInt("business_id"));
				user.setUser_id(rs.getInt("user_id"));
				user.setO_remarks(rs.getString("o_remarks"));
				user.setO_pay(rs.getInt("o_pay"));
				user.setO_creattime(rs.getString("o_creattime"));
		
				usersList.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBaseUtil.closeConn();
		}
		return usersList;
	}

	@Override
	public List<Order> findAllOrderByBuninessID(int user_id) throws Exception {
		// TODO Auto-generated method stub
		List<Order> usersList = new ArrayList<Order>();
		try {
			String sqlStr = "select * from myorder where business_id=?";
			ResultSet rs = DataBaseUtil.executeQuery(sqlStr, new Object[]{user_id});

			while (rs.next()) {
				Order user = new Order();
				user.setOrder_id(rs.getInt("order_id"));
				user.setBusiness_id(rs.getInt("business_id"));
				user.setUser_id(rs.getInt("user_id"));
				user.setO_remarks(rs.getString("o_remarks"));
				user.setO_pay(rs.getInt("o_pay"));
				user.setO_creattime(rs.getString("o_creattime"));
		
				usersList.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBaseUtil.closeConn();
		}
		return usersList;
	}

	@Override
	public boolean deleteOrder(int order_id) throws Exception {
		// TODO Auto-generated method stub
		String sql = "delete from myorder where order_id=?";
		boolean flag = false;
		try {
			int num = DataBaseUtil.executeUpdate(sql, new Object[] { order_id });
			if (num > 0) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConn();
		}

	
		return flag;
	}

}
