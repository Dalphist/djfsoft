package mapper;

import java.util.List;

import pojo.BookType;



public interface BookTypeMapper {
	
	public void add(BookType bookType);  
    
    public void delete(int id);  
        
    public void update(BookType bookType);   
        
    public List<BookType> getRootTypesList();
    
    public List<BookType> getChildrenTypesList(String rootType_id);
    
    
     
}
