package controller;

import javax.servlet.http.HttpServletRequest;
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
	public ResultBean<String> getUser(String userName, String password,HttpServletRequest request) {
		User user = userService.getUser(new User(userName, password));
		ResultBean<String> result = new ResultBean<String>();
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		result.setCode(ResultBean.SUCCESS);
		Log.login(user);
		return result;
	}

}
