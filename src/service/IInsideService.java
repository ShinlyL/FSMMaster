package service;

import java.util.List;

import bean.Inside;
import utils.Page;

public interface IInsideService {
	
	List<Inside> getInside(Page page,int curpage);
	
	int getInsideCount();
	
	int delInside(String id);
	
	int insertInside(Inside inside);
	
	Inside queryInside(String id);
	
	Inside queryInsideBybarcode(String barcode);

	int updateInside(Inside inside);
	
	/**
	 * 获取物品列表的分页内容
	 * @param page
	 * @param curPage
	 * @return
	 */
	Page<Inside> getInsidePage(Page<Inside> page,Integer curPage);
	/**
	 * 根据物品入库状态的不同 获取物品列表的分页内容
	 * @param page
	 * @param curPage
	 * @return
	 */
	Page<Inside> getInsidePage(Page<Inside> page,Integer curPage,String status);
}
