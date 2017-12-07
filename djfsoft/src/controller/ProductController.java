package controller;

import java.time.LocalDate;
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
import pojo.ResultBean;
import service.ProductService;

@Controller
@RequestMapping("manage/product")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("")
	public ModelAndView loginSuccess() {
		ModelAndView mav = new ModelAndView();
		List<Product> list = new ArrayList<Product>();
		list = productService.getProductList();
		String url = "manage/product";
		mav.setViewName(url);
		mav.addObject("productList", list);
		return mav;
	}
	
	@RequestMapping("addProduct")
	@ResponseBody
	public ResultBean<String> addProduct(String productInfo, HttpSession session) {
		Product product = new Product();
		product = getProductFromInfo(productInfo);
		// 添加商品时，设置当前时间为添加时间。
		product.setGmtCreate(LocalDate.now());
		productService.addProduct(product);
		ResultBean<String> result = new ResultBean<String>();
		result.setCode(ResultBean.SUCCESS);
		result.setMsg("添加成功！");
		return result;
	}

	private Product getProductFromInfo(String productInfo) {
		Product product = new Product();
		JSONObject info = JSONObject.fromObject(productInfo);
		product = (Product) JSONObject.toBean(info, Product.class);

		return product;
	}

}
