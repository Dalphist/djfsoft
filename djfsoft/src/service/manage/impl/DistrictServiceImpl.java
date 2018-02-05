package service.manage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.manage.DistrictMapper;
import pojo.District;
import service.manage.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService {
	@Autowired
	DistrictMapper districtMapper;

	@Override
	public List<District> getListByParentId(String parentId) {
		return districtMapper.getListByParentId(parentId);
	}


}
