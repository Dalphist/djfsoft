package pojo.sales;

public class SalesOrderDetailInfo {
	private Integer id;
	private Integer salesOrderId;
	private Integer productId;
	private Double quantity;
	private Double unitPrice;
	private Double cost;
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

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
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

	@Override
	public String toString() {
		return "SalesOrderDetailInfo [id=" + id + ", salesOrderId=" + salesOrderId + ", productId=" + productId
				+ ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", cost=" + cost + ", productCode="
				+ productCode + ", barCode=" + barCode + ", productName=" + productName + ", productUnit=" + productUnit
				+ "]";
	}

}
