package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GoodDao;
import net.sf.json.JSONObject;
import utils.MyStringUtlis;
import bean.MyError;
import bean.Order;
import bean.OrderGood;
import bean.RetrunOrderGood;
import biz.GoodBiz;
import biz.OrderGoodBiz;
import bizImpl.GoodBizImpl;
import bizImpl.OrderGoodBizImpl;
@WebServlet("/OrderGoodServlet")
public class OrderGoodServlet extends HttpServlet {
	OrderGoodBiz orderGoodBiz =new  OrderGoodBizImpl();
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
 		if ("addOrderGoods".equals(type)) {
 			addOrderGoods(req, resp);
			return;
		} else if ("updataOrderGoods".equals(type)) {
			updataOrderGoods(req, resp);
			return;
		}else if ("findAllOrderGoodsByOrderID".equals(type)) {
			findAllOrderGoodsByOrderID(req, resp);
			return;
		}
 	}

	private void findAllOrderGoodsByOrderID(HttpServletRequest req,
			HttpServletResponse resp) {
		// TODO Auto-generated method stub
		try {
			int order_id = Integer.parseInt(req.getParameter("order_id"));
			List<OrderGood> list = orderGoodBiz.findAllOrderGoodsByOrderID(order_id);
			List<RetrunOrderGood> list2 = new ArrayList<RetrunOrderGood>();
			GoodBiz goodBiz = new GoodBizImpl();
			for (int i = 0; i < list.size(); i++) {
				RetrunOrderGood rg = new RetrunOrderGood();
				rg.setGood(goodBiz.findGoodByID(list.get(i).getGood_id()));
				rg.setOg_number(list.get(0).getOg_number());
				rg.setOrder_id(list.get(0).getOrder_id());
				rg.setOrdergoods_id(list.get(0).getOrdergoods_id());
				list2.add(rg);
			}
			if (list2 != null) {
				MyError error = new MyError();
				error.setError("ok");
				error.setObject(list2);
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

	private void updataOrderGoods(HttpServletRequest req,
			HttpServletResponse resp) {
		// TODO Auto-generated method stub
		try {
			int ordergoods_id = Integer.parseInt(req.getParameter("ordergoods_id"));
			int order_id = Integer.parseInt(req.getParameter("order_id"));
			int good_id = Integer.parseInt(req.getParameter("good_id"));
			int og_number = Integer.parseInt(req.getParameter("og_number"));
		

			
			OrderGood order = new OrderGood();
			order.setOrdergoods_id(ordergoods_id);
			order.setOrder_id(order_id);
			order.setGood_id(good_id);
			order.setOg_number(og_number);
			

			OrderGood order2 = orderGoodBiz.updataOrderGoods(order);
			
			
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

	private void addOrderGoods(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		try {
			int order_id = Integer.parseInt(req.getParameter("order_id"));
			int good_id = Integer.parseInt(req.getParameter("good_id"));
			int og_number = Integer.parseInt(req.getParameter("og_number"));
		

			
			OrderGood order = new OrderGood();
			order.setOrder_id(order_id);
			order.setGood_id(good_id);
			order.setOg_number(og_number);
			

			OrderGood order2 = orderGoodBiz.addOrderGoods(order);
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
