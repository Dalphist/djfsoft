package service.impl;

import java.util.List;

import mapper.ProductMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.Product;
import service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductMapper productMapper;

	@Override
	public void addProduct(Product product) {
		productMapper.add(product);
	}

	@Override
	public List<Product> getProductList() {
		return productMapper.list();
	}

}
