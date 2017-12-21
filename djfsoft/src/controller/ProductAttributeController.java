package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pojo.ProductAttribute;
import pojo.ProductAttributeInfo;
import pojo.ProductAttributeValue;
import pojo.ResultBean;
import service.ProductAttributeService;
import service.ProductAttributeValueService;
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
	
	@RequestMapping("getAllAttribute")
	@ResponseBody
	public ResultBean<ProductAttribute> getAllAttribute() {
		ResultBean<ProductAttribute> result = new ResultBean<ProductAttribute>();
		List<ProductAttribute> list = new ArrayList<ProductAttribute>();
		list = productAttributeService.getProductAttributes();
		result.setDataList(list);
		return result;
	}
	
	/**
	 * @Title: getCategoryAttribute
	 * @Description: 获取分类下拥有的规格
	 * @param: @param categoryId
	 * @param: @return   
	 * @return: ResultBean<ProductAttribute>   
	 * @throws
	 */
	@Autowired
	ProductAttributeValueService productAttributeValueService;
	
	@RequestMapping("getCategoryAttribute")
	@ResponseBody
	public ResultBean<ProductAttributeInfo> getCategoryAttribute(String categoryId) {
		ResultBean<ProductAttributeInfo> result = new ResultBean<ProductAttributeInfo>();
		List<ProductAttributeInfo> attributeList = new ArrayList<ProductAttributeInfo>();
		attributeList = productAttributeService.getProductAttributesByCategoryId(categoryId);
		for(ProductAttributeInfo attribute :attributeList){
			String attributeId = attribute.getId().toString();
			List<ProductAttributeValue> valueList = productAttributeValueService.getProductAttributeValuesByAttributeId(attributeId);
			attribute.setValueList(valueList);
		}
		result.setDataList(attributeList);
		return result;
	}
	
	/**
	 * @Title: productAttributeValueByAttributeId
	 * @Description: 根据属性ID获取对应属性值
	 * @param: @param attributeId
	 * @param: @return   
	 * @return: ModelAndView   
	 * @throws
	 */
	/*@RequestMapping("getAttributeValue")
	public ModelAndView getproductAttributeValueByAttributeId(String attributeId) {
		ModelAndView mav = new ModelAndView();
		List<ProductAttributeValue> list = new ArrayList<ProductAttributeValue>();
//		list = productAttributeService.getProductAttributes();
		String url = "manage/productAttribute/list";
		mav.setViewName(url);
		mav.addObject("productAttributeList", list);
		return mav;
	}*/
	
	/**
	 * @Title: validateAttribute
	 * @Description: 校验规格名称是否重复，用在添加和修改规格
	 * @param: @param attributeInfo
	 * @param: @return   
	 * @return: ResultBean<String>   
	 * @throws
	 */
	@RequestMapping("validateAttribute")
	@ResponseBody
	public ResultBean<String> validateAttribute(String attributeInfo) {
		ProductAttribute productAttribute = new ProductAttribute();
		productAttribute = (ProductAttribute)BeanUtil.getBeanFromStr(attributeInfo, "pojo.ProductAttribute");
		ResultBean<String> result = new ResultBean<String>();
		int a = productAttributeService.getProductAttributeByName(productAttribute);
		if (a > 0) {
			result.setCode(ResultBean.FAIL);
			result.setMsg("规格名称重复！");
		}
		return result;
	}
	
	/**
	 * @Title: saveAttribute
	 * @Description: 规格信息保存，包括新加和修改
	 * @param: @param attributeInfo
	 * @param: @return   
	 * @return: ResultBean<String>   
	 * @throws
	 */
	@RequestMapping("saveAttribute")
	@ResponseBody
	public ResultBean<String> saveAttribute(String attributeInfo) {
		ProductAttribute productAttribute = new ProductAttribute();
		productAttribute = (ProductAttribute)BeanUtil.getBeanFromStr(attributeInfo, "pojo.ProductAttribute");
		ResultBean<String> result = new ResultBean<String>();
		//添加
		if(productAttribute.getId() == null){
			productAttribute.setGmtCreate(DateUtil.getNowDate());
			productAttributeService.addProductAttribute(productAttribute);
			result.setMsg("添加成功！");
		}else{	//修改
			productAttribute.setGmtModified(DateUtil.getNowDate());
			productAttributeService.update(productAttribute);
			result.setMsg("修改成功！");
		}
		result.setCode(ResultBean.SUCCESS);
		return result;
	}
	
	/**
	 * @Title: delProductAttribute
	 * @Description: 删除
	 * @param: @param attributeId
	 * @param: @return   
	 * @return: ResultBean<String>   
	 * @throws
	 */
	@RequestMapping("delProductAttribute")
	@ResponseBody
	public ResultBean<String> delProductAttribute(String attributeId) {
		productAttributeService.delProductAttribute(attributeId);
		ResultBean<String> result = new ResultBean<String>();
		result.setCode(ResultBean.SUCCESS);
		result.setMsg("删除成功！");
		return result;
	}
	
	
}
