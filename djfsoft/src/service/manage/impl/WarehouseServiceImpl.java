package service.manage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.manage.WarehouseMapper;
import pojo.manage.RackCodeInfo;
import pojo.manage.WarehouseInfo;
import service.manage.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService {
	@Autowired
	WarehouseMapper warehouseMapper;

	@Override
	public void addWarehouse(WarehouseInfo warehouseInfo) {
		warehouseMapper.addWarehouse(warehouseInfo);
		return;
	}

	@Override
	public void deleteWarehouse(String warehouseId) {
		warehouseMapper.deleteWarehouse(warehouseId);
		return;
	}

	@Override
	public void updateWarehouse(WarehouseInfo warehouseInfo) {
		warehouseMapper.updateWarehouse(warehouseInfo);
		return;
	}

	@Override
	public WarehouseInfo getWarehouseInfo(WarehouseInfo warehouseInfo) {
		return warehouseMapper.getWarehouseInfo(warehouseInfo);
	}

	@Override
	public List<WarehouseInfo> getWarehouseInfoList() {
		return warehouseMapper.getWarehouseInfoList();
	}

	@Override
	public void addRackCode(RackCodeInfo rackCodeInfo) {
		warehouseMapper.addRackCode(rackCodeInfo);
		return;
	}

	@Override
	public void deleteRackCode(String warehouseId) {
		warehouseMapper.deleteRackCode(warehouseId);
		return;
	}

	@Override
	public RackCodeInfo getRackCodeInfo(RackCodeInfo rackCodeInfo) {
		return warehouseMapper.getRackCodeInfo(rackCodeInfo);
	}

	@Override
	public List<RackCodeInfo> getRackCodeInfoList() {
		return warehouseMapper.getRackCodeInfoList();
	}

}
