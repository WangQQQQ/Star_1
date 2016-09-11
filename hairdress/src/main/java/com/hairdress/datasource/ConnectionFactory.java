package com.hairdress.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static Connection connection = null;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		try {
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hairdress?useUnicode=true&characterEncoding=utf8","root","123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
