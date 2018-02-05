package service.manage.impl;

import java.util.List;

import pojo.manage.ProductAttribute;
import pojo.manage.ProductAttributeInfo;
import service.manage.ProductAttributeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.manage.ProductAttributeMapper;

@Service
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
	public List<ProductAttributeInfo> getProductAttributesByProductId(String productId) {
		List<ProductAttributeInfo> list = productAttributeMapper.getProductAttributesByProductId(productId);
		return list;
	}

	@Override
	public List<ProductAttributeInfo> getProductAttributesByCategoryId(	String categoryId) {
		List<ProductAttributeInfo> list = productAttributeMapper.getProductAttributesByCategoryId(categoryId);
		return list;
	}

	@Override
	public int getProductAttributeByName(ProductAttribute productAttribute) {
		return productAttributeMapper.getProductAttributeByName(productAttribute);
	}

}
