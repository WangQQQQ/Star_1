<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
<!-- bootstrap -->
<link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
<link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
<link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

<!-- libraries -->
<link href="css/lib/jquery-ui-1.10.2.custom.css" rel="stylesheet" type="text/css" />
<link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />

<!-- global styles -->
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<link rel="stylesheet" type="text/css" href="css/elements.css" />
<link rel="stylesheet" type="text/css" href="css/icons.css" />

<!-- this page specific styles -->
<link rel="stylesheet" href="css/compiled/index.css" type="text/css" media="screen" />    

<title>分析结果</title>
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
                    <li><a href="exampleList">企业信息查询/修改</a></li>
                    <li><a href="addexample">企业信息添加</a></li>
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
           <div id="pad-wrapper" class="gallery">
                 <div class="row-fluid header">
                    <div class="center">
						<h3>城建数据分析</h3>
                    </div>
                </div>
                <!-- Users table -->
                <div class="row-fluid table">
                    <table class="table table-hover">
                        <thead>
                             <tr>
                                    <th class="span2">
                                                                          项目名称
                                    </th>
                                    <th class="span3">
                                    <span class="line"></span>
                                                                         投资总额
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                                                                 工期
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                                                                工程总面积
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                                                              报建时间
                                    </th>
                                    <th class="span3" >
                                        <span class="line"></span>
                                                                               最大跨度
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                                                               项目地址
                                    </th>
                                     <th class="span3">
                                        <span class="line"></span>
                                                                               地理区域
                                    </th>
                                </tr>
                        </thead>
                         <tbody>
	             			<tr class="first">
                  		    <td>${example.projectName }</td>
                   		    <td>${example.totalInvestment }</td>
                   		    <td>${example.projectPeriod}</td>
                   		    <td>${example.buildTotalArea }</td>
                    	    <td>${constructTime }</td>
                   		    <td>${example.maxSpan }</td>
                      		<td>${example.geographyAddress }</td>
                      		<td>${example.geographyRegion }</td>
               			   </tr>
                	  </tbody>
                	  <thead>
                             <tr>
                                    <th class="span2">
                                                                         相应权重
                                    </th>
                                    <th class="span3">
                                    <span class="line"></span>
                                                                         
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                                                                
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                                                                
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                                                           
                                    </th>
                                    <th class="span3" >
                                        <span class="line"></span>
                                                                              
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                                                            
                                    </th>
                                     <th class="span3">
                                        <span class="line"></span>
                                                                              
                                    </th>
                                </tr>
                        </thead>
                         <tbody>
	             			<tr class="first">
                  		    <td>${example.percent1 }%</td>
        					<td>${example.percent2 }%</td>
        					<td>${example.percent3}%</td>
        					<td>${example.percent4 }%</td>
       						<td>${example.percent5 }%</td>
        					<td>${example.percent6 }%</td>
        					<td>${example.percent7 }%</td>
        					<td>${example.percent8 }%</td>
               			   </tr>
                	  </tbody>
                </table>
               </div>
                <!-- end users table -->
                <label style="color:purple;font-size:14px" >相近的案例分析:</label>
                 <!-- Users table -->
                <div class="row-fluid table">
                    <table class="table table-hover">
                        <thead>
                             <tr>
                                    <th class="span2">
                                                                          项目名称
                                    </th>
                                    <th class="span3">
                                    <span class="line"></span>
                                                                         投资总额
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                                                                 工期
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                                                                工程总面积
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                                                              报建时间
                                    </th>
                                    <th class="span3" >
                                        <span class="line"></span>
                                                                               最大跨度
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                                                               项目地址
                                    </th>
                                     <th class="span3">
                                        <span class="line"></span>
                                                                               地理区域
                                    </th>
                                     <th class="span3">
                                        <span class="line"></span>
                                                                              相似度CBR(百分比%)
                                    </th>
                                </tr>
                        </thead>
                         <tbody>
                        	 <c:forEach items="${listNeed }" var="project">
	          				 <tr class="first">
                      			<td>${project.projectName}</td>
                      			<td>${project.totalInvestment}</td>
                     			<td>${project.projectPeriod}</td>
                      			<td>${project.buildTotalArea}</td>
                      			<td>${project.constructTime}</td>
                      			<td>${project.maxSpan}</td>
                      			<td>${project.geographyAddress}</td>
                      			<td>${project.geographyRegion}</td>
                      			<td>${project.value}</td>
                  			 </tr>
                   			 </c:forEach>
                	     </tbody>
                </table>
               </div>
                <!-- end users table -->
                
                <img src="${chartURL}"><br><br>
                 <label style="color:purple;font-size:14px" >结论如下:</label><br>
                 <label>通过数据挖掘与分析，发现与 ${example.projectName } 相似度最高的是由 <span class="label label-info" style="font-size:14px">${suggestion1}</span>建设的。</label>
                 <label>而相似度最高的前几个中，大多是由<span class="label label-success" style="font-size:14px">${suggestion2}</span>建设的。</label>
                 <label>在选择建设公司的时候可以优先考虑以上单位</label>
           </div>
        </div>
    </div>


	<!-- scripts -->
    <script src="js/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery-ui-1.10.2.custom.min.js"></script>
    <!-- knob -->
    <script src="js/jquery.knob.js"></script>
    <!-- flot charts -->
    <script src="js/jquery.flot.js"></script>
    <script src="js/jquery.flot.stack.js"></script>
    <script src="js/jquery.flot.resize.js"></script>
    <script src="js/theme.js"></script>

    <script type="text/javascript">
        $(function () {

            // jQuery Knobs
            $(".knob").knob();



            // jQuery UI Sliders
            $(".slider-sample1").slider({
                value: 100,
                min: 1,
                max: 500
            });
            $(".slider-sample2").slider({
                range: "min",
                value: 130,
                min: 1,
                max: 500
            });
            $(".slider-sample3").slider({
                range: true,
                min: 0,
                max: 500,
                values: [ 40, 170 ],
            });

            

            // jQuery Flot Chart
            var visits = [[1, 50], [2, 40], [3, 45], [4, 23],[5, 55],[6, 65],[7, 61],[8, 70],[9, 65],[10, 75],[11, 57],[12, 59]];
            var visitors = [[1, 25], [2, 50], [3, 23], [4, 48],[5, 38],[6, 40],[7, 47],[8, 55],[9, 43],[10,50],[11,47],[12, 39]];

            var plot = $.plot($("#statsChart"),
                [ { data: visits, label: "Signups"},
                 { data: visitors, label: "Visits" }], {
                    series: {
                        lines: { show: true,
                                lineWidth: 1,
                                fill: true, 
                                fillColor: { colors: [ { opacity: 0.1 }, { opacity: 0.13 } ] }
                             },
                        points: { show: true, 
                                 lineWidth: 2,
                                 radius: 3
                             },
                        shadowSize: 0,
                        stack: true
                    },
                    grid: { hoverable: true, 
                           clickable: true, 
                           tickColor: "#f9f9f9",
                           borderWidth: 0
                        },
                    legend: {
                            // show: false
                            labelBoxBorderColor: "#fff"
                        },  
                    colors: ["#a7b5c5", "#30a0eb"],
                    xaxis: {
                        ticks: [[1, "JAN"], [2, "FEB"], [3, "MAR"], [4,"APR"], [5,"MAY"], [6,"JUN"], 
                               [7,"JUL"], [8,"AUG"], [9,"SEP"], [10,"OCT"], [11,"NOV"], [12,"DEC"]],
                        font: {
                            size: 12,
                            family: "Open Sans, Arial",
                            variant: "small-caps",
                            color: "#697695"
                        }
                    },
                    yaxis: {
                        ticks:3, 
                        tickDecimals: 0,
                        font: {size:12, color: "#9da3a9"}
                    }
                 });

            function showTooltip(x, y, contents) {
                $('<div id="tooltip">' + contents + '</div>').css( {
                    position: 'absolute',
                    display: 'none',
                    top: y - 30,
                    left: x - 50,
                    color: "#fff",
                    padding: '2px 5px',
                    'border-radius': '6px',
                    'background-color': '#000',
                    opacity: 0.80
                }).appendTo("body").fadeIn(200);
            }

            var previousPoint = null;
            $("#statsChart").bind("plothover", function (event, pos, item) {
                if (item) {
                    if (previousPoint != item.dataIndex) {
                        previousPoint = item.dataIndex;

                        $("#tooltip").remove();
                        var x = item.datapoint[0].toFixed(0),
                            y = item.datapoint[1].toFixed(0);

                        var month = item.series.xaxis.ticks[item.dataIndex].label;

                        showTooltip(item.pageX, item.pageY,
                                    item.series.label + " of " + month + ": " + y);
                    }
                }
                else {
                    $("#tooltip").remove();
                    previousPoint = null;
                }
            });
        });
    </script>

</body>
</html>