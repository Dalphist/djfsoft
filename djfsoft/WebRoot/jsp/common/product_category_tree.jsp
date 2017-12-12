<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$("#product_category_tree").tree({      
	        url: "<%=projectName%>/productCategory/getProductCategoryTree",
	        //dnd: true,
	        onContextMenu: function(e, node){
				e.preventDefault();
				// 查找节点
				$('#product_category_tree').tree('select', node.target);
				$("#product_category_id").val(node.id);
				// 显示快捷菜单
				$('#mm').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			},
	        onClick: function(node){
	        	//复制到隐藏域
				$(".product_category_text").val(node.text);	
				$("#product_category_id").val(node.id);	
				//刷新商品列表
				$("#iframe_product_list").attr("src","<%=projectName%>/manage/product/list?categoryId="	+ node.id);
			}
		});
	})
	//添加分类事件
	function addCategory(){
		$("#win").window("open");
		$("#btn_category_save").attr("onclick","").attr("onclick","saveCategory()");
	}
	//添加分类
	function saveCategory(){
		var category = {};
		var categoryId = $("#product_category_id").val();
		var categoryName = $("#input_category_name").val();
		var categoryCode = $("#input_category_code").val();
		category.parentId = categoryId;
		category.categoryName = categoryName;
		category.categoryCode = categoryCode;
		$.ajax({
			url:"<%=projectName%>/productCategory/addProductCategory",
			type:"post",
			dataType:"json",
            data:{"categoryInfo":JSON.stringify(category)},
            success:function(result){
            	$.messager.alert('提示',result.msg,'info',function(){    
			        location.reload(); 
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
			        location.reload(); 
				}); 
            },
            error:function(){
            }
		})
	}
</script>
<div data-options="region:'west',split:true" title="类别" style="width:200px;">
	<div class="easyui-panel" style="padding:5px;height:100%;">
		<ul id="product_category_tree"></ul>
	</div>
	<input id="product_category_text" class="product_category_text" style="display: none;"/>
	<input id="product_category_id" style="display: none;"/>
	<div id="mm" class="easyui-menu" style="width:120px;">   
		<div onclick="addCategory();">新建</div>   
		<div onclick="operCode();">编辑</div>   
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
	    <div title="规格" style="overflow:auto;padding:20px;display:none;">   
	        tab2    
	    </div> 
    </div>  
    <div>
    	<div style="float: right;padding-right: 10px;padding-top: 10px;">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#win').window('close');">取消</a>  
		</div>
    	<div style="float: right;padding-right: 10px;padding-top: 10px;">
	    	<a id="btn_category_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>  
    	</div>
    	
    </div>
</div> 

