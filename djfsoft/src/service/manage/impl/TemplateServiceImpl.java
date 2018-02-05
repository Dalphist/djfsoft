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
	TemplateMapper purchaseTemplateMapper;

	@Override
	public void addTemplate(TemplateInfo purchaseTemplateInfo) {
		purchaseTemplateMapper.addTemplate(purchaseTemplateInfo);
		return;
	}

	@Override
	public void addTemplateDetail(TemplateDetailInfo purchaseTemplateDetailInfo) {
		purchaseTemplateMapper.addTemplateDetail(purchaseTemplateDetailInfo);
		return;
	}

	@Override
	public void delTemplate(String templateId) {
		purchaseTemplateMapper.delTemplate(templateId);
		return;
	}

	@Override
	public void delTemplateDetail(String templateId) {
		purchaseTemplateMapper.delTemplateDetail(templateId);
		return;
	}

	@Override
	public void updateTemplate(TemplateInfo purchaseTemplateInfo) {
		purchaseTemplateMapper.updateTemplate(purchaseTemplateInfo);
		return;
	}

	@Override
	public List<TemplateInfo> templateList() {
		return purchaseTemplateMapper.templateList();
	}

	@Override
	public List<TemplateDetailInfo> getDetail(String templateId) {
		return purchaseTemplateMapper.getDetail(templateId);
	}

	
}
