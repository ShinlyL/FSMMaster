package impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.Apply;
import bean.Inventory;
import dao.IInventoryDao;
import utils.JdbcUtil;
import utils.Page;

public class InventoryDaoImp implements IInventoryDao{
	@Override
	public List<Inventory> getInventory(Page page, int curpage) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<Inventory> list = new ArrayList<Inventory>();
		StringBuilder sql = new StringBuilder("select * from inventory where STATUS=0 order by id desc limit "+
								((curpage-1)*page.getPageSize())+","+page.getPageSize());
		try {
			List<Map<String,Object>> res = jdbcUtil.findResult(sql.toString(), null);
			for(Map<String,Object> m:res) {
				Inventory inventory = new Inventory(Integer.parseInt((String.valueOf(m.get("id"))))
						,String.valueOf(m.get("NAME")),String.valueOf(m.get("barcode")),
						Integer.parseInt(String.valueOf(m.get("num"))),String.valueOf(m.get("typeone"))
						,String.valueOf(m.get("typetwo")),String.valueOf(m.get("STATUS")));
				list.add(inventory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();		
		return list;
	}

	@Override
	public int getInventoryCount() {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<Map<String,Object>> res = null;		
		StringBuilder sql = new StringBuilder("select * from inventory where STATUS=0");
		try {
			 res = jdbcUtil.findResult(sql.toString(), null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();		
		return res.size();
	}

	@Override
	public int delInventory(String id) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("update inventory set STATUS=1 where id = ?");
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
	public int insertInventory(Inventory inventory) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("insert into inventory(id,NAME,barcode,num,typeone,typetwo,STATUS) values(?,?,?,?,?,?,?)");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(inventory.getId());
		paramList.add(inventory.getName());
		paramList.add(inventory.getBarcode());
		paramList.add(inventory.getNum());
		paramList.add(inventory.getTypeone());
		paramList.add(inventory.getTypetwo());
		paramList.add(inventory.getStatus());
		try {
			return jdbcUtil.updateByPreparedStatement(sql.toString(), paramList);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return 0;
	}

	@Override
	public Inventory queryInventory(String id) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("select * from inventory where id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(id);
		List<Map<String,Object>> result = null;
		Inventory inventory = null;
		try {
			result = jdbcUtil.findResult(sql.toString(), paramList);
			for(Map<String,Object> m:result) {
				inventory = new Inventory(Integer.parseInt((String.valueOf(m.get("id"))))
						,String.valueOf(m.get("NAME")),String.valueOf(m.get("barcode")),
						Integer.parseInt(String.valueOf(m.get("num"))),String.valueOf(m.get("typeone"))
						,String.valueOf(m.get("typetwo")),String.valueOf(m.get("STATUS")));
			}
			jdbcUtil.releaseConnection();
			return inventory;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return null;
	}

	@Override
	public int updateInventory(Inventory inventory) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("update inventory set NAME=?,barcode=?,num=?,typeone=?,typetwo=? where id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(inventory.getName());
		paramList.add(inventory.getBarcode());
		paramList.add(inventory.getNum());
		paramList.add(inventory.getTypeone());
		paramList.add(inventory.getTypetwo());
		paramList.add(inventory.getId());
		try {
			return jdbcUtil.updateByPreparedStatement(sql.toString(), paramList);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return 0;
	}

	@Override
	public Inventory queryInventoryByName(String name) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("select * from inventory where NAME = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(name);
		List<Map<String,Object>> result = null;
		Inventory inventory = null;
		try {
			result = jdbcUtil.findResult(sql.toString(), paramList);
			for(Map<String,Object> m:result) {
				inventory = new Inventory(Integer.parseInt((String.valueOf(m.get("id"))))
						,String.valueOf(m.get("NAME")),String.valueOf(m.get("barcode")),
						Integer.parseInt(String.valueOf(m.get("num"))),String.valueOf(m.get("typeone"))
						,String.valueOf(m.get("typetwo")),String.valueOf(m.get("STATUS")));
			}
			jdbcUtil.releaseConnection();
			return inventory;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return null;
	}

	@Override
	public Inventory queryInventoryByBarcode(String barcode) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("select * from inventory where barcode = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(barcode);
		List<Map<String,Object>> result = null;
		Inventory inventory = null;
		try {
			result = jdbcUtil.findResult(sql.toString(), paramList);
			for(Map<String,Object> m:result) {
				inventory = new Inventory(Integer.parseInt((String.valueOf(m.get("id"))))
						,String.valueOf(m.get("NAME")),String.valueOf(m.get("barcode")),
						Integer.parseInt(String.valueOf(m.get("num"))),String.valueOf(m.get("typeone"))
						,String.valueOf(m.get("typetwo")),String.valueOf(m.get("STATUS")));
			}
			jdbcUtil.releaseConnection();
			return inventory;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return null;
	}

	@Override
	public List<Inventory> getNewInventory(int num) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		StringBuilder sql = new StringBuilder("SELECT * FROM inventory ORDER BY id DESC LIMIT "+num);
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String,Object>> result = null;
		List<Inventory> inventoryList = new ArrayList<Inventory>();
		try {
			result = jdbcUtil.findResult(sql.toString(), paramList);
			for(Map<String,Object> m:result) {
				Inventory inventory = new Inventory(Integer.parseInt((String.valueOf(m.get("id"))))
						,String.valueOf(m.get("NAME")),String.valueOf(m.get("barcode")),
						Integer.parseInt(String.valueOf(m.get("num"))),String.valueOf(m.get("typeone"))
						,String.valueOf(m.get("typetwo")),String.valueOf(m.get("STATUS")));
				inventoryList.add(inventory);
			}
			jdbcUtil.releaseConnection();
			return inventoryList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.releaseConnection();
		return inventoryList;
	}
}
