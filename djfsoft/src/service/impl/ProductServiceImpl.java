package service.impl;

import java.util.List;

import mapper.ProductMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.Product;
import pojo.ProductInfo;
import service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductMapper productMapper;

	@Override
	public void addProduct(Product product) {
		productMapper.add(product);
		return;
	}
	

	@Override
	public void updateProduct(Product product) {
		productMapper.update(product);
		return;
	}

	@Override
	public List<Product> getProductList(String categoryId) {
		return productMapper.list(categoryId);
	}

	@Override
	public ProductInfo getProductById(String productId) {
		return productMapper.get(productId);
	}

	@Override
	public int getProductByCode(Product product) {
		return productMapper.getProductByCode(product);
	}

	@Override
	public int getProductByBarCode(Product product) {
		return productMapper.getProductByBarCode(product);
	}

	@Override
	public void delProduct(String productId) {
		productMapper.delete(productId);
		return;
	}

}
