package tag;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import utils.Page;

/**
 * 分页标签类
 * @author Administrator
 *
 */
public class PageTag extends SimpleTagSupport{

	private String action;//标签参数
	
	@Override
	public void doTag() throws JspException, IOException {
		//获取pageContext对象
		PageContext pageContext = (PageContext)this.getJspContext();
		//获取request对象
		ServletRequest request = pageContext.getRequest();
		//获取当前页面的page对象
		Page page = (Page)request.getAttribute("page");
		//获取项目名称
		String projectName = request.getServletContext().getContextPath();
		
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+projectName+"/";
		String pageHtml = page.getPageBar(action, basePath);
		
		
		System.out.println("projectName = "+projectName);
		System.out.println("basePath = "+basePath);
		System.out.println("pageHtml = "+pageHtml);
		
		
		pageContext.getOut().println(pageHtml);
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
}
