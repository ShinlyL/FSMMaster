package service;

import java.util.List;

import bean.Apply;
import bean.Inventory;
import utils.Page;

public interface IInventoryService {
	
	List<Inventory> getInventory(Page page,int curpage);
	
	int getInventoryCount();
	
	int delInventory(String id);
	
	int insertInventory(Inventory inventory);
	
	Inventory queryInventory(String id);
	
	Inventory queryInventoryByName(String name);

	int updateInventory(Inventory inventory);
	
	/**
	 * 获取物品列表的分页内容
	 * @param page
	 * @param curPage
	 * @return
	 */
	Page<Inventory> getInventoryPage(Page<Inventory> page,Integer curPage);
	
	Inventory queryInventoryByBarcode(String barcode);
	/**
	 * 根据申领的条件 从 库存中取出相应数量的物品
	 */
	boolean pickInventory(Apply apply);
	/**
	 * 将 撤销的申领的物品的数量返回给库存物品数据中
	 */
	void revocationInventory(Apply apply);
	
	List<Inventory> getNewInventory(int num);
}
