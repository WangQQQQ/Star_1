<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<!-- Basic Page Needs
  ================================================== -->
	<meta charset="utf-8" />
	<title>企业信息查询页</title>
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
				<li><a href="enterpriseList"  style="color: #ff9900;">企业信息</a></li>
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
<img src="images/page2.png" class="full-width" />
<div class="page-subtitle"> 
<h3>企业信息页</h3>
<p>查看您所拥有的所有企业信息</p>
</div>

</section>
<section class="container page-content">
<!-- <h2>asdf</h2> -->
<hr />
<table id="customers">
<tr>
<th>企业编号</th>
<th>企业名称</th>
<th>成立时间</th>
<th>注册资金</th>
<th>公司地址</th>
<th>上半年招标数</th>
</tr>

<c:if test="${page == null || page.numberOfElements == 0 }">
		                  没有任何记录. 
</c:if>
<c:if test="${page != null && page.numberOfElements > 0 }">
    <%int i=0; %>
	<c:forEach items="${page.content }" var="enterprise">
	<%
	if(i%2==0){ 
	i++;%>
	<tr>
		<td>${enterprise.eId }</td>
        <td>${enterprise.eName}</td>
        <td>${enterprise.createTime }</td>
        <td>${enterprise.registerFund }</td>
        <td>${enterprise.eAddress }</td>
        <td>${enterprise.bidNum }</td>
	</tr>
	<%}else{ 
	i++;%>
	<tr class="alt">
		<td>${enterprise.eId }</td>
        <td>${enterprise.eName}</td>
        <td>${enterprise.createTime }</td>
        <td>${enterprise.registerFund }</td>
        <td>${enterprise.eAddress }</td>
        <td>${enterprise.bidNum }</td>
	</tr>
	<%} %>
	</c:forEach>
	 <tr>
		<td colspan="8">
					共 ${page.totalElements } 条记录 |
					共 ${page.totalPages } 页 |
					当前第 ${page.number + 1 } 页
			<a href="?pageNo=${page.number + 1 - 1 }">上一页</a>
			<c:if test="${page.totalPages>= page.number + 1 + 1}">
				<a href="?pageNo=${page.number + 1 + 1 }">下一页</a>
			</c:if>
			<c:if test="${page.totalPages< page.number + 1 + 1}">
			</c:if>
		</td>
	</tr>
</c:if>

</table>
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