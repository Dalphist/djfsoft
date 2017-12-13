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
import pojo.ProductAttribute;
import pojo.ProductInfo;
import pojo.ResultBean;
import service.ProductAttributeService;
import service.ProductService;
import util.BeanUtil;
import util.DateUtil;

@Controller
@RequestMapping("manage/productAttribute")
public class ProductAttributeController {
	@Autowired
	ProductAttributeService productAttributeService;
	
	/**
	 * @Title: productAttributeIndex
	 * @Description: 商品规格管理主页的跳转
	 * @param: @return   
	 * @return: ModelAndView   
	 * @throws
	 */
	@RequestMapping("index")
	public ModelAndView productAttributeIndex() {
		ModelAndView mav = new ModelAndView();
		String url = "manage/productAttribute/index";
		mav.setViewName(url);
		return mav;
	}
	
	/**
	 * @Title: productList
	 * @Description: 获取所有商品规格列表
	 * @param: @param categoryId
	 * @param: @return   
	 * @return: ModelAndView   
	 * @throws
	 */
	@RequestMapping("list")
	public ModelAndView productAttributeList() {
		ModelAndView mav = new ModelAndView();
		List<ProductAttribute> list = new ArrayList<ProductAttribute>();
		list = productAttributeService.getProductAttributes();
		String url = "manage/productAttribute/list";
		mav.setViewName(url);
		mav.addObject("productAttributeList", list);
		return mav;
	}
	
	
}
