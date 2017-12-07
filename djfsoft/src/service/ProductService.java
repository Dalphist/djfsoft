package service;

import java.util.List;

import pojo.Product;


public interface ProductService {
	void addProduct(Product product);
	List<Product> getProductList();
}
