package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-08 14:48:16
 */
@KeySequence(clazz = String.class)
@TableName("order_refund")
public class OrderRefundEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.INPUT)
	private String id;
	/**
	 * 订单编号
	 */
	private String orderNo;
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
	 * 退款流水号[支付公司流水号]
	 */
	private String refundNo;
	/**
	 * 退款操作人
	 */
	private String refundOper;
	/**
	 * 退款说明-必填
	 */
	private String refundMemo;
	/**
	 * 退款处理时间
	 */
	private Date operTime;
	/**
	 * 退款时间[到账]
	 */
	private Date refundTime;
	/**
	 * 退款申请时间
	 */
	private Date applyTime;
	/**
	 * 课程名称
	 */
	private String courseName;
	/**
	 * 会员姓名
	 */
	private String userName;
	/**
	 * 会员手机号
	 */
	private String phone;
	/**
	 * 机构名称
	 */
	private String organization;
	/**
	 * 退款方式0-微信支付  1-支付宝   2-银联支付  3-线下转账
	 */
	private String applyType;

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
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：订单编号
	 */
	public String getOrderNo() {
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
	 * 设置：退款流水号[支付公司流水号]
	 */
	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}
	/**
	 * 获取：退款流水号[支付公司流水号]
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
	 * 设置：退款处理时间
	 */
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	/**
	 * 获取：退款处理时间
	 */
	public Date getOperTime() {
		return operTime;
	}
	/**
	 * 设置：退款时间[到账]
	 */
	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
	/**
	 * 获取：退款时间[到账]
	 */
	public Date getRefundTime() {
		return refundTime;
	}
	/**
	 * 设置：退款申请时间
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	/**
	 * 获取：退款申请时间
	 */
	public Date getApplyTime() {
		return applyTime;
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
	 * 设置：会员姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：会员姓名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：会员手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：会员手机号
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：机构名称
	 */
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	/**
	 * 获取：机构名称
	 */
	public String getOrganization() {
		return organization;
	}
	/**
	 * 设置：退款方式0-微信支付  1-支付宝   2-银联支付  3-线下转账
	 */
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	/**
	 * 获取：退款方式0-微信支付  1-支付宝   2-银联支付  3-线下转账
	 */
	public String getApplyType() {
		return applyType;
	}
}
