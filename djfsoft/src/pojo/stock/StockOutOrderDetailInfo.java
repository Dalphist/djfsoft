package pojo.stock;

public class StockOutOrderDetailInfo {
	
	private Integer id;
	private Integer stockOutOrderId;
	private Integer salesOrderId;
	private Integer productId;
	private Integer warehouseId;
	private Double normalQuantity;
	private Double defectQuantity;
	private Double uselessQuantity;
	private Double rackCode;
	
	//info
	private String warehouseName;
	private String productName;
	private String productUnit;
	private String productCode;
	private String barCode;
	private String salesOrderCode;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStockOutOrderId() {
		return stockOutOrderId;
	}
	public void setStockOutOrderId(Integer stockOutOrderId) {
		this.stockOutOrderId = stockOutOrderId;
	}
	public Integer getSalesOrderId() {
		return salesOrderId;
	}
	public void setSalesOrderId(Integer salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Double getNormalQuantity() {
		return normalQuantity;
	}
	public void setNormalQuantity(Double normalQuantity) {
		this.normalQuantity = normalQuantity;
	}
	public Double getDefectQuantity() {
		return defectQuantity;
	}
	public void setDefectQuantity(Double defectQuantity) {
		this.defectQuantity = defectQuantity;
	}
	public Double getUselessQuantity() {
		return uselessQuantity;
	}
	public void setUselessQuantity(Double uselessQuantity) {
		this.uselessQuantity = uselessQuantity;
	}
	public Double getRackCode() {
		return rackCode;
	}
	public void setRackCode(Double rackCode) {
		this.rackCode = rackCode;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getSalesOrderCode() {
		return salesOrderCode;
	}
	public void setSalesOrderCode(String salesOrderCode) {
		this.salesOrderCode = salesOrderCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
