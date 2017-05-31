package bizImpl;

import java.util.List;

import dao.GoodDao;
import daoImpl.GoodDaoImpl;
import bean.Good;
import biz.GoodBiz;

public class GoodBizImpl implements GoodBiz {
	
	GoodDao goodDao = new GoodDaoImpl();

	@Override
	public List<Good> findGoodsByBuniness(int business_id) throws Exception {
		// TODO Auto-generated method stub
		return goodDao.findGoodsByBuniness(business_id);
	}

	@Override
	public Good findGoodByID(int goodID) throws Exception {
		// TODO Auto-generated method stub
		return goodDao.findGoodByID(goodID);
	}

}
