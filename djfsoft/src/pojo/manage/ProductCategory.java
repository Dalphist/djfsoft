package pojo.manage;

import java.util.Date;
import java.util.List;


public class ProductCategory {
	private Integer id;
	private Integer parentId;
	private String categoryName;
	private String categoryCode;
	private Integer orderFlag;
	private Date gmtCreate;
	private Date gmtModified;
	private List<ProductCategory> childrenCategories;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getOrderFlag() {
		return orderFlag;
	}
	public void setOrderFlag(Integer orderFlag) {
		this.orderFlag = orderFlag;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public List<ProductCategory> getChildrenCategories() {
		return childrenCategories;
	}
	public void setChildrenCategories(List<ProductCategory> childrenCategories) {
		this.childrenCategories = childrenCategories;
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
	
}
