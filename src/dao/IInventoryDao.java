package dao;

import java.util.List;

import bean.Apply;
import bean.Inventory;
import utils.Page;

public interface IInventoryDao {
	/**
	 * 根据条件分页显示 库存的货物
	 * @param page
	 * @param curpage
	 * @return 
	 */
	List<Inventory> getInventory(Page page,int curpage);
	/**
	 * 查询库存的总记录数量
	 * @return
	 */
	int getInventoryCount();
	
	
	/**
	 * 根据id删除一个库存中货物
	 * @param id
	 * @return
	 */
	int delInventory(String id);
	
	/**
	 * 插入一个 库存货物信息
	 * @param repertpory
	 * @return
	 */
	int insertInventory(Inventory inventory);
	
	/**
	 * 根据id查找货物
	 * @param id
	 * @return
	 */
	Inventory queryInventory(String id);
	
	/**
	 * 根据名称查找货物
	 * @param name
	 * @return
	 */
	Inventory queryInventoryByName(String name);
	
	/**
	 * 修改货物的信息
	 * @param repertory
	 * @return
	 */
	int updateInventory(Inventory inventory);
	
	/**
	 * 根据条形码查找货物
	 * @param barcode
	 * @return
	 */
	Inventory queryInventoryByBarcode(String barcode);
	
	/**
	 * 获取最新的几个库存物品消息
	 */
	List<Inventory> getNewInventory(int num);
	
	
}
