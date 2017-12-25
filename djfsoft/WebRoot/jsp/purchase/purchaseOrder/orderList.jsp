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
	</style>
	<script type="text/javascript">
		$(function(){
			//单击行变色及获取订单详情
			$("#table_order_list tbody tr").click(function() {  
			   $(this).addClass("select_tr").siblings().removeClass("select_tr"); 
			   var order_id = $(this).find(".td_order_id").text().trim();
			   $("#input_order_id").val(order_id);
			   getorderDetail(order_id);
			}); 
			//添加
		    $("#input_order_add").click(function(){
		    	$("#tab_order input").val("");
		    	$(".order_category_text").val(order_category_text);
		    	$("#tab_order").tabs("select",0);
		    	$("#input_order_id").val("");
		    	getCategoryAttribute(category_id);
		    });
		});
		
		function getorderDetail(order_id){
			$("#iframe_order_detail",window.parent.document).attr("src","<%=projectName%>/purchase/purchaseOrder/orderDetail?orderId="+order_id);
		}
	</script>
  </head>
  <body style="margin: 0px;">
  <input id="input_order_id" style="display: none;">
  <div class="easyui-layout" style="height:100%;">
  	<div data-options="region:'center'" style="height:70%;">
		<div style="height:30px;background-color:#e0ecff">
			<span>采购订单</span>
			<input id="input_order_add" type="button" value="添加" />
			<input id="input_order_del" type="button" value="删除" onclick="delSelectorder();"/>
			<input type="button" value="导出" style="position:absolute;top:5px;right:10px;"/>
		</div>
		<div>
			<table id="table_order_list" class="table_list" cellspacing="0">
				<thead>
					<tr>
						<th style="display: none;">ID</th>
						<th style="width:30px;">序号</th>
						<th>订单编号</th>
						<th>商品总价</th>
						<th>运费</th>
						<th>额外费用</th>
						<th>总价</th>
						<th>采购人</th>
						<th>订单时间</th>
						<th>支付方式</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${orderList}" varStatus="status">  
					    <tr>
					    	<td style="display: none;" class="td_order_id">${order.id}</td>
					    	<td>${status.count}</td>
					    	<td>${order.orderCode}</td>
					    	<td>${order.productPrice}</td>
					    	<td>${order.transportFare}</td>
					    	<td>${order.extraPrice}</td>
					    	<td>${order.totalPrice}</td>
					    	<td>${order.operateName}</td>
					    	<td>${order.operateDate}</td>
					    	<td>${order.payType}</td>
					    </tr>
					</c:forEach> 
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>

