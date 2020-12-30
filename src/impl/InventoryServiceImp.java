package impl;

import java.util.List;

import bean.Apply;
import bean.Inventory;
import service.IInventoryService;
import utils.Page;

public class InventoryServiceImp implements IInventoryService{
	InventoryDaoImp InventoryDao = new InventoryDaoImp();
		
	@Override
	public List<Inventory> getInventory(Page page, int curpage) {
		return InventoryDao.getInventory(page, curpage);
	}

	@Override
	public int getInventoryCount() {
		return InventoryDao.getInventoryCount();
	}

	@Override
	public int delInventory(String id) {
		return InventoryDao.delInventory(id);
	}

	@Override
	public int insertInventory(Inventory inventory) {
		return InventoryDao.insertInventory(inventory);
	}

	@Override
	public Inventory queryInventory(String id) {
		return InventoryDao.queryInventory(id);
	}

	@Override
	public int updateInventory(Inventory inventory) {
		return InventoryDao.updateInventory(inventory);
	}

	@Override
	public Page<Inventory> getInventoryPage(Page<Inventory> page,Integer curPage){
		//查询当前页的列表数据
		List<Inventory> data = InventoryDao.getInventory(page,curPage);
		//查询总记录数
		int rowsCount = InventoryDao.getInventoryCount();
		//将分页信息封装到page对象中
		page.setParam(data, curPage, rowsCount);
		return page;
	}

	@Override
	public Inventory queryInventoryByName(String name) {
		return InventoryDao.queryInventoryByName(name);
	}

	@Override
	public Inventory queryInventoryByBarcode(String barcode) {
		return InventoryDao.queryInventoryByBarcode(barcode);
	}

	@Override
	public List<Inventory> getNewInventory(int num) {
		return InventoryDao.getNewInventory(num);
	}

	@Override
	public boolean pickInventory(Apply apply) {
		Inventory inventory = InventoryDao.queryInventoryByBarcode(apply.getBarcode());
		if(inventory.getNum() >= apply.getNum()) {
			//剩余数量
			int balance = inventory.getNum() - apply.getNum(); 
			inventory.setNum(balance);
			InventoryDao.updateInventory(inventory);
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void revocationInventory(Apply apply) {
		Inventory inventory = InventoryDao.queryInventoryByBarcode(apply.getBarcode());
		inventory.setNum(inventory.getNum()+apply.getNum());
		InventoryDao.updateInventory(inventory);
	}
}
