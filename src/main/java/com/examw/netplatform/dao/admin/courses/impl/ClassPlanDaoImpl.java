package com.examw.netplatform.dao.admin.courses.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.netplatform.dao.admin.courses.IClassPlanDao;
import com.examw.netplatform.dao.impl.BaseDaoImpl;
import com.examw.netplatform.domain.admin.courses.ClassPlan;
import com.examw.netplatform.model.admin.courses.ClassPlanInfo;
import com.examw.netplatform.model.front.FrontClassPlanInfo;
import com.examw.service.Status;
/**
 * 开班计划数据接口实现类
 * @author fengwei.
 * @since 2014年5月20日 下午5:14:59.
 */
public class ClassPlanDaoImpl  extends BaseDaoImpl<ClassPlan> implements IClassPlanDao {
	private static final Logger logger = Logger.getLogger(ClassPlanDaoImpl.class);
	/*
	 * 查询数据
	 * @see com.examw.netplatform.dao.admin.IClassTypeDao#findClassTypes(com.examw.netplatform.model.admin.ClassTypeInfo)
	 */
	@Override
	public List<ClassPlan> findClassPlans(ClassPlanInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据...");
		String hql = "from ClassPlan c where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(StringUtils.isEmpty(info.getOrder())) info.setOrder("asc");
			switch(info.getSort()){
				case "classTypeName":
					info.setSort("classType.name");
					break;
				case "agencyName":
					info.setSort("agency.name");
					break;
				case "examName":
					info.setSort("subject.exam.name");
					break;
				case "subjectName":
					info.setSort("subject.name");
					break;
				case "statusName":
					info.setSort("status");
					break;
			}
			
			hql += " order by c." + info.getSort() + " " + info.getOrder();
		}
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询数据统计。
	 * @see com.examw.netplatform.dao.admin.IClassTypeDao#total(com.examw.netplatform.model.admin.ClassTypeInfo)
	 */
	@Override
	public Long total(ClassPlanInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据统计...");
		String hql = "select count(*) from ClassPlan c where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.count(hql, parameters);
	}
	//添加查询条件到HQL。
	private String addWhere(ClassPlanInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getCategoryId())){
			//2015.02.04修改,查询
			if(!info.getCategoryId().contains(","))
			{
				hql += " and (c.subject.exam.category.id = :categoryId) ";
				parameters.put("categoryId", info.getCategoryId());
			}else{
				hql += " and (c.subject.exam.category.id in (:categoryId)) ";
				parameters.put("categoryId", info.getCategoryId().split(","));
			}
		}
		if(!StringUtils.isEmpty(info.getAgencyId())){
			hql += " and (c.agency.id = :agencyId) ";
			parameters.put("agencyId", info.getAgencyId());
		}
		if(!StringUtils.isEmpty(info.getExamId())){
			hql += " and (c.subject.exam.id = :examId) ";
			parameters.put("examId", info.getExamId());
		}
		if(!StringUtils.isEmpty(info.getSubjectId())){
			hql += " and (c.subject.id = :subjectId) ";
			parameters.put("subjectId", info.getSubjectId());
		}
		if(!StringUtils.isEmpty(info.getClassTypeId())){
			hql += " and (c.classType.id = :classTypeId) ";
			parameters.put("classTypeId", info.getClassTypeId());
		}
		if(!StringUtils.isEmpty(info.getName())){
			hql += " and (c.name = :name) ";
			parameters.put("name", "%"+ info.getName() +"%");
		}
		if(info.getStatus()!=null)
		{
			hql += " and (c.status = :status)";
			parameters.put("status", info.getStatus());
			//2015.02.05前台数据需要启用的数据
			if(info instanceof FrontClassPlanInfo)
			{
				hql += " and (c.subject.exam.status = :examStatus)";
				parameters.put("examStatus", info.getStatus());
			}
		}
		//过期时间
		if(info.getEndTime()!=null)
		{
			hql += " and (c.endTime > :endTime)";
			parameters.put("endTime", info.getEndTime());
		}
		return hql;
	}
	/*
	 * 加载机构下最大排序号。
	 * @see com.examw.netplatform.dao.admin.courses.IClassPlanDao#loadMaxOrder(java.lang.String)
	 */
	@Override
	public Integer loadMaxOrder(String agencyId) {
		if(logger.isDebugEnabled()) logger.debug(String.format("加载机构［%s］下最大排序号...", agencyId));
		final String hql = "select max(c.orderNo) from ClassPlan c  where c.agency.id = :agencyId order by c.orderNo desc ";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("agencyId", agencyId);
		Object obj = this.uniqueResult(hql, parameters);
		return obj == null ? null : (int)obj;
	}
	/*
	 * 重载删除数据。
	 * @see com.examw.netplatform.dao.impl.BaseDaoImpl#delete(java.lang.Object)
	 */
	@Override
	public void delete(ClassPlan data) {
		if(logger.isDebugEnabled()) logger.debug("重载删除数据...");
		if(data == null) return;
		int count = 0;
		if(data.getLessons() != null && (count = data.getLessons().size()) > 0){
			throw new RuntimeException(String.format("班级［%1$s］关联［%2$d］课时资源，暂不能删除！", data.getName(), count));
		}
		if(data.getPackages() != null && (count = data.getPackages().size()) > 0){
			throw new RuntimeException(String.format("班级［%1$s］关联［%2$d］套餐，暂不能删除！", data.getName(), count));
		}
		if(data.getUsers() != null && (count = data.getUsers().size()) > 0){
			throw new RuntimeException(String.format("班级［%1$s］关联［%2$d］用户，暂不能删除！", data.getName(), count));
		}
		if(data.getOrders() != null && (count = data.getOrders().size()) > 0){
			throw new RuntimeException(String.format("班级［%1$s］关联［%2$d］订单，暂不能删除！", data.getName(), count));
		}
		super.delete(data);
	}
	/*
	 * 查询热门班级
	 * @see com.examw.netplatform.dao.admin.courses.IClassPlanDao#findHotClassPlans(com.examw.netplatform.model.admin.courses.ClassPlanInfo)
	 */
	@Override
	public List<ClassPlan> findHotClassPlans(ClassPlanInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询热门班级数据...");
		String hql = "select c from ClassPlan c left join c.orders o where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		hql += "group by c.id order by count(o.id) desc ";
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询启用了的班级
	 * @see com.examw.netplatform.dao.admin.courses.IClassPlanDao#totalEnableClassPlan(com.examw.netplatform.model.admin.courses.ClassPlanInfo)
	 */
	@Override
	public Long totalEnableClassPlan(ClassPlanInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据统计...");
		String hql = "select count(*) from ClassPlan c where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		//查询考试启用
		hql += " and (c.subject.exam.status = :examstatus) ";
		parameters.put("examstatus",Status.ENABLED.getValue());
		//班级启用
		info.setStatus(Status.ENABLED.getValue());
		hql = this.addWhere(info, hql, parameters);
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.count(hql, parameters);
	}
	
}