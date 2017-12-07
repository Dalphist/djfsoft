package util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class MyDate {
	public static String getCurrentTime(){
		String time = "";
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		time = sdf.format(date);
		return time;
	}
	public static LocalDateTime getCurrentDate(){
		return null;
	}
}
