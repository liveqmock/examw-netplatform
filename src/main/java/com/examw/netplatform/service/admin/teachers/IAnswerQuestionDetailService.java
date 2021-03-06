package com.examw.netplatform.service.admin.teachers;

import com.examw.netplatform.domain.admin.teachers.AnswerQuestionDetail;
import com.examw.netplatform.model.admin.teachers.AnswerQuestionDetailInfo;
import com.examw.netplatform.service.IBaseDataService;

/**
 * 教师答疑明细服务接口。
 * 
 * @author yangyong
 * @since 2014年11月20日
 */
public interface IAnswerQuestionDetailService extends IBaseDataService<AnswerQuestionDetailInfo> {
	/**
	 * 数据模型转换
	 * @param data
	 * @return
	 */
	AnswerQuestionDetailInfo conversion(AnswerQuestionDetail data);
}