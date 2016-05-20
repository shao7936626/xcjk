package com.yz.service;

import java.util.List;

import com.yz.model.Crane;

public interface ICraneService {

	// 添加对象
	public abstract void add(Crane crane) throws Exception;

	// 删除对象
	public abstract void delete(Crane crane);

	// 删除某个id的对象
	public abstract void deleteById(int id);

	// 修改对象
	public abstract void update(Crane crane);

	// 获取所有对象
	public abstract List<Crane> getCranes();

	// 加载一个id的对象
	public abstract Crane loadById(int id);

	// 后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, Crane crane);

	// 后台管理-获取符合条件的记录
	public abstract List<Crane> queryList(int con, String convalue,
			Crane crane, int page, int size);


}