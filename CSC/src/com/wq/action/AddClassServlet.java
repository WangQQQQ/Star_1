package com.wq.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wq.dao.ClassDAO;
import com.wq.daoImp.ClassDAOImp;
import com.wq.entity.TbClass;
import com.wq.utils.DAOFactory;

public class AddClassServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String str1 = request.getParameter("class_name").trim();
		String str2 = request.getParameter("class_teacher").trim();
		if(!str1.equals("")&&!str2.equals("")){
			TbClass tbclass = new TbClass();
			tbclass.setClassName(str1);
			tbclass.setClassTeacher(str2);
			try {
				ClassDAO classdao = (ClassDAO)DAOFactory.getInstance("ClassDAO");
				classdao.addClass(tbclass);
				response.sendRedirect("classList");
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		}else{
			response.sendRedirect("addClass.jsp");
		}
	}

}
