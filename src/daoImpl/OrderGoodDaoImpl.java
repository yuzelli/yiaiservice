package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Order;
import bean.OrderGood;
import bean.UserInfo;
import dao.OrderGoodDao;
import db.DataBaseUtil;

public class OrderGoodDaoImpl implements OrderGoodDao {

	@Override
	public OrderGood addOrderGoods(OrderGood order) throws Exception {
		// TODO Auto-generated method stub
		OrderGood orderGood= null;
		
		try {
			String sqlStr = "insert into ordergoods (order_id,good_id,og_number)"
					+ "values(?,?,?)";
			int num = DataBaseUtil.executeUpdate(
					sqlStr,new Object[] {order.getOrder_id(),order.getGood_id(),order.getOg_number()});
			if (num > 0) {
				List<OrderGood> orderGoodList = findAllOrderGoodsByOrderID(order.getOrder_id());
				for (OrderGood ogDB : orderGoodList) {
					if (ogDB.getGood_id()==ogDB.getGood_id()
							&&ogDB.getOg_number()==ogDB.getOg_number()) {
						orderGood = ogDB;
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

		return orderGood;
	}

	@Override
	public OrderGood updataOrderGoods(OrderGood order) throws Exception {
		// TODO Auto-generated method stub
      OrderGood orderGood= null;
		
		try {
			String sqlStr =  "update ordergoods set order_id=?,good_id=?,og_number=? where ordergoods_id=?";
			int num = DataBaseUtil.executeUpdate(
					sqlStr,new Object[] {order.getOrder_id(),order.getGood_id(),order.getOg_number(),order.getOrdergoods_id()});
			if (num > 0) {
				 orderGood = findOrderGoodsByOrderID(order.getOrdergoods_id());
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConn();
		}

		return orderGood;
	}

	private OrderGood findOrderGoodsByOrderID(int order_id) {
		// TODO Auto-generated method stub
      OrderGood orderGood= null;
		
		try {
			String sqlStrid = "select * from ordergoods where ordergoods_id=?";
			ResultSet rs = DataBaseUtil.executeQuery(sqlStrid,
					new Object[] { order_id });
			while (rs.next()) {
				orderGood = new OrderGood();
				orderGood.setOrdergoods_id(rs.getInt("ordergoods_id"));
				orderGood.setOrder_id(rs.getInt("order_id"));
				orderGood.setGood_id(rs.getInt("good_id"));
				orderGood.setOg_number(rs.getInt("og_number"));
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

		return orderGood;
	}

	@Override
	public List<OrderGood> findAllOrderGoodsByOrderID(int order_id)
			throws Exception {
		// TODO Auto-generated method stub
		List<OrderGood> orderGoodList = new ArrayList<OrderGood>();
		try {
			String sqlStr = "select * from ordergoods where order_id=?";
			ResultSet rs = DataBaseUtil.executeQuery(sqlStr, new Object[]{order_id});

			while (rs.next()) {
				OrderGood orderGood = new OrderGood();
				orderGood.setOrdergoods_id(rs.getInt("ordergoods_id"));
				orderGood.setOrder_id(rs.getInt("order_id"));
				orderGood.setGood_id(rs.getInt("good_id"));
				orderGood.setOg_number(rs.getInt("og_number"));
				orderGoodList.add(orderGood);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBaseUtil.closeConn();
		}
		return orderGoodList;
	}

	
}
