package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.ICraneDao;
import com.yz.model.Crane;
import com.yz.service.ICraneService;
@Component("craneService")
public class CraneServiceImp implements ICraneService {
	private ICraneDao craneDao;
	public ICraneDao getCraneDao() {
		return craneDao;
	}
	@Resource
	public void setCraneDao(ICraneDao craneDao) {
		this.craneDao = craneDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ICraneServiceImp#add(com.yz.model.Crane)
	 */
	public void add(Crane crane) throws Exception {
		craneDao.save(crane);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ICraneServiceImp#delete(com.yz.model.Crane)
	 */
	public void delete(Crane crane) {
		craneDao.delete(crane);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ICraneServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		craneDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ICraneServiceImp#update(com.yz.model.Crane)
	 */
	public void update(Crane crane) {
		craneDao.update(crane);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ICraneServiceImp#getCranes()
	 */
	public List<Crane> getCranes() {
		return craneDao.getCranes();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ICraneServiceImp#loadById(int)
	 */
	public Crane loadById(int id) {
		return craneDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ICraneServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ICraneServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, Crane crane) {
		String queryString = "select count(*) from Crane mo where 1=1 and mo.id!="+crane.getId();
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.unit.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.realname like ? "; 
				
			}
			if(con==3){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return craneDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ICraneServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<Crane> queryList(int con, String convalue, Crane crane, int page, int size) {
		String queryString = "from Crane mo where 1=1 and mo.id!="+crane.getId();
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.unit.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.realname like ? "; 
				
			}
			if(con==3){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return craneDao.pageList(queryString,p,page,size);
	}
	
}
