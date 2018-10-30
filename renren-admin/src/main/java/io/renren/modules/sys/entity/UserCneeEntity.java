package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员收货人表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-08 17:26:29
 */
@TableName("user_cnee")
public class UserCneeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 会员id
	 */
	private String userId;
	/**
	 * 收货人姓名
	 */
	private String cneeName;
	/**
	 * 收货人号码
	 */
	private String cneePhone;
	/**
	 * 收货人地址
	 */
	private String cneeAddr;

	public String getCneeMemo() {
		return cneeMemo;
	}

	public void setCneeMemo(String cneeMemo) {
		this.cneeMemo = cneeMemo;
	}

	/**
	 * 说明
	 */
	private String cneeMemo;
	/**
	 * 是否默认[0- 否 ,  1- 是 ]
	 */
	private String isDefaute;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date updateTime;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：会员id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：会员id
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：收货人姓名
	 */
	public void setCneeName(String cneeName) {
		this.cneeName = cneeName;
	}
	/**
	 * 获取：收货人姓名
	 */
	public String getCneeName() {
		return cneeName;
	}
	/**
	 * 设置：收货人号码
	 */
	public void setCneePhone(String cneePhone) {
		this.cneePhone = cneePhone;
	}
	/**
	 * 获取：收货人号码
	 */
	public String getCneePhone() {
		return cneePhone;
	}
	/**
	 * 设置：收货人地址
	 */
	public void setCneeAddr(String cneeAddr) {
		this.cneeAddr = cneeAddr;
	}
	/**
	 * 获取：收货人地址
	 */
	public String getCneeAddr() {
		return cneeAddr;
	}
	/**
	 * 设置：是否默认[0- 否 ,  1- 是 ]
	 */
	public void setIsDefaute(String isDefaute) {
		this.isDefaute = isDefaute;
	}
	/**
	 * 获取：是否默认[0- 否 ,  1- 是 ]
	 */
	public String getIsDefaute() {
		return isDefaute;
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
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
