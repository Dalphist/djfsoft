<%@ page language="java" import="java.util.*,djfsoft.pojo.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$("#layout_addOrder").layout("resize",{  
	    width:"100%",  
	    height:"100%" 
	});  
	getNewOrderCode();
	//全选/取消全选
	$("#checkAll").on("click",function(event){
	   	if($("#checkAll").prop("checked")){
	   		$("#table_order_detail tbody :checkbox").prop("checked", true);
	   	}else{
	   		$("#table_order_detail tbody :checkbox").prop("checked", false);
	   	}
	});
	//单击行变色及获取订单详情
	$("#table_order_detail tbody").on("click","tr",function(){
	   $(this).addClass("select_tr").siblings().removeClass("select_tr"); 
	   var order_id = $(this).find(".td_order_id").text().trim();
	   $("#input_order_id").val(order_id);
	});
	//*************数值修改时联动修改***************
	$("#table_order_detail tbody").on("blur",".quantity",function(){
		calByQuantity($(this).parents("tr"));
		calTableCost();
	}); 
	$("#table_order_detail tbody").on("blur",".unit_price",function(){
	   	calByUnitPrice($(this).parents("tr"));
	   	calTableCost();
	}); 
	$("#table_order_detail tbody").on("blur",".cost",function(){
	   	calByCost($(this).parents("tr"));
	   	calTableCost();
	}); 
	$("#input_transportFare").on("blur",function(){
	   	calTableCost();
	}); 
	$("#input_extraPrice").on("blur",function(){
	   	calTableCost();
	}); 
	
	//获取所有采购模板
	
})
function openWinProduct(){
	$("#win_product").window("open");
}
//单价变动的计算
function calByUnitPrice(tr){
	var unit_price = tr.find(".unit_price").val().trim();
   	var quantity = tr.find(".quantity").val().trim();
   	var cost = tr.find(".cost").val().trim();
   	if(unit_price != "" && quantity != ""){
   		var cost_new = unit_price*quantity;
    	tr.find(".cost").val(cost_new.toFixed(2));
    	return;
   	}
   	if((quantity == "" || quantity == 0) && (cost != "" && cost != 0)){
   		var quantity_new = cost/unit_price;
    	tr.find(".quantity").val(quantity_new.toFixed(2));
    	return;
   	}
}
//数量变动的计算
function calByQuantity(tr){
	var unit_price = tr.find(".unit_price").val().trim();
   	var quantity = tr.find(".quantity").val().trim();
   	var cost = tr.find(".cost").val().trim();
   	if(unit_price != "" && quantity != ""){
   		var cost_new = unit_price*quantity;
    	tr.find(".cost").val(cost_new.toFixed(2));
    	return;
   	}
   	if((unit_price == "" || unit_price == 0) && (cost != "" && cost != 0)){
   		var unit_price_new = cost/quantity;
    	tr.find(".unit_price").val(unit_price_new.toFixed(2));
    	return;
   	}
}
//总价变动的计算
function calByCost(tr){
	var unit_price = tr.find(".unit_price").val().trim();
   	var quantity = tr.find(".quantity").val().trim();
   	var cost = tr.find(".cost").val().trim();
   	if(cost != "" && quantity != "" && quantity!= 0){
   		var unit_price_new = cost/quantity;
    	tr.find(".unit_price").val(unit_price_new.toFixed(2));
    	return;
   	}
   	if(cost != "" && (quantity == "" || quantity == 0) && (unit_price != 0 && unit_price != "")){
   		var quantity_new = cost/unit_price;
    	tr.find(".quantity").val(quantity_new.toFixed(2));
    	return;
   	}
}
//计算总价及总数量
function calTableCost(){
	var trs = $("#table_order_detail tbody tr");
	var total_quantity = 0;
	var total_cost = 0;
	trs.each(function(){
		var quantity = $(this).find(".quantity").val();
		var cost = $(this).find(".cost").val();
		total_quantity += quantity *1;
		total_cost += cost *1;
	});
	$("#total_quantity").html(total_quantity.toFixed(2));
	$("#total_cost").html(total_cost.toFixed(2));
	$("#input_productPrice").val(total_cost.toFixed(2));
	var transportFare = $("#input_transportFare").val();
	var extraPrice = $("#input_extraPrice").val();
	var totalPrice = total_cost*1 + transportFare*1 - extraPrice;
	$("#input_totalPrice").val(totalPrice.toFixed(2));
}
//保存订单
function saveOrder(){
	var productListInfo = getProductList();
	var basicInfo = getBasicInfo();
	if(!productListInfo){	//如果货品信息填写不全，返回false
		return false;
	}
	$.ajax({
		url:"<%=projectName%>/sales/salesOrder/saveSalesOrder",
		type:"post",
        data:{"productListInfo":JSON.stringify(productListInfo),"basicInfo":JSON.stringify(basicInfo)},
        success:function(result){
        	$.messager.alert('提示',result.msg,'info',function(){    
		        location.reload(); 
			});
        }				
	});
}
//获取基本信息
function getBasicInfo(){
	var basicInfo = {};
	basicInfo.orderCode = $("#td_order_code").text().trim();
	basicInfo.dealDate = $("#input_dealDate").val();
	basicInfo.taobaoCode = $("#input_taobaoCode").val();
	basicInfo.customerName = $("#input_customerName").val();
	basicInfo.customerTel = $("#input_customerTel").val();
	basicInfo.customerPostcode = $("#input_customerPostcode").val();
	basicInfo.customerNotes = $("#input_customerNotes").val();
	basicInfo.customerAddress = $("#input_customerAddress").val();
	basicInfo.productPrice = $("#input_productPrice").val();
	basicInfo.transportFare = $("#input_transportFare").val();
	basicInfo.extraPrice = $("#input_extraPrice").val();
	basicInfo.totalPrice = $("#input_totalPrice").val();
	basicInfo.customerDistrict1Id = $("#district1").val();
	basicInfo.customerDistrict2Id = $("#district2").val();
	basicInfo.customerDistrict3Id = $("#district3").val();
	return basicInfo;
}
//获取货品信息
function getProductList(){
	var productListInfo = [];
	var trs = $("#table_order_detail tbody").find("tr");
	var b = true;
	trs.each(function(){
		var productInfo = {};
		var product_id = $(this).find(".td_product_id").text().trim();
		var unit_price = $(this).find(".unit_price").val().trim();
		var quantity = $(this).find(".quantity").val().trim();
		var cost = $(this).find(".cost").val().trim();
		if(unit_price == 0 || unit_price == "" || quantity == 0 || quantity == "" || cost == 0 || cost == "" ){
			b = false;
			return;
		}
		productInfo.productId = product_id;
		productInfo.unitPrice = unit_price;
		productInfo.quantity = quantity;
		productInfo.cost = cost;
		productListInfo.push(productInfo);
	});
	if(!b){
		alert("商品数量/价格 不能为0");
		return false;
	}
	return productListInfo;
}
//删除货品
function delProduct(){
	$("#table_order_detail tbody").find(".select_tr").remove();
	//重排序号
	var trs = $("#table_order_detail tbody").find("tr");
	trs.each(function(i,tr){
		$(this).find("td").eq(1).html(i*1+1);
	});
}
function getNewOrderCode(){
	$.ajax({
		url:"<%=projectName%>/sales/salesOrder/getNewOrderCode",
		type:"get",
        success:function(result){
        	var code = result.data;
        	$("#td_order_code").text(code);
        }				
	});
}
function reset(){
	$("#layout_addOrder input").val("");
	$("#table_order_detail tbody").empty();
	calTableCost();
	getNewOrderCode();
}
//获取采购模板列表
function getPurchaseTemplate(){
	$.ajax({
		url:"<%=projectName%>/manage/template/getTemplateByType",
		data:{"type":1},
		type:"get",
        success:function(result){
        	var templateList = result.dataList;
        	
        }				
	});
}
</script>
 <div id="layout_addOrder" class="easyui-layout">   
    <div data-options="region:'north',title:'基本信息'" style="height:165px;">
    	<table id="table_basicInfo1">
    		<tr>
    			<td>业务员：</td>
    			<td></td>
    			<td>采购单号：</td>
    			<td id="td_order_code"></td>
    			<td>供货方：</td>
    			<td>
    				<select></select>
    			</td>
    		</tr>
    	</table>
    	<hr style="height:1px;border:none;border-top:1px solid #1675a1;margin-left: 10px;margin-right: 10px;margin-top: 0px;" />
    </div>   
    <div data-options="region:'south',title:'结算信息'" style="height:130px;">
    	<table id="table_accountsInfo" >
    		<tr>
    			<td style="width:80px;">商品总价：</td>
    			<td style="width:100px;"><input id="input_productPrice" class="number_text" disabled="disabled" style="width:100%" value="0.0"></td>
    			<td style="width:80px;">运费：</td>
    			<td style="width:100px;"><input id="input_transportFare" class="number_text" style="width:100%" value="0.0"></td>
    			<td style="width:80px;">其他金额：</td>
    			<td style="width:100px;"><input id="input_extraPrice" class="number_text" style="width:100%" value="0.0"></td>
    			<td style="width:80px;">实付金额：</td>
    			<td style="width:100px;"><input id="input_totalPrice" class="number_text" style="width:100%"></td>
    			<td></td>
    		</tr>
    	</table>
    	<div style="width: 220px;margin: 0 auto;margin-top: 35px;">
    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveOrder();">保存为待审订单</a>
    		&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="reset();">重置订单</a>
    	</div>
    </div>   
    <div data-options="region:'center',title:'货品信息'">
    	<div style="height:30px;background-color:#e0ecff;padding-top: 2px;">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="openWinProduct();">选择商品</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-filter'" onclick="chooseModel();">套用模板</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="delProduct();">删除商品</a>
		</div>
		<div>
			<table id="table_order_detail" class="table_list" cellspacing="0">
				<thead>
					<tr style="height: 30px;">
						<th style="width:30px;"><input id="checkAll" type="checkbox"></th>
						<th style="width:30px;">序号</th>
						<th style="width: 200px;">商品编号</th>
						<th style="width: 200px;">商品条形码</th>
						<th style="width: 300px;">商品名称</th>
						<th>单位</th>
						<th style="width: 127px;">单价</th>
						<th style="width: 127px;">数量</th>
						<th style="width: 127px;">总价</th>
					</tr>
					<tr style="height: 30px;">
						<td colspan="7"></td>
						<td style="font-weight:bold; text-align: right;!" id="total_quantity">0</td>
						<td style="font-weight:bold; text-align: right;!" id="total_cost">0</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="detail" items="${detailList}" varStatus="status">  
					    <tr>
					    	<td style="width:30px;"><input type="checkbox"></td>
					    	<td>${status.count}</td>
					    	<td>${detail.productCode}</td>
					    	<td>${detail.barCode}</td>
					    	<td>${detail.productName}</td>
					    	<td>${detail.productUnit}</td>
					    	<td>${detail.unit_price}</td>
					    	<td>${detail.quantity}</td>
					    	<td>${detail.cost}</td>
					    </tr>
					</c:forEach> 
				</tbody>
			</table>
		</div>
    </div>   
</div> 
<div id="win_product" class="easyui-window" title="采购模板" style="width:220px;height:110px" data-options="iconCls:'icon-save',modal:true">   
	<div style="padding-top: 10px;padding-left: 30px;">
	    <select id="select_template">
	    	<option>aa</option>
	    	<option>bb</option>
	    </select> 
	</div>
    <div style="margin-top: 10px;">
    	<div style="float: right;padding-right: 10px;padding-top: 5px;" onclick="$('#win_product').window('close');">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>  
		</div>
    	<div style="float: right;padding-right: 10px;padding-top: 5px;" onclick="importProduct();">
	    	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定</a>  
    	</div>
    </div>
</div> 
<style type="text/css">
	#table_basicInfo td{
		text-align: right;
	}
	#table_accountsInfo td{
		text-align: right;
	}
	#table_basicInfo1 td{
		width:80px;
		text-align: right;
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
	.select_tr{
		background-color: #c4e8f5;
	}
	select{
		font-size: 12px;
	    border: 1px solid #95B8E7;
	    padding: 4px;
	    border-radius: 5px 5px 5px 5px;
	    padding-top: 0px;
	    padding-bottom: 0px;
	    height: 23px;
	    line-height: 23px;
	    width: 127px;
	}
</style>
