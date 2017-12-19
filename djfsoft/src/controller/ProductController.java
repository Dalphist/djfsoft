package controller;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pojo.Product;
import pojo.ProductAttributeValueInfo;
import pojo.ProductInfo;
import pojo.ResultBean;
import service.ProductAttributeValueService;
import service.ProductService;
import util.BeanUtil;
import util.DateUtil;

@Controller
@RequestMapping("manage/product")
public class ProductController {
	@Autowired
	ProductService productService;
	
	/**
	 * @Title: productIndex
	 * @Description: 商品管理主页的跳转
	 * @param: @return   
	 * @return: ModelAndView   
	 * @throws
	 */
	@RequestMapping("index")
	public ModelAndView productIndex() {
		ModelAndView mav = new ModelAndView();
		String url = "manage/product/index";
		mav.setViewName(url);
		return mav;
	}
	
	/**
	 * @Title: productList
	 * @Description: 根据分类Id获取商品列表
	 * @param: @param categoryId
	 * @param: @return   
	 * @return: ModelAndView   
	 * @throws
	 */
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
	
	/**
	 * @Title: getProductById
	 * @Description: 根据商品ID获取对应商品信息，注意此处获取到的是ProductInfo,而不是Product
	 * @param: @param productId
	 * @param: @return   
	 * @return: ResultBean<ProductInfo>   
	 * @throws
	 */
	@Autowired
	ProductAttributeValueService productAttributeValueService;
	
	@RequestMapping("getProductById")
	@ResponseBody
	public ResultBean<ProductInfo> getProductById(String productId) {
		ProductInfo productInfo = new ProductInfo();
		productInfo = productService.getProductById(productId);
		List<ProductAttributeValueInfo> valueList = productAttributeValueService.getProductAttributeValuesByProductId(productId);
		productInfo.setValueList(valueList);
		ResultBean<ProductInfo> result = new ResultBean<ProductInfo>();
		result.setData(productInfo);
		return result;
	}
	
	/**
	 * @Title: addProduct
	 * @Description: 根据前台传过来的商品信息添加商品
	 * @param: @param productInfo
	 * @param: @return   
	 * @return: ResultBean<String>   
	 * @throws
	 */
	@RequestMapping("addProduct")
	@ResponseBody
	public ResultBean<String> addProduct(String productInfo) {
		Product product = new Product();
		product = (Product)BeanUtil.getBeanFromStr(productInfo, "pojo.Product");
		// 添加商品时，设置当前时间为添加时间。
		product.setGmtCreate(DateUtil.getNowDate());
		productService.addProduct(product);
		ResultBean<String> result = new ResultBean<String>();
		result.setCode(ResultBean.SUCCESS);
		result.setMsg("添加成功！");
		return result;
	}
	
	/**
	 * @Title: delProduct
	 * @Description: 根据商品ID集合遍历删除商品
	 * @param: @param selectProductList
	 * @param: @return   
	 * @return: ResultBean<String>   
	 * @throws
	 */
	@RequestMapping("delProduct")
	@ResponseBody
	public ResultBean<String> delProduct(String selectProductList) {
		JSONArray arr = JSONArray.fromObject(selectProductList);
		for (int i = 0; i < arr.size(); i++) {
			JSONObject product = (JSONObject) arr.get(i);
			String id = product.getString("productId");
			productService.delProduct(id);
		}
		ResultBean<String> result = new ResultBean<String>();
		result.setCode(ResultBean.SUCCESS);
		result.setMsg("删除成功！");
		return result;
	}
	
	/**
	 * @Title: validateProduct
	 * @Description: 校验商品是否重复，校验内容为商品编码和条形码
	 * @param: @param productInfo
	 * @param: @return   
	 * @return: ResultBean<String>   
	 * @throws
	 */
	@RequestMapping("validateProduct")
	@ResponseBody
	public ResultBean<String> validateProduct(String productInfo) {
		Product product = new Product();
		product = (Product)BeanUtil.getBeanFromStr(productInfo, "pojo.Product");
		ResultBean<String> result = new ResultBean<String>();
		int a = productService.getProductByCode(product.getProductCode());
		int b = productService.getProductByBarCode(product.getBarCode());
		if (a > 0) {
			result.setCode(ResultBean.FAIL);
			result.setMsg("商品编码重复！");
		}
		if (b > 0) {
			result.setCode(ResultBean.FAIL);
			result.setMsg("商品条形码重复！");
		}
		return result;
	}
	
}
