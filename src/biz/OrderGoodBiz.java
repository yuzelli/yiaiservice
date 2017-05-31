package biz;

import java.util.List;

import bean.OrderGood;

public interface OrderGoodBiz {
	public OrderGood addOrderGoods(OrderGood order) throws Exception;
	public OrderGood updataOrderGoods(OrderGood order) throws Exception;
	public List<OrderGood> findAllOrderGoodsByOrderID(int order_id) throws Exception;

}
