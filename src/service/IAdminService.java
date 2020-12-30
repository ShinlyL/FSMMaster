package service;

import bean.Admin;
public interface IAdminService {
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
	 * 根据用户名查找管理员账号， 防止用户名重复
	 * @param username
	 * @return Admin
	 */
	Admin findAdmin(String username);
	/**
	 * 注册管理员账户
	 * @param admin
	 */
	int saveAdmin(Admin admin);
}