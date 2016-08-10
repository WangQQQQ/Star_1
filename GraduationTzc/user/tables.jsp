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
								<span style="color:#660033"><s:property value = "adminCode"/></span>
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
					

					<li class="active">

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
					
					
						<div id="fullbg"></div> 
						<div id="updiv" style="display: none;width:430px;">
							<a onclick="closeBg();" style="position:absolute;left:92%;">关闭</a>
							<img id="studentImg" />
							<img id="studentImg2" />
						</div>
						
						
					<div id="breadcrumbs">
						
						<ul class="breadcrumb">

							<li><i class="icon-home"></i> <a href="/GraduationTzc/user/index.jsp">Home</a><span class="divider"><i class="icon-angle-right"></i></span></li>

							<li class="active">学生信息</li>
						</ul><!--.breadcrumb-->


					</div><!--#breadcrumbs-->



					<div id="page-content" class="clearfix">
					
					
					
						<div class="page-header position-relative" style="display: relative;">
						
							  <h1>学生信息 <small><i class="icon-double-angle-right"></i></small></h1>
						      <a href="refreshClassMsg?classId=<s:property value="classId"/>" class="btn btn-app btn-yellow btn-mini" style="left:80%">
								<i class="icon-shopping-cart"></i>
								刷新
								</a>
							</div><!--/page-header-->
						
						
						<div class="row-fluid">
						
			<!-- PAGE CONTENT BEGINS HERE -->
			<div class="row-fluid">
				<div class="span12">
				
				
					<table id="table_bug_report" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>用户·</th>
								<th>学号</th>
								<th>解决题目</th>
								<th>总提交</th>
								<th>难度分</th>
								<th>班级</th>
								<th class="page-header position-relative">
									<button class="btn btn-small btn-primary">今日完成题量</button>
					                <button data-toggle="dropdown" class="btn btn-small btn-primary dropdown-toggle"><i><s:property value="todaySolvePro"/></i></button>
					                <ul class="dropdown-menu">
					               	  <li><a href="findStudent?todaySolvePro=0" name="todaySolvePro">所有学生信息</a></li>
					                  <li><a href="findStudent?todaySolvePro=1" name="todaySolvePro">1</a></li>
					                  <li><a href="findStudent?todaySolvePro=2" name="todaySolvePro">2</a></li>
					                  <li><a href="findStudent?todaySolvePro=3" name="todaySolvePro">3</a></li>
					                  <li class="divider"></li>
					                  <li><a href="findStudent?todaySolvePro=4" name="todaySolvePro">4</a></li>
					                  <li><a href="findStudent?todaySolvePro=5" name="todaySolvePro">5</a></li>
					                  <li><a href="findStudent?todaySolvePro=6" name="todaySolvePro">6</a></li>
					                  <li class="divider"></li>
					                  <li><a href="findStudent?todaySolvePro=7" name="todaySolvePro">7</a></li>
					                  <li><a href="findStudent?todaySolvePro=8" name="todaySolvePro">8</a></li>
					                  <li><a href="findStudent?todaySolvePro=9" name="todaySolvePro">9</a></li>
					                  <li class="divider"></li>
					                  <li><a href="findStudent?todaySolvePro=10" name="todaySolvePro">10</a></li>
					                  <li><a href="findStudent?todaySolvePro=15" name="todaySolvePro">15</a></li>
					                  <li><a href="findStudent?todaySolvePro=20" name="todaySolvePro">20</a></li>
					                  <li class="divider"></li>
					                  <li><a href="findStudent?todaySolvePro=25" name="todaySolvePro">25</a></li>
					                  <li><a href="findStudent?todaySolvePro=30" name="todaySolvePro">30</a></li>
					                </ul>
								</th>
								<th>操作</th>
							</tr>
						</thead>
												
						<tbody>
							<s:iterator value="students">
							<tr>
								<td><a href='#'><s:property value="username"/></a></td>
								<td><s:property value="studentNum"/></td>
								<td class='hidden-480'><span class='label label-warning'><s:property value="solveProblem"/></span></td>
								<td class='hidden-phone'><span class='label label-warning'><s:property value="submitCount"/></span></td>
								<td class='hidden-480'><span class='label label-warning'><s:property value="problemSource"/></span></td>
								<td><s:property value="className"/></td>
								<td><span class='label label-warning'><s:property value="todaySolveProblem"/></span></td>
								<td>
									<div class="inline position-relative">
										<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown"><i class="icon-cog icon-only">分析</i></button>
										<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">
											<li>
												<!-- <button data-toggle="dropdown" class="btn btn-mini btn-danger"><i class="icon-bolt"></i> 近日做题 </button>
												<div class="dropdown-menu">
								                  hehehehe
								                </div>-->
								                
											</li>
											
											<li><a onclick="showBg('updiv','<s:property value="username"/>','1');" class="tooltip-warning" data-rel="tooltip" title="Flag" data-placement="left"><span class="blue"><i class="icon-flag"></i>平均比较</span>
												
											</a></li>
											<li><a  onclick="showBg('updiv','<s:property value="username"/>','2');" class="tooltip-error" data-rel="tooltip" title="Delete" data-placement="left"><span class="red"><i class="icon-trash"></i>最值比较</span> </a></li>
										</ul>
									</div>
								</td>
								
							</tr>
							</s:iterator>
						</tbody>
					</table>
				</div><!--/span-->
				
				<div class="pagination" style="width:400px; margin:0 auto;">
	              <ul>
	              	<s:if test="page==1">
	              		<li class="previous"><a href="#">&larr;</a></li>
	              	</s:if>
	              	<s:else>
	                	<li class="previous"><a href="findStudent?page=<s:property value="page-1"/>&todaySolvePro=<s:property value="todaySolvePro"/>">&larr;</a></li>
	              	</s:else>
	                <s:iterator begin = "1" end = "totalPage" var = "p">
	                <s:if test="#p == page">
	                	<li class="active"><a href="findStudent?page=<s:property/>&todaySolvePro=<s:property value="todaySolvePro"/>"><s:property/></a></li>
	                </s:if>
	                <s:else>
	        	       <li><a href="findStudent?page=<s:property/>&todaySolvePro=<s:property value="todaySolvePro"/>"><s:property/></a></li>
	                </s:else>
	                </s:iterator>
	                <s:if test="page==totalPage">
	               		<li class="next"><a href="#"/>&rarr;</a></li>
	                </s:if>
	                <s:else>
		                <li class="next"><a href="findStudent?page=<s:property value="page+1"/>&todaySolvePro=<s:property value="todaySolvePro"/>">&rarr;</a></li>
	                </s:else>
	              </ul>
	            </div>
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
		
		<script type="text/javascript" src="/GraduationTzc/user/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="/GraduationTzc/user/js/jquery.dataTables.bootstrap.js"></script>
		<!-- ace scripts -->
		<script src="/GraduationTzc/user/js/ace-elements.min.js"></script>
		<script src="/GraduationTzc/user/js/ace.min.js"></script>
		<script src="/GraduationTzc/user/1.9.1/jquery.min.js"></script> 
		
		
		<!-- inline scripts related to this page -->
		
		<script type="text/javascript">
		$(function() {
		var oTable1 = $('#table_report').dataTable( {
		"aoColumns": [
	      { "bSortable": false },
	      null, null,null, null, null,
		  { "bSortable": false }
		] }) });
		
		//显示灰色JS遮罩层  
                function showBg(ct,username,flag){  
					if(flag==1){
                    var bH=$("main-content").height();  
                    var bW=$("main-content").width()+16;  
                    var objWH=getObjWh(ct);  
                    $("#fullbg").css({width:bW,height:bH,display:"block"});  
                    var tbT=objWH.split("|")[0]+"px";  
                    var tbL=objWH.split("|")[1]+"px";  
                    $("#"+ct).css({top:"247px",left:"43%",display:"block",position:"absolute",backgroundColor:"yellow"});  
                    var p2=document.getElementById("studentImg");
					var p3=document.getElementById("studentImg2");
					p2.src="/GraduationTzc/chart/findStudentCompareByUsername?username="+username;
					p3.src="/GraduationTzc/chart/findStudentCompareSourceByUsername?username="+username;
                    //$(window).scroll(function(){resetBg()});  
                   	//$(window).resize(function(){resetBg()});  
						
					}
					else if(flag==2){
					var bH=$("main-content").height();  
                    var bW=$("main-content").width()+16;  
                    var objWH=getObjWh(ct);  
                    $("#fullbg").css({width:bW,height:bH,display:"block"});  
                    var tbT=objWH.split("|")[0]+"px";  
                    var tbL=objWH.split("|")[1]+"px";  
                    $("#"+ct).css({top:"247px",left:"43%",display:"block",position:"absolute",backgroundColor:"yellow"});  
                    var p2=document.getElementById("studentImg");
					var p3=document.getElementById("studentImg2");
					p2.src="/GraduationTzc/chart/findStudentSolveProMByUsername?username="+username;
					p3.src="/GraduationTzc/chart/findStudentSourceMByUsername?username="+username;
					}
                }  
                function getObjWh(obj){  
                    var st=document.documentElement.scrollTop;//滚动条距顶部的距离  
                    var sl=document.documentElement.scrollLeft;//滚动条距左边的距离  
                    var ch=document.documentElement.clientHeight;//屏幕的高度  
                    var cw=document.documentElement.clientWidth;//屏幕的宽度  
                    var objH=$("#"+obj).height();//浮动对象的高度  
                    var objW=$("#"+obj).width();//浮动对象的宽度  
                    var objT=Number(st)+(Number(ch)-Number(objH))/2;  
                    var objL=Number(sl)+(Number(cw)-Number(objW))/2;  
                    return objT+"|"+objL;  
                }  
                function resetBg(){  
                    var fullbg=$("#fullbg").css("display");  
                    if(fullbg=="block"){  
                        var bH2=$("body").height();  
                        var bW2=$("body").width()+16;  
                        $("#fullbg").css({width:bW2,height:bH2});  
                        var objV=getObjWh("dialog");  
                        var tbT=objV.split("|")[0]+"px";  
                        var tbL=objV.split("|")[1]+"px";  
                        $("#dialog").css({top:tbT,left:tbL});  
                    }  
                }  
     
                //关闭灰色JS遮罩层和操作窗口  
                function closeBg(){  
                    $("#fullbg").css("display","none");  
                    $("#dialog").css("display","none");  
                     var p=document.getElementById("updiv");
                     p.style.display="none";
                }  
                
		
		</script>
	</body>
</html>
