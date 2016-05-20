package com.yz.service;

import java.util.List;

import com.yz.model.Lift;

public interface ILiftService {

	// 添加对象
	public abstract void add(Lift lift) throws Exception;

	// 删除对象
	public abstract void delete(Lift lift);

	// 删除某个id的对象
	public abstract void deleteById(int id);

	// 修改对象
	public abstract void update(Lift lift);

	// 获取所有对象
	public abstract List<Lift> getLifts();

	// 加载一个id的对象
	public abstract Lift loadById(int id);

	// 后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue,
			Lift lift);

	// 后台管理-获取符合条件的记录
	public abstract List<Lift> queryList(int con, String convalue,
			Lift lift, int page, int size);

}