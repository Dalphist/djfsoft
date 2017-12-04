package controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pojo.Student;

import service.StudentService;

@Controller
@RequestMapping("")
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@RequestMapping("studentList")
	public ModelAndView studentList(){
		ModelAndView mav = new ModelAndView();
		List<Student> list = studentService.list();
		mav.setViewName("studentList");
		mav.addObject("list",list);
		return mav;
	}
}
