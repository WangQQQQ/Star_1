package cn.zucc.edu.jxm.handler;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zucc.edu.jxm.entities.EnterpriseInfo;
import cn.zucc.edu.jxm.entities.Users;
import cn.zucc.edu.jxm.service.EnterpriseInfoService;
import cn.zucc.edu.jxm.service.UsersService;

@Controller
public class EnterpriseInfoHandler {
	@Autowired
	private EnterpriseInfoService enterpriseInfoService;
	private String errorMessage;

	@Autowired
	private UsersService usersService;

	@RequestMapping("/enterpriseList")
	public String list(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
			Map<String, Object> map,HttpServletRequest request) {
		int pageNo = 1;
		try {
			// 对 pageNo 的校验
			pageNo = Integer.parseInt(pageNoStr);
			if (pageNo < 1) {
				pageNo = 1;
			}
		} catch (Exception e) {
		}
		String userName=(String) request.getSession().getAttribute("currentUser");
        Users user=usersService.getByUserName(userName);
		if (user.getUserLevel().equals("管理员"))
        {
			Page<EnterpriseInfo> page = enterpriseInfoService.getPage(pageNo, 6);
			map.put("page", page);
			return "enterprise/enterprise";
        }else{
        	Page<EnterpriseInfo> page = enterpriseInfoService.getPageByUser(user, pageNo, 10);
			map.put("page", page);
			return "enterprise/userEnterprise";
        }
	}
   
	@RequestMapping("/searchEnterprise")
	public String searchEnterprise(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
			Map<String, Object> map,
			@RequestParam(value = "searchName", required = false) String searchName) {
		int pageNo = 1;
		try {
			// 对 pageNo 的校验
			pageNo = Integer.parseInt(pageNoStr);
			if (pageNo < 1) {
				pageNo = 1;
			}
		} catch (Exception e) {
		}
		Page<EnterpriseInfo> page = enterpriseInfoService.searchEnterpriseInfo(
				'%' + searchName + '%', '%' + searchName + '%', pageNo, 6);
		map.put("page", page);
		return "enterprise/enterprise";
	}

	@RequestMapping(value = "/deleteEnterprise/{id}", method = RequestMethod.DELETE)
	public String deleteEnterprise(@PathVariable("id") Integer eId) {
		enterpriseInfoService.deleteEnterprise(eId);
		return "redirect:/enterpriseList";
		/*
		 * usersService.deleteUser(userId); return "redirect:/list";
		 */
	}

	@RequestMapping(value = "/addEnterprise", method = RequestMethod.GET)
	public String input(Map<String, Object> map) {
		map.put("userList", usersService.findAllUser());
		map.put("newEnterprise", new EnterpriseInfo());
		return "enterprise/addEnterprise";
	}

	@RequestMapping(value = "/saveEnterprise", method = RequestMethod.POST)
	public String save(EnterpriseInfo enterpriseInfo, Model model,
			Map<String, Object> map) {
		/*
		 * String enterpriseId=validateEnterpriseId(enterpriseInfo.geteId());
		 * String
		 * enterpriseName=validateEnterpriseName(enterpriseInfo.geteName());
		 * if(enterpriseId.equals("0")&&enterpriseName.equals("0")) {
		 * errorMessage="输入有误"; model.addAttribute("errorMessage",
		 * errorMessage); return "forward:/addEnterprise"; }else{
		 */
		String eName = enterpriseInfo.geteName();
		String eAddress = enterpriseInfo.geteAddress();
		Integer bidNum = enterpriseInfo.getBidNum();
		Date createTime = enterpriseInfo.getCreateTime();
		if (eName.equals("") || eAddress.equals("") || bidNum == null
				|| createTime == null) {
			// System.out.println(enterpriseInfo.getRegisterFund()+""+enterpriseInfo.getCreateTime());
			errorMessage = "请填写完整";
			model.addAttribute("nullError", errorMessage);
			map.put("userList", usersService.findAllUser());
			map.put("newEnterprise", new EnterpriseInfo());
			return "enterprise/addEnterprise";

		} else if (enterpriseInfoService.getByEName(eName) == null
				&& enterpriseInfoService.getByEAddress(eAddress) == null) {
			enterpriseInfoService.save(enterpriseInfo);
			return "redirect:/enterpriseList";
		} else if (enterpriseInfoService.getByEName(eName) != null) {
			errorMessage = "企业名称已存在";
			model.addAttribute("nameError", errorMessage);
			map.put("userList", usersService.findAllUser());
			map.put("newEnterprise", new EnterpriseInfo());
			return "enterprise/addEnterprise";
		}

		return null;
		/*
		 * enterpriseInfoService.save(enterpriseInfo); return
		 * "redirect:/enterpriseList";
		 */
		// }
	}

	@ResponseBody
	@RequestMapping(value = "/ajaxValidateAddress", method = RequestMethod.POST)
	public String validateAddress(
			@RequestParam(value = "eAddress", required = true) String eAddress) {
		EnterpriseInfo enterpriseInfo = enterpriseInfoService
				.getByEAddress(eAddress);
		if (enterpriseInfo == null)
			return "0";
		else
			return "1";
	}

	@ResponseBody
	@RequestMapping(value = "/ajaxValidateNewEnterpriseName", method = RequestMethod.POST)
	public String validateEnterpriseName(
			@RequestParam(value = "eName", required = true) String eName) {
		EnterpriseInfo enterpriseInfo = enterpriseInfoService.getByEName(eName);
		if (enterpriseInfo == null)
			return "0";
		else
			return "1";
	}

	@RequestMapping(value = "/modifyEnterprise/{id}", method = RequestMethod.GET)
	public String modifyEnterprise(@PathVariable("id") Integer eId,
			Map<String, Object> map) {
		EnterpriseInfo enterpriseInfo = enterpriseInfoService.getByEId(eId);
		map.put("modifyEnterpriseInfo", enterpriseInfo);
		map.put("userList", usersService.findAllUser());
		return "enterprise/modifyEnterprise";
	}

	@RequestMapping(value = "/updateEnterprise/{id}", method = RequestMethod.PUT)
	public String updateEnterprise(EnterpriseInfo enterpriseInfo) {
		enterpriseInfoService.save(enterpriseInfo);
		return "redirect:/enterpriseList";
	}
	
	public String userLevel(HttpServletRequest request){
		String userName=(String) request.getSession().getAttribute("currentUser");
        Users user=usersService.getByUserName(userName);
        return user.getUserLevel();
	}

}
