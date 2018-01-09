package mapper;

import java.util.List;

import pojo.manage.Product;
import pojo.manage.ProductInfo;

public interface ProductMapper {
	public void add(Product product);

	public void delete(String productId);
	
	public void delProductValue(String productId);

	public void update(Product product);

	public ProductInfo get(String id);

	public List<Product> list(String categoryId);
	
	public List<ProductInfo> productInfolist(String categoryId);

	public int getProductByCode(Product product);

	public int getProductByBarCode(Product product);
}
