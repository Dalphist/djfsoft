package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pojo.ProductAttribute;
import pojo.ProductAttributeValue;
import service.ProductAttributeService;

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
	 * @Title: productAttributeList
	 * @Description: 获取所有商品规格列表
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
	
	/**
	 * @Title: productAttributeValueByAttributeId
	 * @Description: 根据属性ID获取对应属性值
	 * @param: @param attributeId
	 * @param: @return   
	 * @return: ModelAndView   
	 * @throws
	 */
	@RequestMapping("getAttributeValue")
	public ModelAndView productAttributeValueByAttributeId(String attributeId) {
		ModelAndView mav = new ModelAndView();
		List<ProductAttributeValue> list = new ArrayList<ProductAttributeValue>();
//		list = productAttributeService.getProductAttributes();
		String url = "manage/productAttribute/list";
		mav.setViewName(url);
		mav.addObject("productAttributeList", list);
		return mav;
	}
}
