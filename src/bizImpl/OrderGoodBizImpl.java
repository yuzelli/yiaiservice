package bizImpl;

import java.util.List;

import dao.OrderGoodDao;
import daoImpl.OrderGoodDaoImpl;
import bean.OrderGood;
import biz.OrderGoodBiz;

public class OrderGoodBizImpl implements OrderGoodBiz{
	OrderGoodDao orderGoodDao = new OrderGoodDaoImpl();

	@Override
	public OrderGood addOrderGoods(OrderGood order) throws Exception {
		// TODO Auto-generated method stub
		return orderGoodDao.addOrderGoods(order);
	}

	@Override
	public OrderGood updataOrderGoods(OrderGood order) throws Exception {
		// TODO Auto-generated method stub
		return orderGoodDao.updataOrderGoods(order);
	}

	@Override
	public List<OrderGood> findAllOrderGoodsByOrderID(int order_id)
			throws Exception {
		// TODO Auto-generated method stub
		return orderGoodDao.findAllOrderGoodsByOrderID(order_id);
	}

}
