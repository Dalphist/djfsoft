package controller.manage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pojo.ResultBean;
import pojo.manage.ProductAttributeValue;
import service.ProductAttributeValueService;
import util.ParseUtil;
import util.DateUtil;

@Controller
@RequestMapping("manage/productAttributeValue")
public class ProductAttributeValueController {
	@Autowired
	ProductAttributeValueService productAttributeValueService;
	
	/**
	 * @Title: productAttributeList
	 * @Description: 通过规格获取对应的所有的值列表，用在规格维护页面
	 * @param: @param attributeId
	 * @param: @return   
	 * @return: ModelAndView   
	 * @throws
	 */
	@RequestMapping("getValueListByAttribute")
	public ModelAndView productAttributeList(String attributeId) {
		ModelAndView mav = new ModelAndView();
		List<ProductAttributeValue> list = new ArrayList<ProductAttributeValue>();
		list = productAttributeValueService.getProductAttributeValuesByAttributeId(attributeId);
		String url = "manage/productAttribute/valueList";
		mav.setViewName(url);
		mav.addObject("ValueList", list);
		mav.addObject("attributeId", attributeId);
		return mav;
	}
	
	@RequestMapping("getValueList")
	@ResponseBody
	public ResultBean<ProductAttributeValue> getValueListByAttribute(String attributeId) {
		ResultBean<ProductAttributeValue> result = new ResultBean<ProductAttributeValue>();
		List<ProductAttributeValue> list = new ArrayList<ProductAttributeValue>();
		list = productAttributeValueService.getProductAttributeValuesByAttributeId(attributeId);
		result.setDataList(list);
		return result;
	}
	
	/**
	 * @Title: validateAttributeValue
	 * @Description: 校验要添加或修改的内容是否同名
	 * @param: @param valueInfo
	 * @param: @return   
	 * @return: ResultBean<String>   
	 * @throws
	 */
	@RequestMapping("validateAttributeValue")
	@ResponseBody
	public ResultBean<String> validateAttributeValue(String valueInfo) {
		ProductAttributeValue value = new ProductAttributeValue();
		value = (ProductAttributeValue)ParseUtil.getBeanFromStr(valueInfo, "pojo.ProductAttributeValue");
		ResultBean<String> result = new ResultBean<String>();
		int a = productAttributeValueService.getValueByNameAndAttribute(value);
		if (a > 0) {
			result.setCode(ResultBean.FAIL);
			result.setMsg("该规格下已有同名的内容！");
		}
		return result;
	}
	
	/**
	 * @Title: saveAttributeValue
	 * @Description: 规格值的保存，包括新加和修改
	 * @param: @param valueInfo
	 * @param: @return   
	 * @return: ResultBean<String>   
	 * @throws
	 */
	@RequestMapping("saveAttributeValue")
	@ResponseBody
	public ResultBean<String> saveAttributeValue(String valueInfo) {
		ProductAttributeValue value = new ProductAttributeValue();
		value = (ProductAttributeValue)ParseUtil.getBeanFromStr(valueInfo, "pojo.ProductAttributeValue");
		ResultBean<String> result = new ResultBean<String>();
		//添加
		if(value.getId() == null){
			value.setGmtCreate(DateUtil.getNowDate());
			productAttributeValueService.add(value);
			result.setMsg("添加成功！");
		}else{	//修改
			value.setGmtModified(DateUtil.getNowDate());
			productAttributeValueService.update(value);
			result.setMsg("修改成功！");
		}
		result.setCode(ResultBean.SUCCESS);
		return result;
	}
	
	@RequestMapping("delValue")
	@ResponseBody
	public ResultBean<String> delValue(String valueId) {
		productAttributeValueService.delete(valueId);
		ResultBean<String> result = new ResultBean<String>();
		result.setCode(ResultBean.SUCCESS);
		result.setMsg("删除成功！");
		return result;
	}
}
