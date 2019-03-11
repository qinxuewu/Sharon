package com.ruoyi.project.system.product.mapper;

import com.ruoyi.project.system.product.domain.Product;

import java.util.List;	

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 产品 数据层
 * 
 * @author ruoyi
 * @date 2019-02-27
 */
public interface ProductMapper 
{
	/**
     * 查询产品信息
     * 
     * @param id 产品ID
     * @return 产品信息
     */
	public Product selectProductById(Integer id);
	
	/**
     * 查询产品列表
     * 
     * @param product 产品信息
     * @return 产品集合
     */
	public List<Product> selectProductList(Product product);
	
	/**
     * 新增产品
     * 
     * @param product 产品信息
     * @return 结果
     */
	public int insertProduct(Product product);
	
	/**
     * 修改产品
     * 
     * @param product 产品信息
     * @return 结果
     */
	public int updateProduct(Product product);
	
	/**
     * 删除产品
     * 
     * @param id 产品ID
     * @return 结果
     */
	public int deleteProductById(Integer id);
	
	/**
     * 批量删除产品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProductByIds(String[] ids);
	
	/**
     * 上下架
     * 
     * @param id 产品ID
     * @param type
     * @return 结果
     */
	 @Update("update pflm_product  set status=#{status} where id=#{id}")
	 int out(@Param("id")Integer id,@Param("status") Integer status);
}