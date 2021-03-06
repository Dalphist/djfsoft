package pojo.manage;

import java.util.Date;
import java.util.List;


public class ProductInfo {
	private Integer id;
	private String productCode;
	private String barCode;
	private String productName;
	private String productShortName;
	private Integer categoryId;
	private String categoryName;
	private Double normalPurchasePrice;
	private Double cost;
	private Double lastPurchasePrice;
	private Double salePrice;
	private Double stockWarn;
	private String productUnit;
	private String productPlace;
	private Date gmtCreate;
	private Date gmtModified;
	private Integer effectiveFlag;
	private List<ProductAttributeInfo> attributeList; 
	private Double stock;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getProductShortName() {
		return productShortName;
	}

	public void setProductShortName(String productShortName) {
		this.productShortName = productShortName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Double getNormalPurchasePrice() {
		return normalPurchasePrice;
	}

	public void setNormalPurchasePrice(Double normalPurchasePrice) {
		this.normalPurchasePrice = normalPurchasePrice;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getLastPurchasePrice() {
		return lastPurchasePrice;
	}

	public void setLastPurchasePrice(Double lastPurchasePrice) {
		this.lastPurchasePrice = lastPurchasePrice;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getStockWarn() {
		return stockWarn;
	}

	public void setStockWarn(Double stockWarn) {
		this.stockWarn = stockWarn;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	public String getProductPlace() {
		return productPlace;
	}

	public void setProductPlace(String productPlace) {
		this.productPlace = productPlace;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Integer getEffectiveFlag() {
		return effectiveFlag;
	}

	public void setEffectiveFlag(Integer effectiveFlag) {
		this.effectiveFlag = effectiveFlag;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public List<ProductAttributeInfo> getAttributeList() {
		return attributeList;
	}

	public void setAttributeList(List<ProductAttributeInfo> attributeList) {
		this.attributeList = attributeList;
	}
	
	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ProductInfo [id=" + id + ", productCode=" + productCode + ", barCode=" + barCode + ", productName="
				+ productName + ", productShortName=" + productShortName + ", categoryId=" + categoryId
				+ ", categoryName=" + categoryName + ", normalPurchasePrice=" + normalPurchasePrice + ", cost=" + cost
				+ ", lastPurchasePrice=" + lastPurchasePrice + ", salePrice=" + salePrice + ", stockWarn=" + stockWarn
				+ ", productUnit=" + productUnit + ", productPlace=" + productPlace + ", gmtCreate=" + gmtCreate
				+ ", gmtModified=" + gmtModified + ", effectiveFlag=" + effectiveFlag + ", attributeList="
				+ attributeList + ", stock=" + stock + "]";
	}

}
