package impl;

import java.util.List;

import bean.Inside;
import service.IInsideService;
import utils.Page;

public class InsideServiceImp implements IInsideService{
	InsideDaoImp insideDaoImp = new InsideDaoImp();
	@Override
	public List<Inside> getInside(Page page, int curpage) {
		return insideDaoImp.getInside(page, curpage);
	}

	@Override
	public int getInsideCount() {
		return insideDaoImp.getInsideCount();
	}

	@Override
	public int delInside(String id) {
		return insideDaoImp.delInside(id);
	}

	@Override
	public int insertInside(Inside inside) {
		return insideDaoImp.insertInside(inside);
	}

	@Override
	public Inside queryInside(String id) {
		return insideDaoImp.queryInside(id);
	}

	@Override
	public int updateInside(Inside inside) {
		return insideDaoImp.updateInside(inside);
	}

	@Override
	public Page<Inside> getInsidePage(Page<Inside> page, Integer curPage) {
		//查询当前页的列表数据
		List<Inside> data = insideDaoImp.getInside(page,curPage);
		//查询总记录数
		int rowsCount = insideDaoImp.getInsideCount();
		//将分页信息封装到page对象中
		page.setParam(data, curPage, rowsCount);
		return page;
	}

	@Override
	public Inside queryInsideBybarcode(String barcode) {
		return insideDaoImp.queryInsideBybarcode(barcode);
	}

	@Override
	public Page<Inside> getInsidePage(Page<Inside> page, Integer curPage, String status) {
		//查询当前页的列表数据
		List<Inside> data = insideDaoImp.getInside(page,curPage,status);
		int rowsCount = insideDaoImp.getInsideCount(status);
		//将分页信息封装到page对象中
		page.setParam(data, curPage, rowsCount);
		return page;
	}

}
