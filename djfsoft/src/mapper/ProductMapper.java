package mapper;

import java.util.List;

import pojo.Product;
import pojo.ProductInfo;

public interface ProductMapper {
	public void add(Product product);

	public void delete(String id);

	public void update(Product product);

	public ProductInfo get(String id);

	public List<Product> list(String categoryId);

}
