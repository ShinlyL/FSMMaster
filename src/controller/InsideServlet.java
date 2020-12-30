package controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Admin;
import bean.Inside;
import impl.InsideServiceImp;
import utils.Page;
//通配符URL   .do 结尾的所有的请求都会进入这个 servlet
@WebServlet("*.ido")
public class InsideServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	InsideServiceImp insideService = new InsideServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		// 获取请求的URI地址信息
		// http://localhost:8080/equip/InsideList.do
		String url = request.getRequestURI();
		
		// 截取其中的方法名InsideList
		String methodName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
		Method method = null;
		try {
			// 使用反射机制获取在本类中声明了的方法
			method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			// 执行方法
			method.invoke(this, request, response);
		} catch (Exception e) {
			throw new RuntimeException("调用方法出错！");
		}
	}

	/*
	 * 列表查询
	 */
	public void insideList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取当前要查的页码
		String curPageStr = request.getParameter("curPage");
		//第一次进入的时候，默认为第一页
		int curPage = 1;
		if (curPageStr != null) {
			//如果不是第一次进入     将页面传过来的页码赋值给初始的第一页
			curPage = Integer.parseInt(curPageStr);
		}
		// 获取分页对象
		Page page = (Page) request.getAttribute("page");
		//判断是否是第一次请求
		if (page == null) {
			page = new Page(6);// 参数为每页显示数量
		}
		// 去数据库查询类型管理表，获取数据 返回是list
		page = insideService.getInsidePage(page, curPage);
		// 将数据返回给页面
		request.setAttribute("page", page);
		request.setAttribute("status", "0");
		request.getRequestDispatcher("/WEB-INF/page/admin/inside-list.jsp").forward(request, response);
	}
	/*
	 * 列表查询 所有的申领记录 admin
	 * 0-未审核的申领记录  1-已审核的申领记录 2-驳回的申领记录
	 */
	public void searchInsideByFlag(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取页面请求中的id的值 0--未审核申领  1--已审核申领 2-不通过的申领记录
		String flag = request.getParameter("searchFlag");
		flag = new String(flag.getBytes("iso-8859-1"), "utf-8");
		// 获取当前要查的页码
		String curPageStr = request.getParameter("curPage");
		//第一次进入的时候，默认为第一页
		int curPage = 1;
		if (curPageStr != null) {
			//如果不是第一次进入     将页面传过来的页码赋值给初始的第一页
			curPage = Integer.parseInt(curPageStr);
		}
		// 获取分页对象
		Page page = (Page) request.getAttribute("page");
		//判断是否是第一次请求
		if (page == null) {
			page = new Page(6);// 参数为每页显示数量
		}
		if(flag.equals("已入库")) {
			page = insideService.getInsidePage(page, curPage, "1");
		}else if(flag.equals("未入库")) {
			page = insideService.getInsidePage(page, curPage, "0");
		}else{
			page = insideService.getInsidePage(page, curPage);
			flag="0";
		}
		// 将数据返回给页面
		request.setAttribute("page", page);
		request.setAttribute("flagStatus", flag);
		request.getRequestDispatcher("/WEB-INF/page/admin/inside-list.jsp").forward(request, response);
	}
	//删除
	public void delInside(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//获取页面的id参数
		String id=	request.getParameter("id");
		int res = insideService.delInside(id);;
		if(res>0){
			response.getWriter().write("ok");
		}else {
			response.getWriter().write("no");
		}
	}
	
	//跳转到首页面去
		public void toIndexView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			request.getRequestDispatcher("/WEB-INF/page/view/indexView.jsp").forward(request, response);
		}

	//跳转到新增页面去
	public void toAddInside(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/page/admin/inside-add.jsp").forward(request, response);
	}
	
	
	
	//新增保存
	public void insideAdd(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//获取到参数
		String name = request.getParameter("name");
		String barcode = request.getParameter("barcode");
		String purchaseprice =	request.getParameter("purchaseprice");
		String num = request.getParameter("num");
		Double sums = Double.valueOf(purchaseprice) * Integer.valueOf(num);
		Admin admin = (Admin)request.getSession().getAttribute("adminInfo");
		String manager = admin.getUsername();
				
		//时间 日期格式化  date   转化成string
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//将数据封装成Inside对象
		Inside  inside = new Inside();
		inside.setId(null);
		inside.setName(name);
		inside.setBarcode(barcode);
		inside.setPurchaseprice(Double.valueOf(purchaseprice));
		inside.setNum(Integer.parseInt(num));
		inside.setSums(sums);
		inside.setManager(manager);
		inside.setReleaseDate(sdf.format(new Date()));
		inside.setStatus(0);
		
		//将数据插入到数据库中
		int result=insideService.insertInside(inside);
		if(result>0){
			//重定向   在此sevlet方法中调用另外一个方法
			response.sendRedirect("insideList.ido");
		}else{
			response.getWriter().write("系统异常,新增数据失败,3秒后跳转回添加页面");
			response.setHeader("refresh", "3;url=insideList.ido");
		}
	}
	
	//更新页面跳转
	public void updateInside(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String  id=	request.getParameter("id");
		Inside inside = insideService.queryInside(id);
		request.setAttribute("inside",inside);
		request.getRequestDispatcher("/WEB-INF/page/admin/inside-update.jsp").forward(request, response);
	}
	
	/**
	 * 保存修改的数据
	 */
	public void updateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取到参数
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String barcode = request.getParameter("barcode");
		String purchaseprice = request.getParameter("purchaseprice");
		String num = request.getParameter("num");
		
		Double sums = Double.valueOf(purchaseprice) * Integer.valueOf(num);
		Admin admin = (Admin)request.getSession().getAttribute("adminInfo");
		String manager = admin.getUsername();
		
		String releaseDate = request.getParameter("releaseDate");
		//对releaseDate的值进行 转换
		releaseDate = releaseDate.replace('T', ' ').concat(":00");
		//将数据封装成Inside对象
		Inside  inside = new Inside();
		
		inside.setId(Integer.valueOf(id));
		inside.setName(name);
		inside.setBarcode(barcode);
		inside.setPurchaseprice(Double.valueOf(purchaseprice));
		inside.setNum(Integer.parseInt(num));
		inside.setSums(Double.valueOf(sums));
		inside.setManager(manager);
		inside.setReleaseDate(releaseDate);
		inside.setStatus(0);
		
		int result=0;
		result = insideService.updateInside(inside);
		if(result>0){
			//重定向   在此sevlet方法中调用另外一个方法
			response.sendRedirect("insideList.ido");
		}else{
			response.getWriter().write("系统异常,保存数据失败,3秒后跳转回修改页面");
			response.setHeader("refresh", "3;url=insideList.ido");
		}
	}
	/*
	 * Admin 入库的物品信息的添加 物品名称的输入检测
	 */
	public void ajaxnameCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		name = new String(name.getBytes("iso-8859-1"), "utf-8");
		String regEx1 = "^[a-z0-9A-Z\\u4e00-\\u9fa5]+$"; 
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(name);
		if (m.matches()){
            response.getWriter().print("");
        }else {
            response.getWriter().print("<font color='red'>物品名称输入不合法,只能输入汉字、字母或阿拉伯数字.</font>");
        }
	}
	/*
	 * Admin 入库的物品的物品编码的添加 物品编码的输入检测 必须唯一
	 */
	public void ajaxbarcodeCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String barcode = request.getParameter("barcode");
		String regEx1 = "^[0-9]{4,6}$"; 
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(barcode);
		if (!m.matches()){
			response.getWriter().print("<font color='red'>物品编号输入不合法.</font>");
		}else if(insideService.queryInsideBybarcode(barcode) != null) {
            response.getWriter().print("<font color='red'>物品编号重复，请更换物品编码再输入.</font>");
        }else {
            
        }
	}
	/*
	 * Admin 入库的物品的物品价格的添加 物品价格的输入检测
	 */
	public void ajaxpurchasepriceCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String purchaseprice = request.getParameter("purchaseprice");
		String regEx1 = "^[0-9]+(.[0-9]{1,5})?$"; 
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(purchaseprice);
		if (m.matches() && Double.valueOf(purchaseprice) > 0){
            response.getWriter().print("");
        }else {
            response.getWriter().print("<font color='red'>物品价格输入不合法。</font>");
        }
	}
	/*
	 * Admin 入库的物品的物品数量的添加 物品数量的输入检测
	 */
	public void ajaxnumCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String num = request.getParameter("num");
		String regEx1 = "^[1-9][0-9]*$"; 
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(num);
		if (m.matches() && Integer.valueOf(num)>0){
            response.getWriter().print("");
        }else {
            response.getWriter().print("<font color='red'>物品数量输入不合法。</font>");
        }
	}
}
