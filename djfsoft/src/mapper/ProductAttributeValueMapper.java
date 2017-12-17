package mapper;

import java.util.List;

import pojo.ProductAttributeValue;



public interface ProductAttributeValueMapper {
	
	public void add(ProductAttributeValue productAttributeValue);  
    
    public void delete(String valueId);  
        
    public void update(ProductAttributeValue productAttributeValue);   
    
    public List<ProductAttributeValue> getProductAttributeValuesByProductId(String productId);
    
    public List<ProductAttributeValue> getProductAttributeValuesByAttributeId(String attributeId);
    
    public int getValueByNameAndAttribute(ProductAttributeValue productAttributeValue);
}
