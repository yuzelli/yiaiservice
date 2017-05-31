package control;

import java.io.IOException;








import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderGoodDao;
import daoImpl.OrderGoodDaoImpl;
import net.sf.json.JSONObject;
import utils.MyStringUtlis;
import bean.Good;
import bean.MyError;
import bean.Order;
import bean.OrderGood;
import bean.UserInfo;
import biz.OrderBiz;
import bizImpl.OrderBizImpl;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    OrderBiz uoBiz = new OrderBizImpl();
    @Override
 	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
 			throws ServletException, IOException {
 		// TODO Auto-generated method stub
 		this.doPost(req, resp);
 	}

 	@Override
 	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
 			throws ServletException, IOException {
 		// TODO Auto-generated method stub
 		req.setCharacterEncoding("utf8");
 		resp.setCharacterEncoding("utf8");
 		// 判断
 		String type = req.getParameter("type");
 		if ("addOrder".equals(type)) {
 			addOrder(req, resp);
			return;
		} else if ("updataOrder".equals(type)) {
			updataOrder(req, resp);
			return;
		}else if ("deletOrder".equals(type)) {
			deletOrder(req, resp);
			return;
		}  else if ("findAllOrderByUserID".equals(type)) {
			findAllOrderByUserID(req, resp);
			return;
		} 
		else if ("findAllOrderByBuninessID".equals(type)) {
			findAllOrderByBuninessID(req, resp);
			return;
		} 
 	}

	private void deletOrder(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		try {
			int order_id = Integer.parseInt(req.getParameter("order_id"));
			boolean flag = uoBiz.deleteOrder(order_id);
			
		
			if (flag) {
				
				MyError error = new MyError();
				error.setError("ok");
				error.setObject(flag);
				JSONObject jsonObject = JSONObject.fromObject(error);
				resp.getWriter().print(jsonObject);
			} else {
				MyError error = new MyError();
				error.setError("error");
				error.setObject(flag);
				JSONObject jsonObject = JSONObject.fromObject(error);
				resp.getWriter().print(jsonObject);
			}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void findAllOrderByBuninessID(HttpServletRequest req,
			HttpServletResponse resp) {
		// TODO Auto-generated method stub
		try {
			int business_id = Integer.parseInt(req.getParameter("business_id"));
			List<Order> list = uoBiz.findAllOrderByBuninessID(business_id);
			
//			OrderGoodDao orderGoodDao = new OrderGoodDaoImpl() ;
//			List<Order> list2 = new ArrayList<Order>();
//			for (int i = 0; i < list.size(); i++) {
//				List<OrderGood> ogsGoods = orderGoodDao.findAllOrderGoodsByOrderID(list.get(i).getOrder_id());
//				if (ogsGoods.size()!=0) {
//					list2.add(list.get(i));
//				}
//			}
//			list.clear();
//			list.addAll(list2);
			
			if (list != null) {
				MyError error = new MyError();
				error.setError("ok");
				error.setObject(list);
				JSONObject jsonObject = JSONObject.fromObject(error);
				resp.getWriter().print(jsonObject);
			} else {
				MyError error = new MyError();
				error.setError("error");
				JSONObject jsonObject = JSONObject.fromObject(error);
				resp.getWriter().print(jsonObject);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	private void findAllOrderByUserID(HttpServletRequest req,
			HttpServletResponse resp) {
		// TODO Auto-generated method stub
		try {
			int user_id = Integer.parseInt(req.getParameter("user_id"));
			List<Order> list = uoBiz.findAllOrderByUserID(user_id);
	
			if (list != null) {
				MyError error = new MyError();
				error.setError("ok");
				error.setObject(list);
				JSONObject jsonObject = JSONObject.fromObject(error);
				resp.getWriter().print(jsonObject);
			} else {
				MyError error = new MyError();
				error.setError("error");
				JSONObject jsonObject = JSONObject.fromObject(error);
				resp.getWriter().print(jsonObject);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updataOrder(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		try {
			int ordeer_id = Integer.parseInt(req.getParameter("order_id"));
			int user_id = Integer.parseInt(req.getParameter("user_id"));
			int business_id = Integer.parseInt(req.getParameter("business_id"));
			int o_pay = Integer.parseInt(req.getParameter("o_pay"));
			String o_remarks =MyStringUtlis.toUTFString(req,"o_remarks");
			String o_creattime =MyStringUtlis.toUTFString(req,"o_creattime");

			
			Order order = new Order();
			order.setOrder_id(ordeer_id);
			order.setBusiness_id(business_id);
			order.setUser_id(user_id);
			order.setO_remarks(o_remarks);
			order.setO_pay(o_pay);
			order.setO_creattime(o_creattime);
			

			Order order2 = uoBiz.updataOrder(order);
			if (order2 != null) {
				MyError error = new MyError();
				error.setError("ok");
				error.setObject(order2);
				JSONObject jsonObject = JSONObject.fromObject(error);
				resp.getWriter().print(jsonObject);
			} else {
				MyError error = new MyError();
				error.setError("error");
				JSONObject jsonObject = JSONObject.fromObject(error);
				resp.getWriter().print(jsonObject);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addOrder(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		try {
			int user_id = Integer.parseInt(req.getParameter("user_id"));
			int business_id = Integer.parseInt(req.getParameter("business_id"));
			int o_pay = Integer.parseInt(req.getParameter("o_pay"));
			String o_remarks =MyStringUtlis.toUTFString(req,"o_remarks");
			String o_creattime =MyStringUtlis.toUTFString(req,"o_creattime");

			
			Order order = new Order();
			order.setBusiness_id(business_id);
			order.setUser_id(user_id);
			order.setO_remarks(o_remarks);
			order.setO_pay(o_pay);
			order.setO_creattime(o_creattime);
			

			Order order2 = uoBiz.addOrder(order);
			if (order2 != null) {
				MyError error = new MyError();
				error.setError("ok");
				error.setObject(order2);
				JSONObject jsonObject = JSONObject.fromObject(error);
				resp.getWriter().print(jsonObject);
			} else {
				MyError error = new MyError();
				error.setError("error");
				JSONObject jsonObject = JSONObject.fromObject(error);
				resp.getWriter().print(jsonObject);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
