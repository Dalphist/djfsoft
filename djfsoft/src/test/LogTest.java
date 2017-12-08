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
		int a = 0;
		int b = 0;
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < 100000000; i++) {
			a++;
			b++;
		}
		System.out.println(System.currentTimeMillis()- t1);
		int c = 0;
		int d = 0;
		long t2 = System.currentTimeMillis();
		for (int i = 0; i < 100000000; i++) {
			c++;
		}
		for (int i = 0; i < 100000000; i++) {
			d++;
		}
		System.out.println(System.currentTimeMillis()- t2);
		
	}
}
