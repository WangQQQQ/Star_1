<%@page import="java.util.*,java.text.*,com.wq.entity.TbClass"
	contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
		<title>班级管理</title>
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
						班级管理
					</h1>
					<table class="table">
						<%
							List<TbClass> list = (List) request.getAttribute("classlist");
						%>
						<tr class="table_header">
							<td>
								序号
							</td>
							<td>
								班级名称
							</td>
							<td>
								班主任
							</td>
							<td>
								班级人数
							</td>
							<td>

								操作
							</td>
						</tr>
						<%
							for (int i = 0; i < list.size(); i++) {
								TbClass c = list.get(i);
						%>
						<tr class="row<%=i % 2 + 1%>">
							<td>
								<%=(Integer) (request.getAttribute("page")) * 10
								+ i + 1%>
							</td>
							<td>
								<%=c.getClassName()%>
							</td>
							<td>
								<%=c.getClassTeacher()%>
							</td>
							<td>
								<%=c.getStudentNum()%>
							</td>
							<td>
								<a href="loadClass?id=<%=list.get(i).getId() %>&page=<%=request.getAttribute("page") %>">更新</a>&nbsp;
								<a href="deleteClass?id=<%=list.get(i).getId() %>&page=<%=request.getAttribute("page") %>">删除</a>
							</td>
						</tr>
						<%
							}
						%>
					</table>
					<p>
						<input type="button" class="button" value="添加班级"
							onclick="location='addClass.jsp'" />
						<a
							href="classList?page=<%=(Integer) request.getAttribute("page") + 1%>"
							style="float: right;">下一页</a><span style="float: right;">&nbsp;&nbsp;</span><a
							href="classList?page=<%=(Integer) request.getAttribute("page") - 1%>"
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
