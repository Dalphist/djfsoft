package service.impl;

import java.util.List;

import mapper.ProductAttributeValueMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.ProductAttributeValue;
import service.ProductAttributeValueService;

@Service
public class ProductAttributeValueImpl implements ProductAttributeValueService {
	@Autowired
	ProductAttributeValueMapper productAttributeValueMapper;

	@Override
	public void add(ProductAttributeValue productAttributeValue) {
		productAttributeValueMapper.add(productAttributeValue);
		return;
	}

	@Override
	public void addProductToValue(String productId, String valueId) {
		productAttributeValueMapper.addProductToValue(productId, valueId);
		return;
	}
	@Override
	public void delete(String valueId) {
		productAttributeValueMapper.delete(valueId);
		return;
	}

	@Override
	public void update(ProductAttributeValue productAttributeValue) {
		productAttributeValueMapper.update(productAttributeValue);
		return;
	}

	@Override
	public List<ProductAttributeValue> getProductAttributeValuesByProductId(String productId) {
		return productAttributeValueMapper.getProductAttributeValuesByProductId(productId);
	}

	@Override
	public List<ProductAttributeValue> getProductAttributeValuesByAttributeId(String attributeId) {
		return productAttributeValueMapper.getProductAttributeValuesByAttributeId(attributeId);
	}

	@Override
	public int getValueByNameAndAttribute(ProductAttributeValue productAttributeValue) {
		return productAttributeValueMapper.getValueByNameAndAttribute(productAttributeValue);
	}
}
