package mapper.manage;

import java.util.List;

import pojo.manage.RackCodeInfo;
import pojo.manage.WarehouseInfo;



public interface WarehouseMapper {
	
	public void addWarehouse(WarehouseInfo warehouseInfo);  
    
    public void deleteWarehouse(String warehouseId);  
        
    public void updateWarehouse(WarehouseInfo warehouseInfo);   
    
    public WarehouseInfo getWarehouseInfo(WarehouseInfo warehouseInfo);
    
    public List<WarehouseInfo> getWarehouseInfoList();
    
    public void addRackCode(RackCodeInfo rackCodeInfo);  
    
    public void deleteRackCode(String warehouseId);  
        
    public RackCodeInfo getRackCodeInfo(RackCodeInfo rackCodeInfo);
    
    public List<RackCodeInfo> getRackCodeInfoList(String warehouseId);
    
}
