package biz;

import java.util.List;

import bean.Good;

public interface GoodBiz {
	public List<Good> findGoodsByBuniness(int business_id) throws Exception; 
	public Good findGoodByID(int goodID) throws Exception;
}
