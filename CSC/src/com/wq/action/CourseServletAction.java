package com.wq.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wq.dao.CourseDAO;
import com.wq.dao.StudentDAO;
import com.wq.entity.Course;
import com.wq.entity.Student;
import com.wq.utils.DAOFactory;

public class CourseServletAction extends HttpServlet {
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
		String s = (String) request.getSession().getAttribute("loginname");
		CourseDAO coursedao = null;
		if (s != null) {

			try {
				coursedao = (CourseDAO) DAOFactory.getInstance("CourseDAO");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String uri = request.getRequestURI();
			String action = uri.substring(uri.lastIndexOf("/"), uri
					.lastIndexOf("."));
			if (action.equals("/courseList")) {
				List<Course> list = null;
				List<Course> list1 = null;
				try {
					list = new ArrayList<Course>();
					if (request.getParameter("page") == null) {
						list = coursedao.findCourseMsgByPage(0);
						list1 = coursedao.findCourse();
					} else if (Integer.parseInt(request.getParameter("page")) < 0) {
						list = coursedao.findCourseMsgByPage(0);
					} else {
						list = coursedao.findCourseMsgByPage(Integer
								.parseInt(request.getParameter("page")));
						request.setAttribute("page", Integer.parseInt(request
								.getParameter("page")));
						if (list.size() == 0 || list1 == null) {
							list = coursedao
									.findCourseMsgByPage(Integer
											.parseInt(request
													.getParameter("page")) - 1);
							request
									.setAttribute("page", Integer
											.parseInt(request
													.getParameter("page")) - 1);
						}
					}
					request.setAttribute("courselist", list);
					request.getRequestDispatcher("courseList.jsp").forward(
							request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			} else if (action.equals("/deleteCourse")) {
				try {
					coursedao.deleteCourseById(Integer.parseInt(request
							.getParameter("id")));
					request.setAttribute("page", Integer.parseInt(request
							.getParameter("page")));
					request.setAttribute("id", Integer.parseInt(request
							.getParameter("page")));
					RequestDispatcher rd = request
							.getRequestDispatcher("courseList.do");
					rd.forward(request, response);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					throw new ServletException(e);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			} else if (action.equals("/updateCourse")) {
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html");
				Course course = new Course();
				course.setId(Long.parseLong(request.getParameter("id")));
				course.setCourseName(request.getParameter("course_name"));
				course.setTeacherName(request.getParameter("teacher_name"));
				try {
					coursedao.updateCourse(course);
					request.setAttribute("page", Integer.parseInt(request
							.getParameter("page")));
					RequestDispatcher rd = request
							.getRequestDispatcher("courseList.do");
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			} else if (action.equals("/loadCourse")) {
				try {
					Course course = coursedao.loadCourseById(Integer
							.parseInt(request.getParameter("id")));
					request.setAttribute("course", course);
					request.setAttribute("page", request.getParameter("page"));
					RequestDispatcher rd = request
							.getRequestDispatcher("updateCourse.jsp");
					rd.forward(request, response);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					throw new ServletException(e);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			} else if (action.equals("/addCourse")) {
				String str1 = request.getParameter("course_name").trim();
				String str2 = request.getParameter("teacher_name").trim();
				if (!str1.equals("") && !str2.equals("")) {
					Course course = new Course();

					course.setCourseName(str1);
					course.setTeacherName(str2);
					try {
						coursedao.addCourse(course);
						response.sendRedirect("courseList.do");
					} catch (Exception e) {
						e.printStackTrace();
						throw new ServletException(e);
					}
				} else {
					response.sendRedirect("addCourse.jsp");
				}
			} else if (action.equals("/chooseCourse")) {
				Student student = null;
				List<String> list = new ArrayList<String>();
				try {
					StudentDAO studentdao = (StudentDAO) DAOFactory
							.getInstance("StudentDAO");
					list = coursedao.findAllCourse();
					student = studentdao.loadStudentById(Long.parseLong(request
							.getParameter("id")));
					request.setAttribute("name", student.getStudentName());
					request.setAttribute("allCourse", list);
					request.setAttribute("page", Integer.parseInt(request
							.getParameter("page")));
					request.getRequestDispatcher("chooseCourse.jsp").forward(
							request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
		} else {
			response.sendRedirect("login.jsp");
		}
	}
}
