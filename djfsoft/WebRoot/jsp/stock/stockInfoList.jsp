<%@ page language="java" import="java.util.*,djfsoft.pojo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			var height = $(window).height()-30;  
	        $("#layout_order_list").attr("style","width:100%;height:"+height+"px");  
	        $("#layout_order_list").layout("resize",{  
	            width:"100%",  
	            height:height+"px"  
	        });  
			//单击行变色及获取订单详情
			$("#table_order_list tbody tr").click(function() {  
			   $(this).addClass("select_tr").siblings().removeClass("select_tr"); 
			   var order_id = $(this).find(".td_order_id").text().trim();
			   $("#input_order_id").val(order_id);
			   getorderDetail(order_id);
			   getBasicInfo($(this));
			}); 
		});
	</script>
  </head>
  <body style="margin: 0px;">
  <input id="input_order_id" style="display: none;">
	<div style="height:30px;background-color:#e0ecff;padding-top: 3px;">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addOrder();">手动添加</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="delOrder();">删除订单</a>
		<input type="button" value="导出" style="position:absolute;top:5px;right:10px;"/>
	</div>
	<div id="layout_order_list" class="easyui-layout">
		<div data-options="region:'west',title:'筛选'" style="width: 100px;"></div>
		<div data-options="region:'center'" >
			<div id="div_order_list" class="easyui-layout" style="width:100%;height:100%;">
				<div data-options="region:'center'" >
					<div>
						<table id="table_order_list" class="table_list" cellspacing="0">
							<thead>
								<tr>
									<th style="display: none;">ID</th>
									<th style="width:30px;">序号</th>
									<th>订单编号</th>
									<th>商品总价</th>
									<th>运费</th>
									<th>优惠金额</th>
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
								    	<td><fmt:formatDate value="${order.operateDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
								    	<td>${order.payType}</td>
								    </tr>
								</c:forEach> 
							</tbody>
						</table>
					</div>
				</div>
				<div data-options="region:'south'" style="height: 40%;">
					<div id="tt" class="easyui-tabs" style="width:100%;height: 100%;">   
					    <div title="货品详情" style="display:none;">   
					        <div>
								<table id="table_order_detail" class="table_list" cellspacing="0">
									<thead>
										<tr style="height: 30px;">
											<th style="width: 40px;">序号</th>
											<th style="width: 300px;">商品名称</th>
											<th style="width: 200px;">商品编号</th>
											<th style="width: 200px;">商品条形码</th>
											<th style="width: 40px;">单位</th>
											<th style="width: 127px;">单价</th>
											<th style="width: 127px;">数量</th>
											<th style="width: 127px;">总价</th>
										</tr>
									</thead>
									<tbody>
									
									</tbody>
								</table>
							</div>    
					    </div>   
					    <div title="详情"  style="overflow:auto;display:none;">   
					        <table id="table_basicInfo1">
					    		<tr>
					    			<td>成交时间：</td>
					    			<td id="td_dealDate"></td>
					    			<td>淘宝订单号：</td>
					    			<td id="td_taobaoCode" colspan="2" style="width: 160px;"></td>
					    			<td></td>
					    		</tr>
					    	</table>
					    	<table id="table_basicInfo" >
					    		<tr>
					    			<td style="width:50px;">客户姓名：</td>
					    			<td id="td_customerName" style="width:100px;"></td>
					    			<td style="width:50px;">电话：</td>
					    			<td id="td_customerTel" style="width:100px;"></td>
					    			<td style="width:80px;">邮编：</td>
					    			<td id="td_customerPostcode" style="width:100px;"></td>
					    			<td style="width:100px;"></td>
					    			<td></td>
					    		</tr>
					    		<tr>
					    			<td>地区：</td>
					    			<td id="td_district" colspan="3"></td>
					    			<td>地址：</td>
					    			<td id="td_customerAddress" colspan="5"></td>
					    		</tr>
					    		<tr>
					    			<td>客户备注：</td>
					    			<td id="td_customerNotes" colspan="3"></td>
					    		</tr>
					    	</table>   
					    </div>   
					    <div title="库存"  style="overflow:auto;display:none;">   
					        tab2    
					    </div>   
					</div> 
				</div>
			</div>
		</div>
	</div>

</body>
</html>

