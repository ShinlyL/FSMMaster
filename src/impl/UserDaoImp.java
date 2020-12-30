package impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.Inventory;
import bean.User;
import dao.IUserDao;
import utils.JdbcUtil;
import utils.Page;

public class UserDaoImp implements IUserDao{

	@Override
	public List<User> findAllUser() {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<User> list = new ArrayList<User>();
		try {
			List<Map<String,Object>> res = jdbcUtil.findResult("select * from user", null);
			for(Map<String,Object> m:res) {
				User user = new User(Integer.parseInt(String.valueOf(m.get("id"))),
						String.valueOf(m.get("username")),String.valueOf(m.get("PASSWORD")),
						String.valueOf(m.get("realName")),String.valueOf(m.get("email")),
						String.valueOf(m.get("employeeId")),Integer.parseInt(String.valueOf(m.get("age"))),
						String.valueOf(m.get("sex")),String.valueOf(m.get("dept")));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();		
		return list;
	}

	// 将用户信息存入到数据库中
	@Override
	public int saveUser(User user) {
		int result = -1;
		StringBuilder sql = new StringBuilder(
				"insert into user(id,username,PASSWORd,realName,email,employeeId,age,sex,dept) values(?,?,?,?,?,?,?,?,?);");
		JdbcUtil jdbcUtil = null;
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(user.getId());
		paramList.add(user.getUsername());
		paramList.add(user.getPassword());
		paramList.add(user.getRealname());
		paramList.add(user.getEmail());
		paramList.add(user.getEmployeeId());
		paramList.add(user.getAge());
		paramList.add(user.getSex());
		paramList.add(user.getDept());

		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection(); // 获取数据库连接
			result = jdbcUtil.updateByPreparedStatement(sql.toString(), paramList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateUser(User user) {
		int res = 0;
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("update user set PASSWORD=? where id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(user.getPassword());
		paramList.add(user.getId());
		try {
			res = jdbcUtil.updateByPreparedStatement(sql.toString(), paramList);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return res;
	}

	@Override
	public boolean verifyUser(String username, String password) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("select * from user where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(username);
		sql.append(" and username = ?");
		paramList.add(password);
		sql.append(" and PASSWORD = ?");
		List<Map<String,Object>> result = null;
		try {
			result = jdbcUtil.findResult(sql.toString(), paramList);
			if(result.size() != 0) {
				jdbcUtil.releaseConnection();
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return false;
	}


	@Override
	public Integer findUserId(User user) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("select * from user where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(user.getUsername());
		sql.append(" and username = ?");
		paramList.add(user.getPassword());
		sql.append(" and PASSWORD = ?");
		List<Map<String,Object>> result = null;
		try {
			result = jdbcUtil.findResult(sql.toString(), paramList);
			String id = String.valueOf(result.get(0).get("id"));
			jdbcUtil.releaseConnection();
			return Integer.parseInt(id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return -1;
	}


	@Override
	public boolean checkUser(String username) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("select * from user where username=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(username);
		List<Map<String,Object>> result = null;
		try {
			result = jdbcUtil.findResult(sql.toString(), paramList);
			if(result.size() == 1) {
				jdbcUtil.releaseConnection();
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return false;
	}

	@Override
	public User findUser(String username, String password) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("select * from user where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(username);
		sql.append(" and username = ?");
		paramList.add(password);
		sql.append(" and PASSWORD = ?");
		List<Map<String,Object>> result = null;
		User user = null;
		try {
			result = jdbcUtil.findResult(sql.toString(), paramList);
			for(Map<String,Object> m:result) {
				user = new User(Integer.parseInt(String.valueOf(m.get("id"))),
						String.valueOf(m.get("username")),String.valueOf(m.get("PASSWORD")),
						String.valueOf(m.get("realName")),String.valueOf(m.get("email")),
						String.valueOf(m.get("employeeId")),Integer.parseInt(String.valueOf(m.get("age"))),
						String.valueOf(m.get("sex")),String.valueOf(m.get("dept")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return user;
	}

	@Override
	public int updateUserInfo(User user) {
		int res = 0;
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("update user set realName=?,email=?,employeeId=?,age=?,sex=?,dept=? where id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(user.getRealname());
		paramList.add(user.getEmail());
		paramList.add(user.getEmployeeId());
		paramList.add(user.getAge());
		paramList.add(user.getSex());
		paramList.add(user.getDept());
		paramList.add(user.getId());
		try {
			res = jdbcUtil.updateByPreparedStatement(sql.toString(), paramList);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return res;
	}

	@Override
	public int getUserCount() {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<Map<String,Object>> res = null;		
		StringBuilder sql = new StringBuilder("select * from user");
		try {
			 res = jdbcUtil.findResult(sql.toString(), null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();		
		return res.size();
	}

	@Override
	public List<User> getUser(Page page, int curpage) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<User> list = new ArrayList<User>();
		StringBuilder sql = new StringBuilder("select * from user order by id desc limit "+
								((curpage-1)*page.getPageSize())+","+page.getPageSize());
		try {
			List<Map<String,Object>> res = jdbcUtil.findResult(sql.toString(), null);
			for(Map<String,Object> m:res) {
				User user =  new User(Integer.parseInt(String.valueOf(m.get("id"))),
						String.valueOf(m.get("username")),String.valueOf(m.get("PASSWORD")),
						String.valueOf(m.get("realName")),String.valueOf(m.get("email")),
						String.valueOf(m.get("employeeId")),Integer.parseInt(String.valueOf(m.get("age"))),
						String.valueOf(m.get("sex")),String.valueOf(m.get("dept")));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();		
		return list;
	}

	@Override
	public User queryUserById(String id) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("select * from user where id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(id);
		List<Map<String,Object>> result = null;
		User user = null;
		try {
			result = jdbcUtil.findResult(sql.toString(), paramList);
			for(Map<String,Object> m:result) {
				user = new User(Integer.parseInt(String.valueOf(m.get("id"))),
						String.valueOf(m.get("username")),String.valueOf(m.get("PASSWORD")),
						String.valueOf(m.get("realName")),String.valueOf(m.get("email")),
						String.valueOf(m.get("employeeId")),Integer.parseInt(String.valueOf(m.get("age"))),
						String.valueOf(m.get("sex")),String.valueOf(m.get("dept")));
			}
			jdbcUtil.releaseConnection();
			return user;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return null;
	}

	@Override
	public boolean ajaxValidateEmail(String email) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("select * from user where email = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(email);
		List<Map<String,Object>> result = null;
		try {
			result = jdbcUtil.findResult(sql.toString(), paramList);
			if(result.size() == 0) {
				jdbcUtil.releaseConnection();
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return false;
	}

	@Override
	public User queryUserByUsername(String username) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("select * from user where username = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(username);
		List<Map<String,Object>> result = null;
		User user = null;
		try {
			result = jdbcUtil.findResult(sql.toString(), paramList);
			for(Map<String,Object> m:result) {
				user = new User(Integer.parseInt(String.valueOf(m.get("id"))),
						String.valueOf(m.get("username")),String.valueOf(m.get("PASSWORD")),
						String.valueOf(m.get("realName")),String.valueOf(m.get("email")),
						String.valueOf(m.get("employeeId")),Integer.parseInt(String.valueOf(m.get("age"))),
						String.valueOf(m.get("sex")),String.valueOf(m.get("dept")));
			}
			jdbcUtil.releaseConnection();
			return user;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return null;
	}
}
