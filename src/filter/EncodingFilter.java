package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 乱码过滤器
 * @author Administrator
 *
 */
@WebFilter("/*")
public class EncodingFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("乱码过滤器销毁");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		MyRequest myRequest = new MyRequest((HttpServletRequest)request);
		//过滤器放行
		chain.doFilter(myRequest, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("乱码过滤器初始化");
		
	}
	
	public class MyRequest extends HttpServletRequestWrapper{
		private HttpServletRequest request;
		/**
		 * 构造函数
		 * @param request
		 */
		public MyRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		
		/**
		 * 重写getParameter（用于获取表单参数）
		 */
		@Override
		public String getParameter(String name) {
			String value = request.getParameter(name);
			if(value!=null) {
				try {
					value = new String(value.getBytes("utf-8"),"utf-8");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			return value;
		}
		
	}

}
