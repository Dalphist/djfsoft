package pojo.purchase;

import java.util.Date;

public class PurchaseOrderInfo {
	private Integer id;
	private String orderCode;
	private Double productPrice;
	private Double transportFare;
	private Double extraPrice;
	private Double totalPrice;
	private Integer payTypeId;
	private String payType;
	private Integer operaterId;
	private String operateName;
	private Date operateDate;
	private Integer confirmId;
	private String confirmName;
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

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getConfirmName() {
		return confirmName;
	}

	public void setConfirmName(String confirmName) {
		this.confirmName = confirmName;
	}

	@Override
	public String toString() {
		return "PurchaseOrderInfo [id=" + id + ", orderCode=" + orderCode + ", productPrice=" + productPrice
				+ ", transportFare=" + transportFare + ", extraPrice=" + extraPrice + ", totalPrice=" + totalPrice
				+ ", payTypeId=" + payTypeId + ", payType=" + payType + ", operaterId=" + operaterId + ", operateName="
				+ operateName + ", operateDate=" + operateDate + ", confirmId=" + confirmId + ", confirmName="
				+ confirmName + ", confirmDate=" + confirmDate + ", effectiveFlag=" + effectiveFlag + "]";
	}

}
