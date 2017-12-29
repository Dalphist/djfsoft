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
    <%
		String projectName  = request.getContextPath();
	 %>
    <link rel="stylesheet" type="text/css" href="<%=projectName%>/easyui/themes/default/easyui.css">   
	<link rel="stylesheet" type="text/css" href="<%=projectName%>/easyui/themes/icon.css">   
	<script type="text/javascript" src="<%=projectName%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=projectName%>/easyui/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=projectName%>/css/style.css"> 
	
	<style type="text/css">
	.select_tr{
		background-color: #c4e8f5;
	}
	.number_text{
		font-size: 12px;
	    border: 1px solid #95B8E7;
	    padding: 4px;
	    border-radius: 5px 5px 5px 5px;
	    padding-top: 0px;
	    padding-bottom: 0px;
	    height: 23px;
	    line-height: 23px;
	    width: 127px;
	    text-align: right;
	}
	</style>
	<script type="text/javascript">
		$(function(){
			//单击行变色及获取订单详情
			$("#table_order_detail tbody").on("click","tr",function(){
			   $(this).addClass("select_tr").siblings().removeClass("select_tr"); 
			   var order_id = $(this).find(".td_order_id").text().trim();
			   $("#input_order_id").val(order_id);
			}); 
			//添加
		    $("#input_order_add").click(function(){
		    	$("#tab_order input").val("");
		    	$(".order_category_text").val(order_category_text);
		    	$("#tab_order").tabs("select",0);
		    	$("#input_order_id").val("");
		    });
		    //全选/取消全选
		    $("#checkAll").on("click",function(event){
		    	if($("#checkAll").prop("checked")){
		    		$("#table_order_detail tbody :checkbox").prop("checked", true);
		    	}else{
		    		$("#table_order_detail tbody :checkbox").prop("checked", false);
		    	}
		    });
		    //*************数值修改时联动修改**************
		    $("#table_order_detail tbody").on("blur",".quantity",function(){
		    	calByQuantity($(this).parents("tr"));
		    	calTableCost();
			}); 
		    $("#table_order_detail tbody").on("blur",".unit_price",function(){
		    	calByUnitPrice($(this).parents("tr"));
		    	calTableCost();
			}); 
		    $("#table_order_detail tbody").on("blur",".cost",function(){
		    	calByCost($(this).parents("tr"));
		    	calTableCost();
			}); 
			//*********************************************
		});
		//单价变动的计算
		function calByUnitPrice(tr){
			var unit_price = tr.find(".unit_price").val().trim();
	    	var quantity = tr.find(".quantity").val().trim();
	    	var cost = tr.find(".cost").val().trim();
	    	if(unit_price != "" && quantity != ""){
	    		var cost_new = unit_price*quantity;
		    	tr.find(".cost").val(cost_new.toFixed(2));
		    	return;
	    	}
	    	if((quantity == "" || quantity == 0) && (cost != "" && cost != 0)){
	    		var quantity_new = cost/unit_price;
		    	tr.find(".quantity").val(quantity_new.toFixed(2));
		    	return;
	    	}
		}
		//数量变动的计算
		function calByQuantity(tr){
			var unit_price = tr.find(".unit_price").val().trim();
	    	var quantity = tr.find(".quantity").val().trim();
	    	var cost = tr.find(".cost").val().trim();
	    	if(unit_price != "" && quantity != ""){
	    		var cost_new = unit_price*quantity;
		    	tr.find(".cost").val(cost_new.toFixed(2));
		    	return;
	    	}
	    	if((unit_price == "" || unit_price == 0) && (cost != "" && cost != 0)){
	    		var unit_price_new = cost/quantity;
		    	tr.find(".unit_price").val(unit_price_new.toFixed(2));
		    	return;
	    	}
		}
		//总价变动的计算
		function calByCost(tr){
			var unit_price = tr.find(".unit_price").val().trim();
	    	var quantity = tr.find(".quantity").val().trim();
	    	var cost = tr.find(".cost").val().trim();
	    	if(cost != "" && quantity != "" && quantity!= 0){
	    		var unit_price_new = cost/quantity;
		    	tr.find(".unit_price").val(unit_price_new.toFixed(2));
		    	return;
	    	}
	    	if(cost != "" && (quantity == "" || quantity == 0) && (unit_price != 0 && unit_price != "")){
	    		var quantity_new = cost/unit_price;
		    	tr.find(".quantity").val(quantity_new.toFixed(2));
		    	return;
	    	}
		}
		//计算总价及总数量
		function calTableCost(){
			var trs = $("#table_order_detail tbody tr");
			var total_quantity = 0;
			var total_cost = 0;
			trs.each(function(){
				var quantity = $(this).find(".quantity").val();
				var cost = $(this).find(".cost").val();
				total_quantity += quantity *1;
				total_cost += cost *1;
			});
			$("#total_quantity").html(total_quantity.toFixed(2));
			$("#total_cost").html(total_cost.toFixed(2));
		}
		
		//添加商品
		function addProduct(){
			parent.openWinProduct();
		}
		
	</script>
  </head>
<body style="margin: 0px;">
	<input id="input_order_id" style="display: none;">
	<div style="height:30px;background-color:#e0ecff">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addOrder();">新建订单</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="addProduct();">选择商品</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-filter'" onclick="chooseModel();">套用模板</a>
	</div>
	<div>
		<table id="table_order_detail" class="table_list" cellspacing="0">
			<thead>
				<tr style="height: 30px;">
					<th style="width:30px;"><input id="checkAll" type="checkbox"></th>
					<th style="width:30px;">序号</th>
					<th style="width: 200px;">商品编号</th>
					<th style="width: 200px;">商品条形码</th>
					<th style="width: 300px;">商品名称</th>
					<th>单位</th>
					<th style="width: 127px;">单价</th>
					<th style="width: 127px;">数量</th>
					<th style="width: 127px;">总价</th>
				</tr>
				<tr style="height: 30px;">
					<td colspan="7"></td>
					<td style="font-weight:bold; text-align: right;!" id="total_quantity">0</td>
					<td style="font-weight:bold; text-align: right;!" id="total_cost">0</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="detail" items="${detailList}" varStatus="status">  
				    <tr>
				    	<td style="width:30px;"><input type="checkbox"></td>
				    	<td>${status.count}</td>
				    	<td>${detail.productCode}</td>
				    	<td>${detail.barCode}</td>
				    	<td>${detail.productName}</td>
				    	<td>${detail.productUnit}</td>
				    	<td>${detail.unit_price}</td>
				    	<td>${detail.quantity}</td>
				    	<td>${detail.cost}</td>
				    </tr>
				</c:forEach> 
			</tbody>
		</table>
	</div>
	
</body>
</html>

