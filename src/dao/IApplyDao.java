package dao;

import java.util.List;
import bean.Apply;
import utils.Page;

public interface IApplyDao {
	/**
	 * 根据条件分页显示 申请记录
	 * @param page
	 * @param curpage
	 * @return 
	 */
	List<Apply> getApply(Page page,int curpage);
	
	List<Apply> getApply(Page page,int curpage,String flag);
	
	List<Apply> getApply(Page page,int curpage,String flag,String username);
	/**
	 * 查询申请记录的总记录数量
	 * @return
	 */
	int getApplyCount();
	/**
	 * 查询不同标签下的申请记录数量
	 * @return
	 */
	int getApplyCount(String flag);
	/**
	 * 查询不同标签下所属用户的申请记录数量
	 * @return
	 */
	int getApplyCount(String flag,String username);
	
	/**
	 * 根据id删除一个申请记录
	 * @param id
	 * @return
	 */
	int delApply(String id);
	
	/**
	 * 插入一个申请记录信息
	 * @param repertpory
	 * @return
	 */
	int insertApply(Apply apply);
	
	/**
	 * 根据id查找申请记录
	 * @param id
	 * @return
	 */
	Apply queryApply(String id);
	
	/**
	 * 修改申请记录的信息
	 * @param Apply
	 * @return
	 */
	int updateApply(Apply apply);
	
}
