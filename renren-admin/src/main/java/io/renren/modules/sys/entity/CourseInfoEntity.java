package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-04 11:10:26
 */
@TableName("course_info")
public class CourseInfoEntity implements Serializable {
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
	 * 课程名称
	 */
	@NotBlank(message="课程名称不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String courseName;
	/**
	 * 课程封面图
	 */
	@NotBlank(message="课程封面图不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String coursePic;
	/**
	 * 课程总价格
	 */
	@Digits(integer = 8, fraction = 2,message = "请输入正确的课程价格",groups = {AddGroup.class, UpdateGroup.class})
	private BigDecimal coursePrice;
	/**
	 * 课程老师
	 */
	@NotBlank(message="课程老师不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String courseTeacher;
	/**
	 * 课程时长(min)
	 */
	private String courseMinute;
	/**
	 * 课程分类[1-精品课程， 2-免费专区  3-线下课程]
	 */
	private String courseType;
	/**
	 * 课程标签[0-私募,1-财经，2-保险]
	 */
	private String courseTag;
	/**
	 * 课程状态[0-新建 1-上线, 2-下线 ]
	 */
	private String courseStatus;
	/**
	 * 是否有章节[0-无章节  1- 有章节]
	 */
	private String courseIschapter;
	/**
	 * 是否热门[0-否  1- 是]
	 */
	private String courseIshot;
	/**
	 * 课程排序
	 */
	private String courseSort;
	/**
	 * 课程文件URL（非必填)
	 */
	private String courseFile;
	/**
	 * 播放总次数
	 */
	private String coursePalys;
	/**
	 * 课程简介
	 */
	@NotBlank(message="课程简介不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String courseBrief;
	/**
	 * 课程详情
	 */
	@NotBlank(message="课程详情不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String courseContent;
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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
	 * 设置：课程时长(min)
	 */
	public void setCourseMinute(String courseMinute) {
		this.courseMinute = courseMinute;
	}
	/**
	 * 获取：课程时长(min)
	 */
	public String getCourseMinute() {
		return courseMinute;
	}
	/**
	 * 设置：课程分类[1-精品课程， 2-免费专区  3-线下课程]
	 */
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	/**
	 * 获取：课程分类[1-精品课程， 2-免费专区  3-线下课程]
	 */
	public String getCourseType() {
		return courseType;
	}
	/**
	 * 设置：课程标签[0-私募,1-财经，2-保险]
	 */
	public void setCourseTag(String courseTag) {
		this.courseTag = courseTag;
	}
	/**
	 * 获取：课程标签[0-私募,1-财经，2-保险]
	 */
	public String getCourseTag() {
		return courseTag;
	}
	/**
	 * 设置：课程状态[0-新建 1-上线, 2-下线 ]
	 */
	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}
	/**
	 * 获取：课程状态[0-新建 1-上线, 2-下线 ]
	 */
	public String getCourseStatus() {
		return courseStatus;
	}
	/**
	 * 设置：是否有章节[0-无章节  1- 有章节]
	 */
	public void setCourseIschapter(String courseIschapter) {
		this.courseIschapter = courseIschapter;
	}
	/**
	 * 获取：是否有章节[0-无章节  1- 有章节]
	 */
	public String getCourseIschapter() {
		return courseIschapter;
	}
	/**
	 * 设置：是否热门[0-否  1- 是]
	 */
	public void setCourseIshot(String courseIshot) {
		this.courseIshot = courseIshot;
	}
	/**
	 * 获取：是否热门[0-否  1- 是]
	 */
	public String getCourseIshot() {
		return courseIshot;
	}
	/**
	 * 设置：课程排序
	 */
	public void setCourseSort(String courseSort) {
		this.courseSort = courseSort;
	}
	/**
	 * 获取：课程排序
	 */
	public String getCourseSort() {
		return courseSort;
	}
	/**
	 * 设置：课程文件URL（非必填)
	 */
	public void setCourseFile(String courseFile) {
		this.courseFile = courseFile;
	}
	/**
	 * 获取：课程文件URL（非必填)
	 */
	public String getCourseFile() {
		return courseFile;
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
