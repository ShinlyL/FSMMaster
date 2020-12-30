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

import bean.Goods;
import bean.Inside;
import bean.Inventory;
import impl.GoodsServiceImp;
import impl.InsideServiceImp;
import impl.InventoryServiceImp;
import utils.Page;

/**
 * 和物品goods相关的页面 的操作
 * @author Administrator
 *
 */
//通配符URL   .gdo 结尾的所有的请求都会进入这个 servlet
@WebServlet("*.gdo")
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	GoodsServiceImp goodsService = new GoodsServiceImp();

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
		// http://localhost:8080/equip/GoodsList.do
		String url = request.getRequestURI();
		
		// 截取其中的方法名GoodsList
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
	public void goodsList(HttpServletRequest request, HttpServletResponse response)
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
		page = goodsService.getGoodsPage(page, curPage);
		// 将数据返回给页面
		request.setAttribute("page", page);
		request.getRequestDispatcher("/WEB-INF/page/admin/goods-list.jsp").forward(request, response);
	}	
	//跳转到首页面去
		public void toIndexView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			request.getRequestDispatcher("/WEB-INF/page/view/indexView.jsp").forward(request, response);
		}
	
	//更新页面跳转
	public void updateGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String  id=	request.getParameter("id");
		Goods goods = goodsService.queryGoods(id);
		request.setAttribute("goods",goods);
		request.getRequestDispatcher("/WEB-INF/page/admin/goods-update.jsp").forward(request, response);
	}
	
	/**
	 * 保存修改的数据
	 */
	public void updateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取到参数
		String id = request.getParameter("id");
		String info = request.getParameter("info");
		//获取Goods对象
		Goods goods = goodsService.queryGoods(id);
		goods.setInfo(info);
		int result=0;
		result = goodsService.updateGoods(goods);
		if(result>0){
			//重定向   在此sevlet方法中调用另外一个方法
			response.sendRedirect("goodsList.gdo");
		}else{
			response.getWriter().write("系统异常,保存数据失败,3秒后跳转回修改页面");
			response.setHeader("refresh", "3;url=goodsList.gdo");
		}
	}
	
	//跳转到新增页面去
		public void toAddGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			String goodsname = request.getParameter("goodsname"); 
			if(goodsname == null) {
				//获取库存 物品列表中存在 但是物品列表中不存在的物品名称
				List<String> goodsName = goodsService.getGoodsName();
				request.setAttribute("goodsName", goodsName);
				request.getRequestDispatcher("/WEB-INF/page/admin/goods-add.jsp").forward(request, response);
			}else {
				goodsname = new String(goodsname.getBytes("iso-8859-1"), "utf-8");
				InventoryServiceImp inventoryServiceImp = new InventoryServiceImp();
				Inventory inventory = inventoryServiceImp.queryInventoryByName(goodsname);
				Inside inside = new InsideServiceImp().queryInsideBybarcode(inventory.getBarcode());
				Goods goods = new Goods(null,inventory.getName(),inventory.getBarcode(),Double.toString(inside.getPurchaseprice()),
						inventory.getTypeone(),"","",inventory.getTypetwo());
				request.setAttribute("goods", goods);
				request.getRequestDispatcher("/WEB-INF/page/admin/goods-add2.jsp").forward(request, response);
			}
		}
		
		//新增保存
		public void goodsAdd(HttpServletRequest request, HttpServletResponse response) throws IOException{
			//获取到参数
			String name = request.getParameter("name");
			String barcode = request.getParameter("barcode");
			String purchaseprice = request.getParameter("purchaseprice");
			String typeone = request.getParameter("typeone");
			String typetwo = request.getParameter("typetwo");
			String info = request.getParameter("info");
			//将数据封装成Goods对象
			Goods goods = new Goods();
			goods.setId(null);
			goods.setName(name);
			goods.setBarcode(barcode);
			goods.setPurchaseprice(purchaseprice);
			goods.setTypeone(typeone);
			goods.setTypetwo(typetwo);
			goods.setInfo(info);
			goods.setFlag("有货");
			
			//将数据插入到数据库中
			int result=goodsService.insertGoods(goods);
			if(result>0){
				//重定向   在此sevlet方法中调用另外一个方法
				response.sendRedirect("goodsList.gdo");
			}else{
				response.getWriter().write("系统异常,新增数据失败,3秒后跳转回添加页面");
				response.setHeader("refresh", "3;url=goodsList.gdo");
			}
		}
		/*
		 * Admin 添加物品信息 详细信息的输入
		 */
		public void ajaxinfoCheck(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String info = request.getParameter("info");
			info = new String(info.getBytes("iso-8859-1"), "utf-8");
			String regEx1 = "^[0-9\\u4e00-\\u9fa5]+$"; 
	        Pattern p = Pattern.compile(regEx1);
	        Matcher m = p.matcher(info);
			if (m.matches()){
	            response.getWriter().print("");
	        }else {
	            response.getWriter().print("<font color='red'>详细信息输入不合法,例：每个办公室限领2个.</font>");
	        }
		}
}
