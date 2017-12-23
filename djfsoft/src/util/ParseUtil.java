package util;

import net.sf.json.JSONObject;

public class ParseUtil {
	public static Object getBeanFromStr(String beanStr,String className){
		Object obj = null;
		try {
			Class<?> clazz = Class.forName(className);
			obj = clazz.newInstance();
			JSONObject info = JSONObject.fromObject(beanStr);
			obj = JSONObject.toBean(info, clazz);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
