<%@page import="java.util.*,java.text.*,com.wq.entity.TbClass"
	contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
		<title>更新班级</title>
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
						更新班级:
					</h1>
					<form action="updateClass" method="post">
						<%
							TbClass tbclass = (TbClass) request.getAttribute("tbclass");
						%>
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td>
									<input type="hidden" name="page"
										value="<%=request.getAttribute("page")%>" />
								</td>
							</tr>
							<tr>
								<td>
									<input type="hidden" name="id" value="<%=tbclass.getId()%>" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									班级名称:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="class_name"
										value="<%=tbclass.getClassName()%>" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									班主任:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="class_teacher"
										value="<%=tbclass.getClassTeacher()%>" />
								</td>
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="&nbsp;确认&nbsp;" />
							<input type="button" class="button" value="&nbsp;取消&nbsp;"
								onclick="history.back()" />
						</p>
					</form>
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
