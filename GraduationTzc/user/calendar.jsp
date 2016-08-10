<!DOCTYPE html>
<%@page pageEncoding = "UTF-8" %>
<%@taglib uri = "/struts-tags" prefix="s"%>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>Full Calendar - Ace Admin</title>
		<meta name="description" content="with draggable and editable events" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- basic styles -->
		<link href="/GraduationTzc/user/css/bootstrap.min.css" rel="stylesheet" />
		<link href="/GraduationTzc/user/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="/GraduationTzc/user/css/font-awesome.min.css" />
		<!--[if IE 7]>
		  <link rel="stylesheet" href="/GraduationTzc/user/css/font-awesome-ie7.min.css" />
		<![endif]-->
		<!-- page specific plugin styles -->
		
		<link rel="stylesheet" href="/GraduationTzc/user/css/fullcalendar.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="/GraduationTzc/user/css/ace.min.css" />
		<link rel="stylesheet" href="/GraduationTzc/user/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="/GraduationTzc/user/css/ace-skins.min.css" />
		<!--[if lt IE 9]>
		  <link rel="stylesheet" href="/GraduationTzc/user/css/ace-ie.min.css" />
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

					<li class="active">

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

							<li class="active">日历</li>
						</ul><!--.breadcrumb-->



					</div><!--#breadcrumbs-->



					<div id="page-content" class="clearfix">
						
						<div class="page-header position-relative">
							<h1>日历 <small><i class="icon-double-angle-right"></i>备忘录</small></h1>
						</div><!--/page-header-->
						
						<div class="row-fluid">
<!-- PAGE CONTENT BEGINS HERE -->
<div class="row-fluid">
	<div class="span9">
		<div class="space"></div>
		<div id='calendar'></div>
	</div>
	
	<div class="span3">
		<div class="widget-box transparent">
			<div class="widget-header">
				<h4>备忘录样式</h4>
			</div>
			<div class="widget-main">
				<div id='external-events'>
					<div class='external-event label-grey' data-class="label-grey"><i class="icon-move"></i> Event 1</div>
					<div class='external-event label-success' data-class="label-success"><i class="icon-move"></i> Event 2</div>
					<div class='external-event label-important' data-class="label-important"><i class="icon-move"></i>  Event 3</div>
					<div class='external-event label-purple' data-class="label-purple"><i class="icon-move"></i>  Event 4</div>
					<div class='external-event label-yellow' data-class="label-yellow"><i class="icon-move"></i>  Event 5</div>
					<div class='external-event label-pink' data-class="label-pink"><i class="icon-move"></i>  Event 6</div>
					<div class='external-event label-info' data-class="label-info"><i class="icon-move"></i> Event 7</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- PAGE CONTENT ENDS HERE -->
						 </div><!--/row-->
	
					</div><!--/#page-content-->
					  
					<div id="ace-settings-container">
						<div id="ace-settings-box">
						</div>
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
		
		<script type="text/javascript" src="/GraduationTzc/user/js/jquery-ui-1.10.2.custom.min.js"></script>
		<script type="text/javascript" src="/GraduationTzc/user/js/jquery.ui.touch-punch.min.js"></script>
		<script type="text/javascript" src="/GraduationTzc/user/js/fullcalendar.min.js"></script>
		<script type="text/javascript" src="/GraduationTzc/user/js/bootbox.min.js"></script>
		<!-- ace scripts -->
		<script src="/GraduationTzc/user/js/ace-elements.min.js"></script>
		<script src="/GraduationTzc/user/js/ace.min.js"></script>
		<!-- inline scripts related to this page -->
		
		<script type="text/javascript">
		
$(function() {
	/* initialize the external events
		-----------------------------------------------------------------*/
	
		$('#external-events div.external-event').each(function() {
		
			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			// it doesn't need to have a start or end
			var eventObject = {
				title: $.trim($(this).text()) // use the element's text as the event title
			};
			
			// store the Event Object in the DOM element so we can get to it later
			$(this).data('eventObject', eventObject);
			
			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex: 999,
				revert: true,      // will cause the event to go back to its
				revertDuration: 0  //  original position after the drag
			});
			
		});
		
		
		/* initialize the calendar
		-----------------------------------------------------------------*/
		
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		
		var calendar = $('#calendar').fullCalendar({
			 buttonText: {
				prev: '<i class="icon-chevron-left"></i>',
				next: '<i class="icon-chevron-right"></i>'
			},
		
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			events: [
			{
				title: 'All Day Event',
				start: new Date(y, m, 1),
				className: 'label-important'
			},
			{
				title: 'Long Event',
				start: new Date(y, m, d-5),
				end: new Date(y, m, d-2),
				className: 'label-success'
			},
			{
				title: 'Some Event',
				start: new Date(y, m, d-3, 16, 0),
				allDay: false
			}]
			,
			editable: true,
			droppable: true, // this allows things to be dropped onto the calendar !!!
			drop: function(date, allDay) { // this function is called when something is dropped
			
				// retrieve the dropped element's stored Event Object
				var originalEventObject = $(this).data('eventObject');
				var $extraEventClass = $(this).attr('data-class');
				
				
				// we need to copy it, so that multiple events don't have a reference to the same object
				var copiedEventObject = $.extend({}, originalEventObject);
				
				// assign it the date that was reported
				copiedEventObject.start = date;
				copiedEventObject.allDay = allDay;
				if($extraEventClass) copiedEventObject['className'] = [$extraEventClass];
				
				// render the event on the calendar
				// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
				
				// is the "remove after drop" checkbox checked?
				if ($('#drop-remove').is(':checked')) {
					// if so, remove the element from the "Draggable Events" list
					$(this).remove();
				}
				
			}
			,
			selectable: true,
			selectHelper: true,
			select: function(start, end, allDay) {
				
				bootbox.prompt("New Event Title:", function(title) {
					if (title !== null) {
						calendar.fullCalendar('renderEvent',
							{
								title: title,
								start: start,
								end: end,
								allDay: allDay
							},
							true // make the event "stick"
						);
					}
				});
				
				calendar.fullCalendar('unselect');
				
			}
			,
			eventClick: function(calEvent, jsEvent, view) {
				
				var form = $("<form class='form-inline'><label>Change event name &nbsp;</label></form>");
				form.append("<input autocomplete=off type=text value='" + calEvent.title + "' /> ");
				form.append("<button type='submit' class='btn btn-small btn-success'><i class='icon-ok'></i> Save</button>");
				
				var div = bootbox.dialog(form,
					[
					{
						"label" : "<i class='icon-trash'></i> Delete Event",
						"class" : "btn-small btn-danger",
						"callback": function() {
							calendar.fullCalendar('removeEvents' , function(ev){
								return (ev._id == calEvent._id);
							})
						}
					}
					,
					{
						"label" : "<i class='icon-remove'></i> Close",
						"class" : "btn-small"
					}
					]
					,
					{
						// prompts need a few extra options
						"onEscape": function(){div.modal("hide");}
					}
				);
				
				form.on('submit', function(){
					calEvent.title = form.find("input[type=text]").val();
					calendar.fullCalendar('updateEvent', calEvent);
					div.modal("hide");
					return false;
				});
				
			
				//console.log(calEvent.id);
				//console.log(jsEvent);
				//console.log(view);
				// change the border color just for fun
				//$(this).css('border-color', 'red');
			}
			
		});
})
		</script>
	</body>
</html>
