package mapper.manage;

import java.util.List;

import pojo.manage.TemplateDetailInfo;
import pojo.manage.TemplateInfo;

public interface TemplateMapper {
	public void addTemplate(TemplateInfo templateInfo);

	public void addTemplateDetail(TemplateDetailInfo templateDetailInfo);

	public void delTemplate(String templateId);

	public void delTemplateDetail(String templateId);

	public void updateTemplate(TemplateInfo templateInfo);

	public List<TemplateInfo> templateList();

	public List<TemplateInfo> templateListByType(int type);
	
	public List<TemplateDetailInfo> getDetail(String templateId);
	
	public int existTemplate(TemplateInfo templateInfo);
}
