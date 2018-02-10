package test;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.junit.Test;

import util.enumSet.SalesStateEnum;

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
		String s =  SalesStateEnum.SALESORDER.getState();
		System.out.println(s);
	}
}
