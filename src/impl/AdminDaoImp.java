package impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import bean.Admin;
import dao.IAdminDao;
import utils.JdbcUtil;

public class AdminDaoImp implements IAdminDao{
	@Override
	public int updateAdmin(Admin admin) {
		int res = 0;
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("update admin set PASSWORD=?,loginTime=? where id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(admin.getPassword());
		paramList.add(admin.getLoginTime());
		paramList.add(admin.getId());
		try {
			res = jdbcUtil.updateByPreparedStatement(sql.toString(), paramList);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return res;
	}

	@Override
	public Admin findAdmin(String username, String password) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("select * from admin where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		Admin admin = null;
		paramList.add(username);
		sql.append(" and username = ?");
		paramList.add(password);
		sql.append(" and PASSWORD = ?");
		List<Map<String,Object>> result = null;
		try {
			result = jdbcUtil.findResult(sql.toString(), paramList);
			for(Map<String,Object> m:result) {
				admin = new Admin(Integer.parseInt(String.valueOf(m.get("id"))),
						String.valueOf(m.get("username")),String.valueOf(m.get("PASSWORD")),
						String.valueOf(m.get("email")),String.valueOf(m.get("loginTime")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return admin;
	}

	@Override
	public int saveAdmin(Admin admin) {
		int result = -1;
		StringBuilder sql = new StringBuilder(
				"insert into user(id,username,PASSWORd,email,loginTime) values(?,?,?,?,?);");
		JdbcUtil jdbcUtil = null;
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(admin.getId());
		paramList.add(admin.getUsername());
		paramList.add(admin.getPassword());
		paramList.add(admin.getEmail());
		paramList.add(admin.getLoginTime());

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
	public Admin findAdmin(String username) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("select * from admin where username = ?");
		List<Object> paramList = new ArrayList<Object>();
		Admin admin = null;
		paramList.add(username);
		List<Map<String,Object>> result = null;
		try {
			result = jdbcUtil.findResult(sql.toString(), paramList);
			for(Map<String,Object> m:result) {
				admin = new Admin(Integer.parseInt(String.valueOf(m.get("id"))),
						String.valueOf(m.get("username")),String.valueOf(m.get("PASSWORD")),
						String.valueOf(m.get("email")),String.valueOf(m.get("loginTime")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return admin;
	}
}
