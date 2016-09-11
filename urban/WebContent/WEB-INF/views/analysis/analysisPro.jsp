<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <link rel="stylesheet" href="css/compiled/new-user.css" type="text/css" media="screen" />

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
</style>

<title>城建案例页</title>
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
</head>
<body>
<!-- navbar -->
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <button type="button" class="btn btn-navbar visible-phone" id="menu-toggler">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            
            <a class="brand" href=""><img src="img/logo55.png" /></a>

            <ul class="nav pull-right">                
                 <li class="settings hidden-phone">
                    <a href="" role="button">
                        <i class="icon-cog"></i>
                    </a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle hidden-phone" data-toggle="dropdown">
                       
                        欢迎  ${sessionScope.currentUser }
                       
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="login.jsp">登出</a></li>
                    </ul>
                </li>
              
            </ul>            
        </div>
    </div>
    <!-- end navbar -->

    <!-- sidebar -->
    <div id="sidebar-nav">
        <ul id="dashboard-menu">
             <li >
                <a href="backToMain">
                    <i class="icon-home"></i>
                    <span>主页</span>
                </a>
            </li>    
             <li>
                <a  href="list">
                    <i class="icon-group"></i>
                    <span>用户管理</span>
                </a>
            </li>
                    
            <li>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-signal"></i>
                    <span>企业信息管理</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="enterpriseList">企业信息查询/修改</a></li>
                    <li><a href="addEnterprise">企业信息添加</a></li>
                </ul>
            </li>
            <li>
                <a href="geographyList">
                    <i class="icon-code-fork"></i>
                    <span>地理信息管理</span>
                </a>
            </li>
            <li>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-th-large"></i>
                    <span>城建信息管理</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="goToProject">城建信息导入</a></li>
                    <li><a href="projectList">城建信息显示/修改</a></li>
                    <li><a href="baiduMap">调用百度Map</a></li>
                </ul>
            </li>
              <li class="active">
                <div class="pointer">
                    <div class="arrow"></div>
                    <div class="arrow_border"></div>
                </div>
                <a  href="analysis">
                    <i class="icon-edit"></i>
                    <span>城建数据分析</span>
                </a>
            </li>
             <li>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-calendar-empty"></i>
                    <span>综合数据分析</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="geographyCount">地理信息统计</a></li>
                    <li><a href="statusCount">城建信息统计</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <!-- end sidebar -->


	<!-- main container -->
    <div class="content">
      <div class="container-fluid">
            <div id="pad-wrapper" class="new-user">
                <div class="row-fluid header">
                    <h3>输入城建分析数据</h3>
                </div>
                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span9 with-sidebar">
                        <div class="container">
                        <form:form action="${pageContext.request.contextPath}/analysis2" method="POST" modelAttribute="analysisProCom" class="new_user_form inline-input" onsubmit="return check()">
                                 <div class="span12 field-box">
                                    <label>项目名称:</label>
                                    <form:input class="span5" placeholder="项目名称" path="projectName" id="projectName"/>
                                                                            &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权重(百分比)：
                                    <form:input class="span2" placeholder="百分比%" path="percent1" id="percent1"/>%
                                </div>
                                 <div class="span12 field-box">
                                    <label>投资总额:</label>
                                    <form:input class="span5" placeholder="投资总额" path="totalInvestment" id="totalInvestment"/>
                                       	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权重(百分比)：
                                    <form:input class="span2" placeholder="百分比%" path="percent2" id="percent2"/>%
                                </div>
                                <div class="span12 field-box">
                                    <label>工期:</label>
                                    <form:input class="span5" placeholder="工期" path="projectPeriod" id="projectPeriod"/>
                                       	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权重(百分比)：
                                    <form:input class="span2" placeholder="百分比%" path="percent3" id="percent3"/>%
                                </div>
                                <div class="span12 field-box">
                                    <label>工程总面积:</label>
                                    <form:input class="span5" placeholder="工程总面积" path="buildTotalArea" id="buildTotalArea"/>
                                       	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权重(百分比)：
                                    <form:input class="span2" placeholder="百分比%" path="percent4" id="percent4"/>%
                                </div>
                                <div class="span12 field-box">
									<label>报建时间:</label> 
									<form:input type="text" id="dt" placeholder="点击选择日期" path="constructTime"/>
									<div id="dd"></div>
									 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 	权重(百分比)：
                                    <form:input class="span2" placeholder="百分比%" path="percent5" id="percent5"/>%
								</div>
								<div class="span12 field-box">
                                    <label>最大跨度:</label>
                                    <form:input class="span5" placeholder="最大跨度" path="maxSpan" id="maxSpan"/>
                                    	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权重(百分比)：
                                    <form:input class="span2" placeholder="百分比%" path="percent6" id="percent6"/>%
                                </div>
                                <div class="span12 field-box">
                                    <label>项目地址:</label>
                                    <form:input class="span5" placeholder="项目地址" path="geographyAddress" id="geographyAddress"/>
                                    	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权重(百分比)：
                                    <form:input class="span2" placeholder="百分比%" path="percent7" id="percent7"/>%
                                </div>
                                <div class="span12 field-box">
                                    <label>地理区域:</label>
                                    <form:input class="span5" placeholder="地理区域" path="geographyRegion" id="geographyRegion"/>
                                    	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权重(百分比)：
                                    <form:input class="span2" placeholder="百分比%" path="percent8" id="percent8"/>%
                                </div>
                                <div class="span11 field-box actions">
                                    <input type="submit" value="分析" class="btn-glow primary"/>
                                    <span>或</span>
                                    <input type="reset" value="重置" class="reset" />
                                </div>
                                 </form:form>
                        </div>
                    </div>

                    <!-- side right column -->
                    <div class="span3 form-sidebar pull-right">
                        <div class="btn-group toggle-inputs hidden-tablet">
                            <button class="glow left active" data-input="inline">内联输入框</button>
                            <button class="glow right" data-input="normal">正常输入框</button>
                        </div>
                        <div class="alert alert-info hidden-tablet">
                            <i class="icon-lightbulb pull-left"></i>
                            点击查看表单中的输入框：内联输入和普通输入的不同显示
                        </div>                        
                    </div>
                </div>
            </div>
        </div>
    </div>


	<!-- scripts -->
    <script src="js/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>

	<script src="js/jquery.js"></script>
	<script src="js/calendar.js"></script>
    <script type="text/javascript">
        $(function () {

            // toggle form between inline and normal inputs
            var $buttons = $(".toggle-inputs button");
            var $form = $("form.new_user_form");

            $buttons.click(function () {
                var mode = $(this).data("input");
                $buttons.removeClass("active");
                $(this).addClass("active");

                if (mode === "inline") {
                    $form.addClass("inline-input");
                } else {
                    $form.removeClass("inline-input");
                }
            });
        });
        
    	$(function() {

			// toggle form between inline and normal inputs
			var $buttons = $(".toggle-inputs button");
			var $form = $("form.new_user_form");

			$buttons.click(function() {
				var mode = $(this).data("input");
				$buttons.removeClass("active");
				$(this).addClass("active");

				if (mode === "inline") {
					$form.addClass("inline-input");
				} else {
					$form.removeClass("inline-input");
				}
			});
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

</body>
</html>