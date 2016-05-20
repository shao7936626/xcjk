package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.ILiftDao;
import com.yz.model.Lift;
import com.yz.service.ILiftService;

@Component("liftService")
public class LiftServiceImp implements ILiftService {
	private ILiftDao liftDao;

	public ILiftDao getLiftDao() {
		return liftDao;
	}

	@Resource
	public void setLiftDao(ILiftDao liftDao) {
		this.liftDao = liftDao;
	}

	// 添加对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.ILiftServiceImp#add(com.yz.model.Lift)
	 */
	public void add(Lift lift) throws Exception {
		liftDao.save(lift);
	}

	// 删除对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.ILiftServiceImp#delete(com.yz.model.Lift)
	 */
	public void delete(Lift lift) {
		liftDao.delete(lift);
	}

	// 删除某个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.ILiftServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		liftDao.deleteById(id);
	}

	// 修改对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.ILiftServiceImp#update(com.yz.model.Lift)
	 */
	public void update(Lift lift) {
		liftDao.update(lift);
	}

	// 获取所有对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.ILiftServiceImp#getLifts()
	 */
	public List<Lift> getLifts() {
		return liftDao.getLifts();
	}

	// 加载一个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.ILiftServiceImp#loadById(int)
	 */
	public Lift loadById(int id) {
		return liftDao.loadById(id);
	}

	// 后台管理-页数获取
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.ILiftServiceImp#getPageCount(int,
	 *      java.lang.String, int)
	 */
	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size
				: (totalCount / size + 1);
	}

	// 后台管理-获取总记录数
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.ILiftServiceImp#getTotalCount(int,
	 *      java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, Lift lift) {
		String queryString = "select count(*) from Lift mo where 1=1 and mo.id!="
				+ lift.getId();
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.unit.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.realname like ? ";

			}
			if (con == 3) {
				queryString += "and mo.number like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		return liftDao.getUniqueResult(queryString, p);
	}

	// 后台管理-获取符合条件的记录
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.ILiftServiceImp#queryList(int,
	 *      java.lang.String, int, int)
	 */
	public List<Lift> queryList(int con, String convalue,
			Lift lift, int page, int size) {
		String queryString = "from Lift mo where 1=1 and mo.id!="
				+ lift.getId();
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.unit.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.realname like ? ";

			}
			if (con == 3) {
				queryString += "and mo.number like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		return liftDao.pageList(queryString, p, page, size);
	}

}
