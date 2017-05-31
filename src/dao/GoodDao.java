package dao;

import java.util.List;

import bean.Good;

public interface GoodDao {
	public List<Good> findGoodsByBuniness(int business_id) throws Exception;
	public Good findGoodByID(int goodID) throws Exception;
}
