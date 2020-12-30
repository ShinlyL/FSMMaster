package impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.Inside;
import dao.IInsideDao;
import utils.JdbcUtil;
import utils.Page;

public class InsideDaoImp implements IInsideDao{
	@Override
	public List<Inside> getInside(Page page, int curpage) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<Inside> list = new ArrayList<Inside>();
		StringBuilder sql = new StringBuilder("select * from inside order by id desc limit "+
								((curpage-1)*page.getPageSize())+","+page.getPageSize());
		try {
			List<Map<String,Object>> res = jdbcUtil.findResult(sql.toString(), null);
			for(Map<String,Object> m:res) {
				Inside inside = new Inside(Integer.parseInt((String.valueOf(m.get("id"))))
						,String.valueOf(m.get("NAME")),String.valueOf(m.get("barcode"))
						,Double.valueOf(String.valueOf(m.get("purchaseprice"))),Integer.valueOf(String.valueOf(m.get("num")))
						,Double.valueOf(String.valueOf(m.get("sums"))),String.valueOf(m.get("manager"))
						,String.valueOf(m.get("releaseDate")),Integer.valueOf(String.valueOf(m.get("STATUS"))));
				list.add(inside);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();		
		return list;
	}

	@Override
	public int getInsideCount() {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<Map<String,Object>> res = null;		
		StringBuilder sql = new StringBuilder("select * from inside");
		try {
			 res = jdbcUtil.findResult(sql.toString(), null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();		
		return res.size();
	}

	@Override
	public int delInside(String id) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("delete from inside where id = ?");
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
	public int insertInside(Inside inside) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("insert into inside(id,NAME,barcode,purchaseprice,num,sums,manager,releaseDate,STATUS) values(?,?,?,?,?,?,?,?,?)");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(inside.getId());
		paramList.add(inside.getName());
		paramList.add(inside.getBarcode());
		paramList.add(inside.getPurchaseprice());
		paramList.add(inside.getNum());
		paramList.add(inside.getSums());
		paramList.add(inside.getManager());
		paramList.add(inside.getReleaseDate());
		paramList.add(inside.getStatus());
		try {
			return jdbcUtil.updateByPreparedStatement(sql.toString(), paramList);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return 0;
	}

	@Override
	public Inside queryInside(String id) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("select * from inside where id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(id);
		List<Map<String,Object>> result = null;
		Inside inside = null;
		try {
			result = jdbcUtil.findResult(sql.toString(), paramList);
			for(Map<String,Object> m:result) {
				inside = new Inside(Integer.parseInt((String.valueOf(m.get("id"))))
						,String.valueOf(m.get("NAME")),String.valueOf(m.get("barcode"))
						,Double.valueOf(String.valueOf(m.get("purchaseprice"))),Integer.valueOf(String.valueOf(m.get("num")))
						,Double.valueOf(String.valueOf(m.get("sums"))),String.valueOf(m.get("manager"))
						,String.valueOf(m.get("releaseDate")),Integer.valueOf(String.valueOf(m.get("STATUS"))));
			}
			jdbcUtil.releaseConnection();
			return inside;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return null;
	}

	@Override
	public int updateInside(Inside inside) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("update inside set NAME=?,barcode=?,purchaseprice=?,num=?,sums=?,manager=?,releaseDate=?,STATUS=? where id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(inside.getName());
		paramList.add(inside.getBarcode());
		paramList.add(inside.getPurchaseprice());
		paramList.add(inside.getNum());
		paramList.add(inside.getSums());
		paramList.add(inside.getManager());
		paramList.add(inside.getReleaseDate());
		paramList.add(inside.getStatus());
		paramList.add(inside.getId());
		try {
			return jdbcUtil.updateByPreparedStatement(sql.toString(), paramList);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return 0;
	}

	@Override
	public Inside queryInsideBybarcode(String barcode) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("select * from inside where barcode = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(barcode);
		List<Map<String,Object>> result = null;
		Inside inside = null;
		try {
			result = jdbcUtil.findResult(sql.toString(), paramList);
			for(Map<String,Object> m:result) {
				inside = new Inside(Integer.parseInt((String.valueOf(m.get("id"))))
						,String.valueOf(m.get("NAME")),String.valueOf(m.get("barcode"))
						,Double.valueOf(String.valueOf(m.get("purchaseprice"))),Integer.valueOf(String.valueOf(m.get("num")))
						,Double.valueOf(String.valueOf(m.get("sums"))),String.valueOf(m.get("manager"))
						,String.valueOf(m.get("releaseDate")),Integer.valueOf(String.valueOf(m.get("STATUS"))));
			}
			jdbcUtil.releaseConnection();
			return inside;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return null;
	}

	@Override
	public List<Inside> getInside(Page page, int curpage, String status) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<Inside> list = new ArrayList<Inside>();
		StringBuilder sql = new StringBuilder("select * from inside where STATUS=? order by id desc limit "+
								((curpage-1)*page.getPageSize())+","+page.getPageSize());
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(status);
		try {
			List<Map<String,Object>> res = jdbcUtil.findResult(sql.toString(), paramList);
			for(Map<String,Object> m:res) {
				Inside inside = new Inside(Integer.parseInt((String.valueOf(m.get("id"))))
						,String.valueOf(m.get("NAME")),String.valueOf(m.get("barcode"))
						,Double.valueOf(String.valueOf(m.get("purchaseprice"))),Integer.valueOf(String.valueOf(m.get("num")))
						,Double.valueOf(String.valueOf(m.get("sums"))),String.valueOf(m.get("manager"))
						,String.valueOf(m.get("releaseDate")),Integer.valueOf(String.valueOf(m.get("STATUS"))));
				list.add(inside);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();		
		return list;
	}

	@Override
	public int getInsideCount(String status) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<Map<String,Object>> res = null;		
		StringBuilder sql = new StringBuilder("select * from inside where STATUS=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(status);
		try {
			 res = jdbcUtil.findResult(sql.toString(), paramList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();		
		return res.size();
	}
}