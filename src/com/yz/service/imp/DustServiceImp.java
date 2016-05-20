package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IDustDao;
import com.yz.model.Dust;
import com.yz.service.IDustService;
@Component("dustService")
public class DustServiceImp implements IDustService {
	private IDustDao dustDao;
	public IDustDao getDustDao() {
		return dustDao;
	}
	@Resource
	public void setDustDao(IDustDao dustDao) {
		this.dustDao = dustDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDustServiceImp#add(com.yz.model.Dust)
	 */
	public void add(Dust dust) throws Exception {
		dustDao.save(dust);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDustServiceImp#delete(com.yz.model.Dust)
	 */
	public void delete(Dust dust) {
		dustDao.delete(dust);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDustServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		dustDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDustServiceImp#update(com.yz.model.Dust)
	 */
	public void update(Dust dust) {
		dustDao.update(dust);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDustServiceImp#getDusts()
	 */
	public List<Dust> getDusts() {
		return dustDao.getDusts();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDustServiceImp#loadById(int)
	 */
	public Dust loadById(int id) {
		return dustDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDustServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDustServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, Dust dust) {
		String queryString = "select count(*) from Dust mo where 1=1 and mo.id!="+dust.getId();
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
		return dustDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDustServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<Dust> queryList(int con, String convalue, Dust dust, int page, int size) {
		String queryString = "from Dust mo where 1=1 and mo.id!="+dust.getId();
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
		return dustDao.pageList(queryString,p,page,size);
	}
	
}
