<%@page import="java.util.*,java.text.*,com.wq.entity.*"
	contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
		<title>系统登录</title>
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
					<h1>
						登录
					</h1>
					<%
						String msg = (String) request.getAttribute("error");
					%>
					<form action="login" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									用户名:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="loginname" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									密码:
								</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="password" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									验证码:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="code" />
								</td>
								<td>
									<img src="checkcode"
										onclick="this.src='checkcode?' + Math.random();" />
								</td>
							</tr>
						</table>
						<p>
							<span style="color: red"><%=msg == null ? "" : msg%></span>
							<br />
							<input type="submit" class="button" value="&nbsp;登录 &raquo;" />
							<input type="button" class="button" value="&nbsp;注册 &raquo;"
								onclick="location.href='load.do3'" />
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
