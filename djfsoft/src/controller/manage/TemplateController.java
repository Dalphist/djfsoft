package controller.manage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pojo.ResultBean;
import pojo.manage.TemplateDetailInfo;
import pojo.manage.TemplateInfo;
import service.manage.TemplateService;
import util.DateUtil;
import util.ParseUtil;

@Controller
@RequestMapping("manage/template")
public class TemplateController {
	@Autowired
	TemplateService templateService;

	@RequestMapping("index")
	public ModelAndView productAttributeIndex() {
		ModelAndView mav = new ModelAndView();
		String url = "manage/template/index";
		mav.setViewName(url);
		return mav;
	}
	
	@RequestMapping("list")
	public ModelAndView templateList() {
		ModelAndView mav = new ModelAndView();
		List<TemplateInfo> list = new ArrayList<TemplateInfo>();
		list = templateService.templateList();
		String url = "manage/template/templateList";
		mav.setViewName(url);
		mav.addObject("templateList", list);
		return mav;
	}
	
	
	@RequestMapping("validateTemplate")
	@ResponseBody
	public ResultBean<String> validateTemplate(String templateStr) {
		ResultBean<String> result = new ResultBean<String>();
		TemplateInfo templateInfo = new TemplateInfo();
		templateInfo = (TemplateInfo)ParseUtil.getBeanFromStr(templateStr, "pojo.manage.TemplateInfo");
		int count = templateService.existTemplate(templateInfo);
		result.setCode(count == 0?ResultBean.SUCCESS:ResultBean.FAIL);
		String msg = (count == 0?"添加成功！":"该分类已有同名模板！");
		result.setMsg(msg);
		return result;
	}
	/**
	 * 模板的保存（包括新加和修改）
	 * @param templateStr
	 * @return
	 */
	@RequestMapping("saveTemplate")
	@ResponseBody
	public ResultBean<TemplateInfo> saveTemplate(String templateStr) {
		ResultBean<TemplateInfo> result = new ResultBean<TemplateInfo>();
		TemplateInfo templateInfo = new TemplateInfo();
		templateInfo = (TemplateInfo)ParseUtil.getBeanFromStr(templateStr, "pojo.manage.TemplateInfo");
		if(templateInfo.getId() == null){	//新加
			templateInfo.setGmtCreate(DateUtil.getNowDateTime());
			templateInfo.setGmtModified(DateUtil.getNowDateTime());
			templateService.addTemplate(templateInfo);
			result.setMsg("添加成功！");
		}else{		//修改
			templateInfo.setGmtModified(DateUtil.getNowDateTime());
			templateService.updateTemplate(templateInfo);
			result.setMsg("修改成功！");
		}
		return result;
	}
	
	
	@RequestMapping("getDetail")
	public ModelAndView getDetail(String templateId) {
		ModelAndView mav = new ModelAndView();
		List<TemplateDetailInfo> list = new ArrayList<TemplateDetailInfo>();
		list = templateService.getDetail(templateId);
		String url = "manage/template/detailList";
		mav.setViewName(url);
		mav.addObject("detailList", list);
		return mav;
	}
	
	/**
	 * 保存模板详情的商品价格信息
	 * @param templateStr
	 * @return
	 */
	@RequestMapping("saveTemplateDetail")
	@ResponseBody
	public ResultBean<String> saveTemplateDetail(String productListInfo,String templateId) {
		ResultBean<String> result = new ResultBean<String>();
		List<TemplateDetailInfo> list = ParseUtil.getBeanListFromStr(productListInfo, "pojo.manage.TemplateDetailInfo");
		templateService.delTemplateDetail(templateId);
		for(TemplateDetailInfo detail : list){
			detail.setTemplateId(Integer.parseInt(templateId));
			templateService.addTemplateDetail(detail);
		}
		result.setMsg("保存成功！");
		return result;
	}
	
	/**
	 * 获取某一类型的所有模板
	 * @param type
	 * @return
	 */
	@RequestMapping("getTemplateByType")
	@ResponseBody
	public ResultBean<TemplateInfo> getTemplateByType(String type) {
		ResultBean<TemplateInfo> result = new ResultBean<TemplateInfo>();
		List<TemplateInfo> list = templateService.templateListByType(Integer.parseInt(type));
		result.setDataList(list);
		return result;
	}
	
	/**
	 * 将商品按模板套价
	 * @param selectProductIds
	 * @return
	 */
	@RequestMapping("SetPriceWithTemplate")
	@ResponseBody
	public ResultBean<TemplateDetailInfo> SetPriceWithTemplate(String selectProductIds,String templateId) {
		ResultBean<TemplateDetailInfo> result = new ResultBean<TemplateDetailInfo>();
		List<TemplateDetailInfo> list = new ArrayList<TemplateDetailInfo>();
		List<String> productIdList =  ParseUtil.parseFromStrArray(selectProductIds);
		for(String productId : productIdList){
			//通过商品id和模板id获取到该模板下此商品的套价
			TemplateDetailInfo temp = new TemplateDetailInfo();
			temp.setProductId(Integer.parseInt(productId));
			temp.setTemplateId(Integer.parseInt(templateId));
			TemplateDetailInfo detail = templateService.getPriceDetail(temp);
			if(detail != null){
				list.add(detail);
			}
		}
		result.setDataList(list);
		return result;
	}
	
}
