package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 会员账户表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-04 21:54:43
 */
@TableName("account")
@KeySequence(clazz = String.class)
public class AccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一主键
	 */
	@TableId(type = IdType.INPUT)
	private String accountId;
	/**
	 * 会员ID
	 */
	private String userId;
	/**
	 * 累计充值
	 */
	private BigDecimal totalIn;
	/**
	 * 累计消费
	 */
	private BigDecimal totalOut;
	/**
	 * 账户余额
	 */
	private BigDecimal balance;
	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateTime;

	/**
	 * 用户姓名
	 */
	@TableField(exist=false)
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 用户姓名
	 */
	@TableField(exist=false)
	private String phone;

	/**
	 * 设置：唯一主键
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * 获取：唯一主键
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * 设置：会员ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：会员ID
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：累计充值
	 */
	public void setTotalIn(BigDecimal totalIn) {
		this.totalIn = totalIn;
	}
	/**
	 * 获取：累计充值
	 */
	public BigDecimal getTotalIn() {
		return totalIn;
	}
	/**
	 * 设置：累计消费
	 */
	public void setTotalOut(BigDecimal totalOut) {
		this.totalOut = totalOut;
	}
	/**
	 * 获取：累计消费
	 */
	public BigDecimal getTotalOut() {
		return totalOut;
	}
	/**
	 * 设置：账户余额
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	/**
	 * 获取：账户余额
	 */
	public BigDecimal getBalance() {
		return balance;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
