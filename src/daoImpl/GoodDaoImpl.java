package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Good;
import bean.OrderGood;
import bean.UserInfo;
import dao.GoodDao;
import db.DataBaseUtil;

public class GoodDaoImpl implements GoodDao{

	@Override
	public List<Good> findGoodsByBuniness(int business_id) throws Exception {
		// TODO Auto-generated method stub
		List<Good> usersList = new ArrayList<Good>();
		try {
			String sqlStr = "select * from good where business_id=?";
			ResultSet rs = DataBaseUtil.executeQuery(sqlStr, new Object[]{business_id});

			while (rs.next()) {
				Good good = new Good();
				good.setGood_id(rs.getInt("good_id"));
				good.setBusiness_id(rs.getInt("business_id"));
				good.setG_name(rs.getString("g_name"));
				good.setG_price(rs.getInt("g_price"));
				usersList.add(good);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBaseUtil.closeConn();
		}
		return usersList;
	}

	@Override
	public Good findGoodByID(int goodID) throws Exception {
		// TODO Auto-generated method stub
		Good good= null;
			
			try {
				String sqlStrid = "select * from good where good_id=?";
				ResultSet rs = DataBaseUtil.executeQuery(sqlStrid,
						new Object[] { goodID });
				while (rs.next()) {
					good = new Good();
					good.setGood_id(rs.getInt("good_id"));
					good.setBusiness_id(rs.getInt("business_id"));
					good.setG_name(rs.getString("g_name"));
					good.setG_price(rs.getInt("g_price"));
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

			return good;
	}

}
