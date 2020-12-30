package impl;

import java.util.List;

import bean.Apply;
import service.IApplyService;
import utils.Page;

public class ApplyServiceImp implements IApplyService{
	ApplyDaoImp applyDaoImp = new ApplyDaoImp();
	@Override
	public List<Apply> getApply(Page page, int curpage) {
		return applyDaoImp.getApply(page, curpage);
	}

	@Override
	public int getApplyCount() {
		return applyDaoImp.getApplyCount();
	}

	@Override
	public int delApply(String id) {
		return applyDaoImp.delApply(id);
	}

	@Override
	public int insertApply(Apply apply) {
		return applyDaoImp.insertApply(apply);
	}

	@Override
	public Apply queryApply(String id) {
		return applyDaoImp.queryApply(id);
	}

	@Override
	public int updateApply(Apply apply) {
		return applyDaoImp.updateApply(apply);
	}

	@Override
	public Page<Apply> getApplyPage(Page<Apply> page, Integer curPage) {
		//查询当前页的列表数据
		List<Apply> data = applyDaoImp.getApply(page,curPage);
		//查询总记录数
		int rowsCount = applyDaoImp.getApplyCount();
		//将分页信息封装到page对象中
		page.setParam(data, curPage, rowsCount);
		return page;
	}

	@Override
	public Page<Apply> getApplyPage(Page<Apply> page, Integer curPage, String flag) {
		//查询当前页的列表数据
		List<Apply> data = applyDaoImp.getApply(page,curPage,flag);
		//查询相应条件的记录数
		int rowsCount = applyDaoImp.getApplyCount(flag);
		//将分页信息封装到page对象中
		page.setParam(data, curPage, rowsCount);
		return page;
	}

	@Override
	public Page<Apply> getApplyPage(Page<Apply> page, Integer curPage, String flag, String username) {
		//查询当前页的列表数据
		List<Apply> data = applyDaoImp.getApply(page,curPage,flag,username);
		int rowsCount = applyDaoImp.getApplyCount(flag,username);
		//将分页信息封装到page对象中
		page.setParam(data, curPage, rowsCount);
		return page;
	}
}
