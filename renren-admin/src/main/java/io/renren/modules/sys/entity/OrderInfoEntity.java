package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-05 00:57:48
 */
@TableName("order_info")
public class OrderInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 订单编号[自动生成]
	 */
	private String orderNo;
	/**
	 * 课程名称
	 */
	private String courseName;
	/**
	 * 订单来源[0-堂课， 1-直播  2-录播  3-音频]
	 */
	private String orderSource;
	/**
	 * 课程价格
	 */
	private BigDecimal coursePrice;
	/**
	 * 订单抵扣
	 */
	private BigDecimal orderCoupon;
	/**
	 * 实际收款
	 */
	private BigDecimal orderPrice;
	/**
	 * 付款状态[0-待付款 1-已付款]
	 */
	private String payStatus;
	/**
	 * 付款方式[0-微信支付  1-支付宝   2-银联支付  3-线下转账]
	 */
	private String payType;
	/**
	 * 发票状态[0-未开票  1- 需开票  2-已开票]
	 */
	private String invoiceStatus;
	/**
	 * 会员ID
	 */
	private String userId;
	/**
	 * 会员姓名
	 */
	private String userName;
	/**
	 * 会员电话
	 */
	private String userPhone;
	/**
	 * 机构名称
	 */
	private String orgName;
	/**
	 * 下单时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 付款时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date payTime;

	/**
	 * 付款时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String orderMemo;

	public String getOrderMemo() {
		return orderMemo;
	}

	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}

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
	 * 设置：订单编号[自动生成]
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：订单编号[自动生成]
	 */
	public String getOrderNo() {
		return orderNo;
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
	 * 设置：订单来源[0-堂课， 1-直播  2-录播  3-音频]
	 */
	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	/**
	 * 获取：订单来源[0-堂课， 1-直播  2-录播  3-音频]
	 */
	public String getOrderSource() {
		return orderSource;
	}
	/**
	 * 设置：课程价格
	 */
	public void setCoursePrice(BigDecimal coursePrice) {
		this.coursePrice = coursePrice;
	}
	/**
	 * 获取：课程价格
	 */
	public BigDecimal getCoursePrice() {
		return coursePrice;
	}
	/**
	 * 设置：订单抵扣
	 */
	public void setOrderCoupon(BigDecimal orderCoupon) {
		this.orderCoupon = orderCoupon;
	}
	/**
	 * 获取：订单抵扣
	 */
	public BigDecimal getOrderCoupon() {
		return orderCoupon;
	}
	/**
	 * 设置：实际收款
	 */
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	/**
	 * 获取：实际收款
	 */
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	/**
	 * 设置：付款状态[0-待付款 1-已付款]
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	/**
	 * 获取：付款状态[0-待付款 1-已付款]
	 */
	public String getPayStatus() {
		return payStatus;
	}
	/**
	 * 设置：付款方式[0-微信支付  1-支付宝   2-银联支付  3-线下转账]
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * 获取：付款方式[0-微信支付  1-支付宝   2-银联支付  3-线下转账]
	 */
	public String getPayType() {
		return payType;
	}
	/**
	 * 设置：发票状态[0-未开票  1- 需开票  2-已开票]
	 */
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	/**
	 * 获取：发票状态[0-未开票  1- 需开票  2-已开票]
	 */
	public String getInvoiceStatus() {
		return invoiceStatus;
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
	 * 设置：会员电话
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	/**
	 * 获取：会员电话
	 */
	public String getUserPhone() {
		return userPhone;
	}
	/**
	 * 设置：机构名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * 获取：机构名称
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * 设置：下单时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：下单时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：付款时间
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	/**
	 * 获取：付款时间
	 */
	public Date getPayTime() {
		return payTime;
	}
}
