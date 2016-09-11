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
    
<title>系统登陆页</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
/*   $(function(){
	  $("#submit").change(function(){
    	var val = $(this).val();
		val = $.trim(val);
		$(this).val(val);
		
		var url="${pageContext.request.contextPath }/ajaxValidateUser";
		var args={"useName":val,"data":new Date()};
		
		$.post(url, args, function(data){
			if(data == "0"){
				alert("用户名或密码错误!");
			}else{
				alert("网络或程序出错. ");
			}
		});  
	}); 
	$("#userName").click(function(){
		alert("123");
		$("#errorMessage").val("");
		
	}); 
	
})  */
/* $(document).ready(function() {
	  $("#userName").click(function() {
		  $("#errorMessage").html("");
	  });
	  $("#password").click(function() {
		  $("#errorMessage").html("");
	  });
	 }); */
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
            <img class="logo" src="img/logo33.png" />

        <div class="span4 box">
            <div class="content-wrap">
             <img src="${chartURL2}"><br><br>
                <%-- <h6>${hashMap}</h6> --%>
               <!-- <h6>aaa</h6> -->
                
            </div>
        </div>

    </div>
<!-- <form action="login" method="post">
		username: <input type="text" name="userName" /> <br> 
		password: <input type="password" name="password" /> <br> 
			
		<input type="submit" value="Submit"/>
	</form> -->
	
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