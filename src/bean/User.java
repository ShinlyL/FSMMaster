package bean;
/**
 * 普通用户 的实体类
 * @author Administrator
 *
 */
public class User{
	private Integer id;
	private String username;
	private String password;
	private String realname;
	private String email;
	private String employeeId;
	private int age;
	private String sex;
	private String dept;
	
	// 注册表单
	private String reloginpass;//确认密码
	private String verifyCode;//验证码
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer id, String username, String password, String realname, String email, String employeeId, int age,
			String sex, String dept) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.email = email;
		this.employeeId = employeeId;
		this.age = age;
		this.sex = sex;
		this.dept = dept;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	public String getReloginpass() {
		return reloginpass;
	}
	public void setReloginpass(String reloginpass) {
		this.reloginpass = reloginpass;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", realname=" + realname
				+ ", email=" + email + ", employeeId=" + employeeId + ", age=" + age + ", sex=" + sex + ", dept=" + dept
				+ "]";
	}
	
	
}
