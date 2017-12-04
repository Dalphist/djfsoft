//package aop;
//
//import org.apache.log4j.Logger;
//import org.aspectj.lang.ProceedingJoinPoint;
//
//import pojo.ResultBean;
//
//public class ControllerAOP {
//	private static final Logger logger = Logger.getLogger(ControllerAOP.class);
//	
//	public Object handlerControllerMethod(ProceedingJoinPoint pjp){
//		long startTime = System.currentTimeMillis();
//		ResultBean<?> result;
//		
//		try {
//			result = (ResultBean<?>) pjp.proceed();
//			logger.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
//		} catch (Throwable e) {
//			result = handlerException(pjp, e);
//		}
//		return result;
//	}
//
//	private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
//		ResultBean<?> result = new ResultBean();
//		logger.error(pjp.getSignature() + " error ", e);
//		result.setMsg(e.toString());
//		result.setCode(ResultBean.FAIL);
//		return result;
//	}
//}
