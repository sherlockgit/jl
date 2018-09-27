package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员收货人表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-27 11:35:10
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
	/**
	 * 是否删除(y 删除 n 未删除)
	 */
	private String isDelete;
	/**
	 * 是否默认(y 是 n 否)
	 */
	private String isDefaute;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 创建者
	 */
	private String createdby;

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
	 * 设置：是否删除(y 删除 n 未删除)
	 */
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：是否删除(y 删除 n 未删除)
	 */
	public String getIsDelete() {
		return isDelete;
	}
	/**
	 * 设置：是否默认(y 是 n 否)
	 */
	public void setIsDefaute(String isDefaute) {
		this.isDefaute = isDefaute;
	}
	/**
	 * 获取：是否默认(y 是 n 否)
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
	/**
	 * 设置：创建者
	 */
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	/**
	 * 获取：创建者
	 */
	public String getCreatedby() {
		return createdby;
	}
}
