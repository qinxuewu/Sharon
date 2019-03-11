package com.ruoyi.project.system.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 广告banner表 pflm_banner
 * 
 * @author ruoyi
 * @date 2019-02-27
 */
public class Banner extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** banner名称 */
	private String name;
	/** 图片地址 */
	private String img;
	/** 创建日期 */
	private Date createTime;
	/** 商品编号 */
	private Integer pid;
	private Integer type;
	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setImg(String img) 
	{
		this.img = img;
	}

	public String getImg() 
	{
		return img;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setPid(Integer pid) 
	{
		this.pid = pid;
	}

	public Integer getPid() 
	{
		return pid;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("img", getImg())
            .append("createTime", getCreateTime())
            .append("pid", getPid())
				.append("type", getType())
            .toString();
    }
}
