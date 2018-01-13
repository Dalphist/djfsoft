package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;

public class LogTest {
	private static Logger logger = Logger.getLogger(LogTest.class);
	@Test
	public void test1(){
		logger.info("info ");
		logger.error("error");
		logger.warn("warn");
	}
	@Test
	public void test2() throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String s = df.format(new Date());
        Date date = df.parse(s);
		System.out.println(date);
	}
}
