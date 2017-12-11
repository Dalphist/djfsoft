package service;

import java.util.List;

import pojo.Product;
import pojo.ProductInfo;

public interface ProductService {
	void addProduct(Product product);
	
	ProductInfo getProductById(String productId);
	
	List<Product> getProductList(String categoryId);
	
	//校验用方法
	int getProductByCode(String code);
	int getProductByBarCode(String barCode);
	
}
