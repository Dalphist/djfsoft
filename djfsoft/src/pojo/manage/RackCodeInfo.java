package pojo.manage;

public class RackCodeInfo {
	private Integer id;
	private Integer warehouse_id;
	private String rack_code;
	private Integer effective_flag;
	//info
	private String warehouseName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getWarehouse_id() {
		return warehouse_id;
	}
	public void setWarehouse_id(Integer warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	public String getRack_code() {
		return rack_code;
	}
	public void setRack_code(String rack_code) {
		this.rack_code = rack_code;
	}
	public Integer getEffective_flag() {
		return effective_flag;
	}
	public void setEffective_flag(Integer effective_flag) {
		this.effective_flag = effective_flag;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	
	
}
