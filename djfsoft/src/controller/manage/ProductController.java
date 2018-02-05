package controller.manage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.ResultBean;
import pojo.manage.Product;
import pojo.manage.ProductAttributeInfo;
import pojo.manage.ProductAttributeValue;
import pojo.manage.ProductInfo;
import service.manage.ProductAttributeService;
import service.manage.ProductAttributeValueService;
import service.manage.ProductService;
import util.DateUtil;
import util.ParseUtil;

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
	ProductAttributeService productAttributeService;
	@Autowired
	ProductAttributeValueService productAttributeValueService;
	
	@RequestMapping("getProductById")
	@ResponseBody
	public ResultBean<ProductInfo> getProductById(String productId) {
		ProductInfo productInfo = new ProductInfo();
		productInfo = productService.getProductById(productId);
		List<ProductAttributeInfo> attributeList = productAttributeService.getProductAttributesByProductId(productId);
		for(ProductAttributeInfo attribute :attributeList){
			String attributeId = attribute.getId().toString();
			List<ProductAttributeValue> valueList = productAttributeValueService.getProductAttributeValuesByAttributeId(attributeId);
			attribute.setValueList(valueList);
		}
		productInfo.setAttributeList(attributeList);
		ResultBean<ProductInfo> result = new ResultBean<ProductInfo>();
		result.setData(productInfo);
		return result;
	}
	
	/**
	 * @Title: saveProduct
	 * @Description: 商品保存，包括添加和修改
	 * @param: @param productInfo
	 * @param: @param attributeIdStr	规格id拼接的用逗号隔开的字符串
	 * @param: @return   
	 * @return: ResultBean<String>   
	 * @throws
	 */
	@RequestMapping("saveProduct")
	@ResponseBody
	public ResultBean<String> saveProduct(String productInfo,String valueIdStr) {
		Product product = new Product();
		product = (Product)ParseUtil.getBeanFromStr(productInfo, "pojo.manage.Product");
		ResultBean<String> result = new ResultBean<String>();
		if(product.getId() == null){
			// 添加商品时，设置当前时间为添加时间。
			product.setGmtCreate(DateUtil.getNowDate());
			productService.addProduct(product);
			result.setMsg("添加成功！");
		}else{
			product.setGmtModified(DateUtil.getNowDate());
			productService.updateProduct(product);
			result.setMsg("修改成功！");
		}
		String productId = String.valueOf(product.getId());
		productService.delProductValue(productId);//删除已有规格
		//保存规格
		List<String> ids = ParseUtil.parseFromStrArray(valueIdStr);
		for(String valueId : ids){
			productAttributeValueService.addProductToValue(productId, valueId);
		}
		result.setCode(ResultBean.SUCCESS);
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
		product = (Product)ParseUtil.getBeanFromStr(productInfo, "pojo.manage.Product");
		ResultBean<String> result = new ResultBean<String>();
		int a = productService.getProductByCode(product);
		int b = productService.getProductByBarCode(product);
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
	
	/**
	 * 采购订单选择商品后，获取前台传过来的商品id集合得到对应的商品信息传回
	 * @param productIds
	 * @return
	 */
	@RequestMapping("getProductInfoList")
	@ResponseBody
	public ResultBean<ProductInfo> getProductInfoList(String productIds) {
		List<String> ids = ParseUtil.parseFromStrArray(productIds);
		List<ProductInfo> list = new ArrayList<ProductInfo>();
		for (String id : ids) {
			ProductInfo info = productService.getProductById(id);
			list.add(info);
		}
		ResultBean<ProductInfo> result = new ResultBean<ProductInfo>();
		result.setDataList(list);
		return result;
	}
	
	@RequestMapping("upLoad")
	public ModelAndView upLoad(HttpServletRequest request,HttpSession session,MultipartFile file) {
		ModelAndView mav = new ModelAndView();
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		return mav;
	}
}
