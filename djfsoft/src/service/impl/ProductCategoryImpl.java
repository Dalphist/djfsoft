package service.impl;

import java.util.List;

import mapper.ProductCategoryMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.ProductCategory;
import pojo.Tree;
import service.ProductCategoryService;


@Service
public class ProductCategoryImpl implements ProductCategoryService{
	@Autowired
	ProductCategoryMapper productCategoryMapper;

	@Override
	public List<ProductCategory> getProductCategoryByParentId(int parent_id) {
		return productCategoryMapper.getProductCategoryByParentId(parent_id);
	}

	@Override
	public ProductCategory getProductCategoryById(int id) {
		return productCategoryMapper.getProductCategoryById(id);
	}

	@Override
	public List<Tree> getTreeByCategoryId(int parent_id) {
		return productCategoryMapper.getTreeByCategoryId(parent_id);
	}

	@Override
	public void addProductCategory(ProductCategory productCategory) {
		productCategoryMapper.add(productCategory);
		return;
	}

	@Override
	public void delProductCategory(String categoryId) {
		productCategoryMapper.delete(categoryId);
		return;
	}
}
