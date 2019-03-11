package com.ruoyi.project.system.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.system.product.mapper.ProductMapper;
import com.ruoyi.project.system.product.domain.Product;
import com.ruoyi.project.system.product.service.IProductService;
import com.ruoyi.common.support.Convert;

/**
 * 产品 服务层实现
 * 
 * @author ruoyi
 * @date 2019-02-27
 */
@Service
public class ProductServiceImpl implements IProductService 
{
	@Autowired
	private ProductMapper productMapper;

	/**
     * 查询产品信息
     * 
     * @param id 产品ID
     * @return 产品信息
     */
    @Override
	public Product selectProductById(Integer id)
	{
	    return productMapper.selectProductById(id);
	}
	
	/**
     * 查询产品列表
     * 
     * @param product 产品信息
     * @return 产品集合
     */
	@Override
	public List<Product> selectProductList(Product product)
	{
	    return productMapper.selectProductList(product);
	}
	
    /**
     * 新增产品
     * 
     * @param product 产品信息
     * @return 结果
     */
	@Override
	public int insertProduct(Product product)
	{
	    return productMapper.insertProduct(product);
	}
	
	/**
     * 修改产品
     * 
     * @param product 产品信息
     * @return 结果
     */
	@Override
	public int updateProduct(Product product)
	{
	    return productMapper.updateProduct(product);
	}

	/**
     * 删除产品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteProductByIds(String ids)
	{
		return productMapper.deleteProductByIds(Convert.toStrArray(ids));
	}

	@Override
	public int out(Integer id, Integer status) {
		return productMapper.out(id, status);
	}
	
}
