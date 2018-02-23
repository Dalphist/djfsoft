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
			//单击行
			$("#table_warehouse_list tbody tr").click(function() {  
			   $(this).addClass("select_tr").siblings().removeClass("select_tr"); 
			   $(this).find(":checkbox").prop("checked",true);
			   $(this).siblings().find(":checkbox").prop("checked",false); 
			   var warehouse_id = $(this).find(".td_warehouse_id").text().trim();
			   $("#input_select_warehouse").val(warehouse_id);
			   getRackCodeList(warehouse_id);
			}); 
			//双击行
			$("#table_warehouse_list tbody tr").dblclick(function() {  
				$("#input_warehouse_update").click();
			}); 
			//添加按钮
		    $("#input_warehouse_add").click(function(){
		    	$("#input_select_warehouse").val("");
		    	$("#win_warehouse").window("open");
		    });
		    
			//修改按钮
		    $("#input_warehouse_update").click(function(){
		    	var warehouse_id = $("#input_select_warehouse").val();
		    	if(warehouse_id == ""){
		    		$.messager.alert('提示','请选择要修改的仓库','info');
					return false;
		    	}
		    	var select_tr = $("#tr"+warehouse_id);
		    	$("#win_warehouse").window("open");
		    	$("#input_warehouse_name").textbox("setValue",(select_tr.find(".td_warehouse_name").text().trim())); 
		    	$("#input_effective_flag").prop("checked",select_tr.find(".td_effective_flag").text().trim() == "是");
		    });
		    
		    //删除按钮
		    $("#input_warehouse_del").click(function(){
		    	var warehouse_id = $("#input_select_warehouse").val();
		    	if(warehouse_id == ""){
		    		$.messager.alert('提示','请选择要删除的仓库','info');
					return false;
		    	}
		    	$.messager.confirm('确认','您确认想要删除所选仓库吗？',function(r){    
				    if (r){    
				        $.ajax({
							url:"<%=projectName%>/manage/warehouse/delWarehouse",
							type:"post",
							dataType:"json",
			                data:{"warehouseId":warehouse_id},
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
		    });
		});
		
		//获取仓库信息，返回json
		function getwarehouseInfo(){
			var warehouseInfo = {};
			warehouseInfo.id = $("#input_select_warehouse").val();
			warehouseInfo.name = $("#input_warehouse_name").val().trim();
			warehouseInfo.effectiveFlag = $("#input_effective_flag").prop("checked")?1:0;
			return warehouseInfo;
		}
		
		//校验仓库信息
		function validatewarehouseInput(warehouseInfo){
			var validation = {};
			validation.fail = false;
			if(warehouseInfo.name == ""){
				validation.fail = true;
				validation.msg = "名称不能为空!";
				return validation;
			}
			$.ajax({
				url:"<%=projectName%>/manage/warehouse/validateWarehouse",
				type:"post",
				async: false,
				dataType:"json",
                data:{"warehouseInfo":JSON.stringify(warehouseInfo)},
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
		
		//仓库保存
		function saveWarehouse(){
			var warehouseInfo = getwarehouseInfo();
			var validation = validatewarehouseInput(warehouseInfo);
			if(validation.fail){
				$.messager.alert('提示',validation.msg,'info');
				return false;
			}
			$.ajax({
				url:"<%=projectName%>/manage/warehouse/saveWarehouse",
				type:"post",
				dataType:"json",
                data:{"warehouseInfo":JSON.stringify(warehouseInfo)},
                success:function(result){
                	$.messager.alert('提示',result.msg,'info',function(){    
				        location.reload(); 
					});  
                },
                error:function(){
                }				
			});
		};
		
		//获取仓库对应的货位列表
		function getRackCodeList(warehouse_id){
			$("#iframe_rack_code_list",window.parent.document)
			.attr("src", "<%=projectName%>/manage/warehouse/getRackCodeList?warehouseId="+warehouse_id);
		}
		
	</script>
  </head>
  <body style="margin: 0px;">
  <input id="input_select_warehouse" type="text" style="display: none;"> 
  <div class="easyui-layout">
	<div style="height:30px;background-color:#e0ecff">
		<input id="input_warehouse_add" type="button" value="添加"/>
		<input id="input_warehouse_update" type="button" value="修改" />
		<input id="input_warehouse_del" type="button" value="删除"/>
	</div>
	<div>
		<table id="table_warehouse_list" class="table_list" cellspacing="0">
			<thead>
				<tr>
					<th style="display: none;">ID</th>
					<th>序号</th>
					<th>名称</th>
					<th>可用</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="warehouse" items="${warehouseList}" varStatus="status">  
				    <tr id="tr${warehouse.id}">
				    	<td style="display: none;" class="td_warehouse_id">${warehouse.id}</td>
				    	<td>${status.count}</td>
				    	<td class="td_warehouse_name">${warehouse.name}</td>
				    	<td class="td_effective_flag">
				    		<c:choose> 
							     <c:when test="${warehouse.effectiveFlag == 1}">是 </c:when>      
							     <c:otherwise>否</c:otherwise> 
							</c:choose>
				    	</td>
				    </tr>
				</c:forEach> 
			</tbody>
		</table>
	</div>
</div>

<div id="win_warehouse" class="easyui-window" title="仓库" style="width:300px;height:140px;"   
        data-options="iconCls:'icon-save',modal:true,closed:true">   
    <div style="margin-left:20px;margin-top: 12px;">
		仓库名称:<input id="input_warehouse_name" class="easyui-textbox" style="width:150px;height:30px">
		<input id="input_effective_flag" type="checkbox" checked="checked">可用
	</div>
    <div>
    	<div style="float: right;padding-right: 10px;padding-top: 10px;">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#win_warehouse').window('close');">取消</a>  
		</div>
    	<div style="float: right;padding-right: 10px;padding-top: 10px;" onclick="saveWarehouse();">
	    	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" >保存</a>  
    	</div>
    	
    </div>
</div> 
</body>
</html>

