package util.enumSet;

public enum PurchaseStateEnum {
	NOCHECK("1"),
	CHECKED("2"),
	ARRIVED("3"),
	STOCKIN("4");
	
	private String state;
	
	private PurchaseStateEnum(String state){
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
