package com.wq.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wq.dao.UserDAO;
import com.wq.entity.User;
import com.wq.utils.DBUtils;

public class UserDAOImp implements UserDAO {

	public User findByLoginname(String loginname) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("select *from user where login_name = ?");
			stmt.setString(1, loginname);
			rs = stmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setLoginName(loginname);
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getString("gender").charAt(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			} else if (stmt != null) {
				stmt.close();
			} else if (con != null) {
				con.close();
			}
		}
		return user;
	}

	public void updatePwd(String loginName, String pwd) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("update user set password = ? where login_name = ?");
			stmt.setString(1, pwd);
			stmt.setString(2, loginName);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			} else if (stmt != null) {
				stmt.close();
			} else if (con != null) {
				con.close();
			}
		}
	}

	public User addUser(User user) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("insert into user (login_name,user_name,password,gender) values(?,?,?,?)");
			stmt.setString(1, user.getLoginName());
			stmt.setString(2, user.getUserName());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getGender()+"");
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			} else if (stmt != null) {
				stmt.close();
			} else if (con != null) {
				con.close();
			}
		}
		return user;
	}

	public List<String> findAllLoginName() throws Exception {


		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> list = null;
		try {
			list = new ArrayList<String>();
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("select login_name from user");
			rs = stmt.executeQuery();
			while(rs.next()){
				list.add(rs.getString("login_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			} else if (stmt != null) {
				stmt.close();
			} else if (con != null) {
				con.close();
			}
		}
		return list;
	
	}
}
