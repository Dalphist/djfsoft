package mapper;

import java.util.List;

import pojo.ProductAttribute;
import pojo.ProductAttributeInfo;



public interface ProductAttributeMapper {
	
	public void add(ProductAttribute productAttribute);  
    
    public void delete(String attributeId);  
        
    public void update(ProductAttribute productAttribute);   
    
    public int getProductAttributeByName(ProductAttribute productAttribute);
    
    public List<ProductAttribute> getProductAttributes();
    
    public List<ProductAttributeInfo> getProductAttributesByProductId(String productId);
    
    public List<ProductAttribute> getProductAttributesByCategoryId(String categoryId);
}
