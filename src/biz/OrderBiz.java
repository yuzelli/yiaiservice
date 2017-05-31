package biz;

import java.util.List;

import bean.Order;

public interface OrderBiz {
	public Order addOrder(Order order) throws Exception;
	public Order updataOrder(Order order) throws Exception;
	public List<Order> findAllOrderByUserID(int user_id) throws Exception;
	public List<Order> findAllOrderByBuninessID(int user_id) throws Exception;
	public boolean deleteOrder(int order_id) throws Exception;
	
}
