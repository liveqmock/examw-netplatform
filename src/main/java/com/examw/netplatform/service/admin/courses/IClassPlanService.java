package com.examw.netplatform.service.admin.courses;

import java.util.List;

import com.examw.netplatform.domain.admin.courses.ClassPlan;
import com.examw.netplatform.model.admin.courses.ClassPlanInfo;
import com.examw.netplatform.service.IBaseDataService;
/**
 * 开班计划服务接口。
 * @author fengwei
 * 2014年5月20日 下午9:10:40
 */
public interface IClassPlanService extends IBaseDataService<ClassPlanInfo>{
	/**
	 * 加载讲义模式值名称。
	 * @param handoutMode
	 * 讲义模式。
	 * @return
	 * 讲义模式名称。
	 */
	String loadHandoutModeName(Integer handoutMode);
	/**
	 * 加载试听模式值名称。
	 * @param videoMode
	 * 试听模式。
	 * @return
	 * 试听模式名称。
	 */
	String loadVideoModeName(Integer videoMode);
	/**
	 * 加载状态值名称。
	 * @param status
	 * 班级状态值。
	 * @return
	 * 状态名称。
	 */
	String loadStatusName(Integer status);
	/**
	 * 加载培训机构下最大排序号。
	 * @param agencyId
	 * 培训机构ID。
	 * @return
	 */
	Integer loadMaxOrder(String agencyId);
	/**
	 * 加载机构下班级集合。
	 * @param agencyId
	 * 机构ID。
	 * @return
	 * 班级集合。
	 */
	List<ClassPlanInfo> loadClasses(String agencyId);
	/**
	 * 加载班级数据。
	 * @param classId
	 * 班级ID。
	 * @return
	 */
	ClassPlan loadClassPlan(String classId);
	/**
	 * 数据模型转换。
	 * @param classPlan
	 * 班级数据。
	 * @return
	 * 班级信息。
	 */
	ClassPlanInfo conversion(ClassPlan classPlan);
}