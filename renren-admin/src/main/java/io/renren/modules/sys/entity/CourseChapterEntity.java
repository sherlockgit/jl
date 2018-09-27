package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-27 11:35:10
 */
@TableName("course_chapter")
public class CourseChapterEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 课程PID
	 */
	private Integer courseId;
	/**
	 * 课程编号
	 */
	private Integer courseNo;
	/**
	 * 章节编号
	 */
	private Integer chapterNo;
	/**
	 * 章节名称
	 */
	private String chapterName;
	/**
	 * 章节价格
	 */
	private BigDecimal chapterPrice;
	/**
	 * 课程类型[0-直播， 1-录播， 2-问答]
	 */
	private String chapterType;
	/**
	 * 章节排序
	 */
	private String chapterSort;
	/**
	 * 章节状态[0-上线, 1-下线， 2-新建]
	 */
	private String chapterStatus;
	/**
	 * 章节老师
	 */
	private String chapterTeacher;
	/**
	 * 章节文件[视频/音频文件]
	 */
	private String chapterFile;
	/**
	 * 创建时间
	 */
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
	 * 设置：课程PID
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	/**
	 * 获取：课程PID
	 */
	public Integer getCourseId() {
		return courseId;
	}
	/**
	 * 设置：课程编号
	 */
	public void setCourseNo(Integer courseNo) {
		this.courseNo = courseNo;
	}
	/**
	 * 获取：课程编号
	 */
	public Integer getCourseNo() {
		return courseNo;
	}
	/**
	 * 设置：章节编号
	 */
	public void setChapterNo(Integer chapterNo) {
		this.chapterNo = chapterNo;
	}
	/**
	 * 获取：章节编号
	 */
	public Integer getChapterNo() {
		return chapterNo;
	}
	/**
	 * 设置：章节名称
	 */
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	/**
	 * 获取：章节名称
	 */
	public String getChapterName() {
		return chapterName;
	}
	/**
	 * 设置：章节价格
	 */
	public void setChapterPrice(BigDecimal chapterPrice) {
		this.chapterPrice = chapterPrice;
	}
	/**
	 * 获取：章节价格
	 */
	public BigDecimal getChapterPrice() {
		return chapterPrice;
	}
	/**
	 * 设置：课程类型[0-直播， 1-录播， 2-问答]
	 */
	public void setChapterType(String chapterType) {
		this.chapterType = chapterType;
	}
	/**
	 * 获取：课程类型[0-直播， 1-录播， 2-问答]
	 */
	public String getChapterType() {
		return chapterType;
	}
	/**
	 * 设置：章节排序
	 */
	public void setChapterSort(String chapterSort) {
		this.chapterSort = chapterSort;
	}
	/**
	 * 获取：章节排序
	 */
	public String getChapterSort() {
		return chapterSort;
	}
	/**
	 * 设置：章节状态[0-上线, 1-下线， 2-新建]
	 */
	public void setChapterStatus(String chapterStatus) {
		this.chapterStatus = chapterStatus;
	}
	/**
	 * 获取：章节状态[0-上线, 1-下线， 2-新建]
	 */
	public String getChapterStatus() {
		return chapterStatus;
	}
	/**
	 * 设置：章节老师
	 */
	public void setChapterTeacher(String chapterTeacher) {
		this.chapterTeacher = chapterTeacher;
	}
	/**
	 * 获取：章节老师
	 */
	public String getChapterTeacher() {
		return chapterTeacher;
	}
	/**
	 * 设置：章节文件[视频/音频文件]
	 */
	public void setChapterFile(String chapterFile) {
		this.chapterFile = chapterFile;
	}
	/**
	 * 获取：章节文件[视频/音频文件]
	 */
	public String getChapterFile() {
		return chapterFile;
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
