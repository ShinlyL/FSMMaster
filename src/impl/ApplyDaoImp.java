package impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.Apply;
import dao.IApplyDao;
import utils.JdbcUtil;
import utils.Page;

public class ApplyDaoImp implements IApplyDao{
	@Override
	public List<Apply> getApply(Page page, int curpage) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<Apply> list = new ArrayList<Apply>();
		StringBuilder sql = new StringBuilder("select * from apply order by id desc limit "+
								((curpage-1)*page.getPageSize())+","+page.getPageSize());
		try {
			List<Map<String,Object>> res = jdbcUtil.findResult(sql.toString(), null);
			for(Map<String,Object> m:res) {
				Apply apply = new Apply(Integer.parseInt((String.valueOf(m.get("id"))))
						,String.valueOf(m.get("NAME")),String.valueOf(m.get("barcode"))
						,Double.valueOf(String.valueOf(m.get("purchaseprice"))),Integer.valueOf(String.valueOf(m.get("num")))
						,Double.valueOf(String.valueOf(m.get("sums"))),String.valueOf(m.get("manager"))
						,String.valueOf(m.get("releaseDate")),String.valueOf(m.get("flag")));
				list.add(apply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();		
		return list;
	}

	@Override
	public int getApplyCount() {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<Map<String,Object>> res = null;		
		StringBuilder sql = new StringBuilder("select * from apply");
		try {
			 res = jdbcUtil.findResult(sql.toString(), null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();		
		return res.size();
	}

	@Override
	public int delApply(String id) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("delete from apply where id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(id);
		try {
			return jdbcUtil.updateByPreparedStatement(sql.toString(), paramList);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return 0;
	}

	@Override
	public int insertApply(Apply apply) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("insert into apply(id,NAME,barcode,purchaseprice,num,sums,manager,releaseDate,flag) values(?,?,?,?,?,?,?,?,?)");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(apply.getId());
		paramList.add(apply.getName());
		paramList.add(apply.getBarcode());
		paramList.add(apply.getPurchaseprice());
		paramList.add(apply.getNum());
		paramList.add(apply.getSums());
		paramList.add(apply.getManager());
		paramList.add(apply.getReleaseDate());
		paramList.add(apply.getFlag());
		try {
			return jdbcUtil.updateByPreparedStatement(sql.toString(), paramList);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return 0;
	}

	@Override
	public Apply queryApply(String id) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("select * from apply where id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(id);
		List<Map<String,Object>> result = null;
		Apply apply = null;
		try {
			result = jdbcUtil.findResult(sql.toString(), paramList);
			for(Map<String,Object> m:result) {
				apply = new Apply(Integer.parseInt((String.valueOf(m.get("id"))))
						,String.valueOf(m.get("NAME")),String.valueOf(m.get("barcode"))
						,Double.valueOf(String.valueOf(m.get("purchaseprice"))),Integer.valueOf(String.valueOf(m.get("num")))
						,Double.valueOf(String.valueOf(m.get("sums"))),String.valueOf(m.get("manager"))
						,String.valueOf(m.get("releaseDate")),String.valueOf(m.get("flag")));
			}
			jdbcUtil.releaseConnection();
			return apply;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return null;
	}

	@Override
	public int updateApply(Apply apply) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("update apply set NAME=?,barcode=?,purchaseprice=?,num=?,sums=?,manager=?,releaseDate=?,flag=? where id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(apply.getName());
		paramList.add(apply.getBarcode());
		paramList.add(apply.getPurchaseprice());
		paramList.add(apply.getNum());
		paramList.add(apply.getSums());
		paramList.add(apply.getManager());
		paramList.add(apply.getReleaseDate());
		paramList.add(apply.getFlag());
		paramList.add(apply.getId());
		try {
			return jdbcUtil.updateByPreparedStatement(sql.toString(), paramList);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return 0;
	}

	@Override
	public List<Apply> getApply(Page page, int curpage, String flag) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<Apply> list = new ArrayList<Apply>();
		StringBuilder sql = new StringBuilder("select * from apply where flag = ? order by id desc limit "+
								((curpage-1)*page.getPageSize())+","+page.getPageSize());
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(flag);
		try {
			List<Map<String,Object>> res = jdbcUtil.findResult(sql.toString(), paramList);
			for(Map<String,Object> m:res) {
				Apply apply = new Apply(Integer.parseInt((String.valueOf(m.get("id"))))
						,String.valueOf(m.get("NAME")),String.valueOf(m.get("barcode"))
						,Double.valueOf(String.valueOf(m.get("purchaseprice"))),Integer.valueOf(String.valueOf(m.get("num")))
						,Double.valueOf(String.valueOf(m.get("sums"))),String.valueOf(m.get("manager"))
						,String.valueOf(m.get("releaseDate")),String.valueOf(m.get("flag")));
				list.add(apply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();		
		return list;
	}

	@Override
	public List<Apply> getApply(Page page, int curpage, String flag, String username) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<Apply> list = new ArrayList<Apply>();
		StringBuilder sql = new StringBuilder("select * from apply where flag = ? and manager = ? order by id desc limit "+
								((curpage-1)*page.getPageSize())+","+page.getPageSize());
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(flag);
		paramList.add(username);
		try {
			List<Map<String,Object>> res = jdbcUtil.findResult(sql.toString(), paramList);
			for(Map<String,Object> m:res) {
				Apply apply = new Apply(Integer.parseInt((String.valueOf(m.get("id"))))
						,String.valueOf(m.get("NAME")),String.valueOf(m.get("barcode"))
						,Double.valueOf(String.valueOf(m.get("purchaseprice"))),Integer.valueOf(String.valueOf(m.get("num")))
						,Double.valueOf(String.valueOf(m.get("sums"))),String.valueOf(m.get("manager"))
						,String.valueOf(m.get("releaseDate")),String.valueOf(m.get("flag")));
				list.add(apply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();		
		return list;
	}

	@Override
	public int getApplyCount(String flag) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<Map<String,Object>> res = null;		
		StringBuilder sql = new StringBuilder("select * from apply where flag=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(flag);
		try {
			 res = jdbcUtil.findResult(sql.toString(), paramList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();		
		return res.size();
	}

	@Override
	public int getApplyCount(String flag, String username) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<Map<String,Object>> res = null;		
		StringBuilder sql = new StringBuilder("select * from apply where flag=? and manager=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(flag);
		paramList.add(username);
		try {
			 res = jdbcUtil.findResult(sql.toString(), paramList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();		
		return res.size();
	}
}
