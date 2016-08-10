<%@page import="java.util.*,java.text.*,com.wq.entity.*"
	contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
		<title>学生管理</title>
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
						学生管理
					</h1>
					<table class="table">
						<tr class="table_header">
							<td>
								序号
							</td>
							<td>
								姓名
							</td>
							<td>
								性别
							</td>
							<td>
								年龄
							</td>
							<td>
								所在班级
							</td>
							<td>
								操作
							</td>
						</tr>
						<%
							List<Student> list = (List) request.getAttribute("studentList");
							for (int i = 0; i < list.size(); i++) {
								Student s = list.get(i);
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
								<%=s.getGender()%>
							</td>
							<td>
								<%=s.getAge()%>
							</td>
							<td>
								<%=s.getClassName()%>
							</td>
							<td>
								<a href="chooseCourse.do?id=<%=list.get(i).getId()%>&page=<%=request.getAttribute("page")%>">选课</a>&nbsp;
								<a
									href="loadStudent.do2?id=<%=list.get(i).getId()%>&page=<%=request.getAttribute("page")%>">更新</a>&nbsp;
								<a
									href="deleteStudent.do2?id=<%=list.get(i).getId()%>&page=<%=request.getAttribute("page")%>">删除</a>
							</td>
						</tr>
						<%
							}
						%>
					</table>
					<p>
						<input type="button" class="button" value="添加学生"
							onclick="location='loadClass.do2'" />
						<a
							href="studentList.do2?page=<%=(Integer) request.getAttribute("page") + 1%>"
							style="float: right;">下一页</a><span style="float: right;">&nbsp;&nbsp;</span><a
							href="studentList.do2?page=<%=(Integer) request.getAttribute("page") - 1%>"
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
