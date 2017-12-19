package service;

import java.util.List;

import pojo.ProductAttributeValue;
import pojo.ProductAttributeValueInfo;

public interface ProductAttributeValueService {
	void add(ProductAttributeValue productAttributeValue);

	void delete(String valueId);

	void update(ProductAttributeValue productAttributeValue);
	
	List<ProductAttributeValueInfo> getProductAttributeValuesByProductId(String productId);

	List<ProductAttributeValue> getProductAttributeValuesByAttributeId(String attributeId);
	
	int getValueByNameAndAttribute(ProductAttributeValue productAttributeValue);

}
