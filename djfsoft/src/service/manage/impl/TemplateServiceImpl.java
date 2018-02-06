package service.manage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.manage.TemplateMapper;
import pojo.manage.TemplateDetailInfo;
import pojo.manage.TemplateInfo;
import service.manage.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {
	@Autowired
	TemplateMapper templateMapper;

	@Override
	public void addTemplate(TemplateInfo purchaseTemplateInfo) {
		templateMapper.addTemplate(purchaseTemplateInfo);
		return;
	}

	@Override
	public void addTemplateDetail(TemplateDetailInfo purchaseTemplateDetailInfo) {
		templateMapper.addTemplateDetail(purchaseTemplateDetailInfo);
		return;
	}

	@Override
	public void delTemplate(String templateId) {
		templateMapper.delTemplate(templateId);
		return;
	}

	@Override
	public void delTemplateDetail(String templateId) {
		templateMapper.delTemplateDetail(templateId);
		return;
	}

	@Override
	public void updateTemplate(TemplateInfo purchaseTemplateInfo) {
		templateMapper.updateTemplate(purchaseTemplateInfo);
		return;
	}

	@Override
	public List<TemplateInfo> templateList() {
		return templateMapper.templateList();
	}

	@Override
	public List<TemplateDetailInfo> getDetail(String templateId) {
		return templateMapper.getDetail(templateId);
	}

	
}
