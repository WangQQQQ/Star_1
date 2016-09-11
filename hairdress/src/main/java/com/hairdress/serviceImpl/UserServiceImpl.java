package com.hairdress.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.hairdress.datasource.ConnectionFactory;
import com.hairdress.entity.User;
import com.hairdress.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Override
	public User existUser(String username, String password) {
		Connection connection = null;
		User user = null;
		String sql = "select id, username, password from user where username = ? and password = ?";
		try {
			connection = ConnectionFactory.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt(1));
				user.setUserName(username);
				user.setPassword(password);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public int addUser(String username, String password) {
		// TODO Auto-generated method stub
		return 0;
	}

}
