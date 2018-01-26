package pojo.stock;

public class StockInInfo {
	private Integer productId;
	private Double normalQuantity;
	private Double defectQuantity;
	private Double uselessQuantity;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
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
	
}
