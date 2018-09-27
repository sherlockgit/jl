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
	private Integer orderNo;
	/**
	 * 订单名称[课程/问答/直播]
	 */
	private String courseName;
	/**
	 * 课程价格
	 */
	private BigDecimal coursePrice;
	/**
	 * 订单类型[0-课程， 2-问答  3-直播]
	 */
	private String orderType;
	/**
	 * 订单金额
	 */
	private BigDecimal orderPrice;
	/**
	 * 订单抵扣
	 */
	private BigDecimal orderCoupon;
	/**
	 * 订单状态[0-待付款， 1-已付款， 2-待退款， 3-已退款，4-拒退款]
	 */
	private String orderStatus;
	/**
	 * 付款方式[0-微信， 1-支付宝,  2-银联]
	 */
	private String payType;
	/**
	 * 机构名称
	 */
	private String orgName;
	/**
	 * 会员姓名
	 */
	private String userName;
	/**
	 * 会员电话
	 */
	private String userPhone;
	/**
	 * 下单时间
	 */
	private String createTime;
	/**
	 * 付款时间
	 */
	private Date payTime;

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
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：订单编号[自动生成]
	 */
	public Integer getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：订单名称[课程/问答/直播]
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * 获取：订单名称[课程/问答/直播]
	 */
	public String getCourseName() {
		return courseName;
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
	 * 设置：订单类型[0-课程， 2-问答  3-直播]
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 * 获取：订单类型[0-课程， 2-问答  3-直播]
	 */
	public String getOrderType() {
		return orderType;
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
	 * 设置：订单状态[0-待付款， 1-已付款， 2-待退款， 3-已退款，4-拒退款]
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * 获取：订单状态[0-待付款， 1-已付款， 2-待退款， 3-已退款，4-拒退款]
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/**
	 * 设置：付款方式[0-微信， 1-支付宝,  2-银联]
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * 获取：付款方式[0-微信， 1-支付宝,  2-银联]
	 */
	public String getPayType() {
		return payType;
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
	 * 设置：下单时间
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：下单时间
	 */
	public String getCreateTime() {
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
