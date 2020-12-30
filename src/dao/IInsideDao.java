package dao;

import java.util.List;
import bean.Inside;
import utils.Page;

public interface IInsideDao {
	/**
	 * 根据条件分页显示 入库的物品
	 * @param page
	 * @param curpage
	 * @return 
	 */
	List<Inside> getInside(Page page,int curpage);

	List<Inside> getInside(Page page,int curpage,String status);
	/**
	 * 查询货物的总记录数量
	 * @return
	 */
	int getInsideCount();
	/**
	 * 查询不同入库状态下货物的记录数量
	 * @return
	 */
	int getInsideCount(String status);
	
	/**
	 * 根据id删除一个入库记录
	 * @param id
	 * @return
	 */
	int delInside(String id);
	
	/**
	 * 插入一个入库信息
	 * @param Inside
	 * @return
	 */
	int insertInside(Inside inside);
	
	/**
	 * 根据id查找入库记录
	 * @param id
	 * @return
	 */
	Inside queryInside(String id);
	
	/**
	 * 根据barcode查找入库记录
	 * @param id
	 * @return
	 */
	Inside queryInsideBybarcode(String barcode);
	
	/**
	 * 修改货物的信息
	 * @param Inside
	 * @return
	 */
	int updateInside(Inside inside);
	
}
