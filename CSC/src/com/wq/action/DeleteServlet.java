package com.wq.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wq.dao.ClassDAO;
import com.wq.daoImp.ClassDAOImp;
import com.wq.utils.DAOFactory;

public class DeleteServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		try {
			ClassDAO classdao = (ClassDAO)DAOFactory.getInstance("ClassDAO");
			classdao.deleteClassById(Integer.parseInt(request
					.getParameter("id")));
			request.setAttribute("page", Integer.parseInt(request
					.getParameter("page")));
			request.setAttribute("id", Integer.parseInt(request
					.getParameter("page")));
			RequestDispatcher rd = request.getRequestDispatcher("classList");
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
