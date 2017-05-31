package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//import oracle.jdbc.driver.OracleDriver;

public class DataBaseUtil {
	public static Connection conn;
	public static PreparedStatement pstmt;
	public static ResultSet rs;


	static{
		try {
			//1.加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//连接数据库
	public static Connection getConnection() throws Exception {
		
		if(conn==null||conn.isClosed()){
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yiai?"
					+ "useUnicode=true&characterEncoding=UTF-8","root","2406862");
		}
		return conn;
	}
	//执行查询操作
		public static ResultSet executeQuery(String sqlStr,Object[] objs) throws Exception{
			conn =getConnection();  //单利模式，获取对象
			pstmt = conn.prepareStatement(sqlStr);
			if(objs!=null){
				for(int i =0;i<objs.length;i++){
					pstmt.setObject(i+1, objs[i]);
				}
			}
			rs = pstmt.executeQuery();
			return rs;
		}	
	//执行更新数据库的操作(insert update delete)
	public static int executeUpdate(String sqlStr,Object[] objs) throws Exception{
		int num = 0;
		conn=getConnection();
		pstmt=conn.prepareStatement(sqlStr);
		if(objs!=null){
			for(int i=0; i<objs.length;i++){
				pstmt.setObject(i+1, objs[i]);
			}
		}
		num = pstmt.executeUpdate();
		return num;
	}
	//关闭连接
	public static void closeConn() throws Exception{
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(conn!=null)
			conn.close();
	}


}
