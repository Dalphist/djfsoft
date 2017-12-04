package mapper;

import java.util.List;

import pojo.Student;


public interface StudentMapper {
	public void add(Student student);  
    
    public void delete(int id);  
        
    public Student get(int id);  
      
    public void update(Student student);   
        
    public List<Student> list();
     
    public int count();  
}
