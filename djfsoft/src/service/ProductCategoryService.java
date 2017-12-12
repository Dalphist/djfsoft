package service;

import java.util.List;

import pojo.ProductCategory;
import pojo.Tree;

public interface ProductCategoryService {
	void addProductCategory(ProductCategory productCategory);
	
	void delProductCategory(String categoryId);

	ProductCategory getProductCategoryById(int id);

	List<ProductCategory> getProductCategoryByParentId(int parent_id);

	List<Tree> getTreeByCategoryId(int parent_id);
}
