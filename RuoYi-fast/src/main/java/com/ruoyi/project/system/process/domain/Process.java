package com.ruoyi.project.system.process.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 工序表 pflm_process
 * 
 * @author ruoyi
 * @date 2019-02-21
 */
public class Process extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;
	/** 工票号 */
	@Excel(name = "工票号")
	private String ticketNo;
	/** 工序号 */
	@Excel(name = "工序号")
	private String processNo;
	/**  */
	@Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/** 员工号 */
	@Excel(name = "员工号")
	private String employeeNo;
	/** 公分 */
	@Excel(name = "工分")
	private Integer score;
	/** 备注 */
	@Excel(name = "备注")
	private String remark;



	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setProcessNo(String processNo) 
	{
		this.processNo = processNo;
	}

	public String getProcessNo() 
	{
		return processNo;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setEmployeeNo(String employeeNo) 
	{
		this.employeeNo = employeeNo;
	}

	public String getEmployeeNo() 
	{
		return employeeNo;
	}
	public void setScore(Integer score) 
	{
		this.score = score;
	}

	public Integer getScore() 
	{
		return score;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setTicketNo(String ticketNo) 
	{
		this.ticketNo = ticketNo;
	}

	public String getTicketNo() 
	{
		return ticketNo;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("processNo", getProcessNo())
            .append("createTime", getCreateTime())
            .append("employeeNo", getEmployeeNo())
            .append("score", getScore())
            .append("remark", getRemark())
            .append("ticketNo", getTicketNo())
            .toString();
    }
}
