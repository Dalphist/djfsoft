package controller.manage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pojo.ResultBean;
import pojo.manage.RackCodeInfo;
import pojo.manage.WarehouseInfo;
import service.manage.WarehouseService;
import util.ParseUtil;

@Controller
@RequestMapping("manage/warehouse")
public class WarehouseController {
	@Autowired
	WarehouseService warehouseService;
	
	@RequestMapping("index")
	public ModelAndView warehouseIndex() {
		ModelAndView mav = new ModelAndView();
		String url = "manage/warehouse/index";
		mav.setViewName(url);
		return mav;
	}
	
	@RequestMapping("warehouseList")
	public ModelAndView warehouseList() {
		ModelAndView mav = new ModelAndView();
		List<WarehouseInfo> list = new ArrayList<WarehouseInfo>();
		list = warehouseService.getWarehouseInfoList();
		String url = "manage/warehouse/warehouseList";
		mav.setViewName(url);
		mav.addObject("warehouseList", list);
		return mav;
	}
	
	@RequestMapping("warehouseSelect")
	@ResponseBody
	public ResultBean<WarehouseInfo> warehouseSelect() {
		ResultBean<WarehouseInfo> result = new ResultBean<WarehouseInfo>();
		List<WarehouseInfo> list = warehouseService.getWarehouseInfoList();
		result.setDataList(list);
		return result;
	}
	
	/**
	 * @Title: validateWarehouse
	 * @Description: 校验仓库名称是否重复，用在添加和修改仓库
	 * @param: @param warehouseInfo
	 * @param: @return   
	 * @return: ResultBean<String>   
	 * @throws
	 */
	@RequestMapping("validateWarehouse")
	@ResponseBody
	public ResultBean<String> validateWarehouse(String warehouseInfo) {
		WarehouseInfo warehouse = new WarehouseInfo();
		warehouse = (WarehouseInfo)ParseUtil.getBeanFromStr(warehouseInfo, "pojo.manage.WarehouseInfo");
		ResultBean<String> result = new ResultBean<String>();
		if (warehouseService.getWarehouseInfo(warehouse) != null) {
			result.setCode(ResultBean.FAIL);
			result.setMsg("仓库名称重复！");
		}
		return result;
	}
	
	/**
	 * @Title: saveWarehouse
	 * @Description: 仓库保存，包括新加和修改
	 * @param: @param warehouseInfo
	 * @param: @return   
	 * @return: ResultBean<String>   
	 * @throws
	 */
	@RequestMapping("saveWarehouse")
	@ResponseBody
	public ResultBean<String> saveWarehouse(String warehouseInfo) {
		WarehouseInfo warehouse = new WarehouseInfo();
		warehouse = (WarehouseInfo)ParseUtil.getBeanFromStr(warehouseInfo, "pojo.manage.WarehouseInfo");
		ResultBean<String> result = new ResultBean<String>();
		//添加
		if(warehouse.getId() == null){
			warehouseService.addWarehouse(warehouse);
			result.setMsg("添加成功！");
		}else{	//修改
			warehouseService.updateWarehouse(warehouse);
			result.setMsg("修改成功！");
		}
		result.setCode(ResultBean.SUCCESS);
		return result;
	}
	
	
	/**
	 * @Title: delWarehouse
	 * @Description: 删除
	 * @param: @param warehouseId
	 * @param: @return   
	 * @return: ResultBean<String>   
	 * @throws
	 */
	@RequestMapping("delWarehouse")
	@ResponseBody
	public ResultBean<String> delWarehouse(String warehouseId) {
		warehouseService.deleteWarehouse(warehouseId);
		ResultBean<String> result = new ResultBean<String>();
		result.setCode(ResultBean.SUCCESS);
		result.setMsg("删除成功！");
		return result;
	}
	
	
	/**
	 * @Title: getRackCodeList
	 * @Description: 获取仓库拥有的货位列表
	 * @param: @param warehouseId
	 * @param: @return   
	 * @return: ModelAndView
	 * @throws
	 */
	
	@RequestMapping("getRackCodeList")
	public ModelAndView getRackCodeList(String warehouseId) {
		ModelAndView mav = new ModelAndView();
		List<RackCodeInfo> list = new ArrayList<RackCodeInfo>();
		list = warehouseService.getRackCodeInfoList(warehouseId);
		String url = "manage/warehouse/rackCodeList";
		mav.setViewName(url);
		mav.addObject("rackCodeList", list);
		mav.addObject("warehouseId", warehouseId);
		return mav;
	}
	
	@RequestMapping("getRackCodeSelect")
	@ResponseBody
	public ResultBean<RackCodeInfo> getRackCodeSelect(String warehouseId) {
		ResultBean<RackCodeInfo> result = new ResultBean<RackCodeInfo>();
		List<RackCodeInfo> list = warehouseService.getRackCodeInfoList(warehouseId);
		result.setDataList(list);
		return result;
	}
	
	@RequestMapping("validateRackCode")
	@ResponseBody
	public ResultBean<String> validateRackCode(String rackCodeInfo) {
		RackCodeInfo rackCode = new RackCodeInfo();
		rackCode = (RackCodeInfo)ParseUtil.getBeanFromStr(rackCodeInfo, "pojo.manage.RackCodeInfo");
		ResultBean<String> result = new ResultBean<String>();
		if (warehouseService.getRackCodeInfo(rackCode) != null) {
			result.setCode(ResultBean.FAIL);
			result.setMsg("货位号重复！");
		}
		return result;
	}
	
	@RequestMapping("saveRackCode")
	@ResponseBody
	public ResultBean<String> saveRackCode(String rackCodeInfo) {
		RackCodeInfo rackCode = new RackCodeInfo();
		rackCode = (RackCodeInfo)ParseUtil.getBeanFromStr(rackCodeInfo, "pojo.manage.RackCodeInfo");
		ResultBean<String> result = new ResultBean<String>();
		//添加
		if(rackCode.getId() == null){
			warehouseService.addRackCode(rackCode);
			result.setMsg("添加成功！");
		}else{	//修改
			warehouseService.updateRackCode(rackCode);
			result.setMsg("修改成功！");
		}
		result.setCode(ResultBean.SUCCESS);
		return result;
	}
	
	@RequestMapping("delRackCode")
	@ResponseBody
	public ResultBean<String> delRackCode(String rackCodeId) {
		warehouseService.deleteRackCode(rackCodeId);
		ResultBean<String> result = new ResultBean<String>();
		result.setCode(ResultBean.SUCCESS);
		result.setMsg("删除成功！");
		return result;
	}
	
}
