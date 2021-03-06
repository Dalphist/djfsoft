package service.manage;

import java.util.List;

import pojo.manage.ProductAttribute;
import pojo.manage.ProductAttributeInfo;
public interface ProductAttributeService {
	void addProductAttribute(ProductAttribute productAttribute);
	
	void delProductAttribute(String attributeId);

	void update(ProductAttribute productAttribute);   
	
	int getProductAttributeByName(ProductAttribute productAttribute);
    
    List<ProductAttribute> getProductAttributes();
    
    List<ProductAttributeInfo> getProductAttributesByProductId(String productId);
    
    List<ProductAttributeInfo> getProductAttributesByCategoryId(String categoryId);

}
