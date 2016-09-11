<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/user-list.css" type="text/css" media="screen" />

    <!-- open sans font -->
<!--     <link href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />
 -->
<title>用户管理页</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$(".delete").click(function(){
		var label = $(this).next(":hidden").val();
		var flag = confirm("确定要删除" + label + "的信息吗?");
		if(flag){
			var url = $(this).attr("href");
			
			$("#_form").attr("action", url);
			$("#_method").val("DELETE");
			$("#_form").submit();
		}
		
		return false;
	});
	$(".reset").click(function(){
		/* var label = $(this).next(":hidden").val(); */
		var flag = confirm("确定要重置密码为：123456吗?");
		if(flag){
			var url = $(this).attr("href");
			var args={"time":new Date()};
			$.post(url,args,function(data){
				if(data=="1"){
					alert("密码重置成功！");
				}
			}
			)
			//$("#_form").attr("action", url);
			/* $("#_method").val("RESET"); */
			//$("#_form").submit();
		}
		
		return false;
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
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>用户管理</h3>
                    <div class="span10 pull-right">
                    <form action="searchUser" method="post">
                        <input type="text" class="span5 search" placeholder="输入要查询的用户名" name="searchName"/>
                        
                        <!-- custom popup filter -->
                        <!-- styles are located in css/elements.css -->
                        <!-- script that enables this dropdown is located in js/theme.js -->
                        
                        <div class="ui-dropdown">
                        <!-- <input type="submit"> -->
                            <div class="head" data-toggle="tooltip" title="Click me!">
                             <input type="submit" value="点击搜索" class="btn-glow primary login"/>
                            </div>  
                       <!--    </input> -->
                        </div>
                        
                   </form>
                        
                        <a href="addUser" class="btn-flat success pull-right">
                            <span>&#43;</span>
                            添加管理员
                        </a>
                    </div>
                </div>

                <!-- Users table -->
                <div class="row-fluid table">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <!-- style="font-size: 12px" -->
                                    <th class="span2" >
                                                                           用户编号
                                    </th>
                                    <th class="span3">
                                                                         用户名称
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                                                                 用户级别
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                                                                 状态
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                                                              修改
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                                                               重置密码
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                                                                删除
                                    </th>
                                </tr>
                        </thead>
                        <tbody>
                               <!-- row -->
                <c:if test="${page == null || page.numberOfElements == 0 }">
		                没有任何记录. 
	            </c:if>
	            <c:if test="${page != null && page.numberOfElements > 0 }">
	             <c:forEach items="${page.content }" var="user">
	              <tr class="first">
                      <td>${user.userId }</td>
                      <td>${user.userName }</td>
                      <td>${user.userLevel }</td>
                      <td>${user.uStatus }</td>
                      <td>
                           <a href="${pageContext.request.contextPath }/modifyUser/${user.userId}">
                           <span class="label label-info">修改</span>
                          </a>
                       </td>
                      <td>
                           <a href="${pageContext.request.contextPath }/resetUser/${user.userId}" class="reset">
                           <span class="label label-success">重置密码</span>
                           <input type="hidden" value="${user.userName}"/>
                          </a>
                       </td>
                       <td>
                        	<a href=" ${pageContext.request.contextPath }/deleteUser/${user.userId}" class="delete"><span class="label">删除</span></a>
							<input type="hidden" value="${user.userName}"/>
                       </td>
                  </tr>
                 </c:forEach>
            <tr>
				<td colspan="8">
					共 ${page.totalElements } 条记录 |
					共 ${page.totalPages } 页 |
					当前第 ${page.number + 1 } 页
					<a href="?pageNo=${page.number + 1 - 1 }">上一页</a>
					<c:if test="${page.totalPages>= page.number + 1 + 1}">
					     <a href="?pageNo=${page.number + 1 + 1 }">下一页</a>
					</c:if>
					<c:if test="${page.totalPages< page.number + 1 + 1}">
					     
					</c:if>
				</td>
			</tr>
			
	            </c:if>
                              
                        </tbody>
                    </table>
                </div>
                <!-- end users table -->
            </div>
        </div>
    </div>

   <form action="" method="POST" id="_form">
		<input type="hidden" id="_method" name="_method"/>
	</form>
	<!-- end main container -->


	<!-- scripts -->
    <script src="js/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>

</body>
</html>