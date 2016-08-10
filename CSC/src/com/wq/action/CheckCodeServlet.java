package com.wq.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckCodeServlet extends HttpServlet {
	private int width = 80;
	private int height = 30;
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//阶段一：绘图。
		// 创建一个内存映像对象 (画布)
			BufferedImage image = 
				new BufferedImage(width,height,
						BufferedImage.TYPE_INT_RGB);
		// 获得画笔
			Graphics g = image.getGraphics();
		// 给画笔设置颜色
			Random r = new Random();
			g.setColor(new Color(r.nextInt(255),
					r.nextInt(255),r.nextInt(255)));
		// 给画布设置背景颜色
			g.fillRect(0, 0, width, height);
		// 将一个随机数画在画布上
			String code = getCode(5);
			//将随机数绑订到session对象上
			HttpSession session = request.getSession();
			session.setAttribute("code", code);
			g.setColor(new Color(0,0,0));
			//设置字体
			g.setFont(new Font(null,
					Font.ITALIC|Font.BOLD,20));
			g.drawString(code, 5, 22);
			//加一些干扰线
			for(int i=0;i<12;i++){
				g.setColor(new Color(r.nextInt(255),
						r.nextInt(255),r.nextInt(255)));
				g.drawLine(r.nextInt(width), 
						r.nextInt(height), 
						r.nextInt(width), 
						r.nextInt(height));
			}
		//阶段二: 将图片压缩，输出到浏览器。
			response.setContentType("image/jpeg");
			OutputStream ops = 
				response.getOutputStream();
			//write方法将原始图片压缩，然后发送给
			//response对象。
			javax.imageio.ImageIO
			.write(image, "jpeg", ops);
	}
	
	private String getCode(int size) {
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
				"0123456789";
		Random r = new Random();
		String number = "";
		for(int i=0;i<size;i++){
			number += str.charAt(
					r.nextInt(str.length()));
		}
		return number;
	}

}
