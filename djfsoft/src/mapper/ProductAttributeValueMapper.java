package mapper;

import java.util.List;

import pojo.ProductAttributeValue;
import pojo.ProductAttributeValueInfo;



public interface ProductAttributeValueMapper {
	
	public void add(ProductAttributeValue productAttributeValue);  
    
    public void delete(String valueId);  
        
    public void update(ProductAttributeValue productAttributeValue);   
    
    public List<ProductAttributeValueInfo> getProductAttributeValuesByProductId(String productId);
    
    public List<ProductAttributeValue> getProductAttributeValuesByAttributeId(String attributeId);
    
    public int getValueByNameAndAttribute(ProductAttributeValue productAttributeValue);
}
