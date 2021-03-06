package com.examw.netplatform.domain.admin.courses;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.examw.netplatform.domain.admin.settings.Agency;
import com.examw.netplatform.domain.admin.settings.Exam;
import com.examw.netplatform.domain.admin.settings.Subject;
import com.examw.netplatform.domain.admin.students.Order;

/**
 * 课程套餐
 * @author fengwei.
 * @since 2014年5月21日 下午2:19:55.
 */
public class Package implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,name,description,imgUrl,videoUrl;
	private Integer status,orderNo;
	private BigDecimal price,discountPrice,wholesalePrice;
	private Date startTime,endTime,expireTime,createTime,lastTime;
	private Agency agency;
	private Exam exam;
	private Set<Subject> subjects;
	private Set<ClassPlan> classes;
	private Set<Order> orders;
	/**
	 * 获取套餐ID。
	 * @return 套餐ID。
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置套餐ID。
	 * @param id
	 * 套餐ID。
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取套餐名称。
	 * @return name
	 * 套餐名称。
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置套餐名称。
	 * @param name
	 * 套餐名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取所属考试。
	 * @return 所属考试。
	 */
	public Exam getExam() {
		return exam;
	}
	/**
	 * 设置所属考试。
	 * @param exam
	 * 所属考试。
	 */
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	/**
	 * 获取所属机构。
	 * @return 所属机构。
	 */
	public Agency getAgency() {
		return agency;
	}
	/**
	 * 设置所属机构。
	 * @param agency
	 * 所属机构。
	 */
	public void setAgency(Agency agency) {
		this.agency = agency;
	}
	/**
	 * 获取套餐描述。
	 * @return 套餐描述。
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置套餐描述。
	 * @param description
	 * 套餐描述。
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取宣传图片地址。
	 * @return 宣传图片地址。
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * 设置宣传图片地址。
	 * @param imgUrl
	 * 宣传图片地址。
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * 获取试听地址。
	 * @return 试听地址。
	 */
	public String getVideoUrl() {
		return videoUrl;
	}
	/**
	 * 设置试听地址。
	 * @param videoUrl
	 * 试听地址。
	 */
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	/**
	 * 获取套餐状态。
	 * @return 套餐状态。
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置套餐状态。
	 * @param status
	 * 套餐状态。
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取原价。
	 * @return 原价。
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置原价。
	 * @param price
	 * 原价。
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取优惠价格。
	 * @return 优惠价格。
	 */
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}
	/**
	 * 设置优惠价格。
	 * @param discountPrice
	 *  优惠价格。
	 */
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
	/**
	 * 获取批发价。
	 * @return 批发价。
	 */
	public BigDecimal getWholesalePrice() {
		return wholesalePrice;
	}
	/**
	 * 设置批发价。
	 * @param wholesalePrice
	 * 批发价。
	 */
	public void setWholesalePrice(BigDecimal wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}
	/**
	 * 获取招生开始时间。
	 * @return 招生开始时间。
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置招生开始时间。
	 * @param startTime
	 * 招生开始时间。
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取招生结束时间。
	 * @return 招生结束时间。
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置招生结束时间。
	 * @param endTime
	 * 招生结束时间。
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取到期时间。
	 * @return 到期时间。
	 */
	public Date getExpireTime() {
		return expireTime;
	}
	/**
	 * 设置到期时间。
	 * @param expireTime
	 * 到期时间。
	 */
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	/**
	 * 获取创建时间。
	 * @return 创建时间。
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置创建时间。
	 * @param createTime
	 * 创建时间。
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取最后修改时间。
	 * @return 最后修改时间。
	 */
	public Date getLastTime() {
		return lastTime;
	}
	/**
	 * 设置最后修改时间。
	 * @param lastTime
	 * 最后修改时间。
	 */
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	/**
	 * 获取所属科目集合。
	 * @return 所属科目集合。
	 */
	public Set<Subject> getSubjects() {
		return subjects;
	}
	/**
	 * 设置所属科目集合。
	 * @param subjects 
	 *	  所属科目集合。
	 */
	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
	/**
	 * 获取班级集合。
	 * @return 班级集合。
	 */
	public Set<ClassPlan> getClasses() {
		return classes;
	}
	/**
	 * 设置班级集合。
	 * @param classes
	 *  班级集合。
	 */
	public void setClasses(Set<ClassPlan> classes) {
		this.classes = classes;
	}
	/**
	 * 获取关联的订单集合。
	 * @return 关联的订单集合。
	 */
	public Set<Order> getOrders() {
		return orders;
	}
	/**
	 * 设置关联的订单集合。
	 * @param orders 
	 *	  关联的订单集合。
	 */
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	/**
	 * 获取排序号。
	 * @return 排序号。
	 */
	public Integer getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置排序号。
	 * @param orderNo 
	 *	  排序号。
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	/**
	 * 判断是否过期
	 * 2015.01.23
	 * @return
	 */
	public boolean isOverdue()
	{
		//没有结束时间
		if(expireTime == null) return false;
		if(expireTime.compareTo(new Date()) > 0)
		{
			return false;
		}
		return true;
	}
	
}