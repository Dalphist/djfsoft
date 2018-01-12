package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.manage.ProductCategoryMapper;
import pojo.Tree;
import pojo.manage.ProductCategory;
import service.ProductCategoryService;


@Service
public class ProductCategoryImpl implements ProductCategoryService{
	@Autowired
	ProductCategoryMapper productCategoryMapper;

	@Override
	public void addProductCategory(ProductCategory productCategory) {
		productCategoryMapper.add(productCategory);
		return;
	}
	
	@Override
	public void addCategoryToAttribute(String categoryId, String attributeId) {
		productCategoryMapper.addCategoryToAttribute(categoryId, attributeId);
		return;
	}

	@Override
	public void delProductCategory(String categoryId) {
		productCategoryMapper.delete(categoryId);
		return;
	}
	
	@Override
	public void delCategoryToAttribute(String categoryId) {
		productCategoryMapper.delCategoryToAttribute(categoryId);
		return;
	}
	
	@Override
	public void update(ProductCategory productCategory) {
		productCategoryMapper.update(productCategory);
		return;
	}
	
	@Override
	public int getCategoryCountByInfo(ProductCategory productCategory) {
		return productCategoryMapper.getCategoryCountByInfo(productCategory);
	}
	
	@Override
	public List<ProductCategory> getProductCategoryByParentId(int parent_id) {
		return productCategoryMapper.getProductCategoryByParentId(parent_id);
	}

	@Override
	public ProductCategory getProductCategoryById(String id) {
		return productCategoryMapper.getProductCategoryById(id);
	}

	@Override
	public List<Tree> getTreeByCategoryId(String parent_id) {
		return productCategoryMapper.getTreeByCategoryId(parent_id);
	}
	
	@Override
	public List<ProductCategory> getChildrenCategoryById(String categoryId) {
		return productCategoryMapper.getChildrenCategoryById(categoryId);
	}

}
