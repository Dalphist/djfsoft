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
			$('#tabs').tabs({
				onSelect: function(title){
					if(title == '销售单导入'){
						$("#layout_addOrder").layout("resize",{  
						    width:"100%",  
						    height:"100%" 
						});
					}
				}
			}); 
			$("#win_product").window("close");
			//iframe src 后赋值可以保证只有一次请求。
			$("#iframe_order_list").attr("src","<%=projectName%>/sales/salesStockOutOrder/orderList");
		})
	</script>
  </head>
<body>
	<div id="main_layout" class="easyui-layout" style="width:100%;height:100%;">
		<%@include file="../../common/nav.jsp"%>
		<div data-options="region:'north'" style="height:51px">
			<div id="win_product" class="easyui-window" title="商品列表" style="width:600px;height:400px" data-options="iconCls:'icon-save',modal:true">   
			    <div style="height: 10px;">
			    	<div style="float: right;padding-right: 10px;padding-top: 5px;" onclick="$('#win_product').window('close');">
						<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>  
					</div>
			    	<div style="float: right;padding-right: 10px;padding-top: 5px;" onclick="importProduct();">
				    	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定</a>  
			    	</div>
			    </div>
			</div> 
		</div>
		<div data-options="region:'center'">
			<div id="tabs" class="easyui-tabs" style="width:100%;height:100%;">
			    <div title="出库单列表" style="display:none;">   
			        <iframe id="iframe_order_list" name="iframe_order_list" style="width: 99%;height: 99%;"></iframe>    
			    </div>   
			    <div title="销售单导入" style="display:none;">   
			        <%@ include file="addOrder.jsp"%> 
			    </div>   
			</div> 
		</div>
	</div>



</body>
</html>

