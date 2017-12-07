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
	
	<style type="text/css">
		.table_list { 
			width: 100%; 
			padding: 0; 
			margin: 0; 
			border: 1px solid #95B8E7; 
			border-collapse:collapse;
		}
		.table_list th { 
			font: 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
			color: #0E2D5F; 
			border: 1px solid #dddddd; 
			letter-spacing: 2px;
			text-align: center; 
			padding: 3px 3px 3px 6px; 
			background: #efefef  no-repeat; 
		}
		.table_list td { 
			border: 1px solid #dddddd; 
			background: #fff; 
			font-size:10px; 
			padding: 3px 3px 3px 6px; 
			color: #4f6b72; 
			text-align: center; 
		}
	</style>
	<script type="text/javascript">
		$(function(){
			setMainLayoutHeight();
			$("#tab_product").tabs({
		        tools: [{           //给选项卡头部设置工具栏，接收一个数组，数组里接收一个对象，对象里是键值对设置图标和点击事件
		            iconCls: 'icon-save',
		            handler: function () {
		                productSave();
		            }
		        }]
		    });
		    
		    $("#input_product_add").click(function(){
		    	$("#tab_product input").val("");
		    });
		})
		function setMainLayoutHeight(){
			var height = $(window).height()-40;  
			$("#main_layout").attr("style","width:100%;height:"+height+"px");  
			$("#main_layout").layout("resize",{  
				width:"100%",  
				height:height+"px"  
			});
		}
		
		function productSave(){
			var productInfo = {};
			productInfo.productCode = $("#input_product_code").val();
			productInfo.barCode = $("#input_product_bar_code").val();
			productInfo.productName = $("#input_product_name").val();
			productInfo.productShortName = $("#input_product_shortname").val();
			productInfo.normalPurchasePrice = $("#input_product_normal_purchase_price").val();
			productInfo.stockWarn = $("#input_product_stock_warn").val();
			productInfo.productUnit = $("#input_product_unit").val();
			productInfo.productPlace = $("#input_product_place").val();
			var flag = $("#input_product_effective_flag").attr('checked')?1:0;
			productInfo.effectiveFlag = flag;
			$.ajax({
				url:"<%=projectName%>/manage/product/addProduct",
				type:"post",
				dataType:"json",
                data:{"productInfo":JSON.stringify(productInfo)},
                success:function(result){
                	alert(result.msg);
                },
                error:function(){
                }				
			})
		}
		
	</script>
  </head>
  <body>
	<div id="main_layout" class="easyui-layout" style="width:100%;height:600px;">
		
		<%@include file="../common/nav.jsp"%>
		<div data-options="region:'north'" style="height:51px">
		</div>
		<%@include file="../common/product_category_tree.jsp"%>
		<div data-options="region:'center'">
			<div class="easyui-layout" style="height:100%;">
				<div data-options="region:'center'">
					<div style="height:30px;background-color:#e0ecff">
						<input id="input_product_add" type="button" value="添加" />
						<input type="button" value="导出" style="position:absolute;top:5px;right:10px;"/>
					</div>
					<div>
						<table id="table_stock" class="table_list" cellspacing="0">
							<thead>
								<tr>
									<th style="width:30px;">序号</th>
									<th>编码</th>
									<th style="width:150px;">名称</th>
									<th>条形码</th>
									<th>标准采购价</th>
									<th>成本价</th>
									<th>最后采购价</th>
									<th style="width:70px;">标准品库存</th>
									<th style="width:70px;">缺陷品库存</th>
									<th style="width:60px;">可销售量</th>
									<th style="width:60px;">可发货量</th>
									<th style="width:60px;">预警数量</th>
									<th style="width:60px;">月销量</th>
									<th style="width:60px;">周销量</th>
									<th style="width:60px;">日销量</th>
									<th>备注</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="product" items="${productList}" varStatus="status">  
								    <tr>
								    	<td>${status.count}</td>
								    	<td>${product.productCode}</td>
								    	<td>${product.productName}</td>
								    	<td>${product.barCode}</td>
								    	<td>${product.normalPurchasePrice}</td>
								    	<td>${product.cost}</td>
								    	<td>${product.lastPurchasePrice}</td>
								    	<td></td>
								    	<td></td>
								    	<td></td>
								    	<td></td>
								    	<td>
								    		<c:if test="${product.stockWarn != -1}">
												${product.stockWarn}
											</c:if>
								    	</td>
								    	<td></td>
								    	<td></td>
								    	<td></td>
								    	<td></td>
								    </tr>
								</c:forEach> 
							</tbody>
						</table>
					</div>
					<div id="pp" class="easyui-pagination" data-options="total:2000,pageSize:10" 
					style="width:100%;background:#efefef;border:1px solid #ccc;position:absolute;bottom:0px;"></div> 
				</div>
				<div data-options="region:'south',split:true" style="height:45%;">
					<div id="tab_product" class="easyui-tabs" style="width:100%;height:100%;">   
						<div title="基本信息" style="display:none;"> 
							<table class="table_list">   
								<tbody>   
									<tr>   
										<td colspan="2"> 商品名称：<input id="input_product_name" value="" style="width: 60%;"/></td>
										<td>简称：<input id="input_product_shortname" value=""/></td>
									</tr>   
									<tr>   
										<td>类别：<input id="input_product_category" value=""/></td>
										<td>商品编码：<input id="input_product_code" value=""/></td>
										<td>条形码：<input id="input_product_bar_code" value=""/></td>
									</tr> 
									<tr>   
										<td>单位：<input id="input_product_unit" value=""/></td>
										<td>标准采购单价：<input id="input_product_normal_purchase_price" value=""/></td>
										<td>预警库存数量：<input id="input_product_stock_warn" value=""/></td>
									</tr> 
									<tr>   
										<td><input id="input_product_effective_flag" type="checkbox" checked>启用商品</td>
										<td colspan="2">备注：<input id="input_product_aa" value="" style="width: 60%;"/></td>
									</tr>
									<tr style="height: 30px;">   
									</tr>
									<tr>   
										<td>
											<div>
												多单位<input type="button" value="添加"><input type="button" value="删除">
											</div>
										</td>
										<td>
											<table class="table_list">   
												<thead>
													<tr>   
														<th>单位名称</th>
														<th>与基本单位比例</th>
													</tr>  
												</thead>
												<tbody>   
													<tr>   
														<td><input value=""/></td><td><input value=""/></td>
													</tr> 
												</tbody>
											</table>  
										</td> 
									</tr>
								</tbody>   
							</table>
						</div>   
						<div title="属性" style="overflow:auto;display:none;">   
							tab2    
						</div>   
						<div title="图片" style="overflow:auto;display:none;">   
							tab2    
						</div>   
					</div> 
				</div>
			</div>
		</div>
	</div>
</body>
</html>

