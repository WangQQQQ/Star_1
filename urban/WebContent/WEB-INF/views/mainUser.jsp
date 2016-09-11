<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<!-- Basic Page Needs
  ================================================== -->
	<meta charset="utf-8" />
	<title>首页</title>
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
<div class="top-link"><!-- -->
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
				<li><a href="backToMain" style="color: #ff9900;">首页</a>
					<!-- <ul>
						<li><a href="login.jsp">登出</a></li>
					</ul> -->
				</li>
				<li><a href="enterpriseList">企业信息</a></li>
				<li><a href="userGeographyList">地理信息</a></li>
				<li><a href="#">城建信息</a>
					<ul>
						<li><a href="userProjectList">城建信息显示</a></li>
						<li><a href="baiduMap">调用百度地图查看</a></li>
					</ul>
				</li>
				<li><a href="analysis">城建数据分析</a></li>
				<li><a href="#">综合数据</a>
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

<div class="theme-default">

            <div id="slider" class="nivoSlider">
                <img src="images/userMain.png" title="#htmlcaption" />
                <img src="images/userMain2.png" title="#htmlcaption2" />
               <img src="images/userMain3.png" title="#htmlcaption3" />
     		 </div>
				            <div id="htmlcaption" class="nivo-html-caption">
                          <h2> </h2>       </div>
						   <div id="htmlcaption2" class="nivo-html-caption">
                          <h2> </h2>       </div>
						   <div id="htmlcaption3" class="nivo-html-caption">
                       <!--    <h2> Responsive & grid based  </h2>     -->   </div>
						  
</div><!-- end-slider -->

</section><!-- end-full-width -->
	

<section class="container home-content">
	
		<div class="two-thirds column">
<h1>让城市规划更合理</h1>
<h4>
<a href="#">可以更加直观的感受到城市建设的分布情况</a></h4>
<p>&nbsp;</p>
		</div>
		<div class="one-third column">
						<!-- <img src="images/resp1.png" /> --><br />
						<!-- <h3>Responsive Design</h3>
						<p>
Technology should be about more than newest, loudest, prettiest. It should make a difference. <a href="#">read more</a></p> -->
		</div>	
		<br class="clear" />	

</section>
	<!-- container -->
	
<!-- end-footer -->
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
</body>
</html>