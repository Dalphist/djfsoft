package util;

import org.apache.log4j.Logger;
import pojo.User;

public class Log {
	private static final Logger logger = Logger.getLogger(Log.class);
	
	//登录时写入日志
	public static void login(User user){
		String name = user.getUserName();
		String now = DateUtil.getNowDateStr();
		logger.info("*******************************************************************");
		logger.info(name+"登录于： "+now);
	}
}
