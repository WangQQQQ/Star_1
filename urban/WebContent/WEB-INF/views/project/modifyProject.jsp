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
<title>城建信息修改页</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#projectName").change(function(){
		var val = $(this).val();
		val = $.trim(val);
		$(this).val(val);
		
		//若修改的 lastName 和之前的 lastName 一致, 则不发送 Ajax 请求, 直接 alert:lastName 可用!
		var _oldLastName = $("#_oldLastName").val();
		_oldLastName = $.trim(_oldLastName);
		if(_oldLastName != null && _oldLastName != "" && _oldLastName == val){
			alert("项目名称可用!");
			return;
		} 
		
		var url = "${pageContext.request.contextPath }/ajaxValidateNewProjectName";
		var args = {"projectName":val,"date":new Date()};
		$.post(url, args, function(data){
			if(data == "0"){
				
			}else if(data == "1"){
				alert("项目名称已存在，不可用!");
			}else{
				alert("网络或程序出错. ");
			}
		});
	});
})
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
                    
            <li>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-signal"></i>
                    <span>企业信息管理</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="../enterpriseList">企业信息查询/修改</a></li>
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
           <c:set value="${pageContext.request.contextPath}/updateProject" var="url"></c:set>
           <c:if test="${ modifyProject.pId!=null}">
           <c:set value="${pageContext.request.contextPath}/updateProject/${modifyProject.pId}" var="url"></c:set>
           </c:if>
           
           <div class="container-fluid">
            <div id="pad-wrapper" class="new-user">
                <div class="row-fluid header">
                    <h3>修改城建信息</h3>
                </div>
                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span9 with-sidebar">
                     <div class="container">
                     <form:form action="${url}" method="POST" modelAttribute="modifyProject"
	            	class="new_user_form inline-input">
	              	<c:if test="${modifyProject.pId!=null}">
                      <input type="hidden" id="_oldLastName" value="${modifyProject.projectName }" />
                      <form:hidden path="pId"/>
                      <input type="hidden" name="_method" value="PUT"/>
                    </c:if>
                    <div class="span12 field-box">
						<label>建设单位名称:</label>
			            <form:select path="enterpriseInfo.eId" items="${enterpriseList}" itemLabel="eName" itemValue="eId" style="height:30px" ></form:select>
					</div>
                    <div class="span12 field-box">
                       <label>项目名称:</label>
	            	   <form:input class="span9" path="projectName" id="projectName" />
                    </div>
                    <div class="span12 field-box">
                       <label>总投资额(万元):</label>
	            	   <form:input class="span9" path="totalInvestment" />
                    </div>
                    <div class="span12 field-box">
                       <label>工期:</label>
	            	   <form:input class="span9" path="projectPeriod" />
                    </div>
                    <div class="span12 field-box">
                       <label>工程总面积:</label>
	            	   <form:input class="span9" path="buildTotalArea" />
                    </div>
                    <div class="span12 field-box">
                       <label>最大跨度:</label>
	            	   <form:input class="span9" path="maxSpan" />
                    </div>
                    <div class="span12 field-box">
						<label>状态:</label>
			            <form:select path="projectStatus.sId" items="${statusList}" itemLabel="sName" itemValue="sId" style="height:30px" ></form:select>
					</div>
                    <div class="span12 field-box">
						<label>报建时间:</label> 
						<form:input type="text" id="dt" path="constructTime"/>
						<div id="dd"></div>
					</div>
		                  <div class="span11 field-box actions">
                                    <input type="submit" value="修改完成" class="btn-glow primary"/>
                                    <span>OR</span>
                                    <a href="../projectList">返回列表</a>
                        </div>
	                  </form:form>
	
                     </div>
                    </div>

                    <!-- side right column -->
                    <div class="span3 form-sidebar pull-right">
                        <div class="btn-group toggle-inputs hidden-tablet">
                            <button class="glow left active" data-input="inline">INLINE INPUTS</button>
                            <button class="glow right" data-input="normal">NORMAL INPUTS</button>
                        </div>
                        <div class="alert alert-info hidden-tablet">
                            <i class="icon-lightbulb pull-left"></i>
                            Click above to see difference between inline and normal inputs on a form
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