package biz;

import java.util.List;

import bean.TimeBean;

public interface TimeBiz {
	public TimeBean addTime(TimeBean time) throws Exception;
	public List<TimeBean> findTimeByOrderID(int order_id) throws Exception;
}
