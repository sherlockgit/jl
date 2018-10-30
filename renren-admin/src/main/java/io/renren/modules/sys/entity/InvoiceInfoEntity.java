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
 * @date 2018-10-05 10:07:01
 */
@TableName("invoice_info")
public class InvoiceInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 发票编号
	 */
	private String invoiceNo;
	/**
	 * 发票抬头
	 */
	private String invoiceName;
	/**
	 * 开票金额
	 */
	private BigDecimal invoiceAmt;
	/**
	 * 开票装[0-开票中  1-已开票]
	 */
	private String invoiceStauts;
	/**
	 * 开票类型[0-普票  1- 专票]
	 */
	private String invoiceType;
	/**
	 * 发票性质[0-电子发票，1-纸质发票]
	 */
	private String invoiceNature;
	/**
	 * 开票类目[0-服务费， 1-会务费   2-咨询费]
	 */
	private String invoiceCategory;
	/**
	 * 发票凭证
	 */
	private String invoiceFile;
	/**
	 * 电子发票链接
	 */
	private String invoiceUrl;
	/**
	 * 稅务登记号
	 */
	private String taxNo;
	/**
	 * 开户银行
	 */
	private String bankName;
	/**
	 * 开户账号
	 */
	private String bankCard;
	/**
	 * 会员ID
	 */
	private String userId;
	/**
	 * 收件人
	 */
	private String cneeName;
	/**
	 * 收件电话
	 */
	private String cneePhone;
	/**
	 * 收件地址
	 */
	private String cneeAddr;
	/**
	 * 快递公司
	 */
	private String expressOrg;
	/**
	 * 快递编号
	 */
	private String expressNo;
	/**
	 * 快递签收[0-未签收， 1-已签收]
	 */
	private String expressSign;
	/**
	 * 开票时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date expressTime;
	/**
	 * 申请开票时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date applyTime;
	/**
	 * 订单编号集合[aa,bbb]
	 */
	private String orderNos;

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
	 * 设置：发票编号
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	/**
	 * 获取：发票编号
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}
	/**
	 * 设置：发票抬头
	 */
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	/**
	 * 获取：发票抬头
	 */
	public String getInvoiceName() {
		return invoiceName;
	}
	/**
	 * 设置：开票金额
	 */
	public void setInvoiceAmt(BigDecimal invoiceAmt) {
		this.invoiceAmt = invoiceAmt;
	}
	/**
	 * 获取：开票金额
	 */
	public BigDecimal getInvoiceAmt() {
		return invoiceAmt;
	}
	/**
	 * 设置：开票装[0-开票中  1-已开票]
	 */
	public void setInvoiceStauts(String invoiceStauts) {
		this.invoiceStauts = invoiceStauts;
	}
	/**
	 * 获取：开票装[0-开票中  1-已开票]
	 */
	public String getInvoiceStauts() {
		return invoiceStauts;
	}
	/**
	 * 设置：开票类型[0-普票  1- 专票]
	 */
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	/**
	 * 获取：开票类型[0-普票  1- 专票]
	 */
	public String getInvoiceType() {
		return invoiceType;
	}
	/**
	 * 设置：发票性质[0-电子发票，1-纸质发票]
	 */
	public void setInvoiceNature(String invoiceNature) {
		this.invoiceNature = invoiceNature;
	}
	/**
	 * 获取：发票性质[0-电子发票，1-纸质发票]
	 */
	public String getInvoiceNature() {
		return invoiceNature;
	}
	/**
	 * 设置：开票类目[0-服务费， 1-会务费   2-咨询费]
	 */
	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}
	/**
	 * 获取：开票类目[0-服务费， 1-会务费   2-咨询费]
	 */
	public String getInvoiceCategory() {
		return invoiceCategory;
	}
	/**
	 * 设置：发票凭证
	 */
	public void setInvoiceFile(String invoiceFile) {
		this.invoiceFile = invoiceFile;
	}
	/**
	 * 获取：发票凭证
	 */
	public String getInvoiceFile() {
		return invoiceFile;
	}
	/**
	 * 设置：电子发票链接
	 */
	public void setInvoiceUrl(String invoiceUrl) {
		this.invoiceUrl = invoiceUrl;
	}
	/**
	 * 获取：电子发票链接
	 */
	public String getInvoiceUrl() {
		return invoiceUrl;
	}
	/**
	 * 设置：稅务登记号
	 */
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	/**
	 * 获取：稅务登记号
	 */
	public String getTaxNo() {
		return taxNo;
	}
	/**
	 * 设置：开户银行
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * 获取：开户银行
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * 设置：开户账号
	 */
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	/**
	 * 获取：开户账号
	 */
	public String getBankCard() {
		return bankCard;
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
	 * 设置：收件人
	 */
	public void setCneeName(String cneeName) {
		this.cneeName = cneeName;
	}
	/**
	 * 获取：收件人
	 */
	public String getCneeName() {
		return cneeName;
	}
	/**
	 * 设置：收件电话
	 */
	public void setCneePhone(String cneePhone) {
		this.cneePhone = cneePhone;
	}
	/**
	 * 获取：收件电话
	 */
	public String getCneePhone() {
		return cneePhone;
	}
	/**
	 * 设置：收件地址
	 */
	public void setCneeAddr(String cneeAddr) {
		this.cneeAddr = cneeAddr;
	}
	/**
	 * 获取：收件地址
	 */
	public String getCneeAddr() {
		return cneeAddr;
	}
	/**
	 * 设置：快递公司
	 */
	public void setExpressOrg(String expressOrg) {
		this.expressOrg = expressOrg;
	}
	/**
	 * 获取：快递公司
	 */
	public String getExpressOrg() {
		return expressOrg;
	}
	/**
	 * 设置：快递编号
	 */
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	/**
	 * 获取：快递编号
	 */
	public String getExpressNo() {
		return expressNo;
	}
	/**
	 * 设置：快递签收[0-未签收， 1-已签收]
	 */
	public void setExpressSign(String expressSign) {
		this.expressSign = expressSign;
	}
	/**
	 * 获取：快递签收[0-未签收， 1-已签收]
	 */
	public String getExpressSign() {
		return expressSign;
	}
	/**
	 * 设置：开票时间
	 */
	public void setExpressTime(Date expressTime) {
		this.expressTime = expressTime;
	}
	/**
	 * 获取：开票时间
	 */
	public Date getExpressTime() {
		return expressTime;
	}
	/**
	 * 设置：申请开票时间
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	/**
	 * 获取：申请开票时间
	 */
	public Date getApplyTime() {
		return applyTime;
	}
	/**
	 * 设置：订单编号集合[aa,bbb]
	 */
	public void setOrderNos(String orderNos) {
		this.orderNos = orderNos;
	}
	/**
	 * 获取：订单编号集合[aa,bbb]
	 */
	public String getOrderNos() {
		return orderNos;
	}
}
