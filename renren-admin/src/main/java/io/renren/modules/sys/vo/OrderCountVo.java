package io.renren.modules.sys.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: sherlock
 * Date: 2018-10-11
 */
public class OrderCountVo {

    /**
     * 课程名称
     */
    private String courseName;
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
     * 微信
     */
    private BigDecimal wechatPrice;

    /**
     * 支付宝
     */
    private BigDecimal zhifuPrice;

    /**
     * 对公
     */
    private BigDecimal duigongPrice;

    /**
     * 付款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public BigDecimal getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(BigDecimal coursePrice) {
        this.coursePrice = coursePrice;
    }

    public BigDecimal getOrderCoupon() {
        return orderCoupon;
    }

    public void setOrderCoupon(BigDecimal orderCoupon) {
        this.orderCoupon = orderCoupon;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getWechatPrice() {
        return wechatPrice;
    }

    public void setWechatPrice(BigDecimal wechatPrice) {
        this.wechatPrice = wechatPrice;
    }

    public BigDecimal getZhifuPrice() {
        return zhifuPrice;
    }

    public void setZhifuPrice(BigDecimal zhifuPrice) {
        this.zhifuPrice = zhifuPrice;
    }

    public BigDecimal getDuigongPrice() {
        return duigongPrice;
    }

    public void setDuigongPrice(BigDecimal duigongPrice) {
        this.duigongPrice = duigongPrice;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}
