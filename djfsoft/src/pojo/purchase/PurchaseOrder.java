package pojo.purchase;

import java.util.Date;


public class PurchaseOrder {
	private Integer id;
	private String orderCode;
	private Double productPrice;
	private Double transportFare;
	private Double extraPrice;
	private Double totalPrice;
	private Integer payTypeId;
	private Integer operaterId;
	private Date operateDate;
	private Integer confirmId;
	private Date confirmDate;
	private Integer effectiveFlag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public Double getTransportFare() {
		return transportFare;
	}
	public void setTransportFare(Double transportFare) {
		this.transportFare = transportFare;
	}
	public Double getExtraPrice() {
		return extraPrice;
	}
	public void setExtraPrice(Double extraPrice) {
		this.extraPrice = extraPrice;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getPayTypeId() {
		return payTypeId;
	}
	public void setPayTypeId(Integer payTypeId) {
		this.payTypeId = payTypeId;
	}
	public Integer getOperaterId() {
		return operaterId;
	}
	public void setOperaterId(Integer operaterId) {
		this.operaterId = operaterId;
	}
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	public Integer getConfirmId() {
		return confirmId;
	}
	public void setConfirmId(Integer confirmId) {
		this.confirmId = confirmId;
	}
	public Date getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}
	public Integer getEffectiveFlag() {
		return effectiveFlag;
	}
	public void setEffectiveFlag(Integer effectiveFlag) {
		this.effectiveFlag = effectiveFlag;
	}
	@Override
	public String toString() {
		return "PurchaseOrder [id=" + id + ", orderCode=" + orderCode + ", productPrice=" + productPrice
				+ ", transportFare=" + transportFare + ", extraPrice=" + extraPrice + ", totalPrice=" + totalPrice
				+ ", payTypeId=" + payTypeId + ", operaterId=" + operaterId + ", operateDate=" + operateDate
				+ ", confirmId=" + confirmId + ", confirmDate=" + confirmDate + ", effectiveFlag=" + effectiveFlag
				+ "]";
	}

}
