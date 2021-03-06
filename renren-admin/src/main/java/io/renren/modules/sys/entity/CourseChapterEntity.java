package io.renren.modules.sys.entity;

import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-04 14:56:19
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
	@JsonSerialize
	private Integer courseId;
	/**
	 * 章节编号
	 */
	private String chapterNo;
	/**
	 * 章节名称
	 */
	@NotBlank(message="章节名称不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String chapterName;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * 课程名称
	 */
	private String courseName;
	/**
	 * 章节类型[0-视频 1-音频]
	 */
	@NotBlank(message="章节类型不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String chapterType;
	/**
	 * 章节价格
	 */
	@Digits(integer = 8, fraction = 2,message = "请输入正确的价格",groups = {AddGroup.class, UpdateGroup.class})
	private BigDecimal chapterPrice;
	/**
	 * 章节排序
	 */
	private String chapterSort;
	/**
	 * 章节状态[0-新建 1-上线  2-下线]
	 */
	private String chapterStatus;
	/**
	 * 章节老师
	 */
	@NotBlank(message="章节老师不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String chapterTeacher;
	/**
	 * 章节播放次数
	 */
	@NotBlank(message="章节播放次数不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String chapterPlays;
	/**
	 * 是否允许试看[0-不允许  1-允许]
	 */
	private String chapterIstry;
	/**
	 * 章节文件[视频/音频文件]
	 */
	@NotBlank(message="章节播放文件不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String chapterFile;
	/**
	 * 章节试听文件
	 */
	private String previewFile;

	public String getChapterPic() {
		return chapterPic;
	}

	public void setChapterPic(String chapterPic) {
		this.chapterPic = chapterPic;
	}

	@NotBlank(message="章节封面图不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String chapterPic;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;
	/**
	 * 发布时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date publishTime;
	/**
	 * 下线时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date downTime;

	public String getChapterMinute() {
		return chapterMinute;
	}

	public void setChapterMinute(String chapterMinute) {
		this.chapterMinute = chapterMinute;
	}

	/**
	 * 章节时长
	 */
	private String chapterMinute;


	public String getChapterMemo() {
		return chapterMemo;
	}

	public void setChapterMemo(String chapterMemo) {
		this.chapterMemo = chapterMemo;
	}

	/**
	 * 章节说明

	 */
	private String chapterMemo;
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
	 * 设置：章节编号
	 */
	public void setChapterNo(String chapterNo) {
		this.chapterNo = chapterNo;
	}
	/**
	 * 获取：章节编号
	 */
	public String getChapterNo() {
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
	 * 设置：章节类型[0-视频 1-音频]
	 */
	public void setChapterType(String chapterType) {
		this.chapterType = chapterType;
	}
	/**
	 * 获取：章节类型[0-视频 1-音频]
	 */
	public String getChapterType() {
		return chapterType;
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
	 * 设置：章节状态[0-新建 1-上线  2-下线]
	 */
	public void setChapterStatus(String chapterStatus) {
		this.chapterStatus = chapterStatus;
	}
	/**
	 * 获取：章节状态[0-新建 1-上线  2-下线]
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
	 * 设置：章节播放次数
	 */
	public void setChapterPlays(String chapterPlays) {
		this.chapterPlays = chapterPlays;
	}
	/**
	 * 获取：章节播放次数
	 */
	public String getChapterPlays() {
		return chapterPlays;
	}
	/**
	 * 设置：是否允许试看[0-不允许  1-允许]
	 */
	public void setChapterIstry(String chapterIstry) {
		this.chapterIstry = chapterIstry;
	}
	/**
	 * 获取：是否允许试看[0-不允许  1-允许]
	 */
	public String getChapterIstry() {
		return chapterIstry;
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
	 * 设置：章节试听文件
	 */
	public void setPreviewFile(String previewFile) {
		this.previewFile = previewFile;
	}
	/**
	 * 获取：章节试听文件
	 */
	public String getPreviewFile() {
		return previewFile;
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
