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

					

					<li class="active">

					  <a href="/GraduationTzc/student/toHome">

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

							<li class="active">主页</li>
						</ul><!--.breadcrumb-->


					</div><!--#breadcrumbs-->



					<div id="page-content" class="clearfix">
						
						<div class="page-header position-relative">
							<h1>主页 <small><i class="icon-double-angle-right"></i> 综述 & 概况</small></h1>
						</div><!--/page-header-->
						
						<div class="row-fluid">
<!-- PAGE CONTENT BEGINS HERE -->
<div class="alert alert-block alert-success">
 <button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>
 <i class="icon-ok green"></i> 欢迎来到 <strong class="green"> 智能分析系统<small>(基于数据挖掘)</small></strong>,
 以下是对本系统的简单介绍：
</div>
<div class="space-6"></div>
<div class="row-fluid">
 <div class="span7 infobox-container">
   
	
		<div class="infobox-data">
			<span class="infobox-text">traffic used</span>
			<span class="infobox-content"><span class="approx"></span> 58GB remaining</span>
		</div>
	</div>
	
	<div class="space-6"></div>
	
	
 </div>
  <div>
 <h2>
 	本系统基于数据挖掘，对采集到的数据进行分析并形成良好的柱状图，折线图等。
 	数据挖掘：目前一种新的重要的研究领域，从大量的不完全的，模糊的数据中提取有用信息。
 	随着信息技术的高速发展，人们积累的数据量急剧增长，如何从海量的数据中提取有用的信，
 	息成为当务之急。数据挖掘就是为顺应这种需要应运而生发展起来的数据处理技术。是知识
 	发现的关键步骤。
 </h2>
 </div>
 <div class="vspace"></div>
 <div class="span5">
	<div class="widget-box">
		<div class="widget-header widget-header-flat widget-header-small">
			<h5><i class="icon-signal"></i>图表分析</h5>
			<div class="widget-toolbar no-border">
				<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown">本周 <i class="icon-angle-down icon-on-right"></i></button>
				<ul class="dropdown-menu dropdown-info pull-right dropdown-caret">
					<li class="active"><a href="#">本周</a></li>
					<li><a href="#">Last Week</a></li>
					<li><a href="#">This Month</a></li>
					<li><a href="#">Last Month</a></li>
				</ul>
			</div>
		</div>
		<div class="widget-body">
		 <div class="widget-main">
			<div id="piechart-placeholder"></div>
			
			<div class="hr hr8 hr-double"></div>
			
			<div class="clearfix">
				<div class="grid3">
					<span class="grey"><i class="icon-facebook-sign icon-2x blue"></i> &nbsp; 例1</span>
					<h4 class="bigger pull-right">1,255</h4>
				</div>
				
				<div class="grid3">
					<span class="grey"><i class="icon-twitter-sign icon-2x purple"></i> &nbsp; 例2</span>
					<h4 class="bigger pull-right">941</h4>
				</div>
				
				<div class="grid3">
					<span class="grey"><i class="icon-pinterest-sign icon-2x red"></i> &nbsp; 例3</span>
					<h4 class="bigger pull-right">1,050</h4>
				</div>
			</div>
		 </div><!--/widget-main-->
		</div><!--/widget-body-->
	</div><!--/widget-box-->
 </div><!--/span-->
 

</div><!--/row-->
<div class="hr hr32 hr-dotted"></div>
<div class="row-fluid">
	<div class="span5">
		<div class="widget-box transparent">
			<div class="widget-header widget-header-flat">
				<h4 class="lighter"><i class="icon-star orange"></i>数据显示</h4>
				<div class="widget-toolbar">
					<a href="#" data-action="collapse"><i class="icon-chevron-up"></i></a>
				</div>
			</div>
			
			<div class="widget-body">
			 <div class="widget-main no-padding">
			  <table id="table_bug_report" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th><i class="icon-caret-right blue"></i>货物名称</th>
						<th><i class="icon-caret-right blue"></i>价格</th>
						<th class="hidden-phone"><i class="icon-caret-right blue"></i>状态</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="">gungun</td>
						<td>
							<small><s class="red">$29.99</s></small>
							<b class="green">$19.99</b>
						</td>
						<td class="hidden-phone"><span class="label label-info arrowed-right arrowed-in">上架中</span></td>
					</tr>
					
					<tr>
						<td class="">apple</td>
						<td>
							<b class="blue">$16.45</b>
						</td>
						<td class="hidden-phone"><span class="label label-success arrowed-in arrowed-in-right">通过检验</span></td>
					</tr>
					
					<tr>
						<td class="">banana</td>
						<td>
							<b class="blue">$15.00</b>
						</td>
						<td class="hidden-phone"><span class="label label-important arrowed">待检验</span></td>
					</tr>
					<tr>
						<td class="">watermelon</td>
						<td>
							<small><s class="red">$19.95</s></small>
							<b class="green">$14.99</b>
						</td>
						<td class="hidden-phone"><span class="label arrowed"><s>已脱销</s></span></td>
					</tr>
					
					<tr>
						<td class="">peach</td>
						<td>
							<b class="blue">$12.00</b>
						</td>
						<td class="hidden-phone"><span class="label label-warning arrowed arrowed-right">销售</span></td>
					</tr>
				</tbody>
			  </table>
			 </div><!--/widget-main-->
			</div><!--/widget-body-->
		</div><!--/widget-box-->
	</div>
	
	<div class="span7">
	  <div class="widget-box transparent">
		<div class="widget-header widget-header-flat">
			<h4 class="lighter"><i class="icon-signal"></i> 状态信息</h4>
			<div class="widget-toolbar">
				<a href="#" data-action="collapse"><i class="icon-chevron-up"></i></a>
			</div>
		</div>
		
		<div class="widget-body">
		 <div class="widget-main padding-5">
			<div id="sales-charts"></div>
		 </div><!--/widget-main-->
		</div><!--/widget-body-->
	  </div><!--/widget-box-->
	</div>
</div>
<div class="hr hr32 hr-dotted"></div>
<div class="row-fluid">
 <div class="span6">
	<div class="widget-box transparent">
		
		
		<div class="widget-body">
		 </div><!--/widget-main-->
		</div><!--/widget-body-->
		
		
	</div><!--/widget-box-->
 </div><!--/span-->
 
 <div class="span6">
	<div class="widget-box ">
		 </div><!--/widget-main-->
		</div><!--/widget-body-->
		
	</div><!--/widget-box-->
 </div><!--/span-->
</div><!--/row-->
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
		
		<!--[if lt IE 9]>
		<script type="text/javascript" src="js/excanvas.min.js"></script>
		<![endif]-->
		<script type="text/javascript" src="/GraduationTzc/user/js/jquery-ui-1.10.2.custom.min.js"></script>
		<script type="text/javascript" src="/GraduationTzc/user/js/jquery.ui.touch-punch.min.js"></script>
		<script type="text/javascript" src="/GraduationTzc/user/js/jquery.slimscroll.min.js"></script>
		<script type="text/javascript" src="/GraduationTzc/user/js/jquery.easy-pie-chart.min.js"></script>
		<script type="text/javascript" src="/GraduationTzc/user/js/jquery.sparkline.min.js"></script>
		<script type="text/javascript" src="/GraduationTzc/user/js/jquery.flot.min.js"></script>
		<script type="text/javascript" src="/GraduationTzc/user/js/jquery.flot.pie.min.js"></script>
		<script type="text/javascript" src="/GraduationTzc/user/js/jquery.flot.resize.min.js"></script>
		<!-- ace scripts -->
		<script src="/GraduationTzc/user/js/ace-elements.min.js"></script>
		<script src="/GraduationTzc/user/js/ace.min.js"></script>
		<!-- inline scripts related to this page -->
		
		<script type="text/javascript">
		
$(function() {
	$('.dialogs,.comments').slimScroll({
        height: '300px'
    });
	
	$('#tasks').sortable();
	$('#tasks').disableSelection();
	$('#tasks input:checkbox').removeAttr('checked').on('click', function(){
		if(this.checked) $(this).closest('li').addClass('selected');
		else $(this).closest('li').removeClass('selected');
	});
	var oldie = $.browser.msie && $.browser.version < 9;
	$('.easy-pie-chart.percentage').each(function(){
		var $box = $(this).closest('.infobox');
		var barColor = $(this).data('color') || (!$box.hasClass('infobox-dark') ? $box.css('color') : 'rgba(255,255,255,0.95)');
		var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)' : '#E2E2E2';
		var size = parseInt($(this).data('size')) || 50;
		$(this).easyPieChart({
			barColor: barColor,
			trackColor: trackColor,
			scaleColor: false,
			lineCap: 'butt',
			lineWidth: parseInt(size/10),
			animate: oldie ? false : 1000,
			size: size
		});
	})
	$('.sparkline').each(function(){
		var $box = $(this).closest('.infobox');
		var barColor = !$box.hasClass('infobox-dark') ? $box.css('color') : '#FFF';
		$(this).sparkline('html', {tagValuesAttribute:'data-values', type: 'bar', barColor: barColor , chartRangeMin:$(this).data('min') || 0} );
	});
	
	
  var data = [
	{ label: "数据1",  data: 38.7, color: "#68BC31"},
	{ label: "数据2",  data: 24.5, color: "#2091CF"},
	{ label: "数据3",  data: 8.2, color: "#AF4E96"},
	{ label: "数据4",  data: 18.6, color: "#DA5430"},
	{ label: "数据5",  data: 10, color: "#FEE074"}
  ];
 var placeholder = $('#piechart-placeholder').css({'width':'90%' , 'min-height':'150px'});
 $.plot(placeholder, data, {
	
	series: {
        pie: {
            show: true,
			tilt:0.8,
			highlight: {
				opacity: 0.25
			},
			stroke: {
				color: '#fff',
				width: 2
			},
			startAngle: 2
			
        }
    },
    legend: {
        show: true,
		position: "ne", 
	    labelBoxBorderColor: null,
		margin:[-30,15]
    }
	,
	grid: {
		hoverable: true,
		clickable: true
	},
	tooltip: true, //activate tooltip
	tooltipOpts: {
		content: "%s : %y.1",
		shifts: {
			x: -30,
			y: -50
		}
	}
	
 });
 
  var $tooltip = $("<div class='tooltip top in' style='display:none;'><div class='tooltip-inner'></div></div>").appendTo('body');
  placeholder.data('tooltip', $tooltip);
  var previousPoint = null;
  placeholder.on('plothover', function (event, pos, item) {
	if(item) {
		if (previousPoint != item.seriesIndex) {
			previousPoint = item.seriesIndex;
			var tip = item.series['label'] + " : " + item.series['percent']+'%';
			$(this).data('tooltip').show().children(0).text(tip);
		}
		$(this).data('tooltip').css({top:pos.pageY + 10, left:pos.pageX + 10});
	} else {
		$(this).data('tooltip').hide();
		previousPoint = null;
	}
	
 });
		var d1 = [];
		for (var i = 0; i < Math.PI * 2; i += 0.5) {
			d1.push([i, Math.sin(i)]);
		}
		var d2 = [];
		for (var i = 0; i < Math.PI * 2; i += 0.5) {
			d2.push([i, Math.cos(i)]);
		}
		var d3 = [];
		for (var i = 0; i < Math.PI * 2; i += 0.2) {
			d3.push([i, Math.tan(i)]);
		}
		
		var sales_charts = $('#sales-charts').css({'width':'100%' , 'height':'220px'});
		$.plot("#sales-charts", [
			{ label: "数据1", data: d1 },
			{ label: "数据2", data: d2 },
			{ label: "数据3", data: d3 }
		], {
			hoverable: true,
			shadowSize: 0,
			series: {
				lines: { show: true },
				points: { show: true }
			},
			xaxis: {
				tickLength: 0
			},
			yaxis: {
				ticks: 10,
				min: -2,
				max: 2,
				tickDecimals: 3
			},
			grid: {
				backgroundColor: { colors: [ "#fff", "#fff" ] },
				borderWidth: 1,
				borderColor:'#555'
			}
		});
		$('[data-rel="tooltip"]').tooltip();
})
		</script>
	</body>
</html>
