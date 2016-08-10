package com.wq.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wq.dao.ClassDAO;
import com.wq.entity.TbClass;
import com.wq.utils.DAOFactory;

public class ClassListServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page;
		if ((request.getAttribute("page")) == null) {
			page = 0;
		} else {
			page = (Integer) (request.getAttribute("page"));
		}
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		request.setAttribute("page", page);
		List<TbClass> list = null;
		List<TbClass> list1 = null;
		String s = (String) request.getSession().getAttribute("loginname");
		try {
			if (s != null) {
				ClassDAO classdao = (ClassDAO) DAOFactory
						.getInstance("ClassDAO");
				if (request.getParameter("page") == null) {
					list = classdao.findClassMsgByPage(0);
					list1 = classdao.findClass();
				} else if (Integer.parseInt(request.getParameter("page")) < 0) {
					list = classdao.findClassMsgByPage(0);

				} else {
					list = classdao.findClassMsgByPage(Integer.parseInt(request
							.getParameter("page")));
					request.setAttribute("page", Integer.parseInt(request
							.getParameter("page")));
					if (list.size() == 0 || list1 == null) {
						list = classdao.findClassMsgByPage(Integer
								.parseInt(request.getParameter("page")) - 1);
						request.setAttribute("page", Integer.parseInt(request
								.getParameter("page")) - 1);
					}
				}
				request.setAttribute("classlist", list);
				RequestDispatcher rd = request
						.getRequestDispatcher("classList.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
