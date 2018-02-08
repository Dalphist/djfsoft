package service.manage;

import java.util.List;

import pojo.manage.TemplateDetailInfo;
import pojo.manage.TemplateInfo;

public interface TemplateService {
	void addTemplate(TemplateInfo templateInfo);

	void addTemplateDetail(TemplateDetailInfo templateDetailInfo);

	void delTemplate(String templateId);

	void delTemplateDetail(String templateId);

	void updateTemplate(TemplateInfo purchaseTemplateInfo);

	List<TemplateInfo> templateList();
	
	List<TemplateInfo> templateListByType(int type);

	List<TemplateDetailInfo> getDetail(String templateId);
	
	int existTemplate(TemplateInfo templateInfo);
}
