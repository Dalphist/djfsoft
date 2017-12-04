package mapper;

import java.util.List;

import pojo.ProductCategory;
import pojo.Tree;



public interface ProductCategoryMapper {
	
	public void add(ProductCategory productCategory);  
    
    public void delete(int id);  
        
    public void update(ProductCategory productCategory);   
    
    public ProductCategory getProductCategoryById(int id);
    
    public List<ProductCategory> getProductCategories();
    
    public List<ProductCategory> getProductCategoryByParentId(int parentId);
    
    public List<Tree> getTreeByCategoryId(int parentId);
}
