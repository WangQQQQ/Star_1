<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<!-- Basic Page Needs
  ================================================== -->
	<meta charset="utf-8" />
	<title>城建数据分析页</title>
	<meta name="description" content="" />
	<meta name="author" content="" />

	<!-- Mobile Specific Metas
  ================================================== -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

	<!-- CSS
  ================================================== -->
	<link rel="stylesheet" href="css/style.css" />

  <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    
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

<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
function check() {
	var projectName = document.getElementById("projectName").value;
	var totalInvestment=document.getElementById("totalInvestment").value;
	var projectPeriod=document.getElementById("projectPeriod").value;
	var buildTotalArea=document.getElementById("buildTotalArea").value;
	var date=document.getElementById("dt").value;
	var maxSpan=document.getElementById("maxSpan").value;
	var geographyAddress=document.getElementById("geographyAddress").value;
	var geographyRegion=document.getElementById("geographyRegion").value;
	var percent1=document.getElementById("percent1").value;
	var percent2=document.getElementById("percent2").value;
	var percent3=document.getElementById("percent3").value;
	var percent4=document.getElementById("percent4").value;
	var percent5=document.getElementById("percent5").value;
	var percent6=document.getElementById("percent6").value;
	var percent7=document.getElementById("percent7").value;
	var percent8=document.getElementById("percent8").value;
	var result = date
			.match(/((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/);
	//alert(parseFloat(percent1)+parseFloat(percent1));
	if(projectName==""){
		alert("请输入项目名称！");
		return false;
	}
	if(isNaN(percent1)||percent1==""||percent1==0.0){
		alert("请输入正确的项目名称百分数！");
		return false;
	}
	 if (isNaN(totalInvestment)||totalInvestment == ""||totalInvestment==0.0) {
	    	alert("请输入投资总额！");
			return false;
		}
	 if(isNaN(percent2)||percent2==""||percent2==0.0){
			alert("请输入正确的投资总额百分数！");
			return false;
		}
	if(isNaN(projectPeriod)||projectPeriod==""||projectPeriod==0){
			alert("请输入工期！");
			return false;
	}
	if(isNaN(percent3)||percent3==""||percent3==0.0){
		alert("请输入正确的工期百分数！");
		return false;
	}
	if(isNaN(buildTotalArea)||buildTotalArea==""||buildTotalArea==0.0){
		alert("请输入工程总面积！");
		return false;
	}
	if(isNaN(percent4)||percent4==""||percent4==0.0){
		alert("请输入正确的工期百分数！");
		return false;
	}
	if (result == null||date=="") {
		alert("请输入正确的日期格式");
		return false;
	}
	if(isNaN(percent5)||percent5==""||percent5==0.0){
		alert("请输入正确的日期百分数！");
		return false;
	}
	if (isNaN(maxSpan) || maxSpan == ""||maxSpan==0.0) {
		alert("请输入最大跨度！");
		return false;
	}
	if(isNaN(percent6)||percent6==""||percent6==0.0){
		alert("请输入正确的最大跨度百分数！");
		return false;
	}
	if (geographyAddress == "") {
		alert("请输入项目地址！");
		return false;
	}
	if(isNaN(percent7)||percent7==""||percent7==0.0){
		alert("请输入正确的项目地址百分数！");
		return false;
	}
	if (geographyRegion == "") {
		alert("请输入地理区域！");
		return false;
	}
	if(isNaN(percent8)||percent8==""||percent8==0.0){
		alert("请输入正确的项目地理区域百分数！");
		return false;
	}
	if(parseFloat(percent1)+parseFloat(percent2)+parseFloat(percent3)+parseFloat(percent4)+parseFloat(percent5)+parseFloat(percent6)+parseFloat(percent7)+parseFloat(percent8)!=100){
		alert("权重百分数和不等于100！");
		return false;
	}
	return true;
}
</script>
<link href="css/Roboto.css" rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/calendar.css">
<style type="text/css">
ul, ol, li {
	list-style: none;
	padding: 0;
	margin: 0;
}

#dt {
	margin: 0px auto;
	height: 28px;
	width: 150px;
	padding: 0 6px;
	border: 1px solid #ccc;
	outline: none;
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
				<li><a href="#"  >城建信息</a>
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
<h3>城建数据分析页</h3>
<p>输入城市建设分析数据，系统将会进行数据挖掘分析，并最终给予结论</p>
</div>

</section>
<section class="container page-content">
<form:form action="${pageContext.request.contextPath}/analysis2" method="POST" modelAttribute="analysisProCom" class="new_user_form inline-input" onsubmit="return check()">
                                 <div class="span12 field-box">
                                    <label>项目名称:</label>
                                    <form:input placeholder="项目名称" path="projectName" id="projectName" style="width:300px; height:16px" />
                                                                           &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权重(百分比)：
                                    <form:input placeholder="百分比%" path="percent1" id="percent1" style="width:200px; height:16px" />%
                                </div>
                                 <div class="span12 field-box">
                                    <label>投资总额:</label>
                                    <form:input  placeholder="投资总额" path="totalInvestment" id="totalInvestment" style="width:300px; height:16px"/>
                                       	 &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权重(百分比)：
                                    <form:input class="span2" placeholder="百分比%" path="percent2" id="percent2" style="width:200px; height:16px" />%
                                </div>
                                <div class="span12 field-box">
                                    <label>工期:</label>
                                    <form:input  placeholder="工期" path="projectPeriod" id="projectPeriod" style="width:300px; height:16px"/>
                                       	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权重(百分比)：
                                    <form:input class="span2" placeholder="百分比%" path="percent3" id="percent3" style="width:200px; height:16px" />%
                                </div>
                                <div class="span12 field-box">
                                    <label>工程总面积:</label>
                                    <form:input  placeholder="工程总面积" path="buildTotalArea" id="buildTotalArea" style="width:300px; height:16px"/>
                                       	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权重(百分比)：
                                    <form:input class="span2" placeholder="百分比%" path="percent4" id="percent4" style="width:200px; height:16px" />%
                                </div>
                                <div class="span12 field-box">
									<label>报建时间:</label> 
									<form:input type="text" id="dt" placeholder="点击选择日期" path="constructTime" style="width:300px; height:30px"/>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权重(百分比)：
									<form:input class="span2" placeholder="百分比%" path="percent5" id="percent5" style="width:200px; height:16px" />%
									<div id="dd"></div>
									 	
                                    
								</div>
								<div class="span12 field-box">
                                    <label>最大跨度:</label>
                                    <form:input placeholder="最大跨度" path="maxSpan" id="maxSpan" style="width:300px; height:16px"/>
                                    	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权重(百分比)：
                                    <form:input class="span2" placeholder="百分比%" path="percent6" id="percent6" style="width:200px; height:16px" />%
                                </div>
                                <div class="span12 field-box">
                                    <label>项目地址:</label>
                                    <form:input placeholder="项目地址" path="geographyAddress" id="geographyAddress" style="width:300px; height:16px"/>
                                    	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权重(百分比)：
                                    <form:input class="span2" placeholder="百分比%" path="percent7" id="percent7" style="width:200px; height:16px" />%
                                </div>
                                <div class="span12 field-box">
                                    <label>地理区域:</label>
                                    <form:input placeholder="地理区域" path="geographyRegion" id="geographyRegion" style="width:300px; height:16px"/>
                                    	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权重(百分比)：
                                    <form:input class="span2" placeholder="百分比%" path="percent8" id="percent8" style="width:200px; height:16px" />%
                                </div>
                                <div class="span11 field-box actions">
                                    <input type="submit" value="分析" class="btn-glow primary"/>
                                    <span>或</span>
                                    <input type="reset" value="重置" class="reset" />
                                </div>
                                 </form:form>

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
   <script src="js/calendar.js"></script>
    <script type="text/javascript">
    $(window).load(function() {
        $('#slider').nivoSlider();
    });
    
    $('#dd').calendar({
		trigger : '#dt',
		zIndex : 999,

		format : 'yyyy-mm-dd',
		onSelected : function(view, date, data) {
			console.log('event: onSelected')
		},
		onClose : function(view, date, data) {
			console.log('event: onClose')
			console.log('view:' + view)
			console.log('date:' + date)
			console.log('data:' + (data || 'None'));
		}
	});
    
    </script>
	<script type="text/javascript" src="js/latest-twitt.js"></script>
	<script type="text/javascript" src="js/vincie-send.js"></script>
	
</body>
</html>