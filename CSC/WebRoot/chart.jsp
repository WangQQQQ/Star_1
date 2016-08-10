<%@page import="java.util.*,java.text.*,com.wq.entity.*"
	contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
		<title>图表分析</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>
	<body>
		<div id="wrap">
			<div id="top_content">
				<div id="header">
					<div id="rightheader">
						<p>
							2014/11/20
							<br />
						</p>
					</div>
					<div id="topheader">
						<h1 id="title">
							<a href="#">学生成绩管理系统</a>
						</h1>
					</div>
					<div id="navigation">
						<div id="menu">
							<div class="left">
								欢迎
								<%=session.getAttribute("loginname")%>
								登录系统
								<span>|</span>
								<a href="classList">班级管理</a>
								<span>|</span>
								<a href="studentList.do2">学生管理</a>
								<span>|</span>
								<a href="courseList.do">课程管理</a>
								<span>|</span>
								<a href="scoreList.do1">成绩管理</a>
								<span>|</span>
								<a href="tochart">图表分析</a>
							</div>
							<div class="right">
								<a href="updatePwd.jsp">更改密码</a>
								<span>|</span>
								<a href="login.jsp">退出</a>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						图标分析
					</h1>
					<%
						List list1 = (List) request.getAttribute("classlist");
						List list2 = (List) request.getAttribute("studentlist");
						List list3 = (List) request.getAttribute("courselist");
					%>
					<div align="center">
						<form action="classchart.do0" method="post">
							<select class="inputgri" name="class">
								<option value="">
									--请选择--
								</option>
								<%
									for (int i = 0; i < list1.size(); i++) {
								%>
								<option value="<%=list1.get(i)%>">
									<%=list1.get(i)%>
								</option>
								<%
									}
								%>
							</select>
							<input type="submit" class="button" value="班级分析" />
						</form>
						<form action="" method="post">
							<select class="inputgri" name="student">
								<option value="">
									--请选择--
								</option>
								<%
									for (int i = 0; i < list2.size(); i++) {
								%>
								<option value="<%=list2.get(i)%>">
									<%=list2.get(i)%>
								</option>
								<%
									}
								%>
							</select>
							<input type="submit" class="button" value="学生分析" />
						</form>
						<form action="" method="post">
							<select class="inputgri" name="course">
								<option value="<%=0%>">
									--请选择--
								</option>
								<%
									for (int i = 0; i < list3.size(); i++) {
								%>
								<option value="<%=list3.get(i)%>">
									<%=list3.get(i)%>
								</option>
								<%
									}
								%>
							</select>
							<input type="button" class="button" value="课程分析" />
						</form>
					</div>
					<div align="center">
						<img  src="/CSC/servlet/DisplayChart?filename=<%=request.getAttribute("file")%>" width="600px" height="400px" />
					</div>
					<p></p>
				</div>
			</div>
			<div id="footer">
				<div id="footer_bg">
					www.open1234.com
				</div>
			</div>
		</div>
	</body>
</html>
