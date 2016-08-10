package com.wq.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wq.dao.ClassDAO;
import com.wq.dao.CourseDAO;
import com.wq.dao.StudentDAO;
import com.wq.entity.Course;
import com.wq.entity.Student;
import com.wq.entity.TbClass;
import com.wq.utils.DAOFactory;

public class TochartServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		try {
			ClassDAO classdao = (ClassDAO) DAOFactory.getInstance("ClassDAO");
			StudentDAO studentdao = (StudentDAO) DAOFactory
					.getInstance("StudentDAO");
			CourseDAO coursedao = (CourseDAO) DAOFactory
					.getInstance("CourseDAO");
			List<TbClass> list1 = classdao.findClass();
			List<String> classlist = new ArrayList<String>();;
			for (int i = 0; i < list1.size(); i++) {
				classlist.add(list1.get(i).getClassName());
			}
			request.setAttribute("classlist", classlist);
			List<Student> list2 = studentdao.findAllStudent();
			List<String> studentlist = new ArrayList<String>();
			for (int i = 0; i < list2.size(); i++) {
				studentlist.add(list2.get(i).getStudentName());
			}
			request.setAttribute("studentlist", studentlist);
			List<String> courselist = coursedao.findAllCourse();
			request.setAttribute("courselist", courselist);
			request.getRequestDispatcher("chart.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
