package mapper.manage;

import java.util.List;

import pojo.District;

public interface DistrictMapper {

	public List<District> getListByParentId(String parentId);
	
}
