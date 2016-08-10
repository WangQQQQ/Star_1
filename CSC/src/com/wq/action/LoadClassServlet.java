package com.wq.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wq.dao.ClassDAO;
import com.wq.daoImp.ClassDAOImp;
import com.wq.entity.TbClass;
import com.wq.utils.DAOFactory;

public class LoadClassServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		try {
			ClassDAO classdao = (ClassDAO)DAOFactory.getInstance("ClassDAO");
			TbClass tbclass = classdao.loadClassById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("tbclass", tbclass);
			request.setAttribute("page", request.getParameter("page"));
			RequestDispatcher rd = request.getRequestDispatcher("updateClass.jsp");
			rd.forward(request, response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new ServletException(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
