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
			}); 
		    //全选/取消全选
		    $("#checkAll").on("click",function(event){
		    	if($("#checkAll").prop("checked")){
		    		$("#table_product_list tbody :checkbox").prop("checked", true);
		    	}else{
		    		$("#table_product_list tbody :checkbox").prop("checked", false);
		    	}
		    });
		});
		
		//获取订单对应的货品详情
		function getorderDetail(order_id){
			$("#table_order_detail tbody").empty();
			$.ajax({
				url:"<%=projectName%>/sales/salesStockOutOrder/getOrderDetail",
				type:"get",
		        data:{"orderId":order_id},
		        success:function(result){
		        	var detailList = result.dataList;
		        	$.each(detailList,function(i,detail){
		        		var tr = "<tr>"
		        				+ "<td>"+ (i+1) +"</td>"
		        				+ "<td>"+ detail.salesOrderCode +"</td>"
		        				+ "<td>"+ detail.productName +"</td>"
		        				+ "<td>"+ detail.productCode +"</td>"
		        				+ "<td>"+ detail.barCode +"</td>"
		        				+ "<td>"+ detail.productUnit +"</td>"
		        				+ "<td>"+ detail.normalQuantity +"</td>"
		        				+ "<td>"+ detail.warehouseName +"</td>"
		        				+ "<td>"+ detail.rackCode +"</td>"
		        				+ "</tr>"
		        		$("#table_order_detail tbody").append(tr);
		        	});
		        }				
			});
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
		//销售订单导入成出库订单
		function importSalesOrder(){
			parent.window.location="<%=projectName%>/sales/salesOrder/index";
		}
	</script>
  </head>
  <body style="margin: 0px;">
  <input id="input_order_id" style="display: none;">
	<div style="height:30px;background-color:#e0ecff;padding-top: 3px;">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="importSalesOrder();">销售导入</a>
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
									<th style="width:10px;"><input id="checkAll" type="checkbox"/></th>
									<th style="width:30px;">序号</th>
									<th>出库单号</th>
									<th>操作人</th>
									<th>订单时间</th>
									<th>出库时间</th>
									<th>出库时间</th>
									<th>状态</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="order" items="${orderList}" varStatus="status">  
								    <tr>
								    	<td style="display: none;" class="td_order_id">${order.id}</td>
								    	<td class="td_checkbox"><input type="checkbox"/></td>
								    	<td>${status.count}</td>
								    	<td>${order.orderCode}</td>
								    	<td>${order.operaterName}</td>
								    	<td><fmt:formatDate value="${order.operateDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
								    </tr>
								</c:forEach> 
							</tbody>
						</table>
					</div>
				</div>
				<div data-options="region:'south'" style="height: 40%;">
					<div id="tt" class="easyui-tabs" style="width:100%;height: 100%;">   
					    <div title="出库货品" style="display:none;">   
					        <div>
								<table id="table_order_detail" class="table_list" cellspacing="0">
									<thead>
										<tr style="height: 30px;">
											<th style="width: 40px;">序号</th>
											<th style="width: 200px;">销售订单单号</th>
											<th style="width: 300px;">商品名称</th>
											<th style="width: 200px;">商品编号</th>
											<th style="width: 200px;">商品条形码</th>
											<th style="width: 40px;">单位</th>
											<th style="width: 100px;">出库数量</th>
											<th style="width: 100px;">出库仓库</th>
											<th style="width: 100px;">出库货位号</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>    
					    </div>   
					    <div title="库存"  style="overflow:auto;display:none;">   
					        tab2    
					    </div>   
					    <div title="备注"  style="overflow:auto;display:none;">   
					        tab2    
					    </div>   
					</div> 
					
				</div>
			</div>
		</div>
	</div>

</body>
</html>

