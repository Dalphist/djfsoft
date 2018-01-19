package pojo.sales;

import java.util.Date;

public class SalesStockOutOrderInfo {
	private Integer id;
	private String orderCode;
	private Integer operaterId;
	private Date operateDate;
	private Integer confirmId;
	private Date confirmDate;
	private Integer typeId;
	// info
	private String operateName;
	private String confirmName;
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
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
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
		return "SalesStockOutOrderInfo [id=" + id + ", orderCode=" + orderCode + ", operaterId=" + operaterId
				+ ", operateDate=" + operateDate + ", confirmId=" + confirmId + ", confirmDate=" + confirmDate
				+ ", typeId=" + typeId + ", operateName=" + operateName + ", confirmName=" + confirmName + "]";
	}

}
