package com.wq.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wq.dao.UserDAO;
import com.wq.entity.User;
import com.wq.utils.DAOFactory;
import com.wq.utils.MD5Util;

public class LoginServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		try {
			UserDAO userdao = (UserDAO) DAOFactory.getInstance("UserDAO");
			User user = userdao.findByLoginname(request
					.getParameter("loginname"));
			if (user != null
					&& user.getPassword().equals(
							MD5Util.encrypt(request.getParameter("password")))) {
				HttpSession s = request.getSession();
				if (s.getAttribute("code").equals(
						request.getParameter(("code")))) {
					s.setAttribute("loginname", user.getLoginName());
					response.sendRedirect("classList");
				}else{
					request.setAttribute("error", "验证码错误");
					request.getRequestDispatcher("login.jsp").forward(request,
							response);
				}
			} else {
				request.setAttribute("error", "用户名或密码错误");
				request.getRequestDispatcher("login.jsp").forward(request,
						response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}

	}

}
