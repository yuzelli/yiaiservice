package bizImpl;

import java.util.List;

import dao.OrderDao;
import daoImpl.OrderDaoImpl;
import bean.Order;
import biz.OrderBiz;

public class OrderBizImpl implements OrderBiz {
	OrderDao orderDao = new OrderDaoImpl();

	@Override
	public Order addOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		return orderDao.addOrder(order);
	}

	@Override
	public Order updataOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		return orderDao.updataOrder(order);
	}

	@Override
	public List<Order> findAllOrderByUserID(int user_id) throws Exception {
		// TODO Auto-generated method stub
		return orderDao.findAllOrderByUserID(user_id);
	}

	@Override
	public List<Order> findAllOrderByBuninessID(int user_id) throws Exception {
		// TODO Auto-generated method stub
		return orderDao.findAllOrderByBuninessID(user_id);
	}

	@Override
	public boolean deleteOrder(int order_id) throws Exception {
		// TODO Auto-generated method stub
		return orderDao.deleteOrder(order_id);
	}

}
