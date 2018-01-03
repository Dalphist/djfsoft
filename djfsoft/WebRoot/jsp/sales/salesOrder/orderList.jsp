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
		
		//手动添加订单
		function addOrder(){
			
		}
		
		function getorderDetail(order_id){
			$("#iframe_order_detail",window.parent.document).attr("src","<%=projectName%>/purchase/purchaseOrder/orderDetail?orderId="+order_id);
		}
	</script>
  </head>
  <body style="margin: 0px;">
  <input id="input_order_id" style="display: none;">
	<div style="height:30px;background-color:#e0ecff">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addOrder();">手动添加</a>
		<input type="button" value="导出" style="position:absolute;top:5px;right:10px;"/>
	</div>
	<div class="easyui-layout" style="width:100%;height:90%;">
		<div data-options="region:'west',title:'过滤'" style="width: 100px;"></div>
		<div data-options="region:'center'" >
			<div class="easyui-layout" style="width:100%;height:100%;">
				<div data-options="region:'center'" style="height: 60%;">
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
			</div>
			<div data-options="region:'south'" style="width: 100px;">
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
			</div>
		</div>
	</div>

</body>
</html>

