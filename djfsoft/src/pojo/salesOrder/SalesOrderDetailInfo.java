package pojo.salesOrder;

public class SalesOrderDetailInfo {
	private Integer id;
	private Integer purchase_order_id;
	private Integer product_id;
	private Double quantity;
	private Double unit_price;
	private Double cost;
	//info
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
	public Integer getPurchase_order_id() {
		return purchase_order_id;
	}
	public void setPurchase_order_id(Integer purchase_order_id) {
		this.purchase_order_id = purchase_order_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
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
		return "PurchaseOrderDetailInfo [id=" + id + ", purchase_order_id=" + purchase_order_id + ", product_id="
				+ product_id + ", quantity=" + quantity + ", unit_price=" + unit_price + ", cost=" + cost
				+ ", productCode=" + productCode + ", barCode=" + barCode + ", productName=" + productName
				+ ", productUnit=" + productUnit + "]";
	}

}
