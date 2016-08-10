<%@page import="java.util.*,java.text.*,com.wq.entity.*"
	contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
		<title>添加学生</title>
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
						添加学生:
					</h1>
					<form action="addStudent.do2" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<%
								List<String> list = (List<String>) request.getSession()
										.getAttribute("allClass");
							%>
							<tr>
								<td valign="middle" align="right">
									姓名:
								</td>
								<td valign="middle" align="left">
									<input type="text" id="student" class="inputgri"
										name="studentname" />
								</td>
								<td>
									<span style="color: red" id="msg1"></span>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									性别:
								</td>
								<td valign="middle" align="left">
									<input type="radio" class="inputgri" name="sex"
										checked="checked" value="男" />
									男&nbsp;
									<input type="radio" class="inputgri" name="sex" value="女" />
									女&nbsp;
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									出生年月:
								</td>
								<td valign="middle" align="left">
									<input type="text" id="date1" class="inputgri" name="birthday"
										onclick="SelectDate(this,'yyyy\-MM\-dd')" readonly="readonly"
										value="" />
								</td>
								<td>
									<span style="color: red" id="msg2"></span>
								</td>

							</tr>
							<tr>
								<td valign="middle" align="right">
									所在班级:
								</td>
								<td valign="middle" align="left">
									<select class="inputgri" name="class">
										<%
											for (int i = 0; i < list.size(); i++) {
										%>
										<option value="<%=list.get(i)%>">
											<%=list.get(i)%>
										</option>
										<%
											}
										%>
									</select>
								</td>
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="&nbsp;确认&nbsp;" />
							<input type="button" class="button" value="&nbsp;取消&nbsp;"
								onclick="history.back()" />
						</p>
						<script type="text/javascript">
							var student = document.getElementById("student");
							var date = document.getElementById("date1");
							var msg1 = document.getElementById("msg1");
							var msg2 = document.getElementById("msg2");
							student.onblur = function(){
								if(student.value.trim() == ""){
									msg1.innerHTML = "姓名不能为空";
								}else{
									msg1.innerHTML = "";
								}
							}
							date.onblur = function(){
								if(date.value.trim() == ""){
									msg2.innerHTML = "出生年月不能为空";
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
