<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		loadTree();
		getAllAttribute();
	});
	//加载树形
	function loadTree(){
		$("#product_category_tree").tree({      
	        url: "<%=projectName%>/productCategory/getProductCategoryTree",
	        //dnd: true,
	        onContextMenu: function(e, node){
				e.preventDefault();
				// 查找节点
				$("#product_category_tree").tree("select", node.target);
				$("#product_category_id").val(node.id);
				//查找父节点
				//var father = $("#product_category_tree").tree("getParent",node.target);
				$("#product_category_parent_id").val(node.id);	
				// 显示快捷菜单
				$('#mm').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
				$("#tt_category input").val("");
			},
	        onClick: function(node){
	        	//复制到隐藏域
				$(".product_category_text").val(node.text);	
				$("#product_category_id").val(node.id);	
				//刷新商品列表
				$("#iframe_product_list").attr("src","<%=projectName%>/manage/product/list?categoryId="	+ node.id);
			}
		});
	};
	
	//添加分类
	function addCategory(){
		$("#win").window("open");
		$("#tt_category").tabs("select",0);
		//新加分类，只需要父类Id
		$("#product_category_id").val("");
	}
	//编辑分类
	function editCategory(){
		$("#win").window("open");
		$("#tt_category").tabs("select",0);
		//赋值
		var category_id = $("#product_category_id").val();
		ReadCategoryInfo(category_id);
	}
	
	//获取分类基本信息,返回json数据
	function getCategoryInputInfo(){
		var category = {};
		var categoryId = $("#product_category_id").val();
		var parentId = $("#product_category_parent_id").val();
		var categoryName = $("#input_category_name").val();
		var categoryCode = $("#input_category_code").val();
		category.id = categoryId;
		category.parentId = parentId;
		category.categoryName = categoryName;
		category.categoryCode = categoryCode;
		return category;
	}
	//获取分类所选规格
	function getCategoryAttribute(){
		var inputs = $("#table_all_attribute").find(":checkbox:checked");
		var ids_str = "";
		var i = 0;
		inputs.each(function(){
			if(i > 0){
				ids_str += ",";
			}
			var attribute_id = $(this).parents("tr").find(".td_attribute_id").text().trim();
			ids_str += attribute_id;
			i++;
		});
		return ids_str;
	}
	
	//校验分类信息是否重复
	function validateCategory(categoryInfo){
		var validation = {};
		validation.fail = false;
		if(categoryInfo.categoryName == ""){
			validation.fail = true;
			validation.msg = "名称不能为空!";
			return validation;
		}
		$.ajax({
			url:"<%=projectName%>/productCategory/validateCategory",
			type:"post",
			async: false,
			dataType:"json",
            data:{"categoryInfo":JSON.stringify(categoryInfo)},
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
	
	//保存分类
	function saveCategory(){
		$("#win").window("close");
		var categoryInfo = getCategoryInputInfo();
		var ids_str = getCategoryAttribute();
		var validation = validateCategory(categoryInfo);
		if(validation.fail){
			$.messager.alert('提示',validation.msg,'info');
			return false;
		}
		$.ajax({
			url:"<%=projectName%>/productCategory/saveProductCategory",
			type:"post",
			dataType:"json",
            data:{"categoryInfo":JSON.stringify(categoryInfo),"attributeIdStr":ids_str},
            success:function(result){
            	$.messager.alert('提示',result.msg,'info',function(){    
			        $('#product_category_tree').tree('reload');
				}); 
            },
            error:function(){
            }
		})
	}
	
	//删除分类
	function delCategory(){
		var categoryId = $("#product_category_id").val();
		$.ajax({
			url:"<%=projectName%>/productCategory/delProductCategory",
			type:"post",
			dataType:"json",
            data:{"categoryId":categoryId},
            success:function(result){
            	$.messager.alert('提示',result.msg,'info',function(){    
			        $('#product_category_tree').tree('reload');
				}); 
            },
            error:function(){
            }
		})
	}
	
	//获取所有规格（随页面加载）
	function getAllAttribute(){
		$.ajax({
			url:"<%=projectName%>/manage/productAttribute/getAllAttribute",
			type:"get",
			dataType:"json",
            success:function(result){
            	var allAttributes = result.dataList; 
            	$.each(allAttributes,function(i,attribute){
     				var tr = 
     				'<tr id="tr_'+attribute.id+'">'
	      				+'<td hidden="true" class="td_attribute_id">'+attribute.id+'</td>'
	      				+'<td><input type="checkbox"/></td>'
	      				+'<td>'+attribute.attributeName+'</td>'
      				+'</tr>';
     				$("#table_all_attribute").append(tr);
     			})
            },
            error:function(){
            	alert("获取所有规格出错！");
            }				
		});
	}
	//读取所选分类的信息并赋值在对应位置
	function ReadCategoryInfo(category_id){
		//基本信息
		$.ajax({
			url:"<%=projectName%>/productCategory/getProductCategory",
			type:"get",
			dataType:"json",
            data:{"categoryId":category_id},
            success:function(result){
            	var category = result.data;
            	$("#input_category_name").textbox("setValue",category.categoryName);
            	$("#input_category_code").textbox("setValue",category.categoryCode);
            }				
		});
		//规格
		$("#table_all_attribute").find(":checkbox").prop("checked",false);
		$.ajax({
			url:"<%=projectName%>/manage/productAttribute/getCategoryAttribute",
			type:"get",
			data:{"categoryId":category_id},
			dataType:"json",
            success:function(result){
            	var allAttributes = result.dataList; 
            	$.each(allAttributes,function(i,attribute){
            		var attribute_id = attribute.id;
            		$("#tr_"+attribute_id).find(":checkbox").prop("checked",true);
     			})
            },
            error:function(){
            	alert("获取分类所属规格出错！");
            }				
		});
	}
</script>
<div data-options="region:'west',split:true" title="类别" style="width:200px;">
	<div class="easyui-panel" style="padding:5px;height:100%;">
		<ul id="product_category_tree"></ul>
	</div>
	<input id="product_category_text" class="product_category_text" style="display: none;"/>
	<input id="product_category_id" style="display: none;"/>
	<input id="product_category_parent_id" style="display: none;"/>
	<div id="mm" class="easyui-menu" style="width:120px;">   
		<div onclick="addCategory();">新建</div>   
		<div onclick="editCategory();">编辑</div>   
		<div onclick="delCategory();">删除</div>   
	</div>
</div>
<div id="win" class="easyui-window" title="类别" style="width:300px;height:300px;"   
        data-options="iconCls:'icon-save',modal:true,closed:true">   
    <div id="tt_category" class="easyui-tabs" style="height:85%;">   
	    <div title="基本信息" style="padding:20px;display:none;">   
	        <div style="margin-bottom:20px">
				<div style="margin-bottom:3px">分类名称:</div>
				<input id="input_category_name" class="easyui-textbox" data-options="prompt:'选中分类的子分类名称...'" style="width:100%;height:30px">
			</div>
			<div style="margin-bottom:20px">
				<div style="margin-bottom:3px">编码:</div>
				<input id="input_category_code" class="easyui-textbox" data-options="prompt:'用于拼接商品编码...'" style="width:100%;height:30px">
			</div>    
	    </div>   
	    <div title="规格" style="overflow:auto;display:none;">   
	    	<div style="width:100%;height:185px;">   
			    <table id="table_all_attribute"></table>
			</div> 
	    </div> 
    </div>  
    <div>
    	<div style="float: right;padding-right: 10px;padding-top: 10px;" onclick="$('#win').window('close');">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>  
		</div>
    	<div style="float: right;padding-right: 10px;padding-top: 10px;" onclick="saveCategory();">
	    	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>  
    	</div>
    </div>
</div> 

