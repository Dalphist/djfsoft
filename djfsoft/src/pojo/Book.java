package pojo;

public class Book {
	private int id;
	private String name;
	private String code;
	private int type_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public Book(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}
	public Book() {
		super();
	}
	
	
}
