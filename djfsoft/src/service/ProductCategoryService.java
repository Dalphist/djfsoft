package service;

import java.util.List;

import pojo.ProductCategory;
import pojo.Tree;


public interface ProductCategoryService {
	List<ProductCategory> getProductCategoryByParentId(int parent_id);
	List<Tree> getTreeByCategoryId(int parent_id);
	ProductCategory getProductCategoryById(int id);
}
