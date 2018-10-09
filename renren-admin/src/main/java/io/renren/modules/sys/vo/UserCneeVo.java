package io.renren.modules.sys.vo;

import com.baomidou.mybatisplus.annotations.TableId;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: sherlock
 * Date: 2018-10-09
 */
public class UserCneeVo {

    private static final long serialVersionUID = 1L;

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

    public String getCneeMemo() {
        return cneeMemo;
    }

    public void setCneeMemo(String cneeMemo) {
        this.cneeMemo = cneeMemo;
    }

    /**
     * 说明
     */
    private String cneeMemo;
    /**
     * 是否默认[0- 否 ,  1- 是 ]
     */
    private String isDefaute;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private String userNo;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 修改时间
     */
    private Date updateTime;

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
     * 设置：是否默认[0- 否 ,  1- 是 ]
     */
    public void setIsDefaute(String isDefaute) {
        this.isDefaute = isDefaute;
    }
    /**
     * 获取：是否默认[0- 否 ,  1- 是 ]
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

}