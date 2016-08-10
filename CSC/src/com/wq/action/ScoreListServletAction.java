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
import com.wq.dao.ScoreDAO;
import com.wq.entity.Course;
import com.wq.entity.Score;
import com.wq.utils.DAOFactory;

public class ScoreListServletAction extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
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
		ScoreDAO scoredao = null;
		if (s != null) {
			try {
				scoredao = (ScoreDAO) DAOFactory.getInstance("ScoreDAO");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String uri = request.getRequestURI();
			String action = uri.substring(uri.lastIndexOf("/"), uri
					.lastIndexOf("."));
			if (action.equals("/scoreList")) {
				List<Score> list = null;
				List<Score> list1 = null;
				try {
					if (request.getParameter("page") == null) {
						list = scoredao.findScoreMsgByPage(0);
						list1 = scoredao.findAllScore();
					} else if (Integer.parseInt(request.getParameter("page")) < 0) {
						list = scoredao.findScoreMsgByPage(0);
					} else {
						list = scoredao.findScoreMsgByPage(Integer
								.parseInt(request.getParameter("page")));
						request.setAttribute("page", Integer.parseInt(request
								.getParameter("page")));
						if (list.size() == 0 || list1 == null) {
							list = scoredao
									.findScoreMsgByPage(Integer
											.parseInt(request
													.getParameter("page")) - 1);
							request
									.setAttribute("page", Integer
											.parseInt(request
													.getParameter("page")) - 1);
						}
					}
					request.setAttribute("scorelist", list);
					request.getRequestDispatcher("scoreList.jsp").forward(
							request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			} else if (action.equals("/deleteScore")) {

				try {
					scoredao.deleteScoreById(Integer.parseInt(request
							.getParameter("id")));
					request.setAttribute("page", Integer.parseInt(request
							.getParameter("page")));
					request.setAttribute("id", Integer.parseInt(request
							.getParameter("page")));
					RequestDispatcher rd = request
							.getRequestDispatcher("scoreList.do1");
					rd.forward(request, response);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					throw new ServletException(e);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}

			} else if (action.equals("/updateScore")) {

				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html");
				Score score = new Score();
				score.setId(Long.parseLong(request.getParameter("id")));
				score.setCourseScore(Double.parseDouble(request
						.getParameter("score")));
				try {
					scoredao.updateScore(score);
					request.setAttribute("page", Integer.parseInt(request
							.getParameter("page")));
					RequestDispatcher rd = request
							.getRequestDispatcher("scoreList.do1");
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}

			} else if (action.equals("/loadScore")) {

				try {
					Score score = scoredao.loadScoreById(Integer
							.parseInt(request.getParameter("id")));
					request.setAttribute("score", score);
					request.setAttribute("page", request.getParameter("page"));
					RequestDispatcher rd = request
							.getRequestDispatcher("updateScore.jsp");
					rd.forward(request, response);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					throw new ServletException(e);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}

			} else if (action.equals("/loadStudent")) {
				try {
					List<String> list1 = scoredao.allStuNameFromStudent();
					List<String> list = scoredao.findAllCourseFromCourse();
					request.setAttribute("allcourse", list);
					request.setAttribute("allstuname", list1);
					RequestDispatcher rd = request
							.getRequestDispatcher("addScore.jsp");
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			} else if (action.equals("/addScore")) {
				String str = request.getParameter("score").trim();
				if (!str.equals("") && (Integer.parseInt(str) >= 0)
						&& (Integer.parseInt(str) <= 100)) {
					Score score = new Score();
					score.setCourseName(request.getParameter("courseName"));
					score.setStudentName(request.getParameter("stuName"));
					score.setCourseScore(Double.parseDouble(request
							.getParameter("score")));
					response.sendRedirect("scoreList.do1");
					try {
						scoredao.addScore(score);
					} catch (Exception e) {
						e.printStackTrace();
						throw new ServletException(e);
					}
				} else {
					response.sendRedirect("loadStudent.do1");
				}
			} else if (action.equals("/selectScore")) {
				String name = request.getParameter("stuname");
				String coursename = request.getParameter("corname");
				System.out.println(name + "*******" + coursename);
				try {
					List<Score> list = scoredao.selectScoreBy(name, coursename);
					request.setAttribute("scorelist", list);
					System.out.println(list.size());
					request.getRequestDispatcher("scoreList.jsp").forward(
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
