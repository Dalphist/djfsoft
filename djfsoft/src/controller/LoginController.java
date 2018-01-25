package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.ResultBean;
import pojo.User;
import service.UserService;
import util.Log;

@Controller
@RequestMapping("")
public class LoginController {
	@Autowired
	UserService userService;

	@RequestMapping("login")
	@ResponseBody
	public ResultBean<String> getUser(String userName, String password,HttpSession session) {
		User user = userService.getUser(new User(userName, password));
		ResultBean<String> result = new ResultBean<String>();
		session.setAttribute("user", user);
		result.setCode(ResultBean.SUCCESS);
		Log.login(user);
		return result;
	}

	@RequestMapping("main")
	public String loginSuccess() {
		String url = "stock/index";
		return url;
	}

}
