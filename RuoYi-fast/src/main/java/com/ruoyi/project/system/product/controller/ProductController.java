package com.ruoyi.project.system.product.controller;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.product.domain.Category;
import com.ruoyi.project.system.product.domain.Product;
import com.ruoyi.project.system.product.service.ICategoryService;
import com.ruoyi.project.system.product.service.IProductService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 产品 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-02-27
 */
@Controller
@RequestMapping("/system/product")
public class ProductController extends BaseController
{
    private String prefix = "system/product";
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICategoryService categoryService;
	
	
	@RequiresPermissions("system:product:view")
	@GetMapping()
	public String product()
	{
	    return prefix + "/product";
	}
	
	/**
	 * 查询产品列表
	 */
	@RequiresPermissions("system:product:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Product product)
	{
		startPage();
        List<Product> list = productService.selectProductList(product);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出产品列表
	 */
	@RequiresPermissions("system:product:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Product product)
    {
    	List<Product> list = productService.selectProductList(product);
        ExcelUtil<Product> util = new ExcelUtil<Product>(Product.class);
        return util.exportExcel(list, "product");
    }
	
	/**
	 * 新增产品
	 */
	@GetMapping("/add")
	public String add(HttpServletRequest request)
	{
		List<Category> categoryList = categoryService.selectCategoryList(null);
		request.setAttribute("categoryList",categoryList);
		return prefix + "/add";
	}
	
	/**
	 * 新增保存产品
	 */
	@RequiresPermissions("system:product:add")
	@Log(title = "产品", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Product product)
	{		
		product.setCreateTime(new Date());
		return toAjax(productService.insertProduct(product));
	}

	/**
	 * 修改产品
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{	List<Category> categoryList = categoryService.selectCategoryList(null);
		Product product = productService.selectProductById(id);
		mmap.put("product", product);
		mmap.put("categoryList", categoryList);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存产品
	 */
	@RequiresPermissions("system:product:edit")
	@Log(title = "产品", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Product product)
	{		
		return toAjax(productService.updateProduct(product));
	}
	
	/**
	 * 删除产品 下架
	 */
	@RequiresPermissions("system:product:remove")
	@Log(title = "产品", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(productService.deleteProductByIds(ids));
	}
	
	
	/**
	 * 删除产品 下架
	 */
	@RequiresPermissions("system:product:out")
	@PostMapping( "/out")
	@ResponseBody
	public AjaxResult out(Integer id,Integer status)
	{		
		return toAjax(productService.out(id,status));
	}

	
}
