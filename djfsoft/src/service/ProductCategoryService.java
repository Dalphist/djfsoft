package service;

import java.util.List;

import pojo.ProductCategory;
import pojo.Tree;

public interface ProductCategoryService {
	void addProductCategory(ProductCategory productCategory);
	
	void addCategoryToAttribute(String categoryId,String attributeId);
	
	void delProductCategory(String categoryId);
	
	int getCategoryCountByInfo(ProductCategory productCategory);

	ProductCategory getProductCategoryById(String id);

	List<ProductCategory> getProductCategoryByParentId(int parent_id);

	List<Tree> getTreeByCategoryId(int parent_id);
	
	
}
