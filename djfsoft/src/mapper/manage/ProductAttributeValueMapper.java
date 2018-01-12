package mapper.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.manage.ProductAttributeValue;



public interface ProductAttributeValueMapper {
	
	public void add(ProductAttributeValue productAttributeValue);  
	
	public void addProductToValue(@Param("productId") String productId,@Param("valueId") String valueId);
    
    public void delete(String valueId);  
        
    public void update(ProductAttributeValue productAttributeValue);   
    
    public List<ProductAttributeValue> getProductAttributeValuesByProductId(String productId);
    
    public List<ProductAttributeValue> getProductAttributeValuesByAttributeId(String attributeId);
    
    public int getValueByNameAndAttribute(ProductAttributeValue productAttributeValue);
}
