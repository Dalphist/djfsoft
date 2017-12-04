//package aop;
//
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.apache.log4j.Logger;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import pojo.User;
//
//import com.google.gson.Gson;
//
//@Aspect
//public class TestAnnotationAspect {
//	private static final Logger logger = Logger.getLogger(TestAnnotationAspect.class);
//	private String requestPath = null; // 请求地址
//	private String userName = null; // 用户名
//	private Map<?, ?> inputParamMap = null; // 传入参数
//	private Map<String, Object> outputParamMap = null; // 存放输出结果
//	private long startTimeMillis = 0; // 开始时间
//	private long endTimeMillis = 0; // 结束时间
//	
//	@Around("execution(* service.*.*(..))")
//	public Object process(ProceedingJoinPoint pjp) throws Throwable {
//		/**
//		 * 1.获取request信息 2.根据request获取session 3.从session中取出登录用户信息
//		 */
//		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//		HttpServletRequest request = sra.getRequest();
//		HttpSession session = request.getSession();
//		// 从session中获取用户信息
//		User user = (User) session.getAttribute("user");
//		if (user != null) {
//			userName = user.getName();
//		} else {
//			userName = "用户未登录";
//		}
//		// 获取输入参数
//		inputParamMap = request.getParameterMap();
//		// 获取请求地址
//		requestPath = request.getRequestURI();
//
//		// 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
//		outputParamMap = new HashMap<String, Object>();
//		Object result = pjp.proceed();// result的值就是被拦截方法的返回值
//		outputParamMap.put("result", result);
//
//		return result;
//	}
//
//	@Before("execution(* service.*.*(..))")
//	public void permissionCheck(JoinPoint point) {
//		startTimeMillis = System.currentTimeMillis();
//		// System.out.println("@Before：模拟权限检查...");
//		// System.out.println("@Before：目标方法为：" +
//		// point.getSignature().getDeclaringTypeName() + "."
//		// + point.getSignature().getName());
//		// System.out.println("@Before：参数为：" +
//		// Arrays.toString(point.getArgs()));
//		// System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
//	}
//
//	@AfterReturning(pointcut = "execution(* service.*.*(..))", returning = "returnValue")
//	public void log(JoinPoint point, Object returnValue) {
//		endTimeMillis = System.currentTimeMillis(); // 记录方法执行完成的时间
//		this.printOptLog();
//		// System.out.println("@AfterReturning：模拟日志记录功能...");
//		// System.out.println("@AfterReturning：目标方法为：" +
//		// point.getSignature().getDeclaringTypeName() + "."
//		// + point.getSignature().getName());
//		// System.out.println("@AfterReturning：参数为：" +
//		// Arrays.toString(point.getArgs()));
//		// System.out.println("@AfterReturning：返回值为：" + returnValue);
//		// System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
//	}
//
//	@After("execution(* service.*.*(..))")
//	public void releaseResource(JoinPoint point) {
//		// System.out.println("@After：模拟释放资源...");
//		// System.out.println("@After：目标方法为：" +
//		// point.getSignature().getDeclaringTypeName() + "."
//		// + point.getSignature().getName());
//		// System.out.println("@After：参数为：" + Arrays.toString(point.getArgs()));
//		// System.out.println("@After：被织入的目标对象为：" + point.getTarget());
//	}
//	
//	private void printOptLog() {
//		Gson gson = new Gson();
//		String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);
//		logger.info("\n user：" + userName + "  url：" + requestPath + "; op_time：" + optTime + " pro_time："
//				+ (endTimeMillis - startTimeMillis) + "ms ;" + " param：" + gson.toJson(inputParamMap) + ";"
//				+ "\n result：" + gson.toJson(outputParamMap));
//	}
//}
