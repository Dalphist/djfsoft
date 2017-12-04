package util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	//将结果集输出在页面
		public static void write(HttpServletResponse response,Object o)throws Exception{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(o.toString());
			out.flush();
			out.close();
		}
}
