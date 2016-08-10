<%@page import="java.util.*,java.text.*,com.wq.entity.*"
	contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
		<title>添加课程</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<script src="js/Calendar.js" type="text/javascript">
</script>
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
						添加课程:
					</h1>
					<form action="addCourse.do" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									课程名称:
								</td>
								<td valign="middle" align="left">
									<input type="text" id = "course" class="inputgri" name="course_name" />
								</td>
								<td><span style = "color:red" id = "msg1"></span></td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									任课老师:
								</td>
								<td valign="middle" align="left">
									<input type="text" id = "teacher" class="inputgri" name="teacher_name" />
								</td>
								<td><span style = "color:red" id = "msg2"></span></td>
								
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="&nbsp;确认&nbsp;" />
							<input type="button" class="button" value="&nbsp;取消&nbsp;"
								onclick="history.back()" />
						</p>
						<script type="text/javascript">
							var course = document.getElementById("course");
							var teacher = document.getElementById("teacher");
							var msg1 = document.getElementById("msg1");
							var msg2 = document.getElementById("msg2");
							course.onblur = function(){
								if(course.value.trim() == ""){
									msg1.innerHTML = "课程不能为空";
								}else{
									msg1.innerHTML = "";
								}
							}
							teacher.onblur = function(){
								if(teacher.value.trim() == ""){
									msg2.innerHTML = "任课老师不能为空";
								}else{
									msg2.innerHTML = "";
								}
							}
						</script>
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
