package service.manage;

import java.util.List;

import pojo.manage.RackCodeInfo;
import pojo.manage.WarehouseInfo;

public interface WarehouseService {
	
	void addWarehouse(WarehouseInfo warehouseInfo);  
    
    void deleteWarehouse(String warehouseId);  
        
    void updateWarehouse(WarehouseInfo warehouseInfo);   
    
    WarehouseInfo getWarehouseInfo(WarehouseInfo warehouseInfo);
    
    List<WarehouseInfo> getWarehouseInfoList();
    
    void addRackCode(RackCodeInfo rackCodeInfo);  
    
    void deleteRackCode(String warehouseId);  
        
    RackCodeInfo getRackCodeInfo(RackCodeInfo rackCodeInfo);
    
    List<RackCodeInfo> getRackCodeInfoList(String warehouseId);

}
