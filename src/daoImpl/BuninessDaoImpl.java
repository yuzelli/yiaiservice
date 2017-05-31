package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Buniness;
import bean.UserInfo;
import dao.BuninessDao;
import db.DataBaseUtil;

public class BuninessDaoImpl implements BuninessDao {

	@Override
	public List<Buniness> findAllBuniness() throws Exception {
		// TODO Auto-generated method stub
		List<Buniness> buninessList = new ArrayList<Buniness>();
		try {
			String sqlStr = "select * from business";
			ResultSet rs = DataBaseUtil.executeQuery(sqlStr, null);

			while (rs.next()) {
				Buniness business = new Buniness();
				business.setBusiness_id(rs.getInt("business_id"));
				business.setB_name(rs.getString("b_name"));
				business.setB_notice(rs.getString("b_notice"));
				business.setB_imgurl(rs.getString("b_imgurl"));
				business.setB_phone(rs.getString("b_phone"));
				business.setB_address(rs.getString("b_address"));
				buninessList.add(business);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBaseUtil.closeConn();
		}
		return buninessList;
	}

}
