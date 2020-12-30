package impl;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import bean.Inventory;
import bean.User;
import service.IUserService;
import utils.Mail;
import utils.MailUtils;
import utils.Page;

public class UserServiceImp implements IUserService {
	private UserDaoImp userDaoImp = new UserDaoImp();

	@Override
	public List<User> findAllUser() {
		return userDaoImp.findAllUser();
	}

	@Override
	public int updateUser(User user) {
		return userDaoImp.updateUser(user);

	}

	@Override
	public int saveUser(User user) {
		return userDaoImp.saveUser(user);

	}

	@Override
	public boolean verifyUser(String username, String password) {
		return userDaoImp.verifyUser(username, password);
	}

	@Override
	public Integer findUserId(User user) {
		return userDaoImp.findUserId(user);
	}

	@Override
	public boolean checkUser(String username) {
		return userDaoImp.checkUser(username);
	}

	@Override
	public User findUser(String username, String password) {
		return userDaoImp.findUser(username, password);
	}

	@Override
	public int updateUserInfo(User user) {
		return userDaoImp.updateUserInfo(user);
	}

	@Override
	public Page<Inventory> getUserPage(Page<Inventory> page, Integer curPage) {
		// 查询当前页的列表数据
		List<User> data = userDaoImp.getUser(page, curPage);
		// 查询总记录数
		int rowsCount = userDaoImp.getUserCount();
		// 将分页信息封装到page对象中
		page.setParam(data, curPage, rowsCount);
		return page;
	}

	@Override
	public User queryUserById(String id) {
		return userDaoImp.queryUserById(id);
	}

	@Override
	public boolean ajaxValidateEmail(String email) {
		return userDaoImp.ajaxValidateEmail(email);
	}

	@Override
	public void regist(String email,String verifyCode) {
		// 发邮件
		// 把配置文件内容加载到prop中
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		/*
		 * 登录邮件服务器，得到session
		 */
		String host = prop.getProperty("host");// 服务器主机名
		String name = prop.getProperty("username");// 登录名
		String pass = prop.getProperty("password");// 登录密码
		Session session = MailUtils.createSession(host, name, pass);
		/*
		 * 创建Mail对象
		 */
		String from = prop.getProperty("from");
		String to = email;
		String subject = prop.getProperty("subject");
		// MessageForm.format方法会把第一个参数中的{0},使用第二个参数来替换。
		// 例如MessageFormat.format("你好{0}, 你{1}!", "张三", "nmsl"); 返回“你好张三，nmsl！”
//		String content = MessageFormat.format(prop.getProperty("content"),verifyCode,"。");
		String content = prop.getProperty("content").concat(verifyCode).concat("。");
		
		
		Mail mail = new Mail(from, to, subject, content);
		/*
		 * 发送邮件
		 */
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User queryUserByUserName(String username) {
		return userDaoImp.queryUserByUsername(username);
	}
}
