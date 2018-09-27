package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 会员账户明细表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-04 21:54:43
 */
@TableName("account_item")
@KeySequence(clazz = String.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一主键
	 */
	@TableId(type = IdType.INPUT)
	private String itemId;
	/**
	 * 会员ID
	 */
	private String userId;
	/**
	 * 充值
	 */
	private BigDecimal amtIn;
	/**
	 * 消费
	 */
	private BigDecimal amtOut;
	/**
	 * 账户余额
	 */
	private BigDecimal balance;
	/**
	 * 交易状态[0-失败  1-成功]
	 */
	private String tranStatus;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date createTime;
	/**
	 * 说明
	 */
	private String memo;

	/**
	 * 设置：唯一主键
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	/**
	 * 获取：唯一主键
	 */
	public String getItemId() {
		return itemId;
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
	 * 设置：充值
	 */
	public void setAmtIn(BigDecimal amtIn) {
		this.amtIn = amtIn;
	}
	/**
	 * 获取：充值
	 */
	public BigDecimal getAmtIn() {
		return amtIn;
	}
	/**
	 * 设置：消费
	 */
	public void setAmtOut(BigDecimal amtOut) {
		this.amtOut = amtOut;
	}
	/**
	 * 获取：消费
	 */
	public BigDecimal getAmtOut() {
		return amtOut;
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
	 * 设置：交易状态[0-失败  1-成功]
	 */
	public void setTranStatus(String tranStatus) {
		this.tranStatus = tranStatus;
	}
	/**
	 * 获取：交易状态[0-失败  1-成功]
	 */
	public String getTranStatus() {
		return tranStatus;
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
	 * 设置：说明
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * 获取：说明
	 */
	public String getMemo() {
		return memo;
	}
}
