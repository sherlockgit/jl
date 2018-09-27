package io.renren.modules.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * author: 小宇宙
 * date: 2018/6/10
 */
public class AccountItemVO {

    /**
     * 唯一主键
     */
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
     * 用户姓名
     */
    private String userName;

    /**
     * 手机号码
     */
    private String phone;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAmtIn() {
        return amtIn;
    }

    public void setAmtIn(BigDecimal amtIn) {
        this.amtIn = amtIn;
    }

    public BigDecimal getAmtOut() {
        return amtOut;
    }

    public void setAmtOut(BigDecimal amtOut) {
        this.amtOut = amtOut;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getTranStatus() {
        return tranStatus;
    }

    public void setTranStatus(String tranStatus) {
        this.tranStatus = tranStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

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
}
