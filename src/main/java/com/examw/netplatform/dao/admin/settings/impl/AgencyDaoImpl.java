package com.examw.netplatform.dao.admin.settings.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.netplatform.dao.admin.settings.IAgencyDao;
import com.examw.netplatform.dao.impl.BaseDaoImpl;
import com.examw.netplatform.domain.admin.settings.Agency;
import com.examw.netplatform.model.admin.settings.AgencyInfo;

/**
 * 培训机构数据接口实现。
 * @author yangyong.
 * @since 2014-04-29.
 */
public class AgencyDaoImpl extends BaseDaoImpl<Agency> implements IAgencyDao {
	private static final Logger logger = Logger.getLogger(AgencyDaoImpl.class);
	/*
	 * 查询数据。
	 * @see com.examw.netplatform.dao.admin.IAgencyDao#findAgencies(com.examw.netplatform.model.admin.AgencyInfo)
	 */
	@Override
	public List<Agency> findAgencies(AgencyInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据...");
		String hql = "from Agency a where 1=1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			hql += " order by a." + info.getSort() + " " + info.getOrder();
		}
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 *  查询数据统计。
	 * @see com.examw.netplatform.dao.admin.IAgencyDao#total(com.examw.netplatform.model.admin.AgencyInfo)
	 */
	@Override
	public Long total(AgencyInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据统计...");
		String hql = "select count(*) from Agency a where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.count(hql, parameters);
	}
	//添加查询条件到HQL。
	private String addWhere(AgencyInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getName())){
			hql += "  and ((a.name like :name) or (a.abbr_cn like :name) or (a.abbr_en like :name))";
			parameters.put("name", "%" + info.getName()+ "%");
		}
		return hql;
	}
}