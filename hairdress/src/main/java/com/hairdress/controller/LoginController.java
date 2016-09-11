package com.hairdress.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hairdress.entity.User;
import com.hairdress.service.UserService;


@Controller
public class LoginController {

	
	@Autowired
	private UserService usersService;
	
	private String errorMessage;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(@RequestParam(value = "username", required = true) String username,
                            @RequestParam(value = "password", required = true) String password, Map<String, Object> map, Model model)
    {
        User user = usersService.existUser(username, password);
        if (user != null)
        {
            return "forward:/allCustoms.do";
        }
        else
        {
            errorMessage = "用户名或密码错误";
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("userName", username);
            model.addAttribute("password", password);
            return "forward:/login.jsp";
        }
    }
}
