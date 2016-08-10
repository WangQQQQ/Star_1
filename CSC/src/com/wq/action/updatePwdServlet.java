package com.wq.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wq.dao.UserDAO;
import com.wq.entity.User;
import com.wq.utils.DAOFactory;

public class updatePwdServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String s = (String) request.getSession().getAttribute("loginname");
		if (s != null) {
			if (!(request.getParameter("oldPwd").equals("")
					|| request.getParameter("newPwd").equals("")
					|| request.getParameter("confirmPwd").equals(""))) {
				try {
					UserDAO userdao = (UserDAO) DAOFactory
							.getInstance("UserDAO");
					User user = userdao.findByLoginname(s);
					if (user.getPassword().equals(
							request.getParameter("oldPwd"))) {
						if (request.getParameter("newPwd").equals(
								request.getParameter("confirmPwd"))) {
							userdao
									.updatePwd(s, request
											.getParameter("newPwd"));
							request.getSession().setAttribute("error", "修改成功，1秒后自动跳转登录界面");
							response.sendRedirect("login.jsp");
						} else {
							request.getSession().setAttribute("error",
									"两次密码不一致");
							response.sendRedirect("updatePwd.jsp");
						}
					} else {
						request.getSession().setAttribute("error", "原密码不正确");
						response.sendRedirect("updatePwd.jsp");
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}else{
				request.getSession().setAttribute("error", "输入不能为空");
				response.sendRedirect("updatePwd.jsp");
			}
		} else {
			response.sendRedirect("login.jsp");
		}
	}

}
