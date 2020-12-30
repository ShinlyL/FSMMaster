package impl;

import java.util.List;

import bean.Goods;
import service.IGoodsService;
import utils.Page;

public class GoodsServiceImp implements IGoodsService{
	GoodsDaoImp goodsDaoImp = new GoodsDaoImp();
	@Override
	public List<Goods> getGoods(Page page, int curpage) {
		return goodsDaoImp.getGoods(page, curpage);
	}

	@Override
	public int getGoodsCount() {
		return goodsDaoImp.getGoodsCount();
	}

	@Override
	public int delGoods(String id) {
		return goodsDaoImp.delGoods(id);
	}

	@Override
	public int insertGoods(Goods goods) {
		return goodsDaoImp.insertGoods(goods);
	}

	@Override
	public Goods queryGoods(String id) {
		return goodsDaoImp.queryGoods(id);
	}

	@Override
	public int updateGoods(Goods goods) {
		return goodsDaoImp.updateGoods(goods);
	}

	@Override
	public Page<Goods> getGoodsPage(Page<Goods> page, Integer curPage) {
		//查询当前页的列表数据
		List<Goods> data = goodsDaoImp.getGoods(page,curPage);
		//查询总记录数
		int rowsCount = goodsDaoImp.getGoodsCount();
		//将分页信息封装到page对象中
		page.setParam(data, curPage, rowsCount);
		return page;
	}

	@Override
	public Goods queryGoodsBybarcode(String barcode) {
		return goodsDaoImp.queryGoodsBybarcode(barcode);
	}

	@Override
	public List<String> getGoodsName() {
		return goodsDaoImp.getGoodsName();
	}

}
