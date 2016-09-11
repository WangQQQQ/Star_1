<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<!-- Basic Page Needs
  ================================================== -->
	<meta charset="utf-8" />
	
	<script src="laydate/laydate.js"></script>
	
	<title>地理信息统计页</title>
	<meta name="description" content="" />
	<meta name="author" content="" />

	<!-- Mobile Specific Metas
  ================================================== -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

	<!-- CSS
  ================================================== -->
	<link rel="stylesheet" href="css/style.css" />


	<!-- JS
  ================================================== -->
   <script type="text/javascript" src="js/jquery.min.js"></script>
	<!-- HTML5 Shiv events -->
   <script type="text/javascript" src="js/modernizr.custom.11889.js"></script>
    <!-- HTML5 Shiv events (end)-->
    <script type="text/javascript" src="js/nav-resp.js"></script>

	<!-- Favicons
  ================================================== -->
	<link rel="shortcut icon" href="images/favicon.ico" />

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <!--  ================================================ -->
<script type="text/javascript">
 function check() {
	    var demo=document.getElementById("demo").value;
	    var demo2=document.getElementById("demo2").value;
	    var date1=new Date(demo);
	    var date2=new Date(demo2);
	    if(demo==""||demo2==""){
	    	alert("搜索时间不能为空!");
	    	return false; 
	    }
	    if(date1.getTime()>date2.getTime()){
	    	alert("时间区间不对,请输入正确的区间!");
	    	return false; 
	    }else
			return true; 
	} 
</script>
<style type="text/css">
  /* nav link */
#nav2 a:hover {	color:#55606a; background: #f4f7f7;}
#nav2 a.buy { background:none; padding-right:0; margin-right:0; color:#ff9900; float:right;}
/* nav dropdown */
#nav2 ul { background: #fff url(../images/ul-top1.png) no-repeat;	padding: 8px 7px 18px; margin:-1px 0 0 -5px; position: absolute; border: solid 1px #d2d2d2; display: none; /* hide dropdown */	width: 190px; z-index:999999999;}
#nav2 ul li { float: none; border-bottom:1px dotted #efefef; display:block;	margin: 0;	}
#nav2 ul li a { display:block; text-align:left; height:100%; padding: 9px 3px; border-radius:0; font-size:12px; color:black;  }
#nav2 li:hover > ul {	display: block; visibility:visible; /* show dropdown on hover */}
</style>
</head>
<body>
<!-- Primary Page Layout
	================================================== -->

<div id="wrap">
<header id="header" class="container">
<div class="top-link">
<ul id="nav2">
	<li>
		<a href="#">${sessionScope.currentUser },您好! </a>
		<ul>
			<li><a href="toChangePwd">修改密码</a></li>
			<li><a href="login.jsp">登出</a></li>
		</ul>
	<li>
</ul>
</div>
<div class="four columns"><a href="#"><img src="images/userLogo4.png" /></a></div>
<nav id="nav-wrap" class="twelve columns">
			<ul id="nav">
				<li><a href="backToMain">首页</a>
				</li>
				<li><a href="enterpriseList" >企业信息</a></li>
				<li><a href="userGeographyList">地理信息</a></li>
				<li><a href="#">城建信息</a>
					<ul>
						<li><a href="userProjectList">城建信息显示</a></li>
						<li><a href="baiduMap">调用百度地图查看</a></li>
					</ul>
				</li>
				<li><a href="analysis">城建数据分析</a></li>
				<li><a href="#" style="color: #ff9900;">综合数据</a>
					<ul>
						<li><a href="geographyCount">地理信息统计</a></li>
						<li><a href="statusCount">城建信息统计</a></li>
					</ul>
				</li>
			</ul>
	</nav>
		<!-- /nav-wrap -->
</header><!-- end-header -->

<section id="full-width">
<img src="images/page2.png" class="full-width" />
<div class="page-subtitle"> 
<h3>地理信息统计页</h3>
<p>查看所有的地理信息的分布情况和查询某个时间段的分布情况</p>
</div>

</section>
<section class="container page-content">
<!-- <h2>asdf</h2> -->
<hr />
<h3>地理信息统计</h3> 
               		<form action="searchGCount" method="post" onsubmit="return check()">
               			<input class="laydate-icon" id="demo" name="demo">  -
               			<input class="laydate-icon" id="demo2" name="demo2">
               			<!-- btn-glow success  btn-glow primary btn-glow btn-glow inverse-->
               		 	<input type="submit" value="查看统计" class="btn-glow inverse"/>
               		</form>
 <% if(request.getAttribute("flag").equals(1)){%>
<hr/>
 <img src="${chartURL}"><br><br>
<hr />
<%}else{ %>
<!-- red   #ff9900-->
<h5 style="color: #ff9900;">您查询的<%= request.getAttribute("demo") %>到<%= request.getAttribute("demo2") %>时间段没有数据</h5>
<%} %>
</section>
</div>
<!-- end-wrap -->
<!-- End Document
================================================== -->
   
   <script type="text/javascript" src="js/jquery.nivo.slider.pack.js"></script>
    <script type="text/javascript">
    $(window).load(function() {
        $('#slider').nivoSlider();
    });
    </script>
	<script type="text/javascript" src="js/latest-twitt.js"></script>
	<script type="text/javascript" src="js/vincie-send.js"></script>
	<!--日历控件 -->
	<script>
		!function() {
			laydate.skin('molv'); //切换皮肤，请查看skins下面皮肤库
			laydate({
				elem : '#demo'
			});//绑定元素
			laydate({
				elem : '#demo2'
			});//绑定元素
		}();
	</script>
	<!--日历控件  -->
</body>
</html>