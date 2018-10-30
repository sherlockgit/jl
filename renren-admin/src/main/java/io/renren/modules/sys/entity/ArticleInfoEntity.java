package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-27 11:35:10
 */
@TableName("article_info")
public class ArticleInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 文章编号
	 */
	private String articleNo;
	/**
	 * 文章封面图
	 */
	@NotBlank(message="文章封面图不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String articlePic;
	/**
	 * 文章名称
	 */
	@NotBlank(message="文章标题不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String articleName;
	/**
	 * 文章分类
	 */
	private String articleType;
	/**
	 * 文章状态[0-上线, 1-下线， 2-新建]
	 */
	private String articleStatus;
	/**
	 * 文章标签[0-私募 1-财经 2-保险]
	 */
	private String articleTag;
	/**
	 * 文章作者
	 */
	@NotBlank(message="文章作者不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String articleAuthor;
	/**
	 * 外部引用地址
	 */
	private String articleHref;
	/**
	 * 文章简介
	 */
	private String articleBrief;
	/**
	 * 文章内容
	 */
	@NotBlank(message="文章内容不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String articleContent;
	/**
	 * 文章排序
	 */
	private String articleSort;
	/**
	 * 阅读数
	 */
	private String articleReads;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;
	/**
	 * 发布时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date publishTime;
	/**
	 * 下线时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date downTime;

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
	 * 设置：文章编号
	 */
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	/**
	 * 获取：文章编号
	 */
	public String getArticleNo() {
		return articleNo;
	}
	/**
	 * 设置：文章封面图
	 */
	public void setArticlePic(String articlePic) {
		this.articlePic = articlePic;
	}
	/**
	 * 获取：文章封面图
	 */
	public String getArticlePic() {
		return articlePic;
	}
	/**
	 * 设置：文章名称
	 */
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	/**
	 * 获取：文章名称
	 */
	public String getArticleName() {
		return articleName;
	}
	/**
	 * 设置：文章分类
	 */
	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}
	/**
	 * 获取：文章分类
	 */
	public String getArticleType() {
		return articleType;
	}
	/**
	 * 设置：文章状态[0-上线, 1-下线， 2-新建]
	 */
	public void setArticleStatus(String articleStatus) {
		this.articleStatus = articleStatus;
	}
	/**
	 * 获取：文章状态[0-上线, 1-下线， 2-新建]
	 */
	public String getArticleStatus() {
		return articleStatus;
	}
	/**
	 * 设置：文章标签[0-私募 1-财经 2-保险]
	 */
	public void setArticleTag(String articleTag) {
		this.articleTag = articleTag;
	}
	/**
	 * 获取：文章标签[0-私募 1-财经 2-保险]
	 */
	public String getArticleTag() {
		return articleTag;
	}
	/**
	 * 设置：文章作者
	 */
	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
	}
	/**
	 * 获取：文章作者
	 */
	public String getArticleAuthor() {
		return articleAuthor;
	}
	/**
	 * 设置：外部引用地址
	 */
	public void setArticleHref(String articleHref) {
		this.articleHref = articleHref;
	}
	/**
	 * 获取：外部引用地址
	 */
	public String getArticleHref() {
		return articleHref;
	}
	/**
	 * 设置：文章简介
	 */
	public void setArticleBrief(String articleBrief) {
		this.articleBrief = articleBrief;
	}
	/**
	 * 获取：文章简介
	 */
	public String getArticleBrief() {
		return articleBrief;
	}
	/**
	 * 设置：文章内容
	 */
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	/**
	 * 获取：文章内容
	 */
	public String getArticleContent() {
		return articleContent;
	}
	/**
	 * 设置：文章排序
	 */
	public void setArticleSort(String articleSort) {
		this.articleSort = articleSort;
	}
	/**
	 * 获取：文章排序
	 */
	public String getArticleSort() {
		return articleSort;
	}
	/**
	 * 设置：阅读数
	 */
	public void setArticleReads(String articleReads) {
		this.articleReads = articleReads;
	}
	/**
	 * 获取：阅读数
	 */
	public String getArticleReads() {
		return articleReads;
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
	 * 设置：发布时间
	 */
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	/**
	 * 获取：发布时间
	 */
	public Date getPublishTime() {
		return publishTime;
	}
	/**
	 * 设置：下线时间
	 */
	public void setDownTime(Date downTime) {
		this.downTime = downTime;
	}
	/**
	 * 获取：下线时间
	 */
	public Date getDownTime() {
		return downTime;
	}
}
