package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Order;
import bean.TimeBean;
import dao.TimeDao;
import db.DataBaseUtil;

public class TimeDaoImpl implements TimeDao{

	@Override
	public TimeBean addTime(TimeBean time) throws Exception {
		// TODO Auto-generated method stub
		TimeBean rTime = null;
		try {
			String sqlStr = "insert into mytime (order_id,t_content)"
					+ "values(?,?)";
			int num = DataBaseUtil.executeUpdate(
					sqlStr,new Object[] {time.getOrder_id(),time.getT_content()});
			if (num > 0) {
				List<TimeBean> timeList = findTimeByOrderID(time.getOrder_id());		
					for (TimeBean t : timeList) {
						if (t.getT_content().equals(time.getT_content())) {
							rTime = t;
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

		return rTime;
	}

	@Override
	public List<TimeBean> findTimeByOrderID(int order_id) throws Exception {
		// TODO Auto-generated method stub
		List<TimeBean> timeLists = new ArrayList<TimeBean>();
		try {
			String sqlStr = "select * from mytime where order_id=?";
			ResultSet rs = DataBaseUtil.executeQuery(sqlStr, new Object[]{order_id});

			while (rs.next()) {
				TimeBean t = new TimeBean();
				t.setTime_id(rs.getInt("time_id"));
				t.setOrder_id(rs.getInt("order_id"));
				t.setT_content(rs.getString("t_content"));
				timeLists.add(t);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBaseUtil.closeConn();
		}
		return timeLists;
	}

}
