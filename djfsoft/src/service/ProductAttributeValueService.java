package service;

import java.util.List;

import pojo.ProductAttributeValue;

public interface ProductAttributeValueService {
	void add(ProductAttributeValue productAttributeValue);

	void delete(String valueId);

	void update(ProductAttributeValue productAttributeValue);

	List<ProductAttributeValue> getProductAttributeValuesByProductId(String productId);

	List<ProductAttributeValue> getProductAttributeValuesByAttributeId(String attributeId);

}
