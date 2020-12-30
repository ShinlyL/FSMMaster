package controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

import bean.Admin;
import bean.Apply;
import bean.Goods;
import bean.Inside;
import bean.Inventory;
import bean.User;
import impl.ApplyServiceImp;
import impl.GoodsServiceImp;
import impl.InsideServiceImp;
import impl.InventoryServiceImp;
import utils.Page;
//通配符URL   .ado 结尾的所有的请求都会进入这个 servlet
@WebServlet("*.ado")
public class ApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ApplyServiceImp applyService = new ApplyServiceImp();

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
		// http://localhost:8080/equip/ApplyList.do
		String url = request.getRequestURI();
		
		// 截取其中的方法名ApplyList
		String methodName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
		System.out.println("methodName = "+methodName);
		
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
	 * 列表查询 所有的申领记录 admin
	 * 0-未审核的申领记录  1-已审核的申领记录 2-驳回的申领记录
	 */
	public void applyList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取页面请求中的id的值 0--未审核申领  1--已审核申领 2-不通过的申领记录
		String id = request.getParameter("id");
		
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
		if(id.equals("0")) {
			page = applyService.getApplyPage(page, curPage, "未审核");
		}else if(id.equals("1")) {
			page = applyService.getApplyPage(page, curPage, "已审核");
		}else if(id.equals("2")) {
			page = applyService.getApplyPage(page, curPage, "不通过");
		}
		// 将数据返回给页面
		request.setAttribute("page", page);
		request.setAttribute("status", id);
		request.getRequestDispatcher("/WEB-INF/page/admin/apply-list.jsp").forward(request, response);
	}
	/**
	 * 列表查询 查看此用户的所有的申领记录  user
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void unApplyListByRln(HttpServletRequest request, HttpServletResponse response)
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
		User user = (User)request.getSession().getAttribute("userInfo");
		String tag = request.getParameter("tag");
		if(tag.equals("1")) {
			page = applyService.getApplyPage(page, curPage, "未审核", user.getUsername());
		}else if(tag.equals("2")){
			page = applyService.getApplyPage(page, curPage, "已审核", user.getUsername());
		}else if(tag.equals("3")) {
			page = applyService.getApplyPage(page, curPage, "不通过", user.getUsername());
		}
		// 将数据返回给页面
		request.setAttribute("page", page);
		request.setAttribute("tag",tag);
		request.getRequestDispatcher("/WEB-INF/page/user/apply-list.jsp").forward(request, response);
	}
	//删除
	public void delApply(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//获取页面的id参数
		String id=	request.getParameter("id");
		int res = applyService.delApply(id);;
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

	//跳转到新增页面去(用户)
	public void toAddApplyUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id = request.getParameter("id");
		Inventory inventory = new InventoryServiceImp().queryInventory(id);
		Inside inside = new InsideServiceImp().queryInsideBybarcode(inventory.getBarcode());
		Apply apply = new Apply(null,inside.getName(),inside.getBarcode(),Double.valueOf(inside.getPurchaseprice()),0,0.0,null,null,null);
		request.setAttribute("apply", apply);
		request.getRequestDispatcher("/WEB-INF/page/user/apply-add.jsp").forward(request, response);
	}
	
	//跳转到新增页面去(管理员)
	public void toAddApply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/page/view/apply-add.jsp").forward(request, response);
	}
	
	//新增保存
	public void applyAdd(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//获取到参数
		String name = request.getParameter("name");
		String barcode = request.getParameter("barcode");
		String purchaseprice = request.getParameter("purchaseprice");
		String num = request.getParameter("num");
		User user = (User)request.getSession().getAttribute("userInfo");
		
//		不是从前端获取的数值
		Double sums = Double.valueOf(purchaseprice) * Integer.valueOf(num); 
		String manager = user.getUsername();
		
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		String releaseDate = ft.format(dNow);
		String flag = "未审核";
		//将数据封装成Apply对象
		Apply  apply = new Apply();
		apply.setId(null);
		apply.setName(name);
		apply.setBarcode(barcode);
		apply.setPurchaseprice(Double.valueOf(purchaseprice));
		apply.setNum(Integer.parseInt(num));
		apply.setSums(Double.valueOf(sums));
		apply.setManager(manager);
		apply.setReleaseDate(releaseDate);
		apply.setFlag(flag);
		
		//将数据插入到数据库中
		int result=applyService.insertApply(apply);
		if(result>0){
			//重定向   在此sevlet方法中调用另外一个方法
			response.sendRedirect("inventoryList.do");
		}else{
			response.getWriter().write("新增数据失败,3秒后跳转回物品列表页面");
			response.setHeader("refresh", "3;url=inventoryList.do");
		}
	}
	
	//更新页面跳转
	public void toUpdateApply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String  id=	request.getParameter("id");
		Apply apply = applyService.queryApply(id);		
		request.setAttribute("apply",apply);
		System.out.println("apply.releaseDate =   "+apply.getReleaseDate());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		try {
			Date date = format.parse(apply.getReleaseDate());
			apply.setReleaseDate(format2.format(date));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/page/user/apply-update.jsp").forward(request, response);
	}
	
	//更新页面跳转
		public void toUpdateApplyByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			String  id=	request.getParameter("id");
			Apply apply = applyService.queryApply(id);
			Inventory inventory = new InventoryServiceImp().queryInventoryByBarcode(apply.getBarcode());
			//设置的显示时间
			Map<String,String> applyInfo = new HashMap<String,String>();
			
			applyInfo.put("id",String.valueOf(apply.getId()));
			applyInfo.put("name", apply.getName());
			applyInfo.put("applyNum", String.valueOf(apply.getNum()));
			applyInfo.put("inventoryNum", String.valueOf(inventory.getNum()));
			
			Goods goods = new GoodsServiceImp().queryGoodsBybarcode(apply.getBarcode());
			if(goods != null) {
				applyInfo.put("goodsInfo", goods.getInfo());
			}
			request.setAttribute("applyInfo",applyInfo);
			request.getRequestDispatcher("/WEB-INF/page/admin/apply-update.jsp").forward(request, response);
		}
	/**
	 * 保存修改的数据
	 */
	public void updateApply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取到参数
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String barcode = request.getParameter("barcode");
		String purchaseprice = request.getParameter("purchaseprice");
		String num = request.getParameter("num");
		String sums = request.getParameter("sums");
		String manager = request.getParameter("manager");
		String releaseDate = request.getParameter("releaseDate");
		String flag = request.getParameter("flag");
		
		//对releaseDate的值进行 转换
		releaseDate = releaseDate.replace('T', ' ').concat(":00");		
		System.out.println(releaseDate);
		
		//将数据封装成Apply对象
		Apply  apply = new Apply();
		
		apply.setId(Integer.parseInt(id));
		apply.setName(name);
		apply.setBarcode(barcode);
		apply.setPurchaseprice(Double.valueOf(purchaseprice));
		apply.setNum(Integer.parseInt(num));
		apply.setSums(Double.valueOf(sums));
		apply.setManager(manager);
		apply.setReleaseDate(releaseDate);
		apply.setFlag(flag);
		
		System.out.println("apply = "+apply);
		
		int result=0;
		result = applyService.updateApply(apply);
		if(result>0){
			//重定向   在此sevlet方法中调用另外一个方法
			response.sendRedirect("unApplyListByRln.ado?tag=1");
		}else{
			response.getWriter().write("保存数据失败,3秒后跳转回修改页面");
			response.setHeader("refresh", "3;unApplyListByRln.ado?tag=1");
		}
	}
	
	/**
	 * 保存修改的数据 用户只能修改申领的物品的数量 并更新 登记时间
	 */
	public void updateApplyNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取到参数
		String id = request.getParameter("id");
		String numStr = request.getParameter("num");
		int num = Integer.parseInt(numStr);
		//将数据封装成Apply对象
		Apply apply = applyService.queryApply(id);
		//获取申领的申领状态，确定该重定向到哪个页面
		String flag = apply.getFlag();
		
		int result=0;
		apply.setNum(num);
		//更新申领的登记时间
		Date dNow = new Date( );
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		String releaseDate = ft.format(dNow);
		apply.setReleaseDate(releaseDate);
		//更新申领的申领状态
		apply.setFlag("未审核");
		result = applyService.updateApply(apply);
		
		if(result>0){
			if(flag.equals("未审核")) {
				response.sendRedirect("unApplyListByRln.ado?tag=1");
			}else if(flag.equals("不通过")) {
				response.sendRedirect("unApplyListByRln.ado?tag=3");
			}
		}else{
			response.getWriter().write("保存数据失败,3秒后跳转回修改页面");
			if(flag.equals("未审核")) {
				response.setHeader("refresh", "3;unApplyListByRln.ado?tag=1");
			}else if(flag.equals("不通过")) {
				response.setHeader("refresh", "3;unApplyListByRln.ado?tag=3");
			}
		}
	}
	/**
	 * 保存修改的数据 管理员修改申领的物品的数量 并更新 登记时间
	 */
	public void updateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取到参数
		String id = request.getParameter("id");
		String numStr = request.getParameter("num");
		int num = Integer.parseInt(numStr);
		//将数据封装成Apply对象
		Apply apply = applyService.queryApply(id);
		int result=0;
		apply.setNum(num);
		applyService.updateApply(apply);
		//重定向   在此sevlet方法中调用另外一个方法
		response.sendRedirect("applyList.ado?id=0");
	}
	
	/**
	 * 审批 用户的申领的物品  admin操作
	 */
	public void approveApply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取到参数
		String id = request.getParameter("id");
		Apply apply = applyService.queryApply(id);
		if(new InventoryServiceImp().pickInventory(apply)) {
			apply.setFlag("已审核");
			int result=0;
			result = applyService.updateApply(apply);
			if(result>0){
				//重定向   在此sevlet方法中调用另外一个方法 继续审批
				response.sendRedirect("applyList.ado?id=0");
			}else{
				response.getWriter().write("审批失败，请联系技术人员。");
				response.setHeader("refresh", "2;applyList.ado?id=0");
			}
		}else {
			response.getWriter().write("库存数量不足，请修改申领物品数量，或库存数量充足后审批。");
			response.setHeader("refresh", "2;applyList.ado?id=0");
		}
	}
	/**
	 * 驳回 用户的申领记录  admin操作
	 */
	public void rebackApply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取到参数
		String id = request.getParameter("id");
		Apply apply = applyService.queryApply(id);
		apply.setFlag("不通过");
		applyService.updateApply(apply);
		//重定向   在此sevlet方法中调用另外一个方法
		response.sendRedirect("applyList.ado?id=0");
	}
	/**
	 * 撤销 用户的已审批的申领记录  admin操作
	 */
	public void revocationApproveApply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取到参数
		String id = request.getParameter("id");
		Apply apply = applyService.queryApply(id);
		new InventoryServiceImp().revocationInventory(apply);
		apply.setFlag("未审核");
		applyService.updateApply(apply);
		
		//重定向   在此sevlet方法中调用另外一个方法
		response.sendRedirect("applyList.ado?id=1");
	}
	/**
	 * 撤销 用户的驳回的申领记录  admin操作
	 */
	public void revocationRebackApply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取到参数
		String id = request.getParameter("id");
		Apply apply = applyService.queryApply(id);
		apply.setFlag("未审核");
		applyService.updateApply(apply);
		//重定向   在此sevlet方法中调用另外一个方法
		response.sendRedirect("applyList.ado?id=2");
	}
	/*
	 * Admin 对申领记录中物品的数量的修改 合理输入
	 */
	public void ajaxnumCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String num = request.getParameter("num");
		String inventoryNum = request.getParameter("inventoryNum");
		String regEx1 = "^[1-9][0-9]*$"; 
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(num);
		if (!m.matches()){
            response.getWriter().print("<font color='red'>物品数量只能输入数字。</font>");
        }else if(Integer.valueOf(num) <= 0){
            response.getWriter().print("<font color='red'>物品数量输入数字需要大于0。</font>");
        }else if(Integer.valueOf(num) > Integer.valueOf(inventoryNum)) {
        	response.getWriter().print("<font color='red'>物品输入的数量不得超过库存中剩余数量。</font>");
        }else {
        	response.getWriter().print("");
        }
	}
	
	/*
	 * User 对申领记录中物品的数量合理输入
	 */
	public void ajaxnumCheckByuser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String num = request.getParameter("num");
		String regEx1 = "^[1-9][0-9]*$"; 
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(num);
		if (!m.matches()){
            response.getWriter().print("<font color='red'>物品数量只能输入数字。</font>");
        }else if(Integer.valueOf(num) <= 0){
            response.getWriter().print("<font color='red'>物品数量输入数字需要大于0。</font>");
        }else {
        	response.getWriter().print("");
        }
	}
}
