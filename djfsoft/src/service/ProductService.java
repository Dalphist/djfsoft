package service;

import java.util.List;

import pojo.manage.Product;
import pojo.manage.ProductInfo;

public interface ProductService {
	void addProduct(Product product);
	
	void delProduct(String productId);
	
	void delProductValue(String productId);
	
	void updateProduct(Product product);
	
	ProductInfo getProductById(String productId);
	
	List<Product> getProductList(String categoryId);
	
	List<ProductInfo> getProductInfoByCategoryId(String categoryId);
	//校验用方法
	int getProductByCode(Product product);
	int getProductByBarCode(Product product);
	
}
