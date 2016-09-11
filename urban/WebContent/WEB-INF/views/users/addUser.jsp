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

    <!-- open sans font -->
  <!--   <link href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' /> -->

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<title>用户添加页</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	  $("#userName").click(function() {
		  $("#nameError").html("");
		  $("#nullError").html("");
		  $("#nameSuccess").html("");
	  });
	  $("#password").click(function() {
		  $("#nullError2").html("");
	  });
	  
	  $("#userName").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			$(this).val(val);
			
			/* //若修改的 lastName 和之前的 lastName 一致, 则不发送 Ajax 请求, 直接 alert:lastName 可用!
			var _oldLastName = $("#_oldLastName").val();
			_oldLastName = $.trim(_oldLastName);
			if(_oldLastName != null && _oldLastName != "" && _oldLastName == val){
				alert("lastName 可用!");
				return;
			} 
			*/
			var url = "${pageContext.request.contextPath }/ajaxValidateNewUserName";
			var args = {"userName":val,"date":new Date()};
			$.post(url, args, function(data){
				if(data == "0"){
					$("#nameSuccess").html("用户名可用!");
				}else if(data == "1"){
					$("#nameError").html("用户名已存在，不可用!");
					//alert("用户名已存在，不可用!");
				}else{
					alert("网络或程序出错. ");
				}
			});
		});
		
		/* $("#userId").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			$(this).val(val);
			
			//若修改的 lastName 和之前的 lastName 一致, 则不发送 Ajax 请求, 直接 alert:lastName 可用!
			var _oldLastName = $("#_oldLastName").val();
			_oldLastName = $.trim(_oldLastName);
			if(_oldLastName != null && _oldLastName != "" && _oldLastName == val){
				alert("lastName 可用!");
				return;
			} 
			
			var url = "${pageContext.request.contextPath }/ajaxValidateNewUserId";
			var args = {"userId":val,"date":new Date()};
			$.post(url, args, function(data){
				if(data == "0"){
					$("#message2").html("用户名可用!");
				}else if(data == "1"){
					$("#message2").html("用户名可用!");
				}else{
					alert("网络或程序出错. ");
				}
			}); 
		});*/
		
	 });
	 
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
             <li class="active">
             <div class="pointer">
                    <div class="arrow"></div>
                    <div class="arrow_border"></div>
                </div>
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
             <li>
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
                    <h3>新建一个管理员</h3>
                </div>
                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span9 with-sidebar">
                        <div class="container">
                        <form:form action="${pageContext.request.contextPath}/addUser" method="POST" modelAttribute="newUser" class="new_user_form inline-input">
                               <%--  <div class="span12 field-box">
                                    <label>ID:</label>
                                    <form:input class="span6" placeholder="用户账号" path="userId" id="userId" />
                                    <span id="idError" style="color:red">${idError}</span>
                                    <span id="nullError" style="color:red">${nullError}</span>
                                   <!--  class="glyphicon glyphicon-exclamation-sign" -->
                                </div> --%>
                                 <div class="span12 field-box">
                                    <label>姓名:</label>
                                    <form:input class="span6" placeholder="管理员名称" path="userName" id="userName"/>
                                    <span id="nameError" style="color:red" >${nameError}</span>
                                    <span id="nameSuccess" style="color:green" ></span>
                                    <span id="nullError" style="color:red">${nullError}</span>
                                </div>
                                 <div class="span12 field-box">
                                    <label>密码:</label>
                                    <form:password class="span6" placeholder="管理员密码" path="password" id="password"/>
                                    <span id="nullError2" style="color:red">${nullError}</span>
                                </div>
                                <div class="span12 field-box">
                                    <label>类别:</label>
                                    <div class="ui-select span6">
                                     <form:select path="userLevel" items="${userLevel}"/> 
                                     
                                     </div>
                                    <!-- <input class="span9" type="text" placeholder="用户类别"/> -->
                                </div>
                                <div class="span11 field-box actions">
                                    <input type="submit" value="提交" class="btn-glow primary"/>
                                    <span>或</span>
                                    <input type="reset" value="重置" class="reset" />
                                    <span>或</span>
                                    <a href="list">返回</a>
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
    </script>


</body>
</html>