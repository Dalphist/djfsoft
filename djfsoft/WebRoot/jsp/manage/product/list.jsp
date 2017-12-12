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
	span.required {
		color: #999;
		font-size: 150%;
	}
	</style>
	<script type="text/javascript">
	var product_category_id;
	var product_category_text;
		$(function(){
			product_category_id = $("#product_category_id",window.parent.document).val();
			product_category_text = $("#product_category_text",window.parent.document).val();
			$("#product_category_id").val(product_category_id);
			$(".product_category_text").val(product_category_text);
			
			$("#table_product_list tbody tr").click(function() {  
			   $(this).addClass("select_tr").siblings().removeClass("select_tr"); 
			   $(this).find(":checkbox").prop("checked",true);
			   $(this).siblings().find(":checkbox").prop("checked",false); 
			   var product_id = $(this).find(".td_product_id").text().trim();
			   $("#input_product_id").val(product_id);
			   getProductInfo(product_id);
			}); 
			//复选框  阻止事件冒泡
		    $("#table_product_list tbody .td_checkbox").on("click",function(event){
		    	event.stopPropagation();    
		    });
			//tab栏添加按钮
			$('#tab_product').tabs({
				toolPosition:'left',
				tools:[{
					iconCls:'icon-save',
					handler:function(){
						productSave();
					}
				}]
			});
			//添加
		    $("#input_product_add").click(function(){
		    	$("#tab_product input").val("");
		    	$(".product_category_text").val(product_category_text);
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
		
		//获取商品信息并显示在信息栏
		function getProductInfo(product_id){
			$.ajax({
				url:"<%=projectName%>/manage/product/getProductById",
				type:"post",
				dataType:"json",
                data:{"productId":product_id},
                success:function(result){
                	var product = result.data;  
                	$("#input_product_code").val(product.productCode);
					$("#input_product_bar_code").val(product.barCode);
					$(".product_category_text").val(product.categoryName);
					$("#input_product_name").val(product.productName);
					$("#input_product_shortname").val(product.productShortName);
					$("#input_product_normal_purchase_price").val(product.normalPurchasePrice);
					$("#input_product_stock_warn").val(product.stockWarn == -1?"":product.stockWarn);
					$("#input_product_unit").val(product.productUnit);
                },
                error:function(){
                }
			})
		}
		
		//获取所有input框的商品信息，返回json
		function getInputProductInfo(){
			var productInfo = {};
			productInfo.productCode = $("#input_product_code").val().trim();
			productInfo.barCode = $("#input_product_bar_code").val().trim();
			productInfo.categoryId = $("#product_category_id").val().trim();
			productInfo.productName = $("#input_product_name").val().trim();
			productInfo.productShortName = $("#input_product_shortname").val().trim();
			productInfo.normalPurchasePrice = $("#input_product_normal_purchase_price").val().trim();
			productInfo.stockWarn = $("#input_product_stock_warn").val().trim();
			productInfo.productUnit = $("#input_product_unit").val().trim();
			productInfo.effectiveFlag = $("#input_product_effective_flag").attr('checked')?1:0;
			return productInfo;
		}
		//检查商品信息是否填写合格
		function validateProductInput(productInfo){
			var validation = {};
			validation.fail = false;
			if(productInfo.productCode == "" || productInfo.barCode == "" ||productInfo.productName == "" || productInfo.productUnit == ""){
				validation.fail = true;
				validation.msg = "请填写必填项!";
				return validation;
			}
			$.ajax({
				url:"<%=projectName%>/manage/product/validateProduct",
				type:"post",
				async: false,
				dataType:"json",
                data:{"productInfo":JSON.stringify(productInfo)},
                success:function(result){
                	if(result.code == 1){	//有重复商品信息
                		validation.fail = true;
						validation.msg = result.msg;
						return validation;
                	}
                }				
			})
			
			return validation;
		}
		//商品保存
		function productSave(){
			var productInfo = getInputProductInfo();
			var validation = validateProductInput(productInfo);
			if(validation.fail){
				$.messager.alert('提示',validation.msg,'info');
				return false;
			}
			$.ajax({
				url:"<%=projectName%>/manage/product/addProduct",
				type:"post",
				dataType:"json",
                data:{"productInfo":JSON.stringify(productInfo)},
                success:function(result){
                	$.messager.alert('提示',result.msg,'info',function(){    
				        location.reload(); 
					});  
                },
                error:function(){
                }				
			})
		}
		//获取所有已选择商品
		function getSelectProduct(){
			var checkboxes = $("#table_product_list tbody :checkbox");
			var selectProductIds = [];
			var i = 0;
			$.each(function(j,checkbox){
				if(checkbox.prop("checked")){
					var productId = $(this).parents("tr").find(".td_product_id").text().trim();
					selectProductIds[i] = productId;
					i++;
				}
			});
			return selectProductIds;
		}
		//删除所选商品
		
	</script>
  </head>
  <body style="margin: 0px;">
  <input id="product_category_id" style="display: none;">
  <input id="input_product_id" style="display: none;">
  <div class="easyui-layout" style="height:100%;">
  	<div data-options="region:'center'" style="height:70%;">
		<div style="height:30px;background-color:#e0ecff">
			<input id="input_product_add" type="button" value="添加" />
			<input id="input_product_del" type="button" value="删除" />
			<input type="button" value="导出" style="position:absolute;top:5px;right:10px;"/>
		</div>
		<div>
			<table id="table_product_list" class="table_list" cellspacing="0">
				<thead>
					<tr>
						<th style="display: none;">ID</th>
						<th style="width:10px;"><input id="checkAll" type="checkbox"/></th>
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
					    	<td style="display: none;" class="td_product_id">${product.id}</td>
					    	<td class="td_checkbox"><input type="checkbox"/></td>
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
		style="width:100%;background:#efefef;border:1px solid #ccc;position:absolute;bottom:1px;">
		</div> 
	</div>
	<div data-options="region:'south',split:true" style="height:30%;">
		<div id="tab_product" class="easyui-tabs" style="width:100%;height:100%;">   
			<div title="基本信息" style="display:none;"> 
				<table class="table_list">   
					<tbody>   
						<tr>   
							<td colspan="2"> 商品名称<span class="required">*</span><input id="input_product_name" value="" style="width: 60%;"/></td>
							<td>简称 <input id="input_product_shortname" value=""/></td>
						</tr>   
						<tr>   
							<td>类别 <input class="product_category_text" value="" readonly="readonly"/></td>
							<td>商品编码<span class="required">*</span><input id="input_product_code" value=""/></td>
							<td>条形码<span class="required">*</span><input id="input_product_bar_code" class="easyui-numberbox" data-options="min:0" value=""/></td>
						</tr> 
						<tr>   
							<td>单位<span class="required">*</span><input id="input_product_unit" value=""/></td>
							<td>标准采购单价 <input id="input_product_normal_purchase_price" class="easyui-numberbox" data-options="min:0,precision:2" value=""/></td>
							<td>预警库存数量 <input id="input_product_stock_warn" class="easyui-numberbox" data-options="min:0,precision:2" value=""/></td>
						</tr> 
						<tr>   
							<td><input id="input_product_effective_flag" type="checkbox" checked>启用商品</td>
							<td colspan="2">备注 <input id="input_product_aa" value="" style="width: 60%;"/></td>
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
			<div title="多单位" style="overflow:auto;display:none;">   
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
			</div>   
		</div> 
	</div>
</div>
</body>
</html>

