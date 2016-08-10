package com.wq.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wq.dao.UserDAO;
import com.wq.entity.User;
import com.wq.utils.DAOFactory;
import com.wq.utils.MD5Util;

public class RegistServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		UserDAO userdao = null;
		List<String> list = null;
		PrintWriter out = response.getWriter();
		HttpSession s = request.getSession();
		try {
			userdao = (UserDAO) DAOFactory.getInstance("UserDAO");
			String uri = request.getRequestURI();
			String action = uri.substring(uri.lastIndexOf("/"), uri
					.lastIndexOf("."));
			if (action.equals("/load")) {
				list = new ArrayList<String>();
				list = userdao.findAllLoginName();
				request.setAttribute("allloginname", list);
				request.getRequestDispatcher("regist.jsp").forward(request,
						response);
				s.setAttribute("error", "");
			} else if (action.equals("/regist")) {
				User user = new User();
				if (request.getParameter("loginname").trim().equals("")
						|| request.getParameter("username").trim().equals("")
						|| request.getParameter("pwd").trim().equals("")) {
					s.setAttribute("error", "格式错误");
					response.sendRedirect("regist.jsp");
				}else{
					
					if (s.getAttribute("code").equals(request.getParameter("code"))) {
						user.setLoginName(request.getParameter("loginname"));
						user.setUserName(request.getParameter("username"));
						user.setPassword(MD5Util.encrypt(request
								.getParameter("pwd")));
						user.setGender(request.getParameter("sex").charAt(0));
						userdao.addUser(user);
						response.sendRedirect("login.jsp");
					} else {
						s.setAttribute("error", "验证码错误");
						response.sendRedirect("regist.jsp");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		out.close();
	}
}
