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
		
		//获取规格信息，返回json
		function getValueInfo(){
			var valueInfo = {};
			valueInfo.id = $("#input_select_value").val();
			valueInfo.attributeId = parseInt($("#input_attribute_id").val().trim());
			valueInfo.attributeValueName = $("#input_value_name").val().trim();
			valueInfo.effectiveFlag = $("#input_effective_flag").prop("checked")?1:0;
			return valueInfo;
		}
		
		//校验规格信息
		function validateValueInput(valueInfo){
			var validation = {};
			validation.fail = false;
			if(valueInfo.attributeName == ""){
				validation.fail = true;
				validation.msg = "名称不能为空!";
				return validation;
			}
			$.ajax({
				url:"<%=projectName%>/manage/productAttributeValue/validateAttributeValue",
				type:"post",
				async: false,
				dataType:"json",
                data:{"valueInfo":JSON.stringify(valueInfo)},
                success:function(result){
                	if(result.code == 1){	//名称重复
                		validation.fail = true;
						validation.msg = result.msg;
						return validation;
                	}
                }				
			})
			
			return validation;
		}
		
		//规格保存
		function savettribute(){
			var valueInfo = getValueInfo();
			var validation = validateValueInput(valueInfo);
			if(validation.fail){
				$.messager.alert('提示',validation.msg,'info');
				return false;
			}
			$.ajax({
				url:"<%=projectName%>/manage/productAttributeValue/saveAttributeValue",
				type:"post",
				dataType:"json",
                data:{"valueInfo":JSON.stringify(valueInfo)},
                success:function(result){
                	$.messager.alert('提示',result.msg,'info',function(){    
				        location.reload(); 
					});  
                },
                error:function(){
                }				
			});
		};
		//******************************************************
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
	                		var tr = "<tr>"
	                				+ "<td>"+ (len*1+i*1+1) +"</td>"
	                				+ "<td class='td_product_id' style='display:none;'>"+ product.id +"</td>"
	                				+ "<td>"+ product.productName +"</td>"
	                				+ "<td>"+ product.productCode +"</td>"
	                				+ "<td>"+ product.productUnit +"</td>"
	                				+ "<td><input class='number_text unit_price' data-options='min:0,precision:2'/></td>"
	                				+ "</tr>";
							tbody.append(tr);
		     			});
		     			//先将回显数据全部清除  
		       			$("#tt").treegrid("clearChecked");
	                }				
				});
			}
		}
		function delProduct(){
			$("#table_template_detail tbody").find(".select_tr").remove();
			//重排序号
			var trs = $("#table_template_detail tbody").find("tr");
			trs.each(function(i,tr){
				$(this).find("td").eq(1).html(i*1+1);
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
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="del();">删除商品</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="openWinProduct();">保存</a>
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

