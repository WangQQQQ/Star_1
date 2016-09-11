<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/layout.css" />
    <link rel="stylesheet" type="text/css" href="css/elements.css" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />

    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="css/lib/font-awesome.css" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/signup.css" type="text/css" media="screen" />

<title>注册用户页</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		 $("#userName").click(function() {
			  $("#message1").html("");
			  $("#message2").html("");
		  });

		$("#userName").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			$(this).val(val);
			var url = "${pageContext.request.contextPath }/ajaxValidateNewUserName";
			var args = {"userName":val,"date":new Date()};
			$.post(url, args, function(data){
				if(data == "0"){
					$("#message2").html("用户名可用!");

				}else if(data == "1"){
					$("#message1").html("用户名已存在，不可用!");
					//alert("用户名已存在，不可用!");
				}else{
					alert("网络或程序出错. ");
				}
			});
		});
	});  
	function check() {
		
		var name = document.getElementById("userName").value;
		var pwd = document.getElementById("pwd").value;
		var pwd2 = document.getElementById("pwd2").value;
		if (name == "") {
			alert("用户名不能为空！");
			return false;
		}
		if (pwd == "" || pwd2 == "") {
			alert("密码不能为空！");
			return false;
		}
		if (pwd != "" && pwd2 != "" && pwd != pwd2) {
			alert("两次密码输入不一致，请重新输入！");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
 <div class="header">
        <a href="#">
            <img src="img/logo55.png" class="logo" />
        </a>
    </div>
    <div class="row-fluid login-wrapper">
        <div class="box">
            <div class="content-wrap">
                <h6>注册用户</h6>
                <form action="registerUser" method="post" onsubmit="return check()">
                	<input class="span12" type="text" placeholder="您的姓名" id="userName" name="userName" value="${currentName}"/>
                	<span id="message1" style="color:red">${rnameError}</span>
                	<span id="message2" style="color:green"></span>
                	<input class="span12" type="password" placeholder="您的密码" id="pwd" name="pwd"/>
                	<input class="span12" type="password" placeholder="确认密码" id="pwd2" name="pwd2"/>
                	<div class="action">
                	 <input type="submit" value="完成" class="btn-glow primary signup" id="submit"/>
                   	 <!-- <a class="btn-glow primary signup" href="index.html">完成</a> -->
               		</div> 
                </form>               
            </div>
        </div>

        <div class="span4 already">
            <p>已经有账号了?</p>
            <a href="login.jsp">登陆</a>
        </div>
    </div>

	<!-- scripts -->
    <script src="js/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>
</body>
</html>