package mapper.manage;

import java.util.List;

import pojo.manage.ProductAttribute;
import pojo.manage.ProductAttributeInfo;



public interface ProductAttributeMapper {
	
	public void add(ProductAttribute productAttribute);  
    
    public void delete(String attributeId);  
        
    public void update(ProductAttribute productAttribute);   
    
    public int getProductAttributeByName(ProductAttribute productAttribute);
    
    public List<ProductAttribute> getProductAttributes();
    
    public List<ProductAttributeInfo> getProductAttributesByProductId(String productId);
    
    public List<ProductAttributeInfo> getProductAttributesByCategoryId(String categoryId);
}
