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
			$("#table_attribute_list tbody tr").click(function() {  
			   $(this).addClass("select_tr").siblings().removeClass("select_tr"); 
			   $(this).find(":checkbox").prop("checked",true);
			   $(this).siblings().find(":checkbox").prop("checked",false); 
			   var product_id = $(this).find(".td_product_id").text().trim();
			   $("#input_product_id").val(product_id);
			   getProductInfo(product_id);
			}); 
			//复选框  阻止事件冒泡
		    $("#table_attribute_list tbody .td_checkbox").on("click",function(event){
		    	event.stopPropagation();    
		    });
			//添加
		    $("#input_product_add").click(function(){
		    	$("#tab_product input").val("");
		    	$(".product_category_text").val(product_category_text);
		    });
		    
		    //全选/取消全选
		    $("#checkAll").on("click",function(event){
		    	if($("#checkAll").prop("checked")){
		    		$("#table_attribute_list tbody :checkbox").prop("checked", true);
		    	}else{
		    		$("#table_attribute_list tbody :checkbox").prop("checked", false);
		    	}
		    });
		});
		
		
		/*************************************************************************************/
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
			var checkboxes = $("#table_attribute_list tbody :checkbox");
			var selectDelProduct = [];
			var i = 0;
			checkboxes.each(function(){
				if($(this).prop("checked")){
					var delProduct = {};
					var productId = $(this).parents("tr").find(".td_product_id").text().trim();
					delProduct.productId = productId
					selectDelProduct[i] = delProduct;
					i++;
				}
			});
			return selectDelProduct;
		}
		//删除所选商品
		function delSelectProduct(){
			var selectDelProduct = getSelectProduct();
			if(selectDelProduct.length > 0){
				$.messager.confirm('确认','您确认想要删除所选商品吗？',function(r){    
				    if (r){    
				        $.ajax({
							url:"<%=projectName%>/manage/product/delProduct",
							type:"post",
							dataType:"json",
			                data:{"selectProductList":JSON.stringify(selectDelProduct)},
			                success:function(result){
			                	$.messager.alert('提示',result.msg,'info',function(){    
							        location.reload(); 
								});  
			                },
			                error:function(){
			                }				
						})
				    }    
				});
			}else{
				$.messager.alert('提示','没有选择任何商品','info');
			}
		}
		
	</script>
  </head>
  <body style="margin: 0px;">
  <div class="easyui-layout" style="height:100%;">
	<div style="height:30px;background-color:#e0ecff">
		<input id="input_product_add" type="button" value="添加" />
		<input id="input_product_update" type="button" value="修改" />
		<input id="input_product_del" type="button" value="删除" onclick="delSelectProduct();"/>
		<input type="button" value="导出" style="position:absolute;top:5px;right:10px;"/>
	</div>
	<div>
		<table id="table_attribute_list" class="table_list" cellspacing="0">
			<thead>
				<tr>
					<th style="display: none;">ID</th>
					<th><input id="checkAll" type="checkbox"/></th>
					<th>序号</th>
					<th>名称</th>
					<th>可用</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="attribute" items="${productAttributeList}" varStatus="status">  
				    <tr>
				    	<td style="display: none;" class="td_product_id">${attribute.id}</td>
				    	<td class="td_checkbox"><input type="checkbox"/></td>
				    	<td>${status.count}</td>
				    	<td>${attribute.attributeName}</td>
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
</body>
</html>

