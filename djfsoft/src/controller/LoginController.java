package controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pojo.Book;
import pojo.User;

import service.BookService;
import service.UserService;
import test.LogTest;
import util.Log;
import util.ResponseUtil;

@Controller
@RequestMapping("")
public class LoginController {
	@Autowired
	BookService bookService;
	@Autowired
	UserService userService;
	//第二种写法，参数可以直接写key名，自动获取。注解ResponseBody可以把返回值直接转成json返回到页面
	@RequestMapping("login")
	@ResponseBody
	public Map<String,String> getUser(String user_name,String password,HttpSession session){
		User user = userService.getUser(new User(user_name,password));
		Map<String,String> map = new HashMap<String, String>();
		String result = (user == null?"0":"1");
		session.setAttribute("user",user);
//		Log.login(user);
		map.put("result", result);
		return map;
	}
	
	@RequestMapping("main")
	public String loginSuccess(){
		String url = "main";
		return url;
	}
	
}
