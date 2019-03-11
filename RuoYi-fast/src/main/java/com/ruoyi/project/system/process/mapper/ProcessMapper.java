package com.ruoyi.project.system.process.mapper;

import com.ruoyi.project.system.process.domain.Process;
import java.util.List;	

/**
 * 工序 数据层
 * 
 * @author ruoyi
 * @date 2019-02-21
 */
public interface ProcessMapper 
{
	/**
     * 查询工序信息
     * 
     * @param id 工序ID
     * @return 工序信息
     */
	public Process selectProcessById(Integer id);
	
	/**
     * 查询工序列表
     * 
     * @param process 工序信息
     * @return 工序集合
     */
	public List<Process> selectProcessList(Process process);
	
	/**
     * 新增工序
     * 
     * @param process 工序信息
     * @return 结果
     */
	public int insertProcess(Process process);
	
	/**
     * 修改工序
     * 
     * @param process 工序信息
     * @return 结果
     */
	public int updateProcess(Process process);
	
	/**
     * 删除工序
     * 
     * @param id 工序ID
     * @return 结果
     */
	public int deleteProcessById(Integer id);
	
	/**
     * 批量删除工序
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProcessByIds(String[] ids);

	/**
	 * 工分统计
	 * @param process
	 * @return
	 */
	public List<Process> selectProcessStatistics(Process process);

}