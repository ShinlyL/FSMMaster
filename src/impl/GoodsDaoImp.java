package impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.Goods;
import dao.IGoodsDao;
import utils.JdbcUtil;
import utils.Page;

public class GoodsDaoImp implements IGoodsDao{
	
	@Override
	public List<Goods> getGoods(Page page, int curpage) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<Goods> list = new ArrayList<Goods>();
		// 更新物品数据库中flag的状态
		new GoodsDaoImp().updateGoodsFlag();
		StringBuilder sql = new StringBuilder("select * from goods order by id desc limit "+
								((curpage-1)*page.getPageSize())+","+page.getPageSize());
		try {
			List<Map<String,Object>> res = jdbcUtil.findResult(sql.toString(), null);
			for(Map<String,Object> m:res) {
				Goods goods = new Goods(Integer.parseInt(String.valueOf(m.get("id")))
						,String.valueOf(m.get("NAME")),String.valueOf(m.get("barcode")),
						String.valueOf(m.get("purchaseprice")),String.valueOf(m.get("typeone"))
						,String.valueOf(m.get("info")),String.valueOf(m.get("flag"))
						,String.valueOf(m.get("typetwo")));
				list.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();		
		return list;
	}

	@Override
	public int getGoodsCount() {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<Map<String,Object>> res = null;		
		StringBuilder sql = new StringBuilder("select * from goods");
		try {
			 res = jdbcUtil.findResult(sql.toString(), null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();		
		return res.size();
	}

	@Override
	public int delGoods(String id) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("delete from goods where id = ?;");
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
	public int insertGoods(Goods goods) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("insert into goods(id,NAME,barcode,purchaseprice,typeone,typetwo,info,flag) values(?,?,?,?,?,?,?,?)");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(goods.getId());
		paramList.add(goods.getName());
		paramList.add(goods.getBarcode());
		paramList.add(goods.getPurchaseprice());
		paramList.add(goods.getTypeone());
		paramList.add(goods.getTypetwo());
		paramList.add(goods.getInfo());
		paramList.add(goods.getFlag());
		try {
			return jdbcUtil.updateByPreparedStatement(sql.toString(), paramList);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return 0;
	}

	@Override
	public Goods queryGoods(String id) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("select * from goods where id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(id);
		List<Map<String,Object>> result = null;
		Goods goods = null;
		try {
			result = jdbcUtil.findResult(sql.toString(), paramList);
			for(Map<String,Object> m:result) {
				goods = new Goods(Integer.parseInt(String.valueOf(m.get("id")))
						,String.valueOf(m.get("NAME")),String.valueOf(m.get("barcode")),
						String.valueOf(m.get("purchaseprice")),String.valueOf(m.get("typeone"))
						,String.valueOf(m.get("info")),String.valueOf(m.get("flag"))
						,String.valueOf(m.get("typetwo")));
			}
			jdbcUtil.releaseConnection();
			return goods;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return null;
	}

	@Override
	public int updateGoods(Goods goods) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("update goods set NAME=?,barcode=?,purchaseprice=?,typeone=?,typetwo=?,info=?,flag=? where id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(goods.getName());
		paramList.add(goods.getBarcode());
		paramList.add(goods.getPurchaseprice());
		paramList.add(goods.getTypeone());
		paramList.add(goods.getTypetwo());
		paramList.add(goods.getInfo());
		paramList.add(goods.getFlag());
		paramList.add(goods.getId());
		try {
			return jdbcUtil.updateByPreparedStatement(sql.toString(), paramList);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return 0;
	}

	@Override
	public Goods queryGoodsBybarcode(String barcode) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("select * from goods where barcode = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(barcode);
		List<Map<String,Object>> result = null;
		Goods goods = null;
		try {
			result = jdbcUtil.findResult(sql.toString(), paramList);
			for(Map<String,Object> m:result) {
				goods = new Goods(Integer.parseInt(String.valueOf(m.get("id")))
						,String.valueOf(m.get("NAME")),String.valueOf(m.get("barcode")),
						String.valueOf(m.get("purchaseprice")),String.valueOf(m.get("typeone"))
						,String.valueOf(m.get("info")),String.valueOf(m.get("flag"))
						,String.valueOf(m.get("typetwo")));
			}
			jdbcUtil.releaseConnection();
			return goods;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return null;
	}

	@Override
	public void updateGoodsFlag() {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql1 = new StringBuilder("UPDATE goods,inventory SET goods.`flag`='缺货' WHERE goods.`barcode`=inventory.`barcode` AND inventory.`num`=0;");
		StringBuilder sql2 = new StringBuilder("UPDATE goods,inventory SET goods.`flag`='有货' WHERE goods.`barcode`=inventory.`barcode` AND inventory.`num`>0;");
		try {
			jdbcUtil.updateByPreparedStatement(sql1.toString(), null);
			jdbcUtil.updateByPreparedStatement(sql2.toString(), null);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();		
	}

	@Override
	public List<String> getGoodsName() {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("SELECT  inventory.`NAME` FROM inventory LEFT JOIN goods ON (goods.`barcode` = inventory.`barcode`) "
												+ "WHERE goods.`barcode` IS NULL ;");
		List<Map<String,Object>> result = null;
		List<String> res = new ArrayList<String>();
		try {
			result = jdbcUtil.findResult(sql.toString(), null);
			for(Map<String,Object> m:result) {
				res.add(String.valueOf(m.get("NAME")));
			}
			jdbcUtil.releaseConnection();
			return res;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return res;
	}
}
