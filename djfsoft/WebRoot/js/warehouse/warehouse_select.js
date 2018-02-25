/*
 *	仓库和货位号拼接select  
 */
$(function() {

	

});

function getWarehouseList(){
	$.ajax({
		url:"djfsoft/warehouse/warehouseSelect",
		type:"get",
        success:function(result){
        	var detailList = result.dataList;
        	$.each(detailList,function(i,detail){
        		var tr = "<tr>"
        				+ "<td>"+ (i+1) +"</td>"
        				+ "<td>"+ detail.purchaseOrderCode +"</td>"
        				+ "<td>"+ detail.productName +"</td>"
        				+ "<td>"+ detail.productCode +"</td>"
        				+ "<td>"+ detail.barCode +"</td>"
        				+ "<td>"+ detail.productUnit +"</td>"
        				+ "<td>"+ detail.normalQuantity +"</td>"
        				+ "<td>"+ detail.warehouseName +"</td>"
        				+ "<td>"+ detail.rackCode +"</td>"
        				+ "</tr>"
        	});
        }				
	});
	
}