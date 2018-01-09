package service;

import java.util.List;

import pojo.manage.ProductAttributeValue;

public interface ProductAttributeValueService {
	void add(ProductAttributeValue productAttributeValue);
	
	void addProductToValue(String productId,String valueId);

	void delete(String valueId);

	void update(ProductAttributeValue productAttributeValue);
	
	List<ProductAttributeValue> getProductAttributeValuesByProductId(String productId);

	List<ProductAttributeValue> getProductAttributeValuesByAttributeId(String attributeId);
	
	int getValueByNameAndAttribute(ProductAttributeValue productAttributeValue);

}
