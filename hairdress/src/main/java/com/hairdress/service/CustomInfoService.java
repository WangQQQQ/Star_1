package com.hairdress.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hairdress.entity.CustomInfo;

@Service
public interface CustomInfoService {


	List<CustomInfo> findAll();
	
	CustomInfo findByTelNumber();
	
	int addCustom(CustomInfo custom);
	
	int delCustom(CustomInfo custom);

}
