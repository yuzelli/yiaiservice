package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import net.sf.json.JSONObject;
import utils.MyStringUtlis;
import bean.MyError;
import bean.TimeBean;
import bean.UserInfo;
import biz.TimeBiz;
import bizImpl.TimeBizImpl;
@WebServlet("/TimeServlet")
public class TimeServlet extends HttpServlet{
	  TimeBiz timeBiz = new TimeBizImpl();
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
	 		if ("addTime".equals(type)) {
	 			addTime(req, resp);
				return;
			} else if ("findTimeByOrderID".equals(type)) {
				findTimeByOrderID(req, resp);
			}
	 	}

		private void findTimeByOrderID(HttpServletRequest req,
				HttpServletResponse resp) {
			// TODO Auto-generated method stub
			try {
				int order_id = Integer.parseInt(req.getParameter("order_id"));
				List<TimeBean> list = timeBiz.findTimeByOrderID(order_id);
				
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

		private void addTime(HttpServletRequest req, HttpServletResponse resp) {
			// TODO Auto-generated method stub
			try {
				int order_id = Integer.parseInt(req.getParameter("order_id"));
		
				String t_content =MyStringUtlis.toUTFString(req,"t_content");
				
//			
				TimeBean time = new TimeBean();
				time.setOrder_id(order_id);
				time.setT_content(t_content);
				

				TimeBean userInfo2 = timeBiz.addTime(time);
				if (userInfo2 != null) {
					MyError error = new MyError();
					error.setError("ok");
					error.setObject(userInfo2);
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
