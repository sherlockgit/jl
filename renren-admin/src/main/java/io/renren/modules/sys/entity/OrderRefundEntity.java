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
@TableName("order_refund")
public class OrderRefundEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 订单编号
	 */
	private Integer orderNo;
	/**
	 * 订单金额
	 */
	private BigDecimal orderPrice;
	/**
	 * 实际退款金额
	 */
	private BigDecimal refundPrice;
	/**
	 * 退款状态[0-待退款 1-已退款 2-拒退款]
	 */
	private String refundStatus;
	/**
	 * 退款说明-必填
	 */
	private String refundMemo;
	/**
	 * 退款流水号
	 */
	private String refundNo;
	/**
	 * 退款操作人
	 */
	private String refundOper;
	/**
	 * 
	 */
	private Date refundTime;
	/**
	 * 
	 */
	private Date applyTime;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：订单编号
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：订单编号
	 */
	public Integer getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：订单金额
	 */
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	/**
	 * 获取：订单金额
	 */
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	/**
	 * 设置：实际退款金额
	 */
	public void setRefundPrice(BigDecimal refundPrice) {
		this.refundPrice = refundPrice;
	}
	/**
	 * 获取：实际退款金额
	 */
	public BigDecimal getRefundPrice() {
		return refundPrice;
	}
	/**
	 * 设置：退款状态[0-待退款 1-已退款 2-拒退款]
	 */
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	/**
	 * 获取：退款状态[0-待退款 1-已退款 2-拒退款]
	 */
	public String getRefundStatus() {
		return refundStatus;
	}
	/**
	 * 设置：退款说明-必填
	 */
	public void setRefundMemo(String refundMemo) {
		this.refundMemo = refundMemo;
	}
	/**
	 * 获取：退款说明-必填
	 */
	public String getRefundMemo() {
		return refundMemo;
	}
	/**
	 * 设置：退款流水号
	 */
	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}
	/**
	 * 获取：退款流水号
	 */
	public String getRefundNo() {
		return refundNo;
	}
	/**
	 * 设置：退款操作人
	 */
	public void setRefundOper(String refundOper) {
		this.refundOper = refundOper;
	}
	/**
	 * 获取：退款操作人
	 */
	public String getRefundOper() {
		return refundOper;
	}
	/**
	 * 设置：
	 */
	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
	/**
	 * 获取：
	 */
	public Date getRefundTime() {
		return refundTime;
	}
	/**
	 * 设置：
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	/**
	 * 获取：
	 */
	public Date getApplyTime() {
		return applyTime;
	}
}
