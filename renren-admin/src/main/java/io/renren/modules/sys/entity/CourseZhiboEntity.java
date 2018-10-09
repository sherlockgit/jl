package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-05 22:02:06
 */
@TableName("course_zhibo")
public class CourseZhiboEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 课程编号
	 */
	private String courseNo;
	/**
	 * 课程封面图
	 */
	private String coursePic;
	/**
	 * 课程名称
	 */
	private String courseName;
	/**
	 * 课程老师
	 */
	private String courseTeacher;
	/**
	 * 课程总价格
	 */
	private BigDecimal coursePrice;
	/**
	 * 直播时长(min)
	 */
	private String courseMinute;
	/**
	 * 直播状态[0-新建, 1-预告, 2-正在直播，3-直播结束]
	 */
	private String courseStatus;
	/**
	 * 播放总次数
	 */
	private String coursePalys;
	/**
	 * 课程简介
	 */
	private String courseBrief;
	/**
	 * 课程详情
	 */
	private String courseContent;
	/**
	 * 直播文件URL-录制的
	 */
	private String courseFile;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 发布时间
	 */
	private Date publishTime;
	/**
	 * 下线时间
	 */
	private Date downTime;

	/**
	 * 预约人数
	 */
	private Integer courseCount;

	/**
	 * 电话
	 */
	private String coursePhone;

	public String getCoursePhone() {
		return coursePhone;
	}

	public void setCoursePhone(String coursePhone) {
		this.coursePhone = coursePhone;
	}

	public Integer getCourseCount() {
		return courseCount;
	}

	public void setCourseCount(Integer courseCount) {
		this.courseCount = courseCount;
	}

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：课程编号
	 */
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	/**
	 * 获取：课程编号
	 */
	public String getCourseNo() {
		return courseNo;
	}
	/**
	 * 设置：课程封面图
	 */
	public void setCoursePic(String coursePic) {
		this.coursePic = coursePic;
	}
	/**
	 * 获取：课程封面图
	 */
	public String getCoursePic() {
		return coursePic;
	}
	/**
	 * 设置：课程名称
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * 获取：课程名称
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * 设置：课程老师
	 */
	public void setCourseTeacher(String courseTeacher) {
		this.courseTeacher = courseTeacher;
	}
	/**
	 * 获取：课程老师
	 */
	public String getCourseTeacher() {
		return courseTeacher;
	}
	/**
	 * 设置：课程总价格
	 */
	public void setCoursePrice(BigDecimal coursePrice) {
		this.coursePrice = coursePrice;
	}
	/**
	 * 获取：课程总价格
	 */
	public BigDecimal getCoursePrice() {
		return coursePrice;
	}
	/**
	 * 设置：直播时长(min)
	 */
	public void setCourseMinute(String courseMinute) {
		this.courseMinute = courseMinute;
	}
	/**
	 * 获取：直播时长(min)
	 */
	public String getCourseMinute() {
		return courseMinute;
	}
	/**
	 * 设置：直播状态[0-新建, 1-预告, 2-正在直播，3-直播结束]
	 */
	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}
	/**
	 * 获取：直播状态[0-新建, 1-预告, 2-正在直播，3-直播结束]
	 */
	public String getCourseStatus() {
		return courseStatus;
	}
	/**
	 * 设置：播放总次数
	 */
	public void setCoursePalys(String coursePalys) {
		this.coursePalys = coursePalys;
	}
	/**
	 * 获取：播放总次数
	 */
	public String getCoursePalys() {
		return coursePalys;
	}
	/**
	 * 设置：课程简介
	 */
	public void setCourseBrief(String courseBrief) {
		this.courseBrief = courseBrief;
	}
	/**
	 * 获取：课程简介
	 */
	public String getCourseBrief() {
		return courseBrief;
	}
	/**
	 * 设置：课程详情
	 */
	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}
	/**
	 * 获取：课程详情
	 */
	public String getCourseContent() {
		return courseContent;
	}
	/**
	 * 设置：直播文件URL-录制的
	 */
	public void setCourseFile(String courseFile) {
		this.courseFile = courseFile;
	}
	/**
	 * 获取：直播文件URL-录制的
	 */
	public String getCourseFile() {
		return courseFile;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：发布时间
	 */
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	/**
	 * 获取：发布时间
	 */
	public Date getPublishTime() {
		return publishTime;
	}
	/**
	 * 设置：下线时间
	 */
	public void setDownTime(Date downTime) {
		this.downTime = downTime;
	}
	/**
	 * 获取：下线时间
	 */
	public Date getDownTime() {
		return downTime;
	}
}
