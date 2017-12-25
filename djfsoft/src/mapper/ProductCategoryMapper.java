package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.ProductCategory;
import pojo.Tree;



public interface ProductCategoryMapper {
	
	public void add(ProductCategory productCategory);  
	
	public void addCategoryToAttribute(@Param("categoryId") String categoryId,@Param("attributeId") String attributeId);
    
    public void delete(String categoryId);  
    
    public void delCategoryToAttribute(String categoryId);
        
    public void update(ProductCategory productCategory);  
    
    public int getCategoryCountByInfo(ProductCategory productCategory);
    
    public ProductCategory getProductCategoryById(String id);
    
    public List<ProductCategory> getProductCategories();
    
    public List<ProductCategory> getProductCategoryByParentId(int parentId);
    
    public List<ProductCategory> getChildrenCategoryById(String categoryId);
    
    public List<Tree> getTreeByCategoryId(String parentId);
}
