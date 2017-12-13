package service.impl;

import java.util.List;

import mapper.ProductAttributeMapper;

import org.springframework.beans.factory.annotation.Autowired;

import pojo.ProductAttribute;
import service.ProductAttributeService;

public class ProductAttributeImpl implements ProductAttributeService {
	@Autowired
	ProductAttributeMapper productAttributeMapper;
	
	@Override
	public void addProductAttribute(ProductAttribute productAttribute) {
		productAttributeMapper.add(productAttribute);
		return;
	}

	@Override
	public void delProductAttribute(String attributeId) {
		productAttributeMapper.delete(attributeId);
		return;
	}

	@Override
	public void update(ProductAttribute productAttribute) {
		productAttributeMapper.update(productAttribute);
		return;
	}

	@Override
	public List<ProductAttribute> getProductAttributes() {
		List<ProductAttribute> list = productAttributeMapper.getProductAttributes();
		return list;
	}

	@Override
	public List<ProductAttribute> getProductAttributesByProductId(String productId) {
		List<ProductAttribute> list = productAttributeMapper.getProductAttributesByProductId(productId);
		return list;
	}

	@Override
	public List<ProductAttribute> getProductAttributesByCategoryId(	String categoryId) {
		List<ProductAttribute> list = productAttributeMapper.getProductAttributesByCategoryId(categoryId);
		return list;
	}

}
