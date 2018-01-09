package pojo.manage;

import java.util.Date;



public class ProductAttribute {
	private Integer id;
	private String attributeName;
	private Integer effectiveFlag;
	private Date gmtCreate;
	private Date gmtModified;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
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
		return "ProductAttribute [id=" + id + ", attributeName=" + attributeName + ", effectiveFlag=" + effectiveFlag
				+ ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}
	
}
