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
			templateService.addTemplate(templateInfo);
			result.setMsg("添加成功！");
		}else{
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
}
