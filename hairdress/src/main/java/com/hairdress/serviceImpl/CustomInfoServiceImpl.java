package com.hairdress.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hairdress.datasource.ConnectionFactory;
import com.hairdress.entity.CustomInfo;
import com.hairdress.service.CustomInfoService;

@Component
public class CustomInfoServiceImpl implements CustomInfoService {

	@Override
	public List<CustomInfo> findAll() {

		Connection connection = null;
		List<CustomInfo> customs = new ArrayList<CustomInfo>();
		String sql = "select id, user_name, user_tel_number, stored_money, remain_money, remain_hairdress_count, status from hair_dress_info where status = ?";
		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "A");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CustomInfo custom = new CustomInfo();
				custom.setId(rs.getInt(1));
				custom.setUsername(rs.getString(2));
				custom.setUserTelNumber(rs.getLong(3));
				custom.setStoredMoney(rs.getInt(4));
				custom.setRemainMoney(rs.getInt(5));
				custom.setRemainHairdressCount(rs.getInt(6));
				custom.setStatus(rs.getString(7));
				customs.add(custom);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return customs;
	}

	@Override
	public CustomInfo findByTelNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addCustom(CustomInfo custom) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delCustom(CustomInfo custom) {
		// TODO Auto-generated method stub
		return 0;
	}

}
