package com.wq.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.general.DefaultPieDataset;

import com.wq.dao.ClassDAO;
import com.wq.dao.CourseDAO;
import com.wq.dao.StudentDAO;
import com.wq.utils.DAOFactory;

public class ChartServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		try {
			ClassDAO classdao = (ClassDAO)DAOFactory.getInstance("ClassDAO");
			StudentDAO studentdao = (StudentDAO)DAOFactory.getInstance("StudentDAO");
			CourseDAO coursedao = (CourseDAO)DAOFactory.getInstance("CourseDAO");
			if(action.equals("/classchart")){
				// 默认饼图数据集
				DefaultPieDataset dataset = new DefaultPieDataset();
				// 设置数据集
				String classname = request.getParameter("class");
				dataset.setValue("男", classdao.findManNum(classname));
				dataset.setValue("女", classdao.findWonmanNum(classname));
				
				// 创建饼图
				JFreeChart chart = ChartFactory.createPieChart("班级男女分布图",
						dataset, true, true, true);
				// 保存图片并获得图片名称
				String file = ServletUtilities.saveChartAsPNG(chart, 800, 600,
						request.getSession());
				request.setAttribute("file", file);
				request.getRequestDispatcher("tochart").forward(request, response);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
