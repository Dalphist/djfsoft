package service.manage;

import java.util.List;

import pojo.District;

public interface DistrictService {
	List<District> getListByParentId(String parentId);
}
