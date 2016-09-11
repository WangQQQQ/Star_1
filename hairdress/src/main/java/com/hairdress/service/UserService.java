package com.hairdress.service;

import org.springframework.stereotype.Service;

import com.hairdress.entity.User;

@Service
public interface UserService {

	User existUser(String username, String password);

	int addUser(String username, String password);
}
