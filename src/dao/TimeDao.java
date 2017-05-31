package dao;

import java.util.List;

import bean.Order;
import bean.TimeBean;

public interface TimeDao {
	public TimeBean addTime(TimeBean time) throws Exception;
	public List<TimeBean> findTimeByOrderID(int order_id) throws Exception;
}
