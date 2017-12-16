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
			$("#table_attribute_list tbody tr").click(function() {  
			   $(this).addClass("select_tr").siblings().removeClass("select_tr"); 
			   $(this).find(":checkbox").prop("checked",true);
			   $(this).siblings().find(":checkbox").prop("checked",false); 
			   var attribute_id = $(this).find(".td_attribute_id").text().trim();
			   $("#input_select_attribute").val(attribute_id);
			   getAttributeValues(attribute_id);
			}); 
			//双击行
			$("#table_attribute_list tbody tr").dblclick(function() {  
				$("#input_attribute_update").click();
			}); 
			//复选框  阻止事件冒泡
		    $("#table_attribute_list tbody .td_checkbox").on("click",function(event){
		    	event.stopPropagation();    
		    });
			//添加按钮
		    $("#input_attribute_add").click(function(){
		    	$("#input_select_attribute").val("");
		    	$("#win_attribute").window("open");
		    });
		    
			//修改按钮
		    $("#input_attribute_update").click(function(){
		    	var attribute_id = $("#input_select_attribute").val();
		    	if(attribute_id == ""){
		    		$.messager.alert('提示','请选择要修改的规格','info');
					return false;
		    	}
		    	var select_tr = $("#tr"+attribute_id);
		    	$("#win_attribute").window("open");
		    	$("#input_attribute_name").textbox("setValue",(select_tr.find(".td_attribute_name").text().trim())); 
		    	$("#input_effective_flag").prop("checked",select_tr.find(".td_effective_flag").text().trim() == "是");
		    });
		    
		    //删除按钮
		    $("#input_attribute_del").click(function(){
		    	var attribute_id = $("#input_select_attribute").val();
		    	if(attribute_id == ""){
		    		$.messager.alert('提示','请选择要删除的规格','info');
					return false;
		    	}
		    	$.messager.confirm('确认','您确认想要删除所选规格吗？',function(r){    
				    if (r){    
				        $.ajax({
							url:"<%=projectName%>/manage/productAttribute/delProductAttribute",
							type:"post",
							dataType:"json",
			                data:{"attributeId":attribute_id},
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
		
		//获取规格信息，返回json
		function getAttributeInfo(){
			var attributeInfo = {};
			attributeInfo.id = $("#input_select_attribute").val();
			attributeInfo.attributeName = $("#input_attribute_name").val().trim();
			attributeInfo.effectiveFlag = $("#input_effective_flag").prop("checked")?1:0;
			return attributeInfo;
		}
		
		//校验规格信息
		function validateAttributeInput(attributeInfo){
			var validation = {};
			validation.fail = false;
			if(attributeInfo.attributeName == ""){
				validation.fail = true;
				validation.msg = "名称不能为空!";
				return validation;
			}
			$.ajax({
				url:"<%=projectName%>/manage/productAttribute/validateAttribute",
				type:"post",
				async: false,
				dataType:"json",
                data:{"attributeInfo":JSON.stringify(attributeInfo)},
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
			var attributeInfo = getAttributeInfo();
			var validation = validateAttributeInput(attributeInfo);
			if(validation.fail){
				$.messager.alert('提示',validation.msg,'info');
				return false;
			}
			$.ajax({
				url:"<%=projectName%>/manage/productAttribute/saveAttribute",
				type:"post",
				dataType:"json",
                data:{"attributeInfo":JSON.stringify(attributeInfo)},
                success:function(result){
                	$.messager.alert('提示',result.msg,'info',function(){    
				        location.reload(); 
					});  
                },
                error:function(){
                }				
			});
		};
		
		//获取规格对应的值列表
		function getAttributeValues(attribute_id){
			$.ajax({
				url:"<%=projectName%>/manage/productAttribute/getValuesByAttributeId",
				type:"post",
				dataType:"json",
                data:{"attribute_id":attribute_id},
                success:function(result){
                	$.messager.alert('提示',result.msg,'info',function(){    
				        location.reload(); 
					});  
                },
                error:function(){
                }				
			});
		}
		
	</script>
  </head>
  <body style="margin: 0px;">
  <input id="input_select_attribute" type="text" style="display: none;"> 
  <div class="easyui-layout">
	<div style="height:30px;background-color:#e0ecff">
		<input id="input_attribute_add" type="button" value="添加"/>
		<input id="input_attribute_update" type="button" value="修改" />
		<input id="input_attribute_del" type="button" value="删除"/>
	</div>
	<div>
		<table id="table_attribute_list" class="table_list" cellspacing="0">
			<thead>
				<tr>
					<th style="display: none;">ID</th>
					<th>序号</th>
					<th>名称</th>
					<th>可用</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="attribute" items="${productAttributeList}" varStatus="status">  
				    <tr id="tr${attribute.id}">
				    	<td style="display: none;" class="td_attribute_id">${attribute.id}</td>
				    	<td>${status.count}</td>
				    	<td class="td_attribute_name">${attribute.attributeName}</td>
				    	<td class="td_effective_flag">
				    		<c:choose> 
							     <c:when test="${attribute.effectiveFlag == 1}">是 </c:when>      
							     <c:otherwise>否</c:otherwise> 
							</c:choose>
				    	</td>
				    </tr>
				</c:forEach> 
			</tbody>
		</table>
	</div>
</div>

<div id="win_attribute" class="easyui-window" title="规格" style="width:300px;height:140px;"   
        data-options="iconCls:'icon-save',modal:true,closed:true">   
    <div style="margin-left:20px;margin-top: 12px;">
		规格名称:<input id="input_attribute_name" class="easyui-textbox" style="width:150px;height:30px">
		<input id="input_effective_flag" type="checkbox" checked="checked">可用
	</div>
    <div>
    	<div style="float: right;padding-right: 10px;padding-top: 10px;">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#win_attribute').window('close');">取消</a>  
		</div>
    	<div style="float: right;padding-right: 10px;padding-top: 10px;" onclick="savettribute();">
	    	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" >保存</a>  
    	</div>
    	
    </div>
</div> 
</body>
</html>

