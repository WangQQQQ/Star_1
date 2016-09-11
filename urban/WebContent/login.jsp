<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="login-bg">
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
<link rel="stylesheet" href="css/compiled/signin.css" type="text/css" media="screen" />

<!-- open sans font -->
<!-- <link href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' /> -->
    
<title>系统登陆</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">

 $(document).ready(function() {
	  $("#userName").click(function() {
		  $("#errorMessage").html("");
	  });
	  $("#password").click(function() {
		  $("#errorMessage").html("");
	  });
	 });

	/* function check() {
		if (document.getElementById("password").value == "" || document.getElementById("userName").value == "") {
			alert("用户名或密码为空!");
			return false;
		} else
			return true; 
	} */
</script>
</head>
<body>

    <!-- background switcher -->
    <div class="bg-switch visible-desktop">
        <div class="bgs">
            <a href="#" data-img="landscape.jpg" class="bg active">
                <img src="img/bgs/landscape.jpg" />
            </a>
            <a href="#" data-img="7.jpg" class="bg">
                <img src="img/bgs/7.jpg" />
            </a>
            <a href="#" data-img="8.jpg" class="bg">
                <img src="img/bgs/8.jpg" />
            </a>
            <a href="#" data-img="9.jpg" class="bg">
                <img src="img/bgs/9.jpg" />
            </a>
            <a href="#" data-img="10.jpg" class="bg">
                <img src="img/bgs/10.jpg" />
            </a>
            <a href="#" data-img="11.jpg" class="bg">
                <img src="img/bgs/11.jpg" />
            </a>
        </div>
    </div>


    <div class="row-fluid login-wrapper">
            <img class="logo" src="img/loginImg3.png" />

        <div class="span4 box">
            <div class="content-wrap">
                <h6></h6>
                <!-- onsubmit="return check()" -->
                <form action="login" method="post" >
                <input class="span12" type="text" placeholder="您的姓名" name="userName" id="userName" value="${userName}"/>
                <input class="span12" type="password" placeholder="您的密码" name="password" id="password" value="${password}"/>
                
                <%--  <input class="span12" type="text" placeholder="您的姓名" name="name" id="name" value="${name}"/>
                <input class="span12" type="password" placeholder="您的密码" name="pwd" id="pwd" value="${pwd}"/> --%>
                
               <!--   <a href="toForgetPwd" class="forgot">忘记密码?</a> -->
                <div class="remember">
                    <span id="errorMessage" style="color:red">${errorMessage}</span>
                </div>
                <input type="submit" value="登录" class="btn-glow primary login" id="submit"/>
                </form> 
            </div>
        </div>
        
        <div class="span4 no-account">
            <p>是否没有账号?</p>
            <a href="toRegisterUser">注册</a>
        </div>
    </div>
	<!-- scripts -->
    <script src="js/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>

    <!-- pre load bg imgs -->
    <script type="text/javascript">
        $(function () {
            // bg switcher
            var $btns = $(".bg-switch .bg");
            $btns.click(function (e) {
                e.preventDefault();
                $btns.removeClass("active");
                $(this).addClass("active");
                var bg = $(this).data("img");

                $("html").css("background-image", "url('img/bgs/" + bg + "')");
            });

        });
    </script>
</body>
</html>