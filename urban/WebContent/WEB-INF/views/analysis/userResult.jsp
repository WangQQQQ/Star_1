<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<!-- Basic Page Needs
  ================================================== -->
	<meta charset="utf-8" />
	<title>分析结果页</title>
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
   
   
<link rel="stylesheet" href="css/style.default.css" type="text/css" />
<script type="text/javascript" src="js/jquery-1.7.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/tables.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/general.js"></script>

<style type="text/css">
#customers
  {
  font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
  width:100%;
  border-collapse:collapse;
  }

#customers td, #customers th 
  {
  font-size:1em;
  border:1px solid #98bf21;
  padding:3px 7px 2px 7px;
  }

#customers th 
  {
  font-size:1.1em;
  text-align:left;
  padding-top:5px;
  padding-bottom:4px;
  background-color:#A7C942;
  color:#ffffff;
  }

#customers tr.alt td 
  {
  color:#000000;
  background-color:#EAF2D3;
  }
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
				<li><a href="analysis" style="color: #ff9900;">城建数据分析</a></li>
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
<img src="images/page3.png" class="full-width" />
<div class="page-subtitle"> 
<h3>分析结果页</h3>
<p>查看案例分析结果，通过CBR案例分析模型得出的结论如下</p>
</div>
</section>

<section class="container page-content">
<hr/>
<table id="customers">
<tr>
<th>项目名称</th>
<th>投资总额</th>
<th>工期</th>
<th>工程总面积</th>
<th>报建时间</th>
<th>最大跨度</th>
<th>项目地址</th>
<th>地理区域</th>
</tr>

	<tr>
		<td>${example.projectName }</td>
        <td>${example.totalInvestment }</td>
        <td>${example.projectPeriod}</td>
        <td>${example.buildTotalArea }</td>
        <td>${constructTime }</td>
        <td>${example.maxSpan }</td>
        <td>${example.geographyAddress }</td>
        <td>${example.geographyRegion }</td>
	</tr>
	<tr>
<th>相应权重</th>
<th></th>
<th></th>
<th></th>
<th></th>
<th></th>
<th></th>
<th></th>
</tr>
	<tr>
		<td>${example.percent1 }%</td>
        <td>${example.percent2 }%</td>
        <td>${example.percent3}%</td>
        <td>${example.percent4 }%</td>
        <td>${example.percent5 }%</td>
        <td>${example.percent6 }%</td>
        <td>${example.percent7 }%</td>
        <td>${example.percent8 }%</td>
	</tr>
</table>
<hr/>
<label style="color:purple;font-size:14px" >相近的案例分析:</label>
<table id="customers">
<tr>
<th>项目名称</th>
<th>投资总额</th>
<th>工期</th>
<th>工程总面积</th>
<th>报建时间</th>
<th>最大跨度</th>
<th>项目地址</th>
<th>地理区域</th>
<th>相似度CBR(百分比%)</th>
</tr>
<c:forEach items="${listNeed }" var="project">
	          				 <tr class="first">
                      			<td>${project.projectName}</td>
                      			<td>${project.totalInvestment}</td>
                     			<td>${project.projectPeriod}</td>
                      			<td>${project.buildTotalArea}</td>
                      			<td>${project.constructTime}</td>
                      			<td>${project.maxSpan}</td>
                      			<td>${project.geographyAddress}</td>
                      			<td>${project.geographyRegion}</td>
                      			<td>${project.value}</td>
                  			 </tr>
</c:forEach>
</table>
<hr/>
 <img src="${chartURL}"><br><br>
                 <label style="color:purple;font-size:14px" >结论如下:</label><br>
                 <label>通过数据挖掘与分析，发现与 ${example.projectName } 相似度最高的是由 <span class="label label-info" style="font-size:14px; color:blue">${suggestion1}</span>建设的。</label>
                 <label>而相似度最高的前几个中，大多是由<span class="label label-success" style="font-size:14px; color:blue">${suggestion2}</span>建设的。</label>
                 <label>在选择建设公司的时候可以优先考虑以上单位</label>
                 
<hr />
</section>
<!-- end-slider -->
<!-- end-full-width -->
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