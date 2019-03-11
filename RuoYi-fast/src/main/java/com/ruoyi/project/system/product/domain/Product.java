package com.ruoyi.project.system.product.domain;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 产品表 pflm_product
 * 
 * @author ruoyi
 * @date 2019-02-27
 */
public class Product extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 创建日期 */
	private Date createTime;
	/** 商品标题 */
	private String title;
	/** 现价 */
	private String newprice;
	/** 原价 */
	private String oldprice;
	/** 详情 */
	private String details;
	/** 商品logo */
	private String img;

	private Integer status;
	
	private Integer cid;
	private String category;
	private  Integer recommend;
	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getTitle() 
	{
		return title;
	}
	public void setNewprice(String newprice) 
	{
		this.newprice = newprice;
	}

	public String getNewprice() 
	{
		return newprice;
	}
	public void setOldprice(String oldprice) 
	{
		this.oldprice = oldprice;
	}

	public String getOldprice() 
	{
		return oldprice;
	}
	public void setDetails(String details) 
	{
		this.details = details;
	}

	public String getDetails() 
	{
		return details;
	}
	public void setImg(String img) 
	{
		this.img = img;
	}

	public String getImg() 
	{
		return img;
	}



	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("createTime", getCreateTime())
				.append("title", getTitle())
				.append("newprice", getNewprice())
				.append("oldprice", getOldprice())
				.append("details", getDetails())
				.append("img", getImg())
				.append("status", getStatus())
				.append("cid", getCid())
				.append("category", getCategory())
				.append("recommend", getRecommend())
				.toString();
	}
}
