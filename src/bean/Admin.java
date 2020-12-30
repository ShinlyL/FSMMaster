package bean;
/**
 * 管理员 的实体类
 * @author Administrator
 *
 */
public class Admin {
	private Integer id;
	private String username;
	private String password;
	private String email;
	private String loginTime;
	public Admin() {
		super();
	}
	public Admin(Integer id, String username, String password, String email, String loginTime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.loginTime = loginTime;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", loginTime=" + loginTime + "]";
	}
}
