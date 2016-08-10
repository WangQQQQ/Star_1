package com.wq.dao;

import java.util.List;

import com.wq.entity.User;

public interface UserDAO {
	public User findByLoginname(String loginname) throws Exception;

	public void updatePwd(String loginName, String pwd) throws Exception;

	public User addUser(User user) throws Exception;

	public List<String> findAllLoginName() throws Exception;
}
