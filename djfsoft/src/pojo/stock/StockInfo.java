package pojo.stock;

public class StockInfo {
	private Integer id;
	private Integer productId;
	private Integer warehouseId;
	private Double normalQuantity;
	private Double defectQuantity;
	private Double uselessQuantity;
	private String description;
	
	//info
	private Double beingOutQuantity;
	private Double beingInQuantity;
	private Double stockWarn;
	private String productCode;
	private String barCode;
	private String productName;
	private String productUnit;
	private String warehouseName;
	
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public Double getBeingOutQuantity() {
		return beingOutQuantity;
	}
	public void setBeingOutQuantity(Double beingOutQuantity) {
		this.beingOutQuantity = beingOutQuantity;
	}
	public Double getBeingInQuantity() {
		return beingInQuantity;
	}
	public void setBeingInQuantity(Double beingInQuantity) {
		this.beingInQuantity = beingInQuantity;
	}
	public Double getStockWarn() {
		return stockWarn;
	}
	public void setStockWarn(Double stockWarn) {
		this.stockWarn = stockWarn;
	}
	
}
