package util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

public class ParseUtil {
	/**
	 * 从前台传过来的JSON字符串转成对应的bean
	 * @Title: getBeanFromStr
	 * @Description: TODO
	 * @param: @param beanStr
	 * @param: @param className
	 * @param: @return   
	 * @return: Object   
	 * @throws
	 */
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
	
	public static List<String> parseFromStrArray(String data) {
		List<String> list = new ArrayList<String>();
		if (null == data || data.length() == 0 || data.length() < 4) {
			return null;
		}
		String temp = data.substring(2, data.length() - 2);
		temp = temp.replaceAll("\"", "");
		String splitResult[] = temp.split(",");
		for (int i = 0; i < splitResult.length; i++) {
			list.add(splitResult[i]);
			// list.add(splitResult[i].substring(splitResult[i].indexOf(":") + 1));
		}
		return list;
	}
}
