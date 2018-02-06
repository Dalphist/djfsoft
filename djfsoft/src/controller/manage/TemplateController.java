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
import util.ParseUtil;
import util.enumSet.TemplateEnum;

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
	
	@RequestMapping("saveTemplate")
	@ResponseBody
	public ResultBean<TemplateInfo> saveTemplate(String templateStr) {
		ResultBean<TemplateInfo> result = new ResultBean<TemplateInfo>();
		TemplateInfo templateInfo = new TemplateInfo();
		templateInfo = (TemplateInfo)ParseUtil.getBeanFromStr(templateStr, "pojo.manage.TemplateInfo");
		templateService.addTemplate(templateInfo);
		result.setMsg("添加成功！");
		return result;
	}
	
	
	@RequestMapping("getDetail")
	@ResponseBody
	public ResultBean<TemplateDetailInfo> getAllAttribute(String templateId) {
		ResultBean<TemplateDetailInfo> result = new ResultBean<TemplateDetailInfo>();
		List<TemplateDetailInfo> list = new ArrayList<TemplateDetailInfo>();
		list = templateService.getDetail(templateId);
		result.setDataList(list);
		return result;
	}
}
