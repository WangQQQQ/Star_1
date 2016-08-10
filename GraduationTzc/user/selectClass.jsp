<!DOCTYPE html>
<%@page pageEncoding = "UTF-8" %>
<%@taglib uri = "/struts-tags" prefix="s"%>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>Tables -智能分析</title>
		<meta name="description" content="Static & Dynamic Tables" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- basic styles -->
		<link href="/GraduationTzc/user/css/bootstrap.min.css" rel="stylesheet" />
		<link href="/GraduationTzc/user/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="/GraduationTzc/user/css/font-awesome.min.css" />
		<!--[if IE 7]>
		  <link rel="stylesheet" href="css/font-awesome-ie7.min.css" />
		<![endif]-->
		<!-- page specific plugin styles -->
		
		<!-- ace styles -->
		<link rel="stylesheet" href="/GraduationTzc/user/css/ace.min.css" />
		<link rel="stylesheet" href="/GraduationTzc/user/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="/GraduationTzc/user/css/ace-skins.min.css" />
		<!--[if lt IE 9]>
		  <link rel="stylesheet" href="css/ace-ie.min.css" />
		<![endif]-->
		<style type="text/css"> 
     
                #fullbg{background-color: #000;display:none;z-index:3;position:absolute;left:0px;top:0px;filter:Alpha(Opacity=30);/* IE */-moz-opacity:0.4;/* Moz + FF */opacity: 0.4;}  
     
                #dialog {position:absolute;width:200px;height:200px;background:#F00;display: none;z-index: 5;}  
     
                #main {  
                    height: 1500px;  
                }  
        </style> 
	</head>
	<body>
		<div class="navbar navbar-inverse">
		  <div class="navbar-inner">
		   <div class="container-fluid">
			  <span class="brand"><small><i class="icon-leaf"></i>智能分析系统</small> </span>
			  <ul class="nav ace-nav pull-right">
					<li class="light-blue user-profile">
						<a class="user-menu dropdown-toggle" href="#" data-toggle="dropdown">
							<img alt="User's Photo" src="/GraduationTzc/user/avatars/avatar.png" class="nav-user-photo" />
							<span id="user_info">
								Welcome, 
								<span style="color:#660033">gungun</span>
							</span>
						</a>
					</li>
			  </ul><!--/.ace-nav-->
		   </div><!--/.container-fluid-->
		  </div><!--/.navbar-inner-->
		</div><!--/.navbar-->
		<div class="container-fluid" id="main-container">
			<a href="#" id="menu-toggler"><span></span></a><!-- menu toggler -->


</div>
			<div id="sidebar">

				

				<div id="sidebar-shortcuts">

					<div id="sidebar-shortcuts-large">

						<button class="btn btn-small btn-success"><i class="icon-signal"></i></button>

						<button class="btn btn-small btn-info"><i class="icon-pencil"></i></button>

						<button class="btn btn-small btn-warning"><i class="icon-group"></i></button>

						<button class="btn btn-small btn-danger"><i class="icon-cogs"></i></button>

					</div>

					<div id="sidebar-shortcuts-mini">

						<span class="btn btn-success"></span>

						<span class="btn btn-info"></span>

						<span class="btn btn-warning"></span>

						<span class="btn btn-danger"></span>

					</div>

				</div><!-- #sidebar-shortcuts -->



				<ul class="nav nav-list">

					

					<li>

					  <a href="/GraduationTzc/student/toHome">

						<i class="icon-dashboard"></i>

						<span>主页</span>

						

					  </a>

					</li>
					
					
					<li class="active">

					  <a href="/GraduationTzc/student/findAllClass">

						<i class="icon-list"></i>

						<span>选择班级</span>
					  </a>

					</li>
					

					<li>

					  <a href="/GraduationTzc/student/findStudent">

						<i class="icon-list"></i>

						<span>学生信息</span>
					  </a>

					</li>


					<li>

					  <a href="/GraduationTzc/student/toBlank">

						<i class="icon-list"></i>

						<span>图表分析</span>
					  </a>

					</li>

					<li>

					  <a href="/GraduationTzc/student/toCalendar">

						<i class="icon-calendar"></i>

						<span>日历</span>

					  </a>

					</li>

				</ul><!--/.nav-list-->

				<div id="sidebar-collapse"><i class="icon-double-angle-left"></i></div>

			</div><!--/#sidebar-->



		
			<div id="main-content" class="clearfix">
					

					<div id="breadcrumbs">

						<ul class="breadcrumb">

							<li><i class="icon-home"></i> <a href="/GraduationTzc/user/index.jsp">Home</a><span class="divider"><i class="icon-angle-right"></i></span></li>

							<li class="active">选择班级</li>
						</ul><!--.breadcrumb-->
						
						<div id="nav-search">

						</div><!--#nav-search-->

					</div><!--#breadcrumbs-->
						<div id="page-content" class="clearfix">
						
							<div class="page-header position-relative" style="display: relative;">
						
							<h1>选择班级 <small><i class="icon-double-angle-right"></i></small></h1>
						</div><!--/page-header-->


					<div id="page-content" class="clearfix">
						
						
							<ul >
							<s:iterator value="allClass" id="p">
								<li style="display:block;float:left;width:30%"><a href="../student/selectClass?classId=<s:property value='#p.key'/>"><s:property value='#p.value'/></a>-(classId:<s:property value='#p.key'/>)</li>
							</s:iterator>
							</ul>
						<!-- PAGE CONTENT ENDS HERE -->
						 </div><!--/row-->
						 
					</div><!--/#page-content-->
					  
					<div id="ace-settings-container">
					</div><!--/#ace-settings-container-->
			</div><!-- #main-content -->
		</div><!--/.fluid-container#main-container-->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		<!-- basic scripts -->
		<script src="/GraduationTzc/user/1.9.1/jquery.min.js"></script>
		<script type="text/javascript">
		window.jQuery || document.write("<script src='/GraduationTzc/user/js/jquery-1.9.1.min.js'>\x3C/script>");
		</script>
		
		<script src="/GraduationTzc/user/js/bootstrap.min.js"></script>
		<!-- page specific plugin scripts -->
		
		<!-- ace scripts -->
		<script src="/GraduationTzc/user/js/ace-elements.min.js"></script>
		<script src="/GraduationTzc/user/js/ace.min.js"></script>
		<!-- inline scripts related to this page -->
		
	</body>
</html>
