package pojo.sales;

public class SalesStockOutOrderDetailInfo {
	private Integer id;
	private Integer stockOutOrderId;
	private Integer productId;
	private Double quantity;
	private Integer warehouse_id;
	private String rack_code;
	// info
	private String productCode;
	private String barCode;
	private String productName;
	private String productUnit;
	
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
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Integer getWarehouse_id() {
		return warehouse_id;
	}
	public void setWarehouse_id(Integer warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	public String getRack_code() {
		return rack_code;
	}
	public void setRack_code(String rack_code) {
		this.rack_code = rack_code;
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

	
}
