package bizImpl;

import java.util.List;

import dao.TimeDao;
import daoImpl.TimeDaoImpl;
import bean.TimeBean;
import biz.TimeBiz;

public class TimeBizImpl implements TimeBiz{
    TimeDao TimeDao = new TimeDaoImpl();
	@Override
	public TimeBean addTime(TimeBean time) throws Exception {
		// TODO Auto-generated method stub
		return TimeDao.addTime(time);
	}

	@Override
	public List<TimeBean> findTimeByOrderID(int order_id) throws Exception {
		// TODO Auto-generated method stub
		return TimeDao.findTimeByOrderID(order_id);
	}

}
