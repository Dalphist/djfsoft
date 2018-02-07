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
			$("#table_template_list tbody tr").click(function() {  
			   $(this).addClass("select_tr").siblings().removeClass("select_tr"); 
			   $(this).find(":checkbox").prop("checked",true);
			   $(this).siblings().find(":checkbox").prop("checked",false); 
			   var template_id = $(this).find(".td_template_id").text().trim();
			   $("#input_select_template").val(template_id);
			   getTemplateValues(template_id);
			}); 
			//双击行
			$("#table_template_list tbody tr").dblclick(function() {  
				$("#input_template_update").click();
			}); 
			//复选框  阻止事件冒泡
		    $("#table_template_list tbody .td_checkbox").on("click",function(event){
		    	event.stopPropagation();    
		    });
			//添加按钮
		    $("#input_template_add").click(function(){
		    	$("#input_select_template").val("");
		    	$("#win_template").window("open");
		    });
		    
			//修改按钮
		    $("#input_template_update").click(function(){
		    	var template_id = $("#input_select_template").val();
		    	if(template_id == ""){
		    		$.messager.alert('提示','请选择要修改的规格','info');
					return false;
		    	}
		    	var select_tr = $("#tr"+template_id);
		    	$("#win_template").window("open");
		    	$("#input_template_name").textbox("setValue",(select_tr.find(".td_template_name").text().trim())); 
		    	$("#input_effective_flag").prop("checked",select_tr.find(".td_effective_flag").text().trim() == "可用");
		    });
		    
		    //删除按钮
		    $("#input_template_del").click(function(){
		    	var template_id = $("#input_select_template").val();
		    	if(template_id == ""){
		    		$.messager.alert('提示','请选择要删除的规格','info');
					return false;
		    	}
		    	$.messager.confirm('确认','您确认想要删除所选规格吗？',function(r){    
				    if (r){    
				        $.ajax({
							url:"<%=projectName%>/manage/producttemplate/delProducttemplate",
							type:"post",
							dataType:"json",
			                data:{"templateId":template_id},
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
		function getTemplateInfo(){
			var templateInfo = {};
			templateInfo.id = $("#input_select_template").val();
			templateInfo.type = $("#select_template_type").val();
			templateInfo.name = $("#input_template_name").val().trim();
			templateInfo.effectiveFlag = $("#input_effective_flag").prop("checked")?1:0;
			return templateInfo;
		}
		
		//校验模板信息
		function validatetemplateInput(templateInfo){
			var validation = {};
			validation.fail = false;
			if(templateInfo.templateName == ""){
				validation.fail = true;
				validation.msg = "名称不能为空!";
				return validation;
			}
			$.ajax({
				url:"<%=projectName%>/manage/template/validateTemplate",
				type:"post",
				async: false,
				dataType:"json",
                data:{"templateStr":JSON.stringify(templateInfo)},
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
		
		//模板保存
		function saveTemplate(){
			var templateInfo = getTemplateInfo();
			var validation = validatetemplateInput(templateInfo);
			if(validation.fail){
				$.messager.alert('提示',validation.msg,'info');
				return false;
			}else{
				$.ajax({
					url:"<%=projectName%>/manage/template/saveTemplate/",
					type:"post",
					dataType:"json",
	                data:{"templateStr":JSON.stringify(templateInfo)},
	                success:function(result){
	                	$.messager.alert('提示',result.msg,'info',function(){    
					        location.reload(); 
						});  
	                },
	                error:function(){
	                }				
				});
			}
		};
		
		//获取规格对应的值列表
		function getTemplateValues(template_id){
			$("#iframe_template_detail",window.parent.document)
			.attr("src", "<%=projectName%>/manage/template/getDetail?templateId="+template_id);
		}
		
	</script>
  </head>
  <body style="margin: 0px;">
  <input id="input_select_template" type="text" style="display: none;"> 
  <div class="easyui-layout">
	<div style="height:30px;background-color:#e0ecff">
		<select id="select_template_type">
			<option value="1">采购模板</option>
			<option value="2">销售模板</option>
		</select>
		<input id="input_template_add" type="button" value="新增"/>
		<input id="input_template_saveAs" type="button" value="另存"/>
		<input id="input_template_update" type="button" value="修改" />
		<input id="input_template_del" type="button" value="删除"/>
	</div>
	<div>
		<table id="table_template_list" class="table_list" cellspacing="0">
			<thead>
				<tr>
					<th style="display: none;">ID</th>
					<th style="width: 40px;">序号</th>
					<th style="width: 100px;">模板类型</th>
					<th>模板名称</th>
					<th style="width: 50px;">状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="template" items="${templateList}" varStatus="status">  
				    <tr id="tr${template.id}">
				    	<td style="display: none;" class="td_template_id">${template.id}</td>
				    	<td>${status.count}</td>
				    	<td>${template.typeName}</td>
				    	<td class="td_template_name">${template.name}</td>
				    	<td class="td_effective_flag">
				    		<c:choose> 
							     <c:when test="${template.effectiveFlag == 1}">可用 </c:when>      
							     <c:otherwise>不可用</c:otherwise> 
							</c:choose>
				    	</td>
				    </tr>
				</c:forEach> 
			</tbody>
		</table>
	</div>
</div>

<div id="win_template" class="easyui-window" title="模板" style="width:300px;height:140px;"   
        data-options="iconCls:'icon-save',modal:true,closed:true">   
    <div style="margin-left:20px;margin-top:12px;">
		模板名称:<input id="input_template_name" class="easyui-textbox" style="width:150px;height:30px">
		<input id="input_effective_flag" type="checkbox" checked="checked">可用
	</div>
    <div>
    	<div style="float:right;padding-right:10px;padding-top:10px;">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#win_template').window('close');">取消</a>  
		</div>
    	<div style="float:right;padding-right:10px;padding-top:10px;" onclick="saveTemplate();">
	    	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>  
    	</div>
    </div>
</div> 
</body>
</html>

