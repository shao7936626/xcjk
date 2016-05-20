package com.yz.service;

import java.util.List;

import com.yz.model.Dust;

public interface IDustService {

	// 添加对象
	public abstract void add(Dust dust) throws Exception;

	// 删除对象
	public abstract void delete(Dust dust);

	// 删除某个id的对象
	public abstract void deleteById(int id);

	// 修改对象
	public abstract void update(Dust dust);

	// 获取所有对象
	public abstract List<Dust> getDusts();

	// 加载一个id的对象
	public abstract Dust loadById(int id);

	// 后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue,
			Dust dust);

	// 后台管理-获取符合条件的记录
	public abstract List<Dust> queryList(int con, String convalue,
			Dust dust, int page, int size);

}