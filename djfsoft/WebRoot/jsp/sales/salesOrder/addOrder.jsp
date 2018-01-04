<%@ page language="java" import="java.util.*,djfsoft.pojo.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$("#layout_addOrder").layout("resize",{  
	    width:"100%",  
	    height:"100%" 
	});  
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
	//*********************************************
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
	if(!productListInfo){	//如果货品信息填写不全，返回false
		return false;
	}
	alert("1");
}
//获取基本信息
function getBasicInfo(){
	var basicInfo = {};
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
</script>
 <div id="layout_addOrder" class="easyui-layout">   
    <div data-options="region:'north',title:'基本信息'" style="height:165px;">
    	<table id="table_basicInfo1">
    		<tr>
    			<td>业务员：</td>
    			<td></td>
    			<td>成交时间：</td>
    			<td><input id="input_dealDate" class="easyui-datetimebox" name="birthday" data-options="required:true,showSeconds:false"  style="width:150px"> </td>
    			<td>淘宝订单号：</td>
    			<td colspan="2" style="width: 160px;"><input id="input_taobaoCode" class="easyui-textbox" style="width:100%"></td>
    			<td></td>
    		</tr>
    	</table>
    	<hr style="height:1px;border:none;border-top:1px solid #1675a1;margin-left: 10px;margin-right: 10px;margin-top: 0px;" />
    	<table id="table_basicInfo" >
    		<tr>
    			<td style="width:80px;">客户网名：</td>
    			<td style="width:100px;"><input id="input_customerWebName" class="easyui-textbox" style="width:100%"></td>
    			<td style="width:50px;">姓名：</td>
    			<td style="width:100px;"><input id="input_customerName" class="easyui-textbox" style="width:100%"></td>
    			<td style="width:50px;">电话：</td>
    			<td style="width:100px;"><input id="input_customerTel" class="easyui-textbox" style="width:100%"></td>
    			<td style="width:80px;">邮编：</td>
    			<td style="width:100px;"><input id="input_customerPostcode" class="easyui-textbox" style="width:100%"></td>
    			<td style="width:100px;"></td>
    			<td></td>
    		</tr>
    		<tr>
    			<td>省份：</td>
    			<td><input id="" class="easyui-combobox" name="dept" style="width:100%"/></td>
    			<td>区市：</td>
    			<td><input id="" class="easyui-combobox" name="dept" style="width:100%"/></td>
    			<td>区县：</td>
    			<td><input id="" class="easyui-combobox" name="dept" style="width:100%"/></td>
    			<td>客户备注：</td>
    			<td colspan="2"><input id="input_customerNotes" class="easyui-textbox" style="width:100%"></td>
    		</tr>
    		<tr>
    			<td>地址：</td>
    			<td colspan="5"><input id="input_customerAddress" class="easyui-textbox" style="width:100%"></td>
    		</tr>
    	</table>
    </div>   
    <div data-options="region:'south',title:'结算信息'" style="height:130px;">
    	<table id="table_accountsInfo" >
    		<tr>
    			<td style="width:80px;">商品总价：</td>
    			<td style="width:100px;"><input id="input_productPrice" class="number_text" disabled="disabled" style="width:100%" value="0.0"></td>
    			<td style="width:80px;">邮费：</td>
    			<td style="width:100px;"><input id="input_transportFare" class="number_text" style="width:100%" value="0.0"></td>
    			<td style="width:80px;">优惠金额：</td>
    			<td style="width:100px;"><input id="input_extraPrice" class="number_text" style="width:100%" value="0.0"></td>
    			<td style="width:80px;">实收金额：</td>
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
</style>
