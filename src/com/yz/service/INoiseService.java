package com.yz.service;

import java.util.List;

import com.yz.model.Noise;

public interface INoiseService {

	// 添加对象
	public abstract void add(Noise noise) throws Exception;

	// 删除对象
	public abstract void delete(Noise noise);

	// 删除某个id的对象
	public abstract void deleteById(int id);

	// 修改对象
	public abstract void update(Noise noise);

	// 获取所有对象
	public abstract List<Noise> getNoises();

	// 加载一个id的对象
	public abstract Noise loadById(int id);

	// 后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数

	// 后台管理-获取符合条件的记录
	public abstract List<Noise> queryList(int con, String convalue,
			int projectId, int page, int size);

	public abstract int getTotalCount(int con, String convalue, int areaIndex);

}