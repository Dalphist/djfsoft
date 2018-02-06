package util.enumSet;

public enum TemplateEnum {
	PurchaseTemplate(1),
	SalesTemplate(2);
	
	private int type;
	
	private TemplateEnum(int type){
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
