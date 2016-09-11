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
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<!-- bootstrap -->
<link href="../css/bootstrap/bootstrap.css" rel="stylesheet" />
<link href="../css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
<link href="../css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

<!-- libraries -->
<link rel="stylesheet" type="text/css" href="../css/lib/font-awesome.css" />

<!-- global styles -->
<link rel="stylesheet" type="text/css" href="../css/layout.css" />
<link rel="stylesheet" type="text/css" href="../css/elements.css" />
<link rel="stylesheet" type="text/css" href="../css/icons.css" />

<!-- this page specific styles -->
<link rel="stylesheet" href="../css/compiled/new-user.css" type="text/css" media="screen" />   

<link href="../css/Roboto.css" rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="../css/calendar.css">
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
<title>企业修改页</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#eName").change(function(){
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
		
		var url = "${pageContext.request.contextPath }/ajaxValidateNewEnterpriseName";
		var args = {"eName":val,"date":new Date()};
		$.post(url, args, function(data){
			if(data == "0"){
				
			}else if(data == "1"){
				alert("企业名称已存在，不可用!");
			}else{
				alert("网络或程序出错. ");
			}
		});
	});
	
	$("#eId").change(function(){
		var val = $(this).val();
		val = $.trim(val);
		$(this).val(val);
		
		/* //若修改的 lastName 和之前的 lastName 一致, 则不发送 Ajax 请求, 直接 alert:lastName 可用!
		var _oldLastName = $("#_oldLastName").val();
		_oldLastName = $.trim(_oldLastName);
		if(_oldLastName != null && _oldLastName != "" && _oldLastName == val){
			alert("lastName 可用!");
			return;
		} */
		
		var url = "${pageContext.request.contextPath }/ajaxValidateNewEnterpriseId";
		var args = {"eId":val,"date":new Date()};
		$.post(url, args, function(data){
			if(data == "0"){
				
			}else if(data == "1"){
				alert("企业编号已存在，不可用!");
			}else{
				alert("网络或程序出错. ");
			}
		});
	});	
})
   function check() {
		var date = document.getElementById("dt").value;
		var bidNum=document.getElementById("bidNum").value;
		var eName=document.getElementById("eName").value;
		var registerFund=document.getElementById("registerFund").value;
		var eAddress=document.getElementById("eAddress").value;
		var result = date
				.match(/((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/);
		if(eName==""){
			alert("请输入企业名称！");
			return false;
		}
		 if (eAddress == "") {
		    	alert("请输入公司地址！");
				return false;
			}
		if(isNaN(registerFund)||registerFund==""){
				alert("请输入正确的注册资金：为数字");
				return false;
		}
		if (isNaN(bidNum) || bidNum == "") {
			alert("请输入正确的中标数：为数字");
			return false;
		}
		if (result == null && !isNaN(bidNum) && !isNaN(registerFund)) {
			alert("请输入正确的日期格式");
			return false;
		}
		if (eName != "" && eAddress != "" && result != null && !isNaN(bidNum)
				&& !isNaN(registerFund)) {
			return true;
		}
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
            
            <a class="brand" href=""><img src="../img/logo55.png" /></a>

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
                        <li><a href="logout">登出</a></li>
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
                <a href="../backToMain">
                    <i class="icon-home"></i>
                    <span>主页</span>
                </a>
            </li>    
              <li>
                <a  href="../list">
                    <i class="icon-group"></i>
                    <span>用户管理</span>
                </a>
            </li>
            <li class="active">
                <a class="dropdown-toggle" href="#">
                  <div class="pointer">
                    <div class="arrow"></div>
                    <div class="arrow_border"></div>
                   </div>
                    <i class="icon-signal"></i>
                    <span>企业信息管理</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="active submenu">
                    <li><a href="../enterpriseList" class="active">企业信息查询/修改</a></li>
                    <li><a href="../addEnterprise">企业信息添加</a></li>
                </ul>
            </li>
            <li>
                <a href="../geographyList">
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
                    <li><a href="../goToProject">城建信息导入</a></li>
                    <li><a href="../projectList">城建信息显示/修改</a></li>
                    <li><a href="../baiduMap">调用百度Map</a></li>
                </ul>
            </li>
              <li>
                <a  href="../analysis">
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
                    <li><a href="../geographyCount">地理信息统计</a></li>
                    <li><a href="../statusCount">城建信息统计</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <!-- end sidebar -->
	<!-- main container -->
    <div class="content">
           <c:set value="${pageContext.request.contextPath}/updateEnterprise" var="url"></c:set>
           <c:if test="${ modifyEnterpriseInfo.eId!=null}">
           <c:set value="${pageContext.request.contextPath}/updateEnterprise/${modifyEnterpriseInfo.eId}" var="url"></c:set>
           </c:if>
           
           <div class="container-fluid">
            <div id="pad-wrapper" class="new-user">
                <div class="row-fluid header">
                    <h3>修改企业信息</h3>
                </div>
                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span9 with-sidebar">
                     <div class="container">
                     <form:form action="${url}" method="POST" modelAttribute="modifyEnterpriseInfo"
	            	class="new_user_form inline-input" onsubmit="return check()">
	              	<c:if test="${modifyEnterpriseInfo.eId!=null}">
                      <input type="hidden" id="_oldLastName" value="${modifyEnterpriseInfo.eName }" />
                      <form:hidden path="eId"/>
                      <input type="hidden" name="_method" value="PUT"/>
                    </c:if>
                    <div class="span12 field-box">
						<label>用户名称:</label>
						<%-- <form:input class="span9" placeholder="用户编号"
							path="users.userId" /> --%>
			            <form:select path="users.userId" items="${userList}" itemLabel="userName" itemValue="userId" style="height:30px" ></form:select>
					</div>
                    <div class="span12 field-box">
                       <label>企业名称:</label>
	            	   <form:input class="span9" path="eName" id="eName" />
                    </div>
                    <div class="span12 field-box">
                       <label>公司地址:</label>
	            	   <form:input class="span9" path="eAddress" id="eAddress"/>
                    </div>
                    <div class="span12 field-box">
                       <label>注册资金:</label>
	            	   <form:input class="span9" path="registerFund" id="registerFund"/>
                    </div>
                    <div class="span12 field-box">
                       <label>上半年招标数:</label>
	            	   <form:input class="span9" path="bidNum" id="bidNum"/>
                    </div>
                    <div class="span12 field-box">
						<label>成立时间:</label> 
						<form:input type="text" id="dt" path="createTime"/>
						<div id="dd"></div>
					</div>
		                  <div class="span11 field-box actions">
                                    <input type="submit" value="修改完成" class="btn-glow primary"/>
                                    <span>OR</span>
                                    <a href="../enterpriseList">返回列表</a>
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
    <script src="../js/jquery-latest.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/theme.js"></script>

    <script src="../js/jquery.js"></script>
	<script src="../js/calendar.js"></script>
	
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