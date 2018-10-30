package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 轮播图表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-04 21:54:43
 */
@TableName("banner")
@KeySequence(clazz = String.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BannerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一主键
	 */
	@TableId(type = IdType.INPUT)
	private String bannerId;
	/**
	 * 标题
	 */
	@NotBlank(message="轮播标题不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String title;
	/**
	 * 跳转地址
	 */
	private String href;

	public String getCome() {
		return come;
	}

	public void setCome(String come) {
		this.come = come;
	}

	/**
	 * 归属【1-全部，2-首页，3发现】

	 */
	private String come;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 轮播状态[0-新建 1-已上线 2-已下线]
	 */
	private String statu;
	/**
	 * 轮播图片
	 */
	@NotBlank(message="轮播图片不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String picture;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date createTime;

	/**
	 * 设置：唯一主键
	 */
	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}
	/**
	 * 获取：唯一主键
	 */
	public String getBannerId() {
		return bannerId;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：跳转地址
	 */
	public void setHref(String href) {
		this.href = href;
	}
	/**
	 * 获取：跳转地址
	 */
	public String getHref() {
		return href;
	}
	/**
	 * 设置：排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 设置：轮播状态[0-新建 1-已上线 2-已下线]
	 */
	public void setStatu(String statu) {
		this.statu = statu;
	}
	/**
	 * 获取：轮播状态[0-新建 1-已上线 2-已下线]
	 */
	public String getStatu() {
		return statu;
	}
	/**
	 * 设置：轮播图片
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	/**
	 * 获取：轮播图片
	 */
	public String getPicture() {
		return picture;
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
}
