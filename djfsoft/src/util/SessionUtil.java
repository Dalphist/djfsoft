package util;

import javax.servlet.http.HttpSession;

import pojo.User;

/**
 * 获取session中相关信息
 * @author DJF
 */
public class SessionUtil {
	/**
	 * 通过session 获取userId
	 * @param session
	 * @return
	 */
	public static int getUserId(HttpSession session) {
		User user = (User) session.getAttribute("user");
		int userId = user.getId();
		return userId;
	}
}
