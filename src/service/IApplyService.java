package service;

import java.util.List;

import bean.Apply;
import utils.Page;

public interface IApplyService {
	
	List<Apply> getApply(Page page,int curpage);
	
	int getApplyCount();
	
	int delApply(String id);
	
	int insertApply(Apply apply);
	
	Apply queryApply(String id);

	int updateApply(Apply apply);
	
	/**
	 * 获取申领记录列表的分页内容
	 * @param page
	 * @param curPage
	 * @return
	 */
	Page<Apply> getApplyPage(Page<Apply> page,Integer curPage);
	
	/**
	 * 获取申领记录列表的分页内容
	 * @param page
	 * @param curPage
	 * @param flag
	 * @return
	 */
	Page<Apply> getApplyPage(Page<Apply> page,Integer curPage,String flag);
	
	/**
	 * 获取此用户 的申领记录列表的分页内容
	 * @param page
	 * @param curPage
	 * @param flag
	 * @param username
	 * @return
	 */
	Page<Apply> getApplyPage(Page<Apply> page,Integer curPage,String flag,String username);
}
