<%@page import="java.util.*,java.text.*,com.wq.entity.*"
	contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
		<title>成绩管理</title>
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
								欢迎 <%=session.getAttribute("loginname") %> 登录系统
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
						成绩管理
					</h1>
					<form action = "selectScore.do1" method = "post">
					<p>
						学生姓名：
						<input type="text" class="inputgri" name = "stuname"/>
						课程名称：
						<input type="text" class="inputgri" name = "corname" />
						<input type="submit" class="button" value="查询"/>
					</p>
					</form>
					<table class="table">
						<tr class="table_header">
							<td>
								序号
							</td>
							<td>
								学生姓名
							</td>
							<td>
								课程名称
							</td>
							<td>
								成绩
							</td>
							<td>
								操作
							</td>
						</tr>
						<%
							List<Score> list = (List) request.getAttribute("scorelist");
							for (int i = 0; i < list.size(); i++) {
								Score s = list.get(i);
						%>
						<tr class="row<%=i % 2 + 1%>">
							<td>
								<%=(Integer) (request.getAttribute("page")) * 10
								+ i + 1%>
							</td>
							<td>
								<%=s.getStudentName()%>
							</td>
							<td>
								<%=s.getCourseName()%>
							</td>
							<td>
								<%=s.getCourseScore()%>
							</td>
							<td>
								<a
									href="loadScore.do1?id=<%=list.get(i).getId()%>&page=<%=request.getAttribute("page")%>">更新</a>&nbsp;
								<a
									href="deleteScore.do1?id=<%=list.get(i).getId()%>&page=<%=request.getAttribute("page")%>">删除</a>
							</td>
						</tr>
						<%
							}
						%>
					</table>
					<p>
						<input type="button" class="button" value="录入成绩"
							onclick="location='loadStudent.do1'" />
						<a
							href="scoreList.do1?page=<%=(Integer) request.getAttribute("page") + 1%>"
							style="float: right;">下一页</a><span style="float: right;">&nbsp;&nbsp;</span><a
							href="scoreList.do1?page=<%=(Integer) request.getAttribute("page") - 1%>"
							style="float: right;">上一页</a>
					</p>
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
