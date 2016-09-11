<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- bootstrap -->
<link href="css/bootstrap/bootstrapForMap.css" rel="stylesheet" />
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

<title>调用百度Map页</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.9.1.min.js"></script>
<!--引用百度地图API-->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=7FgowXlniDcLkpK5qA1IyLwi"></script>

</head>
<body  onload="initMap()">
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
            
            <li class="active">
                <a class="dropdown-toggle" href="#">
                  <div class="pointer">
                    <div class="arrow"></div>
                    <div class="arrow_border"></div>
                   </div>
                    <i class="icon-th-large"></i>
                    <span>城建信息管理</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="active submenu">
                    <li><a href="goToProject" >城建信息导入</a></li>
                    <li><a href="projectList">城建信息显示/修改</a></li>
                    <li><a href="baiduMap" class="active">调用百度Map</a></li>
                    
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
             <!--百度地图容器-->
   			 <div style="width:1100px;height:550px;border:#ccc solid 1px;font-size:15px" id="map"></div>
           </div>
        </div>
    </div>
</body>
 <script type="text/javascript">

    //创建和初始化地图函数：
    function initMap(){
      createMap();//创建地图
      setMapEvent();//设置地图事件
      addMapControl();//向地图添加控件
      addMapOverlay();//向地图添加覆盖物
    }
    function createMap(){ 
      map = new BMap.Map("map"); 
      map.centerAndZoom(new BMap.Point(120.161693,30.280059),12);
	  //添加地址
	 $(document).ready(function(){ 
		 $.ajax({
  	    type : "POST", //提交方式  
  	    url : "loadMap",//路径  
  	    dataType : "json",
  	    success : function(data) {//返回数据根据结果进行相应的处理
  	    	 console.log(data.list);
  			 $.each(data.list,function(i,item){
                var marker = new BMap.Marker(new BMap.Point(item[0],item[1]));  // 创建标注
				var content ="项目名称:"+ item[2]+"\<br/>"+"企业名称："+item[3]+"\<br/>"+"总投资额(万元):"+item[4]+"\<br/>"+"工期:"+item[5]+"\<br/>"
				+"工程总面积:"+item[6]+"\<br/>"+"建设时间:"+item[7]+"\<br/>";
				map.addOverlay(marker);               // 将标注添加到地图中
				addClickHandler(content,marker); 
            }); 
  		}
  	   });
	 });
	var opts = {
				width : 250,     // 信息窗口宽度
				height: 140,     // 信息窗口高度
				title : "城建信息" , // 信息窗口标题
				enableMessage:true//设置允许信息窗发送短息
			   };
	function addClickHandler(content,marker){
		marker.addEventListener("click",function(e){
			openInfo(content,e)}
		);
	}
	function openInfo(content,e){
		var p = e.target;
		var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
		var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
		map.openInfoWindow(infoWindow,point); //开启信息窗口
	}
	
    }
    function setMapEvent(){
      map.enableScrollWheelZoom();
      map.enableKeyboard();
      map.enableDragging();
      map.enableDoubleClickZoom()
    }
    function addClickHandler(target,window){
      target.addEventListener("click",function(){
        target.openInfoWindow(window);
      });
    }
    function addMapOverlay(){
    }
    //向地图添加控件
    function addMapControl(){
      var scaleControl = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
      scaleControl.setUnit(BMAP_UNIT_IMPERIAL);
      map.addControl(scaleControl);
      var navControl = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
      map.addControl(navControl);
      var overviewControl = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:true});
      map.addControl(overviewControl);      
   	  
	  var mapType1 = new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP]});
	  map.addControl(mapType1);   //2D图，卫星图

    }
    var map;
    var test;
    var data_info;
      initMap();
  </script>
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
</html>