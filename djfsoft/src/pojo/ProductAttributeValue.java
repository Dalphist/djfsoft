package pojo;

import java.util.Date;



public class ProductAttributeValue {
	private Integer id;
	private Integer attributeId;
	private String attributeValueName;
	private Integer effectiveFlag;
	private Date gmtCreate;
	private Date gmtModified;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}
	public String getAttributeValueName() {
		return attributeValueName;
	}
	public void setAttributeValueName(String attributeValueName) {
		this.attributeValueName = attributeValueName;
	}
	public Integer getEffectiveFlag() {
		return effectiveFlag;
	}
	public void setEffectiveFlag(Integer effectiveFlag) {
		this.effectiveFlag = effectiveFlag;
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
	@Override
	public String toString() {
		return "ProductAttributeValue [id=" + id + ", attributeId=" + attributeId + ", attributeValueName="
				+ attributeValueName + ", effectiveFlag=" + effectiveFlag + ", gmtCreate=" + gmtCreate
				+ ", gmtModified=" + gmtModified + "]";
	}
	
}
