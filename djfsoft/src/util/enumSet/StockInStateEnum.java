package util.enumSet;

public enum StockInStateEnum {
	ARRIVED("0"),
	STOCKIN("1");
	
	private String state;
	
	private StockInStateEnum(String state){
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
