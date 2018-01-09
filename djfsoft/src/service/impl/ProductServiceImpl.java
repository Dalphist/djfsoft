package service.impl;

import java.util.List;

import mapper.ProductMapper;
import pojo.manage.Product;
import pojo.manage.ProductInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void delProduct(String productId) {
		productMapper.delete(productId);
		return;
	}

	@Override
	public void delProductValue(String productId) {
		productMapper.delProductValue(productId);
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
	public List<ProductInfo> getProductInfoByCategoryId(String categoryId) {
		return productMapper.productInfolist(categoryId);
	}

}
