package pojo.stock;

public class StockInOrderDetailInfo {
	
	private Integer id;
	private Integer stockInOrderId;
	private Integer purchaseOrderId;
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
	private String purchaseOrderCode;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getStockInOrderId() {
		return stockInOrderId;
	}
	public void setStockInOrderId(Integer stockInOrderId) {
		this.stockInOrderId = stockInOrderId;
	}
	public Integer getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public void setPurchaseOrderId(Integer purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	public String getPurchaseOrderCode() {
		return purchaseOrderCode;
	}
	public void setPurchaseOrderCode(String purchaseOrderCode) {
		this.purchaseOrderCode = purchaseOrderCode;
	}
	
}
