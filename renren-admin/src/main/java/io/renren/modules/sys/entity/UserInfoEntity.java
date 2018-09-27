package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-05 21:47:20
 */
@TableName("user_info")
@KeySequence(clazz = String.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一主键
	 */
	@TableId(type = IdType.INPUT)
	private String userId;
	/**
	 * 用户姓名
	 */
	@NotBlank(message="会员姓名不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String userName;
	/**
	 * 用户类型[1-VIP  2-普通用户]
	 */
	@NotBlank(message="会员类型不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String userType;
	/**
	 * 性别
	 */
	private String userSex;
	/**
	 * 手机号码
	 */
	@NotBlank(message="会员手机不能为空",groups = {AddGroup.class, UpdateGroup.class})
	@Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$",message = "请输入正确的手机号码",groups = {AddGroup.class, UpdateGroup.class})
	private String phone;
	/**
	 * 住址
	 */
	private String address;
	/**
	 * 微信名称
	 */
	@NotBlank(message="微信号不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String wxUname;
	/**
	 * 微信OPENID
	 */
	private String wxOpenid;
	/**
	 * 微信头像
	 */
	private String wxHeadpic;
	/**
	 * 说明
	 */
	private String memo;
	/**
	 * 会员编号
	 */
	private String userNo;
	/**
	 * 注册时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date registTime;

	@TableField(exist=false)
	private BigDecimal balance;

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * 设置：唯一主键
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：唯一主键
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：用户姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户姓名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：用户类型[1-VIP  2-普通用户]
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * 获取：用户类型[1-VIP  2-普通用户]
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * 设置：性别
	 */
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	/**
	 * 获取：性别
	 */
	public String getUserSex() {
		return userSex;
	}
	/**
	 * 设置：手机号码
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号码
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：住址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：住址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：微信名称
	 */
	public void setWxUname(String wxUname) {
		this.wxUname = wxUname;
	}
	/**
	 * 获取：微信名称
	 */
	public String getWxUname() {
		return wxUname;
	}
	/**
	 * 设置：微信OPENID
	 */
	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}
	/**
	 * 获取：微信OPENID
	 */
	public String getWxOpenid() {
		return wxOpenid;
	}
	/**
	 * 设置：微信头像
	 */
	public void setWxHeadpic(String wxHeadpic) {
		this.wxHeadpic = wxHeadpic;
	}
	/**
	 * 获取：微信头像
	 */
	public String getWxHeadpic() {
		return wxHeadpic;
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


	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
}
