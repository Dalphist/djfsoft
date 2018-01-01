package pojo.salesOrder;

import java.util.Date;

public class SalesOrderInfo {
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
	private Integer stateFlag;
	private Date dealDate;
	private String taobaoCode;
	private Integer customerId;
	private String customerName;
	private String customerTel;
	private String customerPostcode;
	private Integer customerDistrictId;
	private String customerAddress;
	private String customerNotes;
	// info
	private String payType;
	private String operateName;
	private String confirmName;
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

	public Integer getStateFlag() {
		return stateFlag;
	}

	public void setStateFlag(Integer stateFlag) {
		this.stateFlag = stateFlag;
	}

	public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	public String getTaobaoCode() {
		return taobaoCode;
	}

	public void setTaobaoCode(String taobaoCode) {
		this.taobaoCode = taobaoCode;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerTel() {
		return customerTel;
	}

	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

	public String getCustomerPostcode() {
		return customerPostcode;
	}

	public void setCustomerPostcode(String customerPostcode) {
		this.customerPostcode = customerPostcode;
	}

	public Integer getCustomerDistrictId() {
		return customerDistrictId;
	}

	public void setCustomerDistrictId(Integer customerDistrictId) {
		this.customerDistrictId = customerDistrictId;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerNotes() {
		return customerNotes;
	}

	public void setCustomerNotes(String customerNotes) {
		this.customerNotes = customerNotes;
	}

	@Override
	public String toString() {
		return "SalesOrderInfo [id=" + id + ", orderCode=" + orderCode + ", productPrice=" + productPrice
				+ ", transportFare=" + transportFare + ", extraPrice=" + extraPrice + ", totalPrice=" + totalPrice
				+ ", payTypeId=" + payTypeId + ", operaterId=" + operaterId + ", operateDate=" + operateDate
				+ ", confirmId=" + confirmId + ", confirmDate=" + confirmDate + ", stateFlag=" + stateFlag
				+ ", dealDate=" + dealDate + ", taobaoCode=" + taobaoCode + ", customerId=" + customerId
				+ ", customerName=" + customerName + ", customerTel=" + customerTel + ", customerPostcode="
				+ customerPostcode + ", customerDistrictId=" + customerDistrictId + ", customerAddress="
				+ customerAddress + ", customerNotes=" + customerNotes + ", payType=" + payType + ", operateName="
				+ operateName + ", confirmName=" + confirmName + ", effectiveFlag=" + effectiveFlag + "]";
	}

}
