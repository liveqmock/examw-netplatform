package com.examw.netplatform.dao.admin.courses.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.netplatform.dao.admin.courses.IPackageDao;
import com.examw.netplatform.dao.impl.BaseDaoImpl;
import com.examw.netplatform.domain.admin.courses.Package;
import com.examw.netplatform.model.admin.courses.PackageInfo;
import com.examw.netplatform.model.front.FrontPackageInfo;
import com.examw.service.Status;

/**
 * 套餐数据接口实现类
 * 
 * @author fengwei.
 * @since 2014年5月21日 下午3:08:21.
 */
public class PackageDaoImpl extends BaseDaoImpl<Package> implements IPackageDao {
	private static final Logger logger = Logger.getLogger(PackageDaoImpl.class);

	/*
	 * 查询数据。
	 * 
	 * @see
	 * com.examw.netplatform.dao.admin.courses.IPackageDao#findPackages(com.
	 * examw.netplatform.model.admin.courses.PackageInfo)
	 */
	@Override
	public List<Package> findPackages(PackageInfo info) {
		if (logger.isDebugEnabled())
			logger.debug("查询数据...");
		String hql = "from Package p where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if (!StringUtils.isEmpty(info.getSort())) {
			if (StringUtils.isEmpty(info.getOrder()))
				info.setOrder("asc");
			switch (info.getSort()) {
			case "agencyName":
				info.setSort("agency.name");
				break;
			case "examName":
				info.setSort("exam.name");
				break;
			case "statusName":
				info.setSort("status");
				break;
			}
			hql += " order by p." + info.getSort() + " " + info.getOrder();
		}
		if (logger.isDebugEnabled())
			logger.debug(hql);
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}

	/*
	 * 查询数据统计。
	 * 
	 * @see com.examw.netplatform.dao.admin.courses.IPackageDao#total(com.examw.
	 * netplatform.model.admin.courses.PackageInfo)
	 */
	@Override
	public Long total(PackageInfo info) {
		String hql = "select count(*) from Package p where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.count(hql, parameters);
	}

	// 查询条件。
	private String addWhere(PackageInfo info, String hql, Map<String, Object> parameters) {
		if (!StringUtils.isEmpty(info.getCategoryId())) {
			// 2015.02.04修改,查询不完全
			if (!info.getCategoryId().contains(",")) {
				hql += " and (p.exam.category.id = :categoryId) ";
				parameters.put("categoryId", info.getCategoryId());
			} else {
				hql += " and (p.exam.category.id in (:categoryId)) ";
				parameters.put("categoryId", info.getCategoryId().split(","));
			}
		}
		if (!StringUtils.isEmpty(info.getAgencyId())) {
			hql += " and (p.agency.id = :agencyId) ";
			parameters.put("agencyId", info.getAgencyId());
		}
		if (!StringUtils.isEmpty(info.getName())) {
			hql += " and (p.name like :name)";
			parameters.put("name", "%" + info.getName() + "%");
		}
		if (!StringUtils.isEmpty(info.getExamId())) {
			hql += " and (p.exam.id = :examId) ";
			parameters.put("examId", info.getExamId());
		}
		if (info.getStatus() != null) {
			hql += " and (p.status = :status)";
			parameters.put("status", info.getStatus());
			//2015.02.05前台数据需要启用的数据
			if(info instanceof FrontPackageInfo)
			{
				hql += " and (p.exam.status = :examStatus)";
				parameters.put("examStatus", info.getStatus());
			}
		}
		//过期时间
		if(info.getExpireTime()!=null)
		{
			hql += " and (p.expireTime > :expireTime)";
			parameters.put("expireTime", info.getExpireTime());
		}
		return hql;
	}

	/*
	 * 加载机构下最大排序号。
	 * 
	 * @see
	 * com.examw.netplatform.dao.admin.courses.IPackageDao#loadMaxOrder(java
	 * .lang.String)
	 */
	@Override
	public Integer loadMaxOrder(String agencyId) {
		if (logger.isDebugEnabled())
			logger.debug(String.format("加载机构［%s］下最大排序号...", agencyId));
		final String hql = "select max(p.orderNo) from Package p where p.agency.id = :agencyId order by p.orderNo desc ";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("agencyId", agencyId);
		Object obj = this.uniqueResult(hql, parameters);
		return obj == null ? null : (int) obj;
	}

	/*
	 * 重载删除。
	 * 
	 * @see com.examw.netplatform.dao.impl.BaseDaoImpl#delete(java.lang.Object)
	 */
	@Override
	public void delete(Package data) {
		if (logger.isDebugEnabled())
			logger.debug("重载删除....");
		if (data == null)
			return;
		int count = 0;
		if (data.getOrders() != null && (count = data.getOrders().size()) > 0) {
			throw new RuntimeException(String.format("套餐［%1$s］关联［%2$d］订单，暂不能删除！", data.getName(), count));
		}
		super.delete(data);
	}

	/*
	 * 查询热门套餐
	 * 
	 * @see
	 * com.examw.netplatform.dao.admin.courses.IPackageDao#findHotPackages(com
	 * .examw.netplatform.model.admin.courses.PackageInfo)
	 */
	@Override
	public List<Package> findHotPackages(PackageInfo info) {
		if (logger.isDebugEnabled())
			logger.debug("查询热门班级数据...");
		String hql = "select p from Package p join p.orders o where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		hql += "group by p.id order by count(o.id) desc ";
		if (logger.isDebugEnabled())
			logger.debug(hql);
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	public Long totalEnablePackage(PackageInfo info) {
		String hql = "select count(*) from Package p where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		// 考试启用
		hql += " and (p.exam.status = :examstatus) ";
		parameters.put("examstatus", Status.ENABLED.getValue());
		// 套餐启用
		info.setStatus(Status.ENABLED.getValue());
		hql = this.addWhere(info, hql, parameters);
		return this.count(hql, parameters);
	}
}