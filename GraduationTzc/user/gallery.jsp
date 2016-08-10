<!DOCTYPE html>
<%@page pageEncoding = "UTF-8" %>
<%@taglib uri = "/struts-tags" prefix="s"%>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>Gallery - Ace Admin</title>
		<meta name="description" content="responsive photo gallery using colorbox" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- basic styles -->
		<link href="css/bootstrap.min.css" rel="stylesheet" />
		<link href="css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="css/font-awesome.min.css" />
		<!--[if IE 7]>
		  <link rel="stylesheet" href="css/font-awesome-ie7.min.css" />
		<![endif]-->
		<!-- page specific plugin styles -->
		
		<link rel="stylesheet" href="css/colorbox.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="css/ace.min.css" />
		<link rel="stylesheet" href="css/ace-responsive.min.css" />
		<link rel="stylesheet" href="css/ace-skins.min.css" />
		<!--[if lt IE 9]>
		  <link rel="stylesheet" href="css/ace-ie.min.css" />
		<![endif]-->
	</head>
	<body>
		<div class="navbar navbar-inverse">
		  <div class="navbar-inner">
		   <div class="container-fluid">
			  <a class="brand" href="#"><small><i class="icon-leaf"></i>智能分析系统</small> </a>
		   </div><!--/.container-fluid-->
		  </div><!--/.navbar-inner-->
		</div><!--/.navbar-->
		<div class="container-fluid" id="main-container">
			<a href="#" id="menu-toggler"><span></span></a><!-- menu toggler -->

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

					  <a href="/GraduationTzc/user/index.jsp">

						<i class="icon-dashboard"></i>

						<span>主页</span>

						

					  </a>

					</li>
					
					
					<li>

					  <a href="/GraduationTzc/student/toFindAllClass">

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

					  <a href="/GraduationTzc/user/blank.jsp">

						<i class="icon-list"></i>

						<span>图表分析</span>
					  </a>

					</li>

					<li>

					  <a href="/GraduationTzc/user/calendar.jsp">

						<i class="icon-calendar"></i>

						<span>日历</span>

						

					  </a>

					</li>



					<li class="active">

					  <a href="/GraduationTzc/user/gallery.jsp">

						<i class="icon-picture"></i>

						<span>画廊</span>

						

					  </a>

					</li>
					

				</ul><!--/.nav-list-->



				<div id="sidebar-collapse"><i class="icon-double-angle-left"></i></div>





			</div><!--/#sidebar-->



		
			<div id="main-content" class="clearfix">
					

					<div id="breadcrumbs">

						<ul class="breadcrumb">

							<li><i class="icon-home"></i> <a href="/GraduationTzc/user/index.jsp">Home</a><span class="divider"><i class="icon-angle-right"></i></span></li>

							<li class="active">画廊</li>
						</ul><!--.breadcrumb-->


					</div><!--#breadcrumbs-->



					<div id="page-content" class="clearfix">
						
						<div class="page-header position-relative">
							<h1>Gallery <small><i class="icon-double-angle-right"></i> responsive photo gallery using colorbox</small></h1>
						</div><!--/page-header-->
						
						<div class="row-fluid">
<!-- PAGE CONTENT BEGINS HERE -->
<div class="row-fluid">
	<ul class="ace-thumbnails">
	  <li>
		<a href="assets/images/gallery/image-1.jpg" title="Photo Title" data-rel="colorbox">
		  <img alt="150x150" src="gallery/thumb-1.jpg" />
		  <div class="tags">
			<span class="label label-info">breakfast</span>
			<span class="label label-important">fruits</span>
			<span class="label label-success">toast</span>
			<span class="label label-warning arrowed-in">diet</span>
		  </div>
		</a>
		<div class="tools">
			<a href="#"><i class="icon-link"></i></a>
			<a href="#"><i class="icon-paper-clip"></i></a>
			<a href="#"><i class="icon-pencil"></i></a>
			<a href="#"><i class="icon-remove red"></i></a>
		</div>
	  </li>
	  <li>
		<a href="assets/images/gallery/image-2.jpg" data-rel="colorbox">
		   <img alt="150x150" src="gallery/thumb-2.jpg">
		   <div class="text">
			<div class="inner">Sample Caption on Hover</div>
		   </div>
		</a>
	  </li>
	  <li>
		<a href="assets/images/gallery/image-3.jpg" data-rel="colorbox">
		   <img alt="150x150" src="gallery/thumb-3.jpg">
		   <div class="text">
			<div class="inner">Sample Caption on Hover</div>
		   </div>
		</a>
		<div class="tools tools-bottom">
			<a href="#"><i class="icon-link"></i></a>
			<a href="#"><i class="icon-paper-clip"></i></a>
			<a href="#"><i class="icon-pencil"></i></a>
			<a href="#"><i class="icon-remove red"></i></a>
		</div>
	  </li>
	  <li>
		<a href="assets/images/gallery/image-4.jpg" data-rel="colorbox">
		   <img alt="150x150" src="gallery/thumb-4.jpg">
		   <div class="tags">
			<span class="label label-info arrowed">fountain</span>
			<span class="label label-important">recreation</span>
		  </div>
		</a>
		<div class="tools tools-top">
			<a href="#"><i class="icon-link"></i></a>
			<a href="#"><i class="icon-paper-clip"></i></a>
			<a href="#"><i class="icon-pencil"></i></a>
			<a href="#"><i class="icon-remove red"></i></a>
		</div>
	  </li>
	  <li>
		<div>
		  <img alt="150x150" src="gallery/thumb-5.jpg">
		  <div class="text">
			<div class="inner">
				<span>Some Title!</span>
				<br />
				<a href="assets/images/gallery/image-5.jpg" data-rel="colorbox"><i class="icon-zoom-in"></i></a>
				<a href="#"><i class="icon-user"></i></a>
				<a href="#"><i class="icon-share-alt"></i></a>
			</div>
		  </div>
		</div>
	  </li>
	  <li>
		<a href="assets/images/gallery/image-6.jpg" data-rel="colorbox">
		   <img alt="150x150" src="gallery/thumb-6.jpg">
		</a>
		<div class="tools tools-right">
			<a href="#"><i class="icon-link"></i></a>
			<a href="#"><i class="icon-paper-clip"></i></a>
			<a href="#"><i class="icon-pencil"></i></a>
			<a href="#"><i class="icon-remove red"></i></a>
		</div>
	  </li>
	  <li>
		<a href="assets/images/gallery/image-1.jpg" data-rel="colorbox">
		   <img alt="150x150" src="gallery/thumb-1.jpg">
		</a>
		<div class="tools">
			<a href="#"><i class="icon-link"></i></a>
			<a href="#"><i class="icon-paper-clip"></i></a>
			<a href="#"><i class="icon-pencil"></i></a>
			<a href="#"><i class="icon-remove red"></i></a>
		</div>
	  </li>
	  <li>
		<a href="assets/images/gallery/image-2.jpg" data-rel="colorbox">
		   <img alt="150x150" src="gallery/thumb-2.jpg">
		</a>
		<div class="tools tools-top">
			<a href="#"><i class="icon-link"></i></a>
			<a href="#"><i class="icon-paper-clip"></i></a>
			<a href="#"><i class="icon-pencil"></i></a>
			<a href="#"><i class="icon-remove red"></i></a>
		</div>
	  </li>
   </ul>
</div>
<!-- PAGE CONTENT ENDS HERE -->
						 </div><!--/row-->
	
					</div><!--/#page-content-->
					</div><!--/#ace-settings-container-->
			</div><!-- #main-content -->
		</div><!--/.fluid-container#main-container-->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		<!-- basic scripts -->
		<script src="1.9.1/jquery.min.js"></script>
		<script type="text/javascript">
		window.jQuery || document.write("<script src='js/jquery-1.9.1.min.js'>\x3C/script>");
		</script>
		
		<script src="js/bootstrap.min.js"></script>
		<!-- page specific plugin scripts -->
		
		<script type="text/javascript" src="js/jquery.colorbox-min.js"></script>
		<!-- ace scripts -->
		<script src="js/ace-elements.min.js"></script>
		<script src="js/ace.min.js"></script>
		<!-- inline scripts related to this page -->
		
		<script type="text/javascript">
		
$(function() {
	var colorbox_params = {
		reposition:true,
		scalePhotos:true,
		scrolling:false,
		previous:'<i class="icon-arrow-left"></i>',
		next:'<i class="icon-arrow-right"></i>',
		close:'&times;',
		current:'{current} of {total}',
		maxWidth:'100%',
		maxHeight:'100%',
		onOpen:function(){
			document.body.style.overflow = 'hidden';
		},
		onClosed:function(){
			document.body.style.overflow = 'auto';
		},
		onComplete:function(){
			$.colorbox.resize();
		}
	};
	$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
	$(window).on('resize.colorbox', function() {
		try {
			$.fn.colorbox.load();//to redraw the current frame
		} catch(e){}
	});
})
		</script>
	</body>
</html>
