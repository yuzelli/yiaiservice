package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.UserInfo;
import dao.UserDao;
import db.DataBaseUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public UserInfo registerUserInfo(UserInfo user) throws Exception {
		// TODO Auto-generated method stub
		UserInfo userInfo= null;
		 
		 boolean flag = VerificationUserPhone(user.getU_phone());
		 if(flag){
			 return null;
		 }
		 
		try {
			String sqlStr = "insert into user (u_imgurl,u_phone,u_password,u_name,u_address)"
					+ "values(?,?,?,?,?)";
			int num = DataBaseUtil.executeUpdate(
					sqlStr,new Object[] {user.getU_imgurl(),user.getU_phone(),user.getU_password(),user.getU_name(),user.getU_address()});
			if (num > 0) {
				List<UserInfo> userInfoList = findAllUserInfo();
				for (UserInfo userDB : userInfoList) {
					if (userDB.getU_phone().equals(user.getU_phone())
							&&userDB.getU_password().equals(user.getU_password())) {
						userInfo = userDB;
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConn();
		}

		return userInfo;
	}

	@Override
	public boolean deleteUserInfoByID(int userID) throws Exception {
		// TODO Auto-generated method stub
		String sql = "delete from user where user_id=?";
		boolean flag = false;
		try {
			int num  = DataBaseUtil.executeUpdate(sql, new Object[]{userID});
		    if(num>0){
		    	flag = true;
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataBaseUtil.closeConn();
		}
		
		return flag;
	}

	@Override
	public UserInfo updateUserInfoByID(int userID, UserInfo user)
			throws Exception {
		// TODO Auto-generated method stub
		UserInfo userInfo = null;
		try {
		
		String sqlStr = "update user set u_imgurl=?,u_phone=?,u_password=?,u_name=?,u_address=? where user_id=?";
		
		int num = DataBaseUtil.executeUpdate(
					sqlStr,
					new Object[] {user.getU_imgurl(),user.getU_phone(),user.getU_password(),user.getU_name(),user.getU_address(),userID });
		if(num>0){    
		userInfo = findUserInfoByID(userID);
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataBaseUtil.closeConn();
		}
				
		return userInfo;
	}

	@Override
	public List<UserInfo> findAllUserInfo() throws Exception {
		// TODO Auto-generated method stub
		List<UserInfo> usersList = new ArrayList<UserInfo>();
		try {
			String sqlStr = "select * from user";
			ResultSet rs = DataBaseUtil.executeQuery(sqlStr, null);

			while (rs.next()) {
				UserInfo user = new UserInfo();
				user.setUser_id(rs.getInt("user_id"));
				user.setU_imgurl(rs.getString("u_imgurl"));
				user.setU_phone(rs.getString("u_phone"));
				user.setU_password(rs.getString("u_password"));
				user.setU_name(rs.getString("u_name"));
				user.setU_address(rs.getString("u_address"));
				usersList.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBaseUtil.closeConn();
		}
		return usersList;
	}

	@Override
	public UserInfo findUserInfoByID(int userID) throws Exception {
		// TODO Auto-generated method stub
     UserInfo user = null;
		
		try {
			String sqlStrid = "select * from user where user_id=?";
			ResultSet rs = DataBaseUtil.executeQuery(sqlStrid,
					new Object[] { userID });
			while (rs.next()) {
				user = new UserInfo();
				user.setUser_id(rs.getInt("user_id"));
				user.setU_imgurl(rs.getString("u_imgurl"));
				user.setU_phone(rs.getString("u_phone"));
				user.setU_password(rs.getString("u_password"));
				user.setU_name(rs.getString("u_name"));
				user.setU_address(rs.getString("u_address"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConn();
		}
		return user;
	}

	@Override
	public UserInfo LoginUserInfo(String phone, String passWord)
			throws Exception {
		// TODO Auto-generated method stub
		try {
			List<UserInfo> uList = findAllUserInfo();
			for (UserInfo u : uList) {
				if(u.getU_phone().equals(phone)&&
						u.getU_password().equals(passWord)){
					return u;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataBaseUtil.closeConn();
		}
		return null;
	}

	@Override
	public boolean VerificationUserPhone(String phone) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		List<UserInfo> userList = findAllUserInfo();
		
		for(UserInfo user : userList){
			if(user.getU_phone().equals(phone)){
				flag = true;
			}
		}
		
		return flag;
	}

}
