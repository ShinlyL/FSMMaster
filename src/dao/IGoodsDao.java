package dao;

import java.util.List;

import bean.Goods;
import utils.Page;

public interface IGoodsDao {
	/**
	 * 根据条件分页显示物品
	 * @param page
	 * @param curpage
	 * @return 
	 */
	List<Goods> getGoods(Page page,int curpage);
	/**
	 * 查询物品的总记录数量
	 * @return
	 */
	int getGoodsCount();
	
	
	/**
	 * 根据id删除一个物品
	 * @param id
	 * @return
	 */
	int delGoods(String id);
	
	/**
	 * 插入一个物品信息
	 * @param repertpory
	 * @return
	 */
	int insertGoods(Goods goods);
	
	/**
	 * 根据id查找物品
	 * @param id
	 * @return
	 */
	Goods queryGoods(String id);
	/**
	 * 根据条形码 查找物品
	 * @param id
	 * @return
	 */
	Goods queryGoodsBybarcode(String barcode);
	/**
	 * 修改物品的信息
	 * @param Goods
	 * @return
	 */
	int updateGoods(Goods goods);
	
	/**
	 * 将所有物品的数量进行检查，如果为0，则标记为缺货，否则为有货
	 */
	void updateGoodsFlag();
	
	/**
	 * 获取物品的名称 库存中存在，物品列表中不存在
	 */
	List<String> getGoodsName();
	
}
