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
	<link rel="stylesheet" type="text/css" href="<%=projectName%>/css/style.css"> 
	<script type="text/javascript" src="<%=projectName%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=projectName%>/easyui/jquery.easyui.min.js"></script>
	<style type="text/css">
	</style>
	<script type="text/javascript">
		$(function(){
			$('#tabs').tabs({
				onSelect: function(title){
					if(title == '手动添加'){
						$("#layout_addOrder").layout("resize",{  
						    width:"100%",  
						    height:"100%" 
						});
					}
				}
			}); 
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
			//iframe src 后赋值可以保证只有一次请求。
			$("#iframe_order_list").attr("src","<%=projectName%>/purchase/purchaseOrder/orderList");
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
			var tbody = $("#table_order_detail tbody");
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
                				+ "<td><input type='checkbox'></td>"
                				+ "<td>"+ (len*1+i*1+1) +"</td>"
                				+ "<td class='td_product_id' style='display:none;'>"+ product.id +"</td>"
                				+ "<td>"+ product.productCode +"</td>"
                				+ "<td>"+ product.barCode +"</td>"
                				+ "<td>"+ product.productName +"</td>"
                				+ "<td>"+ product.productUnit +"</td>"
                				+ "<td><input class='number_text unit_price' data-options='min:0,precision:2'/></td>"
                				+ "<td><input class='number_text quantity' data-options='min:0,precision:2'/></td>"
                				+ "<td><input class='number_text cost' data-options='min:0,precision:2' value='0'/></td>"
                				+ "</tr>";
						tbody.append(tr);
	     			});
	     			//先将回显数据全部清除  
	       			$("#tt").treegrid("clearChecked");
                }				
			});
		}
	</script>
  </head>
  <body>
	<div id="main_layout" class="easyui-layout" style="width:100%;height:100%;">
		<%@include file="../../common/nav.jsp"%>
		<div data-options="region:'north'" style="height:51px">
			<div id="win_product" class="easyui-window" title="商品列表" style="width:600px;height:400px" data-options="iconCls:'icon-save',modal:true">   
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
		<div data-options="region:'center'">
			<div id="tabs" class="easyui-tabs" style="width:100%;height:100%;">
			    <div title="订单列表" style="display:none;">   
			        <iframe id="iframe_order_list" name="iframe_order_list" style="width: 99%;height: 99%;"></iframe>    
			    </div>   
			    <div title="手动添加" style="display:none;">   
			    	<%@ include file="addOrder.jsp"%>
			    </div>   
			</div> 
		</div>
	</div>
</body>
</html>

