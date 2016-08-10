package com.wq.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wq.dao.ScoreDAO;
import com.wq.dao.StudentDAO;
import com.wq.entity.Student;
import com.wq.utils.DAOFactory;

public class StudentListServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
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
		StudentDAO studentdao = null;
		if (s != null) {
			try {
				studentdao = (StudentDAO) DAOFactory.getInstance("StudentDAO");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String uri = request.getRequestURI();
			String action = uri.substring(uri.lastIndexOf("/"), uri
					.lastIndexOf("."));
			if (action.equals("/studentList")) {

				List<Student> list = null;
				List<Student> list1 = null;
				try {
					list = new ArrayList<Student>();
					if (request.getParameter("page") == null) {
						list = studentdao.findStudentMsgByPage(0);
						list1 = studentdao.findAllStudent();
					} else if (Integer.parseInt(request.getParameter("page")) < 0) {
						list = studentdao.findStudentMsgByPage(0);
					} else {
						list = studentdao.findStudentMsgByPage(Integer
								.parseInt(request.getParameter("page")));
						request.setAttribute("page", Integer.parseInt(request
								.getParameter("page")));
						if (list.size() == 0 || list1 == null) {
							list = studentdao
									.findStudentMsgByPage(Integer
											.parseInt(request
													.getParameter("page")) - 1);
							request
									.setAttribute("page", Integer
											.parseInt(request
													.getParameter("page")) - 1);
						}
					}
						request.setAttribute("studentList", list);
						request.getRequestDispatcher("studentList.jsp").forward(
								request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			} else if (action.equals("/loadStudent")) {

				try {
					Student student = studentdao.loadStudentById(Integer
							.parseInt(request.getParameter("id")));
					List<String> list = studentdao.findAllClassFromClass();
					request.setAttribute("student", student);
					request.setAttribute("allClass", list);
					request.setAttribute("page", request.getParameter("page"));
					RequestDispatcher rd = request
							.getRequestDispatcher("updateStudent.jsp");
					rd.forward(request, response);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					throw new ServletException(e);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			} else if (action.equals("/updateStudent")) {

				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html");
				Student student = new Student();
				student.setId(Long.parseLong(request.getParameter("id")));
				student.setStudentName(request.getParameter("name"));
				student.setGender(request.getParameter("sex").charAt(0));
				student.setDate(request.getParameter("birthday"));
				student.setClassName(request.getParameter("class"));
				try {
					studentdao.updateStudent(student);
					request.setAttribute("page", Integer.parseInt(request
							.getParameter("page")));
					RequestDispatcher rd = request
							.getRequestDispatcher("studentList.do2");
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			} else if (action.equals("/deleteStudent")) {

				try {
					ScoreDAO scoredao = (ScoreDAO) DAOFactory
							.getInstance("ScoreDAO");
					String studentname = studentdao.findStudentNameById(Long
							.parseLong(request.getParameter("id")));
					System.out.println(studentname + "********");
					scoredao.deleteScoreByName(studentname);
					studentdao.deleteStudentById(Integer.parseInt(request
							.getParameter("id")));
					request.setAttribute("page", Integer.parseInt(request
							.getParameter("page")));
					request.setAttribute("id", Integer.parseInt(request
							.getParameter("page")));
					RequestDispatcher rd = request
							.getRequestDispatcher("studentList.do2");
					rd.forward(request, response);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					throw new ServletException(e);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			} else if (action.equals("/addStudent")) {
				String str1 = request.getParameter("studentname").trim();
				String str2 = request.getParameter("birthday");
				if (!str1.equals("") && !str2.equals("")) {

					try {
						Student student = new Student();
						student.setStudentName(str1);
						student
								.setGender(request.getParameter("sex")
										.charAt(0));
						student.setDate(str2);
						student.setClassName(request.getParameter("class"));
						student.setClassId(studentdao
								.findclassIdByClassName(request
										.getParameter("class")));
						studentdao.addStudent(student);
						response.sendRedirect("studentList.do2");
					} catch (Exception e) {
						e.printStackTrace();
						throw new ServletException(e);
					}
				} else {
					response.sendRedirect("addStudent.jsp");
				}
			} else if (action.equals("/loadClass")) {
				List<String> list = null;
				try {
					list = studentdao.findAllClassFromClass();
					request.getSession().setAttribute("allClass", list);
					// request.setAttribute("allClass", list);
					response.sendRedirect("addStudent.jsp");
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			} else if (action.equals("/addCourse")) {
				try {

					String str = request.getParameter("course");
					System.out.println(studentdao.findcourseIdByStuName(request
							.getParameter("name")));
					if (studentdao.findcourseIdByStuName(request
							.getParameter("name")) == 0) {
						studentdao.updatecourseId(studentdao
								.findcourseIdByCourseName(str), request
								.getParameter("name"));
						request.setAttribute("page", Integer.parseInt(request
								.getParameter("page")));
						request.getRequestDispatcher("studentList.do2")
								.forward(request, response);
					} else {
						Student student = studentdao.findStudentByName(request
								.getParameter("name"));
						student.setCourseId(studentdao
								.findcourseIdByCourseName(str));
						studentdao.addStudentHasCourse(student);
						request.setAttribute("page", Integer.parseInt(request
								.getParameter("page")));
						request.getRequestDispatcher("studentList.do2")
								.forward(request, response);
					}
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
