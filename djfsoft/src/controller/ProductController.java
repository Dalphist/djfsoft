package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pojo.Product;
import pojo.ProductInfo;
import pojo.ResultBean;
import service.ProductService;
import util.DateUtil;

@Controller
@RequestMapping("manage/product")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("index")
	public ModelAndView productIndex() {
		ModelAndView mav = new ModelAndView();
		String url = "manage/product/index";
		mav.setViewName(url);
		return mav;
	}
	
	@RequestMapping("list")
	public ModelAndView productList(String categoryId) {
		ModelAndView mav = new ModelAndView();
		List<Product> list = new ArrayList<Product>();
		list = productService.getProductList(categoryId);
		String url = "manage/product/list";
		mav.setViewName(url);
		mav.addObject("productList", list);
		return mav;
	}
	
	
	@RequestMapping("getProductById")
	@ResponseBody
	public ResultBean<ProductInfo> getProductById(String productId) {
		ProductInfo product = new ProductInfo();
		product = productService.getProductById(productId);
		ResultBean<ProductInfo> result = new ResultBean<ProductInfo>();
		result.setData(product);
		return result;
	}
	
	@RequestMapping("addProduct")
	@ResponseBody
	public ResultBean<String> addProduct(String productInfo) {
		Product product = new Product();
		product = getProductFromInfo(productInfo);
		// 添加商品时，设置当前时间为添加时间。
		product.setGmtCreate(DateUtil.getNowDate());
		productService.addProduct(product);
		ResultBean<String> result = new ResultBean<String>();
		result.setCode(ResultBean.SUCCESS);
		result.setMsg("添加成功！");
		return result;
	}
	
	@RequestMapping("validateProduct")
	@ResponseBody
	public ResultBean<String> validateProduct(String productInfo) {
		Product product = new Product();
		product = getProductFromInfo(productInfo);
		ResultBean<String> result = new ResultBean<String>();
		int a = productService.getProductByCode(product.getProductCode());
		int b = productService.getProductByBarCode(product.getBarCode());
		if(a>0){
			result.setCode(ResultBean.FAIL);
			result.setMsg("商品编码重复！");
		}
		if(b>0){
			result.setCode(ResultBean.FAIL);
			result.setMsg("商品条形码重复！");
		}
		return result;
	}

	private Product getProductFromInfo(String productInfo) {
		Product product = new Product();
		JSONObject info = JSONObject.fromObject(productInfo);
		product = (Product) JSONObject.toBean(info, Product.class);

		return product;
	}

}
