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

@Controller
@RequestMapping("purchase/purchaseTemplate")
public class TemplateController {
	@Autowired
	TemplateService purchaseTemplateService;

	@RequestMapping("index")
	public ModelAndView productAttributeIndex() {
		ModelAndView mav = new ModelAndView();
		String url = "purchase/purchaseTemplate/index";
		mav.setViewName(url);
		return mav;
	}
	
	@RequestMapping("list")
	public ModelAndView productAttributeList() {
		ModelAndView mav = new ModelAndView();
		List<TemplateInfo> list = new ArrayList<TemplateInfo>();
		list = purchaseTemplateService.templateList();
		String url = "purchase/purchaseTemplate/list";
		mav.setViewName(url);
		mav.addObject("templateList", list);
		return mav;
	}
	
	@RequestMapping("getDetail")
	@ResponseBody
	public ResultBean<TemplateDetailInfo> getAllAttribute(String templateId) {
		ResultBean<TemplateDetailInfo> result = new ResultBean<TemplateDetailInfo>();
		List<TemplateDetailInfo> list = new ArrayList<TemplateDetailInfo>();
		list = purchaseTemplateService.getDetail(templateId);
		result.setDataList(list);
		return result;
	}
}
