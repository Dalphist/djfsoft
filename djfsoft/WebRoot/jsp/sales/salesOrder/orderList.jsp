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
			//添加
		    $("#input_order_add").click(function(){
		    	$("#tab_order input").val("");
		    	$(".order_category_text").val(order_category_text);
		    	$("#tab_order").tabs("select",0);
		    	$("#input_order_id").val("");
		    	getCategoryAttribute(category_id);
		    });
		    //全选/取消全选
		    $("#checkAll").on("click",function(event){
		    	if($("#checkAll").prop("checked")){
		    		$("#table_order_list tbody :checkbox").prop("checked", true);
		    	}else{
		    		$("#table_order_list tbody :checkbox").prop("checked", false);
		    	}
		    });
		});
		
		//手动添加订单
		function addOrder(){
			parent.addOrder();
		}
		//获取订单对应的货品详情
		function getorderDetail(order_id){
			$("#table_order_detail tbody").empty();
			$.ajax({
				url:"<%=projectName%>/sales/salesOrder/getOrderDetail",
				type:"get",
		        data:{"orderId":order_id},
		        success:function(result){
		        	var detailList = result.dataList;
		        	$.each(detailList,function(i,detail){
		        		var tr = "<tr>"
		        				+ "<td>"+ (i+1) +"</td>"
		        				+ "<td>"+ detail.productName +"</td>"
		        				+ "<td>"+ detail.productCode +"</td>"
		        				+ "<td>"+ detail.barCode +"</td>"
		        				+ "<td>"+ detail.productUnit +"</td>"
		        				+ "<td>"+ detail.unitPrice +"</td>"
		        				+ "<td>"+ detail.quantity +"</td>"
		        				+ "<td>"+ detail.cost +"</td>"
		        				+ "</tr>"
		        		$("#table_order_detail tbody").append(tr);
		        	});
		        }				
			});
		}
		//订单基本信息显示在下面
		function getBasicInfo(tr){
			$("#td_dealDate").text(tr.find(".dealDate").text());
			$("#td_taobaoCode").text(tr.find(".taobaoCode").text());
			$("#td_customerName").text(tr.find(".customerName").text());
			$("#td_customerTel").text(tr.find(".customerTel").text());
			$("#td_customerPostcode").text(tr.find(".customerPostcode").text());
			$("#td_district").text(tr.find(".customerDistrictName").text());
			$("#td_customerNotes").text(tr.find(".customerNotes").text());
			$("#td_customerAddress").text(tr.find(".customerAddress").text());
		}
		
		//删除订单
		function delOrder(){
			var order_id = $("#table_order_list tbody").find(".select_tr").find(".td_order_id").text().trim();;
			$.ajax({
				url:"<%=projectName%>/sales/salesOrder/delOrder",
				type:"post",
		        data:{"orderId":order_id},
		        success:function(result){
		        	$.messager.alert('提示',result.msg,'info',function(){    
				        location.reload(); 
					});
		        }				
			});
		}
		
		//将选中的销售订单出库操作
		function stockOut(){
			var selectOrderIds = getSelectOrder();
			if(selectOrderIds.length == 0){
				$.messager.alert('提示','没有选择任何订单','info');
				return false;
			}else{
				$.ajax({
					url:"<%=projectName%>/sales/salesStockOutOrder/toStockOut",
					type:"post",
			        data:{"selectOrderIds":JSON.stringify(selectOrderIds)},
			        success:function(result){
			        	$.messager.alert('提示',result.msg,'info',function(){    
					        parent.window.location="<%=projectName%>/sales/salesStockOutOrder/index";
						});
			        }				
				});
			}
		}
		
		//获取选中的订单
		function getSelectOrder(){
			var checkboxes = $("#table_order_list tbody :checkbox");
			var selectOrderIds = [];
			checkboxes.each(function(){
				if($(this).prop("checked")){
					var orderId = $(this).parents("tr").find(".td_order_id").text().trim();
					selectOrderIds.push(orderId);
				}
			});
			return selectOrderIds;
		}
	</script>
  </head>
  <body style="margin: 0px;">
  <input id="input_order_id" style="display: none;">
	<div style="height:28px;background-color:#e0ecff;padding-top: 1px;">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addOrder();">手动添加</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="delOrder();">删除订单</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="stockOut();">订单出库</a>
		<input type="button" value="导出" style="position:absolute;top:5px;right:10px;"/>
	</div>
	<div id="layout_order_list" class="easyui-layout" style="height: 560px;">
		<div data-options="region:'west',title:'筛选'" style="width: 100px;"></div>
		<div data-options="region:'center'" >
			<div id="div_order_list" class="easyui-layout" style="width:100%;height:100%;">
				<div data-options="region:'center'" >
					<div>
						<table id="table_order_list" class="table_list" cellspacing="0">
							<thead>
								<tr>
									<th style="display: none;">ID</th>
									<th style="width:10px;"><input id="checkAll" type="checkbox"/></th>
									<th style="width:30px;">序号</th>
									<th>订单编号</th>
									<th>状态</th>
									<th>商品总价</th>
									<th>运费</th>
									<th>优惠金额</th>
									<th>总价</th>
									<th>操作人</th>
									<th>订单时间</th>
									<th>支付方式</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="order" items="${orderList}" varStatus="status">  
								    <tr>
								    	<td style="display: none;" class="td_order_id">${order.id}</td>
								    	<td class="td_checkbox"><input type="checkbox"/></td>
								    	<td>${status.count}</td>
								    	<td>${order.orderCode}</td>
								    	<td>${order.orderState}</td>
								    	<td>${order.productPrice}</td>
								    	<td>${order.transportFare}</td>
								    	<td>${order.extraPrice}</td>
								    	<td>${order.totalPrice}</td>
								    	<td>${order.operateName}</td>
								    	<td><fmt:formatDate value="${order.operateDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
								    	<td>${order.payType}</td>
								    	<!-- 隐藏信息 -->
								    	<td class="dealDate" hidden="hidden"><fmt:formatDate value="${order.dealDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
								    	<td class="taobaoCode" hidden="hidden">${order.taobaoCode}</td>
								    	<td class="customerName" hidden="hidden">${order.customerName}</td>
								    	<td class="customerTel" hidden="hidden">${order.customerTel}</td>
								    	<td class="customerPostcode" hidden="hidden">${order.customerPostcode}</td>
								    	<td class="customerDistrictName" hidden="hidden">${order.customerDistrict1Name} ${order.customerDistrict2Name} ${order.customerDistrict3Name}</td>
								    	<td class="customerNotes" hidden="hidden">${order.customerNotes}</td>
								    	<td class="customerAddress" hidden="hidden">${order.customerAddress}</td>
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
					    <div title="支付"  style="overflow:auto;display:none;">   
					        tab2    
					    </div>   
					    <div title="备注"  style="overflow:auto;display:none;">   
					        tab2    
					    </div>   
					    <div title="退换"  style="overflow:auto;display:none;">   
					        tab2    
					    </div>   
					    <div title="补款"  style="overflow:auto;display:none;">   
					        tab2    
					    </div>   
					    <div title="额外包裹"  style="overflow:auto;display:none;">   
					        tab2    
					    </div>   
					</div> 
					
				</div>
			</div>
		</div>
	</div>

</body>
</html>

