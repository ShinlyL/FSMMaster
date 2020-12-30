package dao;

import bean.Admin;
public interface IAdminDao {
	/**
	 * 更新管理员账户的信息
	 * @param admin
	 * @return
	 */
	int updateAdmin(Admin admin);
	/**
	 * 根据用户名和密码查找管理员账号， 用于登录
	 * @param username
	 * @param password
	 * @return
	 */
	Admin findAdmin(String username,String password);
	/**
	 * 注册管理员账户
	 * @param admin
	 */
	int saveAdmin(Admin admin);
	
	/**
	 * 查找用户名，检查是否已存在
	 * @param username
	 * @param password
	 * @return
	 */
	Admin findAdmin(String username);
}