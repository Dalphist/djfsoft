package pojo;

import java.util.Date;

public class ProductAttributeValueInfo {
	private Integer id;
	private Integer attributeId;
	private String attributeName;
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
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
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
		return "ProductAttributeValueInfo [id=" + id + ", attributeId="
				+ attributeId + ", attributeName=" + attributeName
				+ ", attributeValueName=" + attributeValueName
				+ ", effectiveFlag=" + effectiveFlag + ", gmtCreate="
				+ gmtCreate + ", gmtModified=" + gmtModified + "]";
	}
	
}
