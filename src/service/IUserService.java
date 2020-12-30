package service;

import java.util.List;

import bean.Inventory;
import bean.User;
import utils.Page;

public interface IUserService {

/**
 * 查找所有用户信息
 * @return List<User>
 */
	List<User> findAllUser();
	
/**
 * 更新用户信息 -修改用户的密码
 * @param user
 * @return
 */
	int updateUser(User user);
	/**
	 * 更新用户信息-修改用户的 真实姓名、身份证、年龄、性别、部门
	 * @param user
	 * @return
	 */
	int updateUserInfo(User user);
/**
 * 插入用户信息
 * @param user
 * @return int
 */
	int saveUser(User user);
	
/**
 * 根据用户登录的信息查找到用户的id
 * @param user
 * @return Integer
 */
	Integer findUserId(User user);
	
	
/**
 * 检查用户的用户名信息是否重复	
 * @param user
 * @return T 存在该用户名  F 表示不存在该用户名
 */
	boolean checkUser(String username);
	
/**	
 * 查找用户用于登录 根据用户名和密码
 * @param id
 * @return boolean
 */
	boolean verifyUser(String username,String password);
	/**
	 * 查找用户
	 * @param username
	 * @param password
	 * @return User
	 */
	User findUser(String username,String password);
	/**
	 * 获取用户列表的分页内容
	 * @param page
	 * @param curPage
	 * @return
	 */
	Page<Inventory> getUserPage(Page<Inventory> page,Integer curPage);
	
	/**
	 * 通过 id 查找用户信息
	 * @param id
	 * @return User
	 */
	User queryUserById(String id);
	
	/**
	 * 通过 username 查找用户信息
	 * @param username
	 * @return User
	 */
	User queryUserByUserName(String userName);
	
	/**
	 * Email校验
	 * @param email
	 * @return
	 */
	boolean ajaxValidateEmail(String email);
	
	/**
	 * 注册功能
	 * @param user
	 */
	void regist(String email,String verifyCode);
}
