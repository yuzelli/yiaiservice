package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import bean.Good;
import bean.MyError;
import bean.UserInfo;
import biz.GoodBiz;
import biz.UserBiz;
import bizImpl.GoodBizImpl;
import bizImpl.UserBizImpl;
@WebServlet("/GoodServlet")
public class GoodServlet extends HttpServlet{
	  GoodBiz goodBiz = new GoodBizImpl();
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
	 		if ("findgoodbybusiness".equals(type)) {
	 			findgoodbybusiness(req, resp);
				return;
			} 
	 	}

		private void findgoodbybusiness(HttpServletRequest req,
				HttpServletResponse resp) {
			// TODO Auto-generated method stub
			try {
				int business_id = Integer.parseInt(req.getParameter("business_id"));
				List<Good> list = goodBiz.findGoodsByBuniness(business_id);
				
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
}
