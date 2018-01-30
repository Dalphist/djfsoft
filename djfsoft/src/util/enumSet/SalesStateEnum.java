package util.enumSet;

public enum SalesStateEnum {
	SALESORDER("0"),
	STOCKOUT("1");
	
	private String state;
	
	private SalesStateEnum(String state){
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
