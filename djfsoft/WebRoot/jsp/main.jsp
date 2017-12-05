<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		.nav{
			width:50px;
			height: 46px;
			line-height: 46px;
			text-align:center;
			cursor: pointer;
			border: 1px solid blue;
			float: left;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			setMainLayoutHeight();
		})
		function setMainLayoutHeight(){
			var height = $(window).height()-40;  
			$("#main_layout").attr("style","width:100%;height:"+height+"px");  
			$("#main_layout").layout("resize",{  
				width:"100%",  
				height:height+"px"  
			});
		}
	</script>
  </head>
  <body>
	<div id="main_layout" class="easyui-layout" style="width:100%;height:600px;">
		<div data-options="region:'north'" style="height:50px">
			<div class="nav" onclick="window.location='main'">库存</div>
			<div class="nav" onclick="window.location='jsp/manage/product.jsp'">管理</div>
		</div>
		<%@include file="tree/product_category_tree.jsp"%>
		<div data-options="region:'center'">
			<div class="easyui-layout" style="height:100%;">
				<div data-options="region:'center'">
					<div style="height:30px;background-color:#e0ecff">
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
								<tr>
									<td>111</td>
									<td>111</td>
									<td>111</td>
									<td>111</td><td>111</td>
									<td>111</td><td>111</td>
									
								</tr>
							</tbody>
						</table>
					</div>
					<div id="pp" class="easyui-pagination" data-options="total:2000,pageSize:10" 
					style="width:100%;background:#efefef;border:1px solid #ccc;position:absolute;bottom:0px;"></div> 
				</div>
				<div data-options="region:'south',split:true" style="height:45%;">
					<div id="tt" class="easyui-tabs" style="width:100%;height:100%;">   
						<div title="库存明细" style="display:none;">   
							<table class="table_list">   
								<thead>   
									<tr>   
										<th>序号</th>   
										<th>编码</th>   
										<th>名称</th>   
										<th>仓库</th>   
										<th>仓位</th>   
										<th>库存</th>   
										<th>仓位容量</th>   
									</tr>   
								</thead>   
								<tbody>   
									<tr>   
										<td>1</td><td>001</td><td>name1</td><td>2323</td><td>2323</td><td>2323</td><td>2323</td>
									</tr>   
									<tr>   
										<td>2</td><td>002</td><td>name2</td><td>4612</td><td>2323</td><td>2323</td><td>2323</td>   
									</tr> 
								</tbody>   
							</table>
						</div>
						<div title="执行中的销售" style="display:none;"> 
						</div>   
						<div title="执行中的采购" style="overflow:auto;display:none;">   
							tab2    
						</div>   
						<div title="执行中的退货" style="display:none;">   
							tab3    
						</div> 
						<div title="执行中的调拨" style="display:none;">   
							tab4    
						</div> 						
					</div> 
				</div>
			</div>
		</div>
	</div>
</body>
</html>

