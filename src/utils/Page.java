package utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Page<T> {

	/** 
	* 分页数据 
	*/ 
	private List data = null; 
	/** 
	* 当前页 
	*/ 
	private int curPage; 
	/** 
	* 每页显示的记录数 
	*/ 
	private int pageSize=10; 
	/** 
	* 记录行数 
	*/ 
	private int rowsCount; 
	/** 
	* 页数 
	*/ 
	private int pageCount; 
	/** 
	* 查询参数
	*/ 
	private Map<String,String> params;
	
	public Page() {
		super();
	}
	
	public Page(int pageSize){
		this.pageSize = pageSize;
	}

	public Page(List data, int curPage, int rowsCount,int pageSize) { 
		this.data = data; 
		this.curPage = curPage; 
		this.rowsCount = rowsCount; 
		this.pageSize = pageSize;
		this.pageCount = (((int) Math.ceil((double) rowsCount / pageSize))==0 ? 1 : ((int) Math.ceil((double) rowsCount / pageSize))); 
	} 
	
	public void setParam(List data, int curPage, int rowsCount){
		this.data = data; 
		this.curPage = curPage; 
		this.rowsCount = rowsCount; 
		this.pageCount = (((int) Math.ceil((double) rowsCount / pageSize))==0 ? 1 : ((int) Math.ceil((double) rowsCount / pageSize))); 
	}
	
	/** 
	* getCurPage:返回当前的页数 
	* 
	* @return int 
	*/ 
	public int getCurPage() { 
		return curPage; 
	} 
	public void setCurPage(int curPage){
		this.curPage = curPage;
	}
	
	/** 
	* getPageSize：返回分页大小 
	* 
	* @return int 
	*/ 
	public int getPageSize() { 
		return pageSize; 
	} 
	public void setPageSize(int pageSize){
		this.pageSize = pageSize;
	}
	
	/** 
	* getRowsCount：返回总记录行数 
	* 
	* @return int 
	*/ 
	public int getRowsCount() { 
		return rowsCount; 
	} 
	public void setRowCount(int rowsCount){
		this.rowsCount = rowsCount;
	}
	
	/** 
	* getPageCount：返回总页数 
	* 
	* @return int 
	*/ 
	public int getPageCount() { 
		return pageCount; 
	} 
	/** 
	* 第一页 
	* 
	* @return int 
	*/ 
	public int first() { 
		return 1; 
	} 
	/** 
	* 最后一页 
	* 
	* @return int 
	*/ 
	public int last() { 
		return pageCount; 
	} 
	/** 
	* 上一页 
	* 
	* @return int 
	*/ 
	public int previous() { 
		return (curPage - 1 < 1) ? 1 : curPage - 1; 
	} 
	/** 
	* 下一页 
	* 
	* @return int 
	*/ 
	public int next() { 
		return (curPage + 1 > pageCount) ? pageCount : curPage + 1; 
	} 
	/** 
	* 第一页 
	* 
	* @return boolean 
	*/ 
	public boolean isFirst() { 
		return (curPage == 1) ? true : false; 
	} 
	/** 
	* 最后一页 
	* 
	* @return boolean 
	*/ 
	public boolean isLast() { 
		return (curPage == pageCount) ? true : false; 
	}
	
	public void appendParam(String param,String value){
		if(params==null){
			params = new HashMap<String, String>();
		}
		if(param!=null&&!param.trim().equals("")&&params.containsKey(param)==false){
			params.put(param, value);
		}
	}
	
	/** * 获取当前页数据 
	* 
	* @return Collection 
	*/ 

	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	
	/** 
	* 获取工具条 
	* 
	* @return String 
	*/ 
	public String getPageBar(String action,String basePath) { 
		StringBuffer sb = new StringBuffer();
		//大于1页才有分页工具栏
		if(pageCount>1) {
			sb.append("<form action=\"\" method=\"post\" id=\"pageForm\" name=\"pageForm\">");
			//设置页面参数（如查询条件等）
			if(params!=null) {
				Iterator ite = params.entrySet().iterator();
				while(ite.hasNext()) {
					Map.Entry et = (Map.Entry)ite.next();
					String key = (String)et.getKey();
					String value = (String)et.getValue();
					sb.append("<input type='hidden' name='"+key+"' value='"+(value!=null?value:"")+"'>");
				}
			}
			
			sb.append("<div class=\"row\">");
			sb.append("<div class=\"col-sm-12 col-md-12\">");
			sb.append("<div class=\"dataTables_paginate paging_simple_numbers\">");
			sb.append("<ul class=\"pagination\">");
			//首页和上一页按钮
			if(curPage==1) {
				sb.append("<li class=\"paginate_button page-item previous disabled\"><a href=\"#\" class=\"page-link\">首页</a></li>");
				sb.append("<li class=\"paginate_button page-item previous disabled\"><a href=\"#\" class=\"page-link\">上一页</a></li>");
			}else {
				System.out.println("basePath === "+basePath);
				System.out.println("action == "+action);
				if(action.contains("?")) {
					sb.append("<li class=\"paginate_button page-item\"><a href=\"javascript:pageForm.action='"+basePath+action+"&curPage="+(1)+"';pageForm.submit();\" class=\"page-link\">首页</a></li>");
					sb.append("<li class=\"paginate_button page-item\"><a href=\"javascript:pageForm.action='"+basePath+action+"&curPage="+(curPage-1)+"';pageForm.submit();\" class=\"page-link\">上一页</a></li>");
				}else {
					sb.append("<li class=\"paginate_button page-item\"><a href=\"javascript:pageForm.action='"+basePath+action+"?curPage="+(1)+"';pageForm.submit();\" class=\"page-link\">首页</a></li>");
					sb.append("<li class=\"paginate_button page-item\"><a href=\"javascript:pageForm.action='"+basePath+action+"?curPage="+(curPage-1)+"';pageForm.submit();\" class=\"page-link\">上一页</a></li>");
				}
			}
			//中间的数字页码按钮
			for(int i=1;i<=pageCount;i++) {
				if(action.contains("?")) {
					if(i==curPage) {
						sb.append("<li class=\"paginate_button page-item active\"><a href=\"javascript:pageForm.action='"+basePath+action+"&curPage="+i+"';pageForm.submit();\" class=\"page-link\">"+i+"</a></li>");
					}else {
						sb.append("<li class=\"paginate_button page-item\"><a href=\"javascript:pageForm.action='"+basePath+action+"&curPage="+i+"';pageForm.submit();\" class=\"page-link\">"+i+"</a></li>");
					}
				}else {
					if(i==curPage) {
						sb.append("<li class=\"paginate_button page-item active\"><a href=\"javascript:pageForm.action='"+basePath+action+"?curPage="+i+"';pageForm.submit();\" class=\"page-link\">"+i+"</a></li>");
					}else {
						sb.append("<li class=\"paginate_button page-item\"><a href=\"javascript:pageForm.action='"+basePath+action+"?curPage="+i+"';pageForm.submit();\" class=\"page-link\">"+i+"</a></li>");
					}
				}
			}
			//下一页和最后一页按钮
			if(action.contains("?")) {
				if(curPage<pageCount) {
					sb.append("<li class=\"paginate_button page-item\"><a href=\"javascript:pageForm.action='"+basePath+action+"&curPage="+(curPage+1)+"';pageForm.submit();\" class=\"page-link\">下一页</a></li>");
					sb.append("<li class=\"paginate_button page-item\"><a href=\"javascript:pageForm.action='"+basePath+action+"&curPage="+(pageCount)+"';pageForm.submit();\" class=\"page-link\">最后一页</a></li>");
				}else {
					sb.append("<li class=\"paginate_button page-item previous disabled\"><a href=\"#\" class=\"page-link\">下一页</a></li>");
					sb.append("<li class=\"paginate_button page-item previous disabled\"><a href=\"#\" class=\"page-link\">最后一页</a></li>");
				}
			}else {
				if(curPage<pageCount) {
					sb.append("<li class=\"paginate_button page-item\"><a href=\"javascript:pageForm.action='"+basePath+action+"?curPage="+(curPage+1)+"';pageForm.submit();\" class=\"page-link\">下一页</a></li>");
					sb.append("<li class=\"paginate_button page-item\"><a href=\"javascript:pageForm.action='"+basePath+action+"?curPage="+(pageCount)+"';pageForm.submit();\" class=\"page-link\">最后一页</a></li>");
				}else {
					sb.append("<li class=\"paginate_button page-item previous disabled\"><a href=\"#\" class=\"page-link\">下一页</a></li>");
					sb.append("<li class=\"paginate_button page-item previous disabled\"><a href=\"#\" class=\"page-link\">最后一页</a></li>");
				}
			}
			sb.append("</ul></div></div></div></form>"); 
		}
		
		return sb.toString(); 
	}
	
}
