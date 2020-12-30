package controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Admin;
import bean.Goods;
import bean.Inside;
import bean.Inventory;
import bean.User;
import impl.GoodsServiceImp;
import impl.InsideServiceImp;
import impl.InventoryServiceImp;
import utils.Page;
//通配符URL   .do 结尾的所有的请求都会进入这个 servlet
@WebServlet("*.do")
public class InventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	InventoryServiceImp inventoryService = new InventoryServiceImp();

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
		// http://localhost:8080/equip/InventoryList.do
		String url = request.getRequestURI();
		
		// 截取其中的方法名InventoryList
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
	public void inventoryList(HttpServletRequest request, HttpServletResponse response)
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
		page = inventoryService.getInventoryPage(page, curPage);
		// 将数据返回给页面
		request.setAttribute("page", page);
		Admin admin = (Admin)request.getSession().getAttribute("adminInfo");
		User user = (User)request.getSession().getAttribute("userInfo");
		if( user != null) {
			request.getRequestDispatcher("/WEB-INF/page/user/inventory-list.jsp").forward(request, response);
		}else if(admin != null){
			request.getRequestDispatcher("/WEB-INF/page/admin/inventory-list.jsp").forward(request, response);
		}
	}
	
	//删除
	public void delInventory(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//获取页面的id参数
		String id=	request.getParameter("id");
		Inventory inventory = inventoryService.queryInventory(id);
		//删除物品库 中的对应物品
		Goods goods = new GoodsServiceImp().queryGoodsBybarcode(inventory.getBarcode());
		//修改入库商品中对应物品的入库状态
		Inside inside = new InsideServiceImp().queryInsideBybarcode(inventory.getBarcode());
		inside.setStatus(0);
		new InsideServiceImp().updateInside(inside);
		
		int res1 = new GoodsServiceImp().delGoods(String.valueOf(goods.getId()));
		int res2 = inventoryService.delInventory(id);
		if(res1 > 0 && res2 > 0){
			response.getWriter().write("ok");
		}else {
			response.getWriter().write("no");
		}
	}
	
	//跳转到首页面去
		public void toIndexView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			List<Inventory> iList = inventoryService.getNewInventory(5);
			User user = null;
			Admin admin = null;
			user = (User) request.getSession().getAttribute("userInfo");
			admin = (Admin) request.getSession().getAttribute("adminInfo");
			if(user != null) {
				request.setAttribute("status",0);
			}else if(admin != null) {
				request.setAttribute("status",1);
			}
			
			if(iList.size() > 0) {
				Page page = new Page(iList.size());
				page.setParam(iList, 1, iList.size());
				request.setAttribute("iList", page);
			}
			request.getRequestDispatcher("/WEB-INF/page/view/indexView.jsp").forward(request, response);
		}

	//跳转到新增页面去
	public void toAddInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id = request.getParameter("id");
		if(id != null) {
			Inside inside = new InsideServiceImp().queryInside(id);
			Inventory inventory = new Inventory(null,inside.getName(),inside.getBarcode(),inside.getNum(),"","","1");
			request.setAttribute("inventory", inventory);
		}
		request.getRequestDispatcher("/WEB-INF/page/admin/inventory-add.jsp").forward(request, response);
	}
	
	
	
	//新增保存
	public void inventoryAdd(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//获取到参数
		String name = request.getParameter("name");
		String barcode = request.getParameter("barcode");
		String num =	request.getParameter("num");
		String typeone = request.getParameter("typeone");
		String typetwo = request.getParameter("typetwo");
		//将数据封装成Inventory对象
		Inventory  inventory = new Inventory();
		inventory.setId(null);
		inventory.setName(name);
		inventory.setBarcode(barcode);
		inventory.setNum(Integer.parseInt(num));
		inventory.setTypeone(typeone);
		inventory.setTypetwo(typetwo);
		inventory.setStatus(String.valueOf(0));
		
		//将数据插入到数据库中
		int result=inventoryService.insertInventory(inventory);
		
		/*
		 * //将该数据添加到物品Goods库中 String purchaseprice = String.valueOf(new
		 * InsideServiceImp().queryInsideBybarcode(barcode).getPurchaseprice()); Goods
		 * goods = new Goods(null,name,barcode,purchaseprice,typeone,"","有货",typetwo);
		 * int res = new GoodsServiceImp().insertGoods(goods);
		 */
		
		if(result>0){
			//修改库存中此货物的入库状态
			InsideServiceImp insideService = new InsideServiceImp();
			Inside inside = insideService.queryInsideBybarcode(barcode);
			inside.setStatus(1);
			insideService.updateInside(inside);
			//重定向   在此sevlet方法中调用另外一个方法
			response.sendRedirect("inventoryList.do");
		}else{
			response.getWriter().write("系统异常,新增数据失败,3秒后跳转回添加页面");
			response.setHeader("refresh", "3;url=inventoryList.do");
		}
	}
	
	//更新页面跳转
	public void updateInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String  id=	request.getParameter("id");
		Inventory inventory = inventoryService.queryInventory(id);
		request.setAttribute("inventory",inventory);
		request.getRequestDispatcher("/WEB-INF/page/admin/inventory-update.jsp").forward(request, response);
	}
	
	/**
	 * 保存修改的数据
	 */
	public void updateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取到参数
		String id = request.getParameter("id");
		String typeone = request.getParameter("typeone");
		String typetwo = request.getParameter("typetwo");
		//将数据封装成Inventory对象
		Inventory  inventory = inventoryService.queryInventory(id);
		inventory.setTypeone(typeone);
		inventory.setTypetwo(typetwo);
		
		//如果物品已计入 物品库中，则修改相应信息
		Goods goods = new GoodsServiceImp().queryGoodsBybarcode(inventory.getBarcode());
		if(goods != null) {
			goods.setTypeone(typeone);
			goods.setTypetwo(typetwo);
			//保存到数据库
			new GoodsServiceImp().updateGoods(goods);
		}
		int result=0;
		result = inventoryService.updateInventory(inventory);
		if(result>0){
			//重定向   在此sevlet方法中调用另外一个方法
			response.sendRedirect("inventoryList.do");
		}else{
			response.getWriter().write("系统异常,保存数据失败,3秒后跳转回修改页面");
			response.setHeader("refresh", "3;url=inventoryList.do");
		}
	}
	/*
	 * Admin 将物品入库 分类信息一的输入
	 */
	public void ajaxTypeoneCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String typeone = request.getParameter("typeone");
		String typeInfo = new String(typeone.getBytes("iso-8859-1"), "utf-8");
		String regEx1 = "^[A-z0-9\\u4e00-\\u9fa5]+$"; 
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(typeInfo);
		if (m.matches()){
            response.getWriter().print("");
        }else {
            response.getWriter().print("<font color='red'>详细信息输入不合法,只能是字母、数字或汉字的组合.</font>");
        }
	}
	/*
	 * Admin 将物品入库 分类信息二的输入
	 */
	public void ajaxTypetwoCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String typetwo = request.getParameter("typetwo");
		String typeInfo = new String(typetwo.getBytes("iso-8859-1"), "utf-8");
		String regEx1 = "^[A-z0-9\\u4e00-\\u9fa5]+$"; 
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(typeInfo);
		if (m.matches()){
            response.getWriter().print("");
        }else {
            response.getWriter().print("<font color='red'>详细信息输入不合法,只能是字母、数字或汉字的组合.</font>");
        }
	}
}
