package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.INoiseDao;
import com.yz.model.Noise;
import com.yz.service.INoiseService;
@Component("noiseService")
public class NoiseServiceImp implements INoiseService {
	private INoiseDao noiseDao;
	public INoiseDao getNoiseDao() {
		return noiseDao;
	}
	@Resource
	public void setNoiseDao(INoiseDao noiseDao) {
		this.noiseDao = noiseDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.INoiseServiceImp#add(com.yz.model.Noise)
	 */
	public void add(Noise noise) throws Exception {
		noiseDao.save(noise);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.INoiseServiceImp#delete(com.yz.model.Noise)
	 */
	public void delete(Noise noise) {
		noiseDao.delete(noise);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.INoiseServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		noiseDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.INoiseServiceImp#update(com.yz.model.Noise)
	 */
	public void update(Noise noise) {
		noiseDao.update(noise);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.INoiseServiceImp#getNoises()
	 */
	public List<Noise> getNoises() {
		return noiseDao.getNoises();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.INoiseServiceImp#loadById(int)
	 */
	public Noise loadById(int id) {
		return noiseDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.INoiseServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.INoiseServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, Noise noise) {
		String queryString = "select count(*) from Noise mo where 1=1 and mo.id!="+noise.getId();
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.propertyCardNumber like ? "; 
				
			}
			if(con==3){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return noiseDao.getUniqueResult(queryString,p);
	}
	
	public int getTotalCount(int con, String convalue,
			int projectId) {
		String queryString = "select count(*) from Noise mo where 1=1 and mo.project.id="+projectId;
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.propertyCardNumber like ? "; 
				
			}
			if(con==3){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return noiseDao.getUniqueResult(queryString,p);
	} 
	
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.INoiseServiceImp#queryList(int, java.lang.String, int, int)
	 */
//	public List<Noise> queryList(int con, String convalue, Noise noise, int page, int size) {
//		String queryString = "from Noise mo where 1=1 and mo.id!="+noise.getId();
//		Object[] p = null;
//		if(con!=0&&convalue!=null&&!convalue.equals("")){
//			if(con==1){
//				queryString += "and mo.unit.name like ? "; 
//			}
//			if(con==2){
//				queryString += "and mo.realname like ? "; 
//				
//			}
//			if(con==3){
//				queryString += "and mo.number like ? "; 
//			}
//			p = new Object[]{'%'+convalue+'%'};
//		}
//		return noiseDao.pageList(queryString,p,page,size);
//	}
	public List<Noise> queryList(int con, String convalue, int projectId,
			int page, int size) {
		String queryString = "from Noise mo where 1=1 and mo.projectId="+projectId;
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.propertyCardNumber like ? "; 
				
			}
			if(con==3){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return noiseDao.pageList(queryString,p,page,size);
	}
	
	
}
