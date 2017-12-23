package service;

import java.util.List;

import pojo.Product;
import pojo.ProductInfo;

public interface ProductService {
	void addProduct(Product product);
	
	void delProduct(String productId);
	
	void updateProduct(Product product);
	
	ProductInfo getProductById(String productId);
	
	List<Product> getProductList(String categoryId);
	
	//校验用方法
	int getProductByCode(Product product);
	int getProductByBarCode(Product product);
	
}
