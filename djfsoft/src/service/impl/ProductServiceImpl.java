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
	public List<Product> getProductList(String categoryId) {
		return productMapper.list(categoryId);
	}

	@Override
	public ProductInfo getProductById(String productId) {
		return productMapper.get(productId);
	}

	@Override
	public int getProductByCode(String code) {
		return productMapper.getProductByCode(code);
	}

	@Override
	public int getProductByBarCode(String barCode) {
		return productMapper.getProductByBarCode(barCode);
	}

	@Override
	public void delProduct(String productId) {
		productMapper.delete(productId);
		return;
	}

}
