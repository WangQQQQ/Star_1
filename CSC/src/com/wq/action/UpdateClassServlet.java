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

public class UpdateClassServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		TbClass tbclass = new TbClass();
		tbclass.setId(Long.parseLong(request.getParameter("id")));
		tbclass.setClassName(request.getParameter("class_name"));
		tbclass.setClassTeacher(request.getParameter("class_teacher"));
		try {
			ClassDAO classdao = (ClassDAO)DAOFactory.getInstance("ClassDAO");
			classdao.updateClass(tbclass);
			request.setAttribute("page", Integer.parseInt(request
					.getParameter("page")));
			RequestDispatcher rd = request.getRequestDispatcher("classList");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
