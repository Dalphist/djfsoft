package pojo;

import java.util.Date;
import java.util.List;

/**
 * 比正常的实体类多出了规格值的列表和选中的值的id
 * @author DJF
 */

public class ProductAttributeInfo {
	private Integer id;
	private Integer valueId;
	private String attributeName;
	private Integer effectiveFlag;
	private Date gmtCreate;
	private Date gmtModified;
	private List<ProductAttributeValue> valueList;
	
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
	
	public Integer getValueId() {
		return valueId;
	}
	public void setValueId(Integer valueId) {
		this.valueId = valueId;
	}
	public List<ProductAttributeValue> getValueList() {
		return valueList;
	}
	public void setValueList(List<ProductAttributeValue> valueList) {
		this.valueList = valueList;
	}
	@Override
	public String toString() {
		return "ProductAttributeInfo [id=" + id + ", valueId=" + valueId
				+ ", attributeName=" + attributeName + ", effectiveFlag="
				+ effectiveFlag + ", gmtCreate=" + gmtCreate + ", gmtModified="
				+ gmtModified + ", valueList=" + valueList + "]";
	}
	
}
