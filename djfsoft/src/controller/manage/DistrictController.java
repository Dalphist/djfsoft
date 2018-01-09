package controller.manage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.District;
import pojo.ResultBean;
import service.DistrictService;

@Controller
@RequestMapping("district")
public class DistrictController {
	@Autowired
	DistrictService districtService;
	
	@RequestMapping("list")
	@ResponseBody
	public ResultBean<District> productList(String parentId) {
		List<District> list = new ArrayList<District>();
		list = districtService.getListByParentId(parentId);
		ResultBean<District> result = new ResultBean<District>();
		result.setDataList(list);
		return result;
	}
}
