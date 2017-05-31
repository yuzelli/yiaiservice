package bizImpl;

import java.util.List;

import bean.Buniness;
import biz.BuninessBiz;
import dao.BuninessDao;
import daoImpl.BuninessDaoImpl;

public class BuninessBizImpl implements BuninessBiz {
    BuninessDao buninessDao = new BuninessDaoImpl();
	@Override
	public List<Buniness> findAllBuniness() throws Exception {
		// TODO Auto-generated method stub
		return buninessDao.findAllBuniness();
	}

}
