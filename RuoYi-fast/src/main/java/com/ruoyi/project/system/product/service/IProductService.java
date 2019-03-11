package com.ruoyi.project.system.product.service;

import com.ruoyi.project.system.product.domain.Product;

import java.util.List;

/**
 * 产品 服务层
 * 
 * @author ruoyi
 * @date 2019-02-27
 */
public interface IProductService 
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
     * 删除产品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProductByIds(String ids);
	
	public int out(Integer id,Integer status);
	
}
