<%@page import="java.util.*,java.text.*,com.wq.entity.*"
	contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
		<title>更改密码</title>
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
						更改密码:
					</h1>
					<%
						if(session.getAttribute("loginname") == null){
							response.sendRedirect("login.jsp");
						}
					 %>
					<form action="updatePwd" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									输入原密码:
								</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="oldPwd" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									输入新密码:
								</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="newPwd"  />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									确认新密码:
								</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="confirmPwd" />
								</td>
							</tr>
							<tr>
								<td>
									<span style="color: red"><%=session.getAttribute("error")==null? "":session.getAttribute("error") %></span>
								</td>
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="&nbsp;确认&nbsp;" />
							<input type="reset" onclick = "location='updatePwd.jsp'" class="button" value="&nbsp;清空&nbsp;" />
						</p>
					</form>
					<%session.setAttribute("error",""); %>
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
