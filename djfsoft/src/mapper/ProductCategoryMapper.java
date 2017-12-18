package mapper;

import java.util.List;

import pojo.ProductCategory;
import pojo.Tree;



public interface ProductCategoryMapper {
	
	public void add(ProductCategory productCategory);  
	
	public void addCategoryToAttribute(String categoryId,String attributeId);
    
    public void delete(String categoryId);  
        
    public void update(ProductCategory productCategory);  
    
    public int getCategoryCountByInfo(ProductCategory productCategory);
    
    public ProductCategory getProductCategoryById(String id);
    
    public List<ProductCategory> getProductCategories();
    
    public List<ProductCategory> getProductCategoryByParentId(int parentId);
    
    public List<Tree> getTreeByCategoryId(int parentId);
}
