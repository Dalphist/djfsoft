package mapper;

import java.util.List;

import pojo.User;


public interface UserMapper {
	public void add(User user);  
    
    public void delete(int id);  
        
    public User get(User user);  
      
    public void update(User user);   
        
    public List<User> list();
     
    public int count();  
}
