package service;

import java.util.List;

import pojo.ProductAttribute;
import pojo.ProductAttributeInfo;
public interface ProductAttributeService {
	void addProductAttribute(ProductAttribute productAttribute);
	
	void delProductAttribute(String attributeId);

	void update(ProductAttribute productAttribute);   
	
	int getProductAttributeByName(ProductAttribute productAttribute);
    
    List<ProductAttribute> getProductAttributes();
    
    List<ProductAttributeInfo> getProductAttributesByProductId(String productId);
    
    List<ProductAttribute> getProductAttributesByCategoryId(String categoryId);

}
