package io.renren.modules.sys.entity;

import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 项目信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-04 21:54:43
 */
@TableName("project")
@KeySequence(clazz = String.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一主键
	 */
	@TableId(type = IdType.INPUT)
	private String proId;
	/**
	 * 项目名称
	 */
	@NotBlank(message="项目名称不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String proName;
	/**
	 * 项目推荐语
	 */
	@NotBlank(message="项目推荐语不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String proBrief;
	/**
	 * 项目状态[0-新建 1-已上线 2-已下线]
	 */
	private String proStatus;
	/**
	 * 项目类型[0-足疗 1-保健 2-纤体 3-养生]
	 */
	@NotBlank(message="项目类型不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String proType;
	/**
	 * 项目定价
	 */
	@Digits(integer = 8, fraction = 2,message = "请输入正确的服务时长",groups = {AddGroup.class, UpdateGroup.class})
	private BigDecimal proAmt;
	/**
	 * 服务时长/分钟
	 */
	@Pattern(regexp = "^[0-9]*$",message = "请输入正确的服务时长",groups = {AddGroup.class, UpdateGroup.class})
	private String proLong;
	/**
	 * 项目介绍
	 */
	@NotBlank(message="项目介绍不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String proContent;

	public String getDatetimeStart() {
		return datetimeStart;
	}

	public void setDatetimeStart(String datetimeStart) {
		this.datetimeStart = datetimeStart;
	}

	public String getDateMinStart() {
		return dateMinStart;
	}

	public void setDateMinStart(String dateMinStart) {
		this.dateMinStart = dateMinStart;
	}

	public String getDatetimeEnd() {
		return datetimeEnd;
	}

	public void setDatetimeEnd(String datetimeEnd) {
		this.datetimeEnd = datetimeEnd;
	}

	public String getDateMinEnd() {
		return dateMinEnd;
	}

	public void setDateMinEnd(String dateMinEnd) {
		this.dateMinEnd = dateMinEnd;
	}

	/**
	 * 每天人数限制
	 */
	@Pattern(regexp = "^[0-9]*$",message = "请输入正确的人数限制",groups = {AddGroup.class, UpdateGroup.class})
	@NotBlank(message="人数限制不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String maxPeople;
	/**
	 * 封面图
	 */
	@NotBlank(message="封面图不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String coverPic;
	/**
	 * 介绍轮播图
	 */
	@NotBlank(message="轮播图不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String cyclePic;
	/**
	 * 受理预约开始时间
	 */
	private Float beginTime;
	/**
	 * 受理预约结束时间
	 */
	private Float endTime;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 项目说明
	 */
	private String memo;

	/**
	 * 项目服务开始时间/时
	 */
	@TableField(exist=false)
	@NotBlank(message="项目服务开始时间/时不能为空",groups = {AddGroup.class, UpdateGroup.class})
	@Pattern(regexp = "^((\\d)|(1\\d)|(2[0-4]))$",message = "请输入正确的项目服务开始时间",groups = {AddGroup.class, UpdateGroup.class})
	private String datetimeStart;
	/**
	 * 项目服务开始时间/分
	 */
	@NotBlank(message="项目服务开始时间/分不能为空",groups = {AddGroup.class, UpdateGroup.class})
	@Pattern(regexp = "[0-6][0-9]",message = "请输入正确的项目服务开始时间",groups = {AddGroup.class, UpdateGroup.class})
	@TableField(exist=false)
	private String dateMinStart;
	/**
	 * 项目服务结束时间/时
	 */
	@NotBlank(message="项目服务结束时间/时不能为空",groups = {AddGroup.class, UpdateGroup.class})
	@Pattern(regexp = "^((\\d)|(1\\d)|(2[0-4]))$",message = "请输入正确的项目服务开始时间",groups = {AddGroup.class, UpdateGroup.class})
	@TableField(exist=false)
	private String datetimeEnd;
	/**
	 * 项目服务结束时间/分
	 */
	@NotBlank(message="项目服务结束时间/分不能为空",groups = {AddGroup.class, UpdateGroup.class})
	@Pattern(regexp = "[0-6][0-9]",message = "请输入正确的项目服务开始时间",groups = {AddGroup.class, UpdateGroup.class})
	@TableField(exist=false)
	private String dateMinEnd;

	/**
	 * 设置：唯一主键
	 */
	public void setProId(String proId) {
		this.proId = proId;
	}
	/**
	 * 获取：唯一主键
	 */
	public String getProId() {
		return proId;
	}
	/**
	 * 设置：项目名称
	 */
	public void setProName(String proName) {
		this.proName = proName;
	}
	/**
	 * 获取：项目名称
	 */
	public String getProName() {
		return proName;
	}
	/**
	 * 设置：项目推荐语
	 */
	public void setProBrief(String proBrief) {
		this.proBrief = proBrief;
	}
	/**
	 * 获取：项目推荐语
	 */
	public String getProBrief() {
		return proBrief;
	}
	/**
	 * 设置：项目状态[0-新建 1-已上线 2-已下线]
	 */
	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}
	/**
	 * 获取：项目状态[0-新建 1-已上线 2-已下线]
	 */
	public String getProStatus() {
		return proStatus;
	}
	/**
	 * 设置：项目类型[0-足疗 1-保健 2-纤体 3-养生]
	 */
	public void setProType(String proType) {
		this.proType = proType;
	}
	/**
	 * 获取：项目类型[0-足疗 1-保健 2-纤体 3-养生]
	 */
	public String getProType() {
		return proType;
	}
	/**
	 * 设置：项目定价
	 */
	public void setProAmt(BigDecimal proAmt) {
		this.proAmt = proAmt;
	}
	/**
	 * 获取：项目定价
	 */
	public BigDecimal getProAmt() {
		return proAmt;
	}
	/**
	 * 设置：服务时长/分钟
	 */
	public void setProLong(String proLong) {
		this.proLong = proLong;
	}
	/**
	 * 获取：服务时长/分钟
	 */
	public String getProLong() {
		return proLong;
	}
	/**
	 * 设置：项目介绍
	 */
	public void setProContent(String proContent) {
		this.proContent = proContent;
	}
	/**
	 * 获取：项目介绍
	 */
	public String getProContent() {
		return proContent;
	}
	/**
	 * 设置：每天人数限制
	 */
	public void setMaxPeople(String maxPeople) {
		this.maxPeople = maxPeople;
	}
	/**
	 * 获取：每天人数限制
	 */
	public String getMaxPeople() {
		return maxPeople;
	}
	/**
	 * 设置：封面图
	 */
	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}
	/**
	 * 获取：封面图
	 */
	public String getCoverPic() {
		return coverPic;
	}
	/**
	 * 设置：介绍轮播图
	 */
	public void setCyclePic(String cyclePic) {
		this.cyclePic = cyclePic;
	}
	/**
	 * 获取：介绍轮播图
	 */
	public String getCyclePic() {
		return cyclePic;
	}
	/**
	 * 设置：受理预约开始时间
	 */
	public void setBeginTime(Float beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * 获取：受理预约开始时间
	 */
	public Float getBeginTime() {
		return beginTime;
	}
	/**
	 * 设置：受理预约结束时间
	 */
	public void setEndTime(Float endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：受理预约结束时间
	 */
	public Float getEndTime() {
		return endTime;
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
	 * 设置：项目说明
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * 获取：项目说明
	 */
	public String getMemo() {
		return memo;
	}
}
