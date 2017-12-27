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
			$("#win_product").window("close");
			$("#tt").treegrid({    
			    url:"<%=projectName%>/productCategory/getCategoryToProductTree",    
			    idField:'id',    
			    treeField:'text',   
			    checkbox: true,
				rownumbers: true, 
			    columns:[[   
			    	{field:'id',title:'id',hidden:true,halign:"center"}, 
            		{field:'text',title:'名称',width:222,halign:"center"},         
            		{field:'productCode',title:'编号',width:120,halign:"center",align:"right"},         
            		{field:'barCode',title:'条形码',width:100,halign:"center",align:"right"},         
            		{field:'stock',title:'库存',width:80,halign:"center",align:"right"},         
			    ]]    
			});
		})
		//获取选中商品
		function getSelectProduct(){
			var nodes = $("#tt").treegrid("getCheckedNodes");
			var productIds = [];
			$.each(nodes,function(i,node){
				var node_id = node.id;
				if(node_id.substr(0,1) == "p"){
					productIds.push(node_id.substr(1));
				}
			});
			return productIds;
		}
		//导入选中的商品
		function importProduct(){
			$("#win_product").window("close");
			var tbody = $(window.frames["iframe_order_detail"].document).find("#table_order_detail tbody");
			var len = tbody.find("tr").length;
			var productIds = getSelectProduct();
			$.ajax({
				url:"<%=projectName%>/manage/product/getProductInfoList",
				type:"get",
                data:{"productIds":JSON.stringify(productIds)},
                success:function(result){
                	var productList = result.dataList;
                	$.each(productList,function(i,product){
                		var tr = "<tr>"
                				+ "<td>"+ (len*1+i*1+1) +"</td>"
                				+ "<td>"+ product.productCode +"</td>"
                				+ "<td>"+ product.barCode +"</td>"
                				+ "<td>"+ product.productName +"</td>"
                				+ "<td>"+ product.productUnit +"</td>"
                				+ "<td><input class='easyui-numberbox unit_price' data-options='min:0,precision:2'/></td>"
                				+ "<td><input class='easyui-numberbox quantity' data-options='min:0,precision:2'/></td>"
                				+ "<td><input class='easyui-numberbox cost' data-options='min:0,precision:2' value='0'/></td>"
                				+ "</tr>";
                		
						tbody.append(tr);
	     			});
                }				
			});
		}
		function openWinProduct(){
			$("#win_product").window("open");
		}
	</script>
  </head>
  <body>
	<div id="main_layout" class="easyui-layout" style="width:100%;height:100%;">
		<%@include file="../../common/nav.jsp"%>
		<div data-options="region:'north'" style="height:51px">
		</div>
		<div data-options="region:'west',title:'West',split:true" style="width:100px;">
		</div>
		<div data-options="region:'center'">
			<div class="easyui-layout" style="width:100%;height:100%;">
				<div data-options="region:'center'">
					<iframe id="iframe_order_list" src="<%=projectName%>/purchase/purchaseOrder/orderList" style="width: 99%;height: 99%;"></iframe>
				</div>
				<div data-options="region:'south'" style="height: 40%;">
					<iframe id="iframe_order_detail" name="iframe_order_detail" src="<%=projectName%>/purchase/purchaseOrder/orderDetail" style="width: 99%;height: 99%;"></iframe>
				</div>
			</div>
		</div>
		
		
		<div id="win_product" class="easyui-window" title="商品列表" style="width:600px;height:400px"   
	        data-options="iconCls:'icon-save',modal:true">   
		    <table id="tt" style="width:581px;height:333px"></table>  
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
</body>
</html>

