package test;

import java.time.LocalDate;

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
	public void test2(){
		LocalDate d = LocalDate.now();
		System.out.println(d);
	}
}
