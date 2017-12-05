<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$("ul").mousedown(function(e){
			if(3 == e.which){
				$("#mm").show();
			}
			return false;
		}).on("contextmenu",function(){		//屏蔽页面右键事件
			return false;
		});
			
		$("#product_category_tree").tree({      
	        url: "<%=projectName%>/productCategory/getProductCategoryTree",      
	        loadFilter: function(data){      
	            if (data.d){      
	                return data.d;      
	            } else {      
	                return data;      
	            }     
	        }      
	   });   
	})
</script>
<div data-options="region:'west',split:true" title="类别" style="width:200px;">
	<div class="easyui-panel" style="padding:5px;height:100%;">
		<ul id="product_category_tree"></ul>
	</div>
	<div id="mm" class="easyui-menu" style="width:120px;">   
		<div>New</div>   
		<div>   
			<span>Open</span>   
			<div style="width:150px;">   
				<div><b>Word</b></div>   
				<div>Excel</div>   
				<div>PowerPoint</div>   
			</div>   
		</div>   
		<div data-options="iconCls:'icon-save'">Save</div>   
		<div class="menu-sep"></div>   
		<div>Exit</div>   
	</div>
</div>

