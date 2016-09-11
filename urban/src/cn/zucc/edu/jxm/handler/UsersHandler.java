package cn.zucc.edu.jxm.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.zucc.edu.jxm.entities.Users;
import cn.zucc.edu.jxm.service.UsersService;

@SessionAttributes(value = { "currentUser" })
@Controller
public class UsersHandler {
	@Autowired
	private UsersService usersService;

	private String errorMessage;
	private String nameError;
	private String nullError;
	private String currentName;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(@RequestParam(value = "userName", required = true) String userName,
                            @RequestParam(value = "password", required = true) String password, Map<String, Object> map, Model model)
    {
        Users user = usersService.existUser(userName, password);
        if (user != null)
        {
            map.put("currentUser", user.getUserName());
            if (user.getUserLevel().equals("管理员"))
            {
                return "main";
            }
            else
                return "mainUser";
        }
        else
        {
            errorMessage = "用户名或密码错误";
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("userName", userName);
            model.addAttribute("password", password);
            return "forward:/login.jsp";
        }
    }
    
    @RequestMapping(value="/toRegisterUser",method=RequestMethod.GET)
    public String toRegisterUser(){
    	return "users/registerUser";
    }
    @RequestMapping(value="/toRegisterUser",method=RequestMethod.POST)
    public String toRegisterUser2(){
    	return "users/registerUser";
    }
    
    @RequestMapping(value="/toChangePwd",method=RequestMethod.GET)
    public String toChangePwd(HttpServletRequest request,Map<String,Object> map){
    	String userName=(String) request.getSession().getAttribute("currentUser");
        Users user=usersService.getByUserName(userName);
        map.put("changeUser", user);
    	return "users/changePwd";
    }
    
    @RequestMapping(value="/changePwd",method=RequestMethod.POST)
    public String changePwd(HttpServletRequest request,Map<String,Object> map,@RequestParam(value = "pwd", required = true) String pwd){
    	String userName=(String) request.getSession().getAttribute("currentUser");
        Users user=usersService.getByUserName(userName);
        user.setPassword(pwd);
        usersService.save(user);
    	return "redirect:/backToMain";
    }
    
  /*  @RequestMapping(value="/toForgetPwd",method=RequestMethod.GET)
    public String toForgetPwd(){
    	return "users/forgetPwd";
    }*/
    
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(
			@RequestParam(value = "userName", required = true) String name,
			@RequestParam(value = "pwd", required = true) String pwd,Model model) {
		if(usersService.getByUserName(name) != null){
			nameError = "注册的姓名已存在,请重新输入";
			currentName=name;
			model.addAttribute("rnameError", nameError);
			model.addAttribute("currentName", currentName);
			return "forward:/toRegisterUser";
		}
		Users user=new Users();
		user.setUserName(name);
		user.setPassword(pwd);
		user.setUserLevel("普通用户");
		user.setuStatus("正常");
		usersService.save(user);
		model.addAttribute("userName", name);
		model.addAttribute("password", pwd);
		return "forward:/login.jsp";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/ajaxValidateUser", method = RequestMethod.POST)
	public String validateUserName(
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "password", required = true) String password) {
		Users user = usersService.existUser(userName, password);
		if (user == null) {
			return "0";
		} else {
			return "1";
		}
	}

	@RequestMapping("/list")
	public String list(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
			Map<String, Object> map) {
		int pageNo = 1;
		try {
			// // 对 pageNo 的校验
			pageNo = Integer.parseInt(pageNoStr);
			if (pageNo < 1) {
				pageNo = 1;
			}
		} catch (Exception e) {
		}
		Page<Users> page = usersService.getPage(pageNo, 10);
		map.put("page", page);
		return "users/user";
	}

	@RequestMapping(value = "/backToMain", method = RequestMethod.GET)
	public String backToMain(HttpServletRequest request) {
		String level=userLevel(request);
		if (level.equals("管理员"))
        {
            return "main";
        }
        else
            return "mainUser";
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String input(Map<String, Object> map) {
		Map<String, String> levelMap = new HashMap<String, String>();
		levelMap.put("管理员", "管理员");
		/*levelMap.put("游客", "游客");*/
		/*levelMap.put("普通用户", "普通用户");*/

		map.put("userLevel", levelMap);
		map.put("newUser", new Users());
		return "users/addUser";
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String save(Users user, Model model, Map<String, Object> map) {
		Map<String, String> levelMap = new HashMap<String, String>();
		levelMap.put("管理员", "管理员");
		/*levelMap.put("游客", "游客");*/
		/*levelMap.put("普通用户", "普通用户");*/
		if (usersService.getByUserName(user.getUserName()) == null
				&& !user.getPassword().equals("")) {
			usersService.save(user);
			return "redirect:/list";
		} else if (usersService.getByUserName(user.getUserName()) != null
				&& !user.getPassword().equals("")) {
			nameError = "姓名已存在";
			model.addAttribute("nameError", nameError);
			map.put("newUser", new Users());
			map.put("userLevel", levelMap);
			return "users/addUser";
		} else if (user.getPassword().equals("")
				|| user.getUserName().equals("")) {
			nullError = "请填写完整";
			model.addAttribute("nullError", nullError);
			map.put("newUser", new Users());
			map.put("userLevel", levelMap);
			return "users/addUser";
		}

		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/ajaxValidateNewUserName", method = RequestMethod.POST)
	public String validateUserName(
			@RequestParam(value = "userName", required = true) String userName) {
		Users user = usersService.getByUserName(userName);
		if (user == null)
			return "0";
		else
			return "1";
	}

	@ResponseBody
	@RequestMapping(value = "/ajaxValidateNewUserId", method = RequestMethod.POST)
	public String validateUserId(
			@RequestParam(value = "userId", required = true) int userId) {
		Users user = usersService.getByUserId(userId);
		if (user == null)
			return "0";
		else
			return "1";
	}

	@RequestMapping(value = "/modifyUser/{id}", method = RequestMethod.GET)
	public String modifyUser(@PathVariable("id") int userId,
			Map<String, Object> map) {
		Users user = usersService.getModifyUser(userId);
		Map<String, String> levelMap = new HashMap<String, String>();
		levelMap.put("管理员", "管理员");
		/*levelMap.put("游客", "游客");*/
		levelMap.put("普通用户", "普通用户");

		map.put("userLevel", levelMap);
		map.put("modifyUser", user);
		return "users/modifyUser";

	}

	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.PUT)
	public String updateUser(Users user, Map<String, Object> map,Model model) {
//	    if (usersService.getByUserName(user.getUserName())== null){
	        usersService.save(user);
	        return "redirect:/list";
	   /* }else{
	        nameError = "姓名已存在";
	        model.addAttribute("nameError", nameError);
	        return "users/modifyUser";
	    }*/
		
	}
	
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") int userId) {
        usersService.deleteUser(userId);
        return "redirect:/list";
    }
	
	@ResponseBody
	@RequestMapping(value = "/resetUser/{id}", method = RequestMethod.POST)
	public String resetUser(@PathVariable("id") int userId) {
		Users user=usersService.getByUserId(userId);
		user.setPassword("123456");
		usersService.save(user);
		return "1";
		//return "redirect:/list";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login.jsp";
	}

	@RequestMapping("/searchUser")
	public String searchUser(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
			Map<String, Object> map,
			@RequestParam(value = "searchName", required = true) String searchName) {
		int pageNo = 1;
		try {
			// 对 pageNo 的校验
			pageNo = Integer.parseInt(pageNoStr);
			if (pageNo < 1) {
				pageNo = 1;
			}
		} catch (Exception e) {
		}
		Page<Users> page = usersService.getSearchPage('%' + searchName + '%',
				pageNo, 10);
		map.put("page", page);
		return "users/user";
	}
	
	public String userLevel(HttpServletRequest request){
		String userName=(String) request.getSession().getAttribute("currentUser");
        Users user=usersService.getByUserName(userName);
        return user.getUserLevel();
	}
	
}