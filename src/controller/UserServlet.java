package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URLDecoder;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Admin;
import bean.User;
import impl.AdminServiceImp;
import impl.UserServiceImp;
import utils.Page;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("*.udo")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 获取请求的URI地址信息
		// http://localhost:8080/equip/typeList.do
		String url = request.getRequestURI();
				
		// 截取其中的方法名typeList
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
	protected void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/UserLogin.jsp").forward(request, response);
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserServiceImp userServiceImp = new UserServiceImp();
		AdminServiceImp adminServiceImp = new AdminServiceImp();
		User user = null;
		Admin admin = null;
		user = userServiceImp.findUser(username, password);
		admin = adminServiceImp.findAdmin(username, password);
		//用于存放反馈给前端的信息
		Map<String,Object> map = new HashMap<String,Object>();	
		if(admin != null) {
			System.out.println("管理员登录:"+username+"  "+password);
			request.getSession().setAttribute("adminInfo", admin);
			map.put("flag",true);
			map.put("msg","管理员登录成功");
		}else if(user != null){
			System.out.println("普通用户登录:"+username+"  "+password);
			request.getSession().setAttribute("userInfo", user);
			map.put("flag",true);
			map.put("msg","普通用户登录成功");
		}else {
			map.put("flag",false);
			map.put("msg","输入账号和密码错误！");
		}
		//将map转为json，并且传递给客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        System.out.println("json = "+json);
        //并且传递给客户端
        mapper.writeValue(response.getWriter(),map);
	}
	// 注册页面 获取验证码
	protected void signUpGetCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> map = new HashMap<String,Object>();	
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String verifyCode = String.valueOf(System.currentTimeMillis()%1000000);
		
		System.out.println("verifycode : "+verifyCode);
		
		UserServiceImp userServiceImp = new UserServiceImp();
		if(!userServiceImp.ajaxValidateEmail(email)) {
			map.put("flag",false);
            map.put("msg","该邮箱已被注册！");
		}else if(userServiceImp.queryUserByUserName(username) != null) {
			map.put("flag", false);
			map.put("msg","该用户名已被使用！");
		}else {
			request.getSession().setAttribute("verifyCode", verifyCode);
			//发送 验证码 邮件 为了不必长时间的等待
			userServiceImp.regist(email, verifyCode);

			map.put("flag",true);
		}
        //将map转为json，并且传递给客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        System.out.println("json = "+json);
        //并且传递给客户端
        mapper.writeValue(response.getWriter(),map);
	}
	
	public void userInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/page/user/user-info.jsp").forward(request, response);
	}
	public void adminInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("adminInfo");
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		try {
			Date date = format.parse(admin.getLoginTime());
			admin.setLoginTime(format2.format(date));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/page/admin/admin-info.jsp").forward(request, response);
	}
	
	//更新页面跳转
	public void updateUserByadmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String  id=	request.getParameter("id");
		UserServiceImp userServiceImp = new UserServiceImp();
		User user = userServiceImp.queryUserById(id);
		request.setAttribute("userInfo",user);
		request.getRequestDispatcher("/WEB-INF/page/admin/user-update.jsp").forward(request, response);
	}
	
	/**user-info.jsp 传递过来的用户信息 进行修改
	 * 保存修改的数据
	 */
	public void updateUserById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UserServiceImp userService = new UserServiceImp();
		//获取到参数
		String id = request.getParameter("id");
		String realname = request.getParameter("realname");
		String email =	request.getParameter("email");
		String employeeId = request.getParameter("employeeId");
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		String dept = request.getParameter("dept");
		//通过id获取需要修改的对象在数据库中的原型
		User user = userService.queryUserById(id);
		
		user.setRealname(realname);
		user.setEmail(email);
		user.setEmployeeId(employeeId);
		user.setAge(Integer.parseInt(age));
		user.setSex(sex);
		user.setDept(dept);
		
		int result=0;		
		result = userService.updateUserInfo(user);
		if(result>0){
			//重定向
			request.getRequestDispatcher("userList.udo").forward(request, response);
		}else{
			response.getWriter().write("系统异常,保存数据失败,3秒后跳转回修改页面");
			response.setHeader("refresh", "3;url=indexServlet");
		}
	}
	
	/**user-info.jsp 传递过来的用户信息 进行修改
	 * 保存修改的数据
	 */
	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取到参数
		String realname = request.getParameter("realname");
		String email =	request.getParameter("email");
		String employeeId = request.getParameter("employeeId");
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		String dept = request.getParameter("dept");
		//获取Session中存放的User对象
		User user = (User)request.getSession().getAttribute("userInfo");
		
		user.setRealname(realname);
		user.setEmail(email);
		user.setEmployeeId(employeeId);
		user.setAge(Integer.parseInt(age));
		user.setSex(sex);
		user.setDept(dept);
		
		int result=0;
		UserServiceImp userService = new UserServiceImp();
		result = userService.updateUserInfo(user);
		if(result>0){
			//重新保存Session中的userInfo值
			request.getSession().setAttribute("userInfo", user);
			
			//重定向   在此sevlet方法中调用另外一个方法
			response.sendRedirect("indexServlet");
		}else{
			response.getWriter().write("系统异常,保存数据失败,3秒后跳转回修改页面");
			response.setHeader("refresh", "3;url=indexServlet");
		}
	}
	
	/**
	 * 保存修改的数据
	 */
	public void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取到参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//获取Session中存放的User对象
		User user = (User)request.getSession().getAttribute("userInfo");
		Admin admin = (Admin)request.getSession().getAttribute("adminInfo");
		int result = 0;
		if(user != null) {
			user.setUsername(username);
			user.setPassword(password);
			UserServiceImp userService = new UserServiceImp();
			result = userService.updateUser(user);
		}else if(admin != null) {
			admin.setUsername(username);
			admin.setPassword(password);
			AdminServiceImp adminService = new AdminServiceImp();
			result = adminService.updateAdmin(admin);
		}
		if(result>0){
			response.getWriter().write("true");
		}else{
			response.getWriter().write("false");
		}
	}
	
	public void outLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getSession().removeAttribute("userInfo");
		Admin admin = (Admin)request.getSession().getAttribute("adminInfo");
		if(admin!=null) {
			//更新管理员账号的登录时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			admin.setLoginTime(sdf.format(new Date()));
			System.out.println("管理员退出了"+admin);
			new AdminServiceImp().updateAdmin(admin);
		}
		request.getSession().removeAttribute("adminInfo");
		request.getRequestDispatcher("toLogin.udo").forward(request, response);
	}
	
	//跳转到新增页面去
	public void toAddUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/page/admin/user-add.jsp").forward(request, response);
	}	
	
	/**
	 * 添加用户
	 */
	public void userAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取到参数
		String username = request.getParameter("username");
		String realName = request.getParameter("realName");
		String email = request.getParameter("email");
		String employeeId = request.getParameter("employeeId");
		String password = employeeId;
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		String dept = request.getParameter("dept");
		
		User user = new User(null,username,password,realName,email,employeeId,Integer.valueOf(age),sex,dept);
		UserServiceImp userService = new UserServiceImp();
		int result = userService.saveUser(user);
		if(result>0){
			//重定向   在此sevlet方法中调用另外一个方法
			response.sendRedirect("userList.udo");
		}else{
			response.getWriter().write("系统异常,新增数据失败,3秒后跳转回添加页面");
			response.setHeader("refresh", "3;url=userList.udo");
		}
	}
	
	/**
	 * 注册用户界面
	 */
	public void registUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取到参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String verifycode = request.getParameter("verifycode");
		
		//后台生成的验证码
		String code = String.valueOf(request.getSession().getAttribute("verifyCode"));
		
		Map<String,Object> map = new HashMap<String,Object>();
		UserServiceImp userService = new UserServiceImp();
		AdminServiceImp adminService = new AdminServiceImp();
		//该用户名不能已被其他用户使用
		Admin admin = adminService.findAdmin(username);
		boolean ckusernameFromUser = userService.checkUser(username); 
		
		if(admin == null || !ckusernameFromUser) {
			if(code.equals(verifycode)) {
				User newUser = new User(null,username,password,"未设置",email,"未设置",0,"未设置","未设置");
				int result = userService.saveUser(newUser);
				if(result>0){
					map.put("flag",true);
				}else{
					map.put("flag",false);
					map.put("msg","系统出错了，请重试！");
				}
			}else {
				map.put("flag",false);
	            map.put("msg","验证码输入错误，请重试！");
			}
		}else {
			map.put("flag",false);
            map.put("msg","该用户名已存在！");
		}
		//将map转为json，并且传递给客户端
        //将map转为json
        ObjectMapper mapper = new ObjectMapper();
        //并且传递给客户端
        mapper.writeValue(response.getWriter(),map);
	}
//	对页面中用户输入的邮箱发送验证码 由于用户注册
	public void verifyMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Map<String,Object> map = new HashMap<String,Object>();	
		String email = request.getParameter("email");
		String verifyCode = String.valueOf(System.currentTimeMillis());
		UserServiceImp userServiceImp = new UserServiceImp();
		if(userServiceImp.ajaxValidateEmail(email)) {
			String verifycode = request.getParameter("verifycode");
			userServiceImp.regist(email, verifycode);
			map.put("flag",true);
			request.getSession().setAttribute("verifyCode", verifyCode);
		}else {
			map.put("flag",false);
            map.put("msg","该邮箱已被注册，请换个换个邮箱，或者找回密码。");
		}	
        //将map转为json，并且传递给客户端
        //将map转为json
        ObjectMapper mapper = new ObjectMapper();
        //并且传递给客户端
        mapper.writeValue(response.getWriter(),map);
	}
	
//	对页面中用户输入的邮箱发送验证码 由于用户修改密码，该邮箱是已存在的，若不存在则邮箱填写有误
	public void sendUserVerifyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Map<String,Object> map = new HashMap<String,Object>();	
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String verifyCode = String.valueOf(System.currentTimeMillis());
		
		System.out.println("verifycode : "+verifyCode);
		
		UserServiceImp userServiceImp = new UserServiceImp();
		User user = userServiceImp.queryUserByUserName(username);
		user.setVerifyCode(verifyCode);
		
		if(!userServiceImp.ajaxValidateEmail(email)) {
			
			userServiceImp.regist(email, verifyCode);
			map.put("flag",true);
			request.getSession().setAttribute("verifyCode", verifyCode);
		}else {
			map.put("flag",false);
            map.put("msg","该邮箱不存在！");
		}	
        //将map转为json，并且传递给客户端
        //将map转为json
        ObjectMapper mapper = new ObjectMapper();
        //并且传递给客户端
        mapper.writeValue(response.getWriter(),map);
	}
	
	//	UserRetriveve.jsp页面中 用户修改密码时 获取验证码操作
	public void retrieveGetCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Map<String,Object> map = new HashMap<String,Object>();	
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String verifyCode = String.valueOf(System.currentTimeMillis()%1000000);
		
		System.out.println("verifycode : "+verifyCode);
		
		UserServiceImp userServiceImp = new UserServiceImp();
		User user = userServiceImp.queryUserByUserName(username);
		
		if(!userServiceImp.ajaxValidateEmail(email)) {
			user.setVerifyCode(verifyCode);
			request.getSession().setAttribute("user", user);
			
			//发送 验证码 邮件 为了不必长时间的等待
			userServiceImp.regist(email, verifyCode);
			
			map.put("flag",true);
		}else {
			map.put("flag",false);
            map.put("msg","该邮箱不存在！");
		}	
        //将map转为json，并且传递给客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        System.out.println("json = "+json);
        //并且传递给客户端
        mapper.writeValue(response.getWriter(),map);
	}
//	UserRetriveve.jsp页面中 用户修改密码
	public void userRetrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Map<String,Object> map = new HashMap<String,Object>();	
		
		String password = request.getParameter("password");
		String verifycode = request.getParameter("verifycode");
		
		UserServiceImp userServiceImp = new UserServiceImp();
		User user = (User)request.getSession().getAttribute("user");
		
		if(verifycode.equals(user.getVerifyCode())) {
			user.setPassword(password);
			int result = 0;
			result = userServiceImp.updateUser(user);
			if(result >0) {
				map.put("flag",true);
			}else {
				map.put("flag",false);
	            map.put("msg","出错了，请联系技术人员！");
			}
			
		}else {
			map.put("flag",false);
            map.put("msg","验证码输入有误！");
		}	
		//将map转为json，并且传递给客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        System.out.println("json = "+json);
        //并且传递给客户端
        mapper.writeValue(response.getWriter(),map);
	}
	/*
	 * 列表查询
	 */
	public void userList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserServiceImp userService = new UserServiceImp();
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
		page = userService.getUserPage(page, curPage);
		// 将数据返回给页面
		request.setAttribute("page", page);
		request.getRequestDispatcher("/WEB-INF/page/admin/user-list.jsp").forward(request, response);
	}
	/*
	 * Admin 用户管理页面 用户的添加  用户名是否重复验证
	 */
	public void ajaxusernameCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		boolean checkUsername = new UserServiceImp().checkUser(username);
		String regEx1 = "^[A-z0-9_]{8,}$"; 
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(username);
        if (checkUsername){
            response.getWriter().print("<font color='red'> 用户名已被注册。</font>");
        }else if(username.equals("admin")){
            response.getWriter().print("<font color='red'> 用户名不能为这个。</font>");
        }else if(username==null || username.equals("")){
            response.getWriter().print("<font color='red'> 用户名不能为空。</font>");
        }else if(!m.matches()){
        	response.getWriter().print("<font color='red'> 用户名必须有数字、大小写字母、下划线组成。</font>");
        }else {
        	response.getWriter().print("<font color='green'></font>");
        }
	}
	/*
	 * Admin 用户管理页面 用户的添加 真实姓名的输入
	 */
	public void ajaxrealNameCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String realName = request.getParameter("realName");
		realName = new String(realName.getBytes("iso-8859-1"), "utf-8");
		String regEx1 = "[\\u4e00-\\u9fa5]{2,4}$"; 
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(realName);
		if (m.matches()){
            response.getWriter().print("");
        }else {
            response.getWriter().print("<font color='red'>真实姓名输入不合法.</font>");
        }
	}
	/*
	 * Admin 用户管理页面 用户的添加  邮箱是否已存在
	 */
	public void ajaxemailCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		email = new String(email.getBytes("iso-8859-1"), "utf-8");
		String regEx1 = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"; 
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(email);
        boolean ckEmail = m.matches();
        boolean checkEmail = new UserServiceImp().ajaxValidateEmail(email);
        
		if (checkEmail && ckEmail){
            response.getWriter().print("");
        }else {
            response.getWriter().print("<font color='red'>邮箱输入不合法或已被注册.</font>");
        }
	}
	/*
	 * Admin 用户管理页面 用户的添加  员工编号是否合法
	 */
	public void ajaxemployeeIdCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String employeeId = request.getParameter("employeeId");
		String regEx1 = "^[0-9]{4,6}$"; 
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(employeeId);
		if (m.matches()){
            response.getWriter().print("");
        }else {
            response.getWriter().print("<font color='red'>员工编号输入不合法.</font>");
        }
	}
	/*
	 * Admin 用户管理页面 用户的添加  年龄是否合法
	 */
	public void ajaxageCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String age = request.getParameter("age");
		String regEx1 = "^[2-5][0-9]$"; 
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(age);
		if (m.matches()){
            response.getWriter().print("");
        }else {
            response.getWriter().print("<font color='red'>年龄输入不合法(20-59).</font>");
        }
	}
	/*
	 * Admin 用户管理页面 用户的添加  性别的输入
	 */
	public void ajaxsexCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sex = request.getParameter("sex");
		sex = new String(sex.getBytes("iso-8859-1"), "utf-8");
		if (sex.equals("男") || sex.equals("女")){
            response.getWriter().print("");
        }else {
            response.getWriter().print("<font color='red'>性别输入不合法.</font>");
        }
	}
	/*
	 * Admin 用户管理页面 用户的添加 部门的输入
	 */
	public void ajaxdeptCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dept = request.getParameter("dept");
		dept = new String(dept.getBytes("iso-8859-1"), "utf-8");
		String regEx1 = "[\\u4e00-\\u9fa5]{2,4}$"; 
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(dept);
		if (dept.endsWith("部") && m.matches()){
            response.getWriter().print("");
        }else {
            response.getWriter().print("<font color='red'>部门名称输入不合法.</font>");
        }
	}
}
