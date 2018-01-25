<%@ page language="java" import="java.util.*,djfsoft.pojo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>主页</title>
    <%String projectName  = request.getContextPath();%>
    <link rel="stylesheet" type="text/css" href="<%=projectName%>/easyui/themes/default/easyui.css">   
	<link rel="stylesheet" type="text/css" href="<%=projectName%>/easyui/themes/icon.css">   
	<script type="text/javascript" src="<%=projectName%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=projectName%>/easyui/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=projectName%>/css/style.css"> 
	<style type="text/css">
	</style>
	<script type="text/javascript">
		$(function(){
			$("#iframe_stock_list").attr("src","<%=projectName%>/stock/stockInfoList");
		})
	</script>
  </head>
<body>
	<div id="main_layout" class="easyui-layout" style="width:100%;height:100%;">
		<%@include file="../../common/nav.jsp"%>
		<div data-options="region:'north'" style="height:51px"></div>
		<div data-options="region:'center'">
			<div id="tabs" class="easyui-tabs" style="width:100%;height:100%;">
			    <div title="库存信息" style="display:none;">   
			        <iframe id="iframe_stock_list" name="iframe_stock_list" style="width: 99%;height: 99%;"></iframe>    
			    </div>   
			    <div title="aaa" style="display:none;">   
			    </div>   
			</div> 
		</div>
	</div>



</body>
</html>

