package pojo;

import java.util.Date;
import java.util.List;


public class ProductCategory {
	private int id;
	private int parentId;
	private String categoryName;
	private String categoryCode;
	private int orderFlag;
	private Date gmtCreate;
	private Date gmtModified;
	private List<ProductCategory> childrenCategories;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
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
	public int getOrder() {
		return orderFlag;
	}
	public void setOrder(int orderFlag) {
		this.orderFlag = orderFlag;
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
