package com.wq.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class DBUtils {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	static{
		Properties pro = new Properties();
		try {
			pro.load(DBUtils.class.getClassLoader().getResourceAsStream("com/wq/utils/db.properties"));
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			user = pro.getProperty("user");
			password = pro.getProperty("password");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static Connection getConnection()throws Exception{
		try {
			
			return DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
public static void main(String[] args) throws Exception {
	Connection con =DBUtils.getConnection();
	System.out.println(con);
}
}
