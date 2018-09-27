package io.renren.modules.sys.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * author: 小宇宙
 * date: 2018/6/10
 */
public class AccountVO {

    /**
     * 唯一主键
     */
    private String accountId;
    /**
     * 会员ID
     */
    private String userId;
    /**
     * 充值
     */
    @NotNull(message="充值金额不能为空",groups = {AddGroup.class, UpdateGroup.class})
    @Min(value = 1,message = "充值金额不能小于一元",groups = {AddGroup.class, UpdateGroup.class})
    @Digits(integer = 6,fraction = 2,message = "金额不能超过两位数")
    private BigDecimal totalInNow;

    public BigDecimal getTotalInNow() {
        return totalInNow;
    }

    public void setTotalInNow(BigDecimal totalInNow) {
        this.totalInNow = totalInNow;
    }

    /**
     * 充值
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
    private String userName;

    /**
     * 住址
     */
    private String address;
    /**
     * 微信名称
     */
    private String wxUname;

    /**
     * 用户类型[1-VIP  2-普通用户]
     */
    private String userType;
    /**
     * 性别
     */
    private String userSex;

    /**
     * 说明
     */
    @NotBlank(message="说明不能为空",groups = {AddGroup.class, UpdateGroup.class})
    private String memo;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWxUname() {
        return wxUname;
    }

    public void setWxUname(String wxUname) {
        this.wxUname = wxUname;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalIn() {
        return totalIn;
    }

    public void setTotalIn(BigDecimal totalIn) {
        this.totalIn = totalIn;
    }

    public BigDecimal getTotalOut() {
        return totalOut;
    }

    public void setTotalOut(BigDecimal totalOut) {
        this.totalOut = totalOut;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    /**
     * 手机号码
     */
    private String phone;
}
