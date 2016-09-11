package com.hairdress.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hairdress.entity.CustomInfo;
import com.hairdress.service.CustomInfoService;

@Controller
public class CustomInfoController {


	
	@Autowired
	private CustomInfoService customService;
	
	
	@RequestMapping(value = "/allCustoms", method = RequestMethod.POST)
    public String loginUser(Map<String, Object> map, Model model)
    {
		List<CustomInfo> customs = customService.findAll();
		map.put("customs", customs);
		return "users/user";
    }

}
