package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import dao.BuninessDao;
import bean.Buniness;
import bean.MyError;
import bean.UserInfo;
import biz.BuninessBiz;
import bizImpl.BuninessBizImpl;

@WebServlet("/BuninessServlet")
public class BuninessServlet extends HttpServlet {
	BuninessBiz buninessBiz = new BuninessBizImpl();
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
	 		if ("findAllBuniness".equals(type)) {
	 			findAllBuniness(req, resp);
				return;
			}
	 	}

		private void findAllBuniness(HttpServletRequest req,
				HttpServletResponse resp) {
			// TODO Auto-generated method stub
			try {
				List<Buniness> list = buninessBiz.findAllBuniness();
				
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
