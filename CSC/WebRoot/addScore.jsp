<%@page import="java.util.*,java.text.*,com.wq.entity.*"
	contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
		<title>录入成绩</title>
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
						录入成绩:
					</h1>
					<%
						List<String> list1 = (List) request.getAttribute("allstuname");
						List<String> list = (List) request.getAttribute("allcourse");
					%>
					<form action="addScore.do1" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									学生姓名:
								</td>
								<td valign="middle" align="left">
									<select class="inputgri" name="stuName">
										<%
											for (int i = 0; i < list1.size(); i++) {
												String str = list1.get(i);
												
										%>
										<option value="<%=str%>"><%=str%></option>
										<%
											}
										%>
									</select>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									课程名称:
								</td>
								<td valign="middle" align="left">
									<select class="inputgri" name="courseName">
										<%
										for(int i = 0;i<list.size();i++){
										String str = list.get(i);
										%>
										<option value="<%=str%>">
											<%=str%>
										</option>
										<%
											}
										%>
									</select>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									成绩:
								</td>
								<td valign="middle" align="left">
									<input type="text" id = "score1" class="inputgri" name="score" />
								</td>
								<td><span id = "msg1" style = "color:red"></span></td>
								
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="&nbsp;确认&nbsp;" />
							<input type="button" class="button" value="&nbsp;取消&nbsp;"
								onclick="history.back()" />
						</p>
						<script type="text/javascript">
							var score = document.getElementById("score1");
							var msg1 = document.getElementById("msg1");
							score.onblur = function(){
								if(score.value.trim() == ""){
								msg1.innerHTML = "成绩不能为空";
								}else{
								msg1.innerHTML = "";
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
