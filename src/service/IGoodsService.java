package service;

import java.util.List;

import bean.Goods;
import utils.Page;

public interface IGoodsService {
	
	List<Goods> getGoods(Page page,int curpage);
	
	int getGoodsCount();
	
	int delGoods(String id);
	
	int insertGoods(Goods goods);
	
	Goods queryGoods(String id);
	
	Goods queryGoodsBybarcode(String barcode);

	int updateGoods(Goods goods);
	
	/**
	 * 获取物品列表的分页内容
	 * @param page
	 * @param curPage
	 * @return
	 */
	Page<Goods> getGoodsPage(Page<Goods> page,Integer curPage);
	
	List<String> getGoodsName();
}
