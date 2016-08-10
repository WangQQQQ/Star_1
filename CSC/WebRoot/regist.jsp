<%@page import="java.util.*,java.text.*,com.wq.entity.*"
	contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
		<title>用户注册</title>
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
					</div>
				</div>
				<div id="content">
					<p id="whereami">
					</p>
					<% List<String> list = (List)request.getAttribute("allloginname"); %>
					<h1>
						注册
					</h1>
					<form action="regist.do3" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									用户名:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="loginname" id="lname" />
								</td>
								<td>
									<span id="msg1" style = "color:red;"></span>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									真实姓名:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="username" id="uname" />
								</td>
								<td>
									<span id="msg2" style = "color:red;"></span>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									密码:
								</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="pwd"
										id="password" />
								</td>
								<td>
									<span id="msg3" style = "color:red;"></span>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									性别:
								</td>
								<td valign="middle" align="left">
									男
									<input type="radio" class="inputgri" name="sex" value="男"
										checked="checked" />
									女
									<input type="radio" class="inputgri" name="sex" value="女" />
								</td>
							</tr>

							<tr>
								<td valign="middle" align="right">
									验证码:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="code" id = "cd" />
								</td>
								<td>
									<span id="msg4" style = "color:red;"></span>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									<a href="javascript:;"
										onclick="document.getElementById('code').src='checkcode?'+Math.random()">换一张</a>
								</td>
								<td valign="middle" align="left">
									<img id="code" src="checkcode" />
								</td>
							</tr>
						</table>
						<span style="color: red;" /><%=session.getAttribute("error") == null ? "" : session
					.getAttribute("error")%></span>
						<script type="text/javascript">
						var lname = document.getElementById("lname");
						var uname = document.getElementById("uname");
						var password = document.getElementById("password");
						var code = document.getElementById("cd");
						var msg1 = document.getElementById("msg1");
						var msg2 = document.getElementById("msg2");
						var msg3 = document.getElementById("msg3");
						var msg4 = document.getElementById("msg4");
						lname.onblur = function(){
							if(lname.value.trim() == ""){
								msg1.innerHTML = "用户名不能为空";
							}else{
								msg1.innerHTML = "";
							}
						}
						uname.onblur = function(){
							if(uname.value.trim() == ""){
								msg2.innerHTML = "真实姓名不能为空";
							}else{
								msg2.innerHTML = "";
							}
						}
						password.onblur = function(){
							if(password.value.trim() == ""){
								msg3.innerHTML = "密码不能为空或空字符开头";
							}else{
								msg3.innerHTML = "";
							}
						}
						code.onblur = function(){
							if(password.value.trim() == ""){
								msg4.innerHTML = "验证码为空";
							}else{
								msg4.innerHTML = "";
							}
						}
						</script>					
						<br />
						<p>
							<%
								session.setAttribute("error", "");
							%>
							<input type="submit" class="button" value="&nbsp;注册 &raquo;" />
							<input type="button" class="button" value="&nbsp;返回 &raquo;"
								onclick="location='login.jsp'" />
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
