package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.Student;

import mapper.StudentMapper;
import service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentMapper studentMapper;

	public List<Student> list() {
		return studentMapper.list();
	}
	
	
}
