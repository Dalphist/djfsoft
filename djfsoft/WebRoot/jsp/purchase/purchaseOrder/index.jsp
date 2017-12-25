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
	<style type="text/css"></style>
	<script type="text/javascript">
		$(function(){
			$("#tt").treegrid({    
			    url:"<%=projectName%>/productCategory/getCategoryToProductTree",    
			    idField:'id',    
			    treeField:'text',   
			    checkbox: true,
				rownumbers: true, 
			    columns:[[   
			    	{field:'id',title:'id',width:300,hidden:true}, 
            		{field:'text',title:'名称',width:300,iconCls:"icon-sum"},         
			    ]]    
			});
		})
	</script>
  </head>
  <body>
	<div id="main_layout" class="easyui-layout" style="width:100%;height:100%;">
		<%@include file="../../common/nav.jsp"%>
		<div data-options="region:'north'" style="height:51px">
		</div>
		<div data-options="region:'center'">
			<iframe id="iframe_order_list" src="<%=projectName%>/purchase/purchaseOrder/orderList" style="width: 99%;height: 99%;"></iframe>
		</div>
		<div data-options="region:'south'" style="height: 40%;">
			<iframe id="iframe_order_detail" src="<%=projectName%>/purchase/purchaseOrder/orderDetail" style="width: 99%;height: 99%;"></iframe>
		</div>
		
		<div id="win_product" class="easyui-window" title="My Window" style="width:600px;height:400px"   
	        data-options="iconCls:'icon-save',modal:true">   
		    <table id="tt" style="width:600px;height:400px"></table> 
		</div>  
	</div>
</body>
</html>

