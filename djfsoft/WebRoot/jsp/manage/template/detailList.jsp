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
	<link rel="stylesheet" type="text/css" href="<%=projectName%>/css/style.css"> 
	<script type="text/javascript" src="<%=projectName%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=projectName%>/easyui/jquery.easyui.min.js"></script>
	
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
			//单击行
			$("#table_template_detail tbody").on("click","tr",function(){
			   $(this).addClass("select_tr").siblings().removeClass("select_tr"); 
			}); 
		    
		});
		
		function openWinProduct(){
			$("#win_product").window("open");
		}
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
			var tbody = $("#table_template_detail tbody");
			var len = tbody.find("tr").length;
			var productIds = getSelectProduct();
			if(productIds.length > 0){
				$.ajax({
					url:"<%=projectName%>/manage/product/getProductInfoList",
					type:"get",
	                data:{"productIds":JSON.stringify(productIds)},
	                success:function(result){
	                	var productList = result.dataList;
	                	$.each(productList,function(i,product){
	                		if($("#tr"+product.id).length == 0){
		                		var tr = "<tr id='tr"+ product.id +"'>"
		                				+ "<td></td>"
		                				+ "<td>"+ product.productName +"</td>"
		                				+ "<td>"+ product.productCode +"</td>"
		                				+ "<td>"+ product.productUnit +"</td>"
		                				+ "<td><input class='number_text price' data-options='min:0,precision:2'/></td>"
		                				+ "<td class='td_product_id' style='display:none;'>"+ product.id +"</td>"
		                				+ "</tr>";
								tbody.append(tr);
	                		}
		     			});
		     			tableSort();
		     			//先将前一次选择数据全部清除  
		       			//$("#tt").treegrid("clearChecked");
	                }				
				});
			}
		}
		//删除商品行
		function delProduct(){
			$("#table_template_detail tbody").find(".select_tr").remove();
			tableSort();
		}
		//重新排序
		function tableSort(){
			var trs = $("#table_template_detail tbody").find("tr");
			trs.each(function(i,tr){
				$(this).find("td").eq(0).html(i*1+1);
			});
		}
		
		//获取所有模板详情
		function getDetail(){
			var productListInfo = [];
			var trs = $("#table_template_detail tbody").find("tr");
			var b = true;
			trs.each(function(){
				var productInfo = {};
				var product_id = $(this).find(".td_product_id").text().trim();
				var price = $(this).find(".price").val().trim();
				if(price == 0 || price == "" ){
					b = false;
					return;
				}
				productInfo.productId = product_id;
				productInfo.price = price;
				productListInfo.push(productInfo);
			});
			if(!b){
				alert("商品价格不能为0");
				return false;
			}
			return productListInfo;
		}
		
		//保存
		function saveDetail(){
			var productListInfo = getDetail();
			var templateId = $(window.parent.frames["iframe_template"].document).find("#input_select_template").val();
			$.ajax({
				url:"<%=projectName%>/manage/template/saveTemplateDetail",
				type:"post",
                data:{"productListInfo":JSON.stringify(productListInfo),"templateId":templateId},
                success:function(result){
                }				
			});
		}
		
	</script>
  </head>
  <body style="margin: 0px;">
  <input id="input_attribute_id" type="text" value="${attributeId}" style="display: none;"> 
  <input id="input_select_value" type="text" style="display: none;"> 
  <div class="easyui-layout">
	<div style="height:30px;background-color:#e0ecff">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="openWinProduct();">选择商品</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="delProduct();">删除商品</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveDetail();">保存</a>
	</div>
	<div>
		<table id="table_template_detail" class="table_list" cellspacing="0">
			<thead>
				<tr>
					<th style="display: none;">ID</th>
					<th style="width: 40px;">序号</th>
					<th>货品名称</th>
					<th>货品编码</th>
					<th>单位</th>
					<th>价格</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="value" items="${ValueList}" varStatus="status">  
				    <tr id="tr${value.id}">
				    	<td style="display: none;" class="td_value_id">${value.id}</td>
				    	<td>${status.count}</td>
				    	<td class="td_value_name">${value.attributeValueName}</td>
				    	<td class="td_effective_flag">
				    		<c:choose> 
							     <c:when test="${value.effectiveFlag == 1}">是 </c:when>      
							     <c:otherwise>否</c:otherwise> 
							</c:choose>
				    	</td>
				    </tr>
				</c:forEach> 
			</tbody>
		</table>
	</div>
</div>

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
</body>
</html>

