package service;

import java.util.List;

import pojo.ProductAttribute;
public interface ProductAttributeService {
	void addProductAttribute(ProductAttribute productAttribute);
	
	void delProductAttribute(String attributeId);

	void update(ProductAttribute productAttribute);   
	
	int getProductAttributeByName(ProductAttribute productAttribute);
    
    List<ProductAttribute> getProductAttributes();
    
    List<ProductAttribute> getProductAttributesByProductId(String productId);
    
    List<ProductAttribute> getProductAttributesByCategoryId(String categoryId);

}
