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
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.product.domain.Category;
import com.ruoyi.project.system.product.service.ICategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 商品分类 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-03-05
 */
@Controller
@RequestMapping("/system/category")
public class CategoryController extends BaseController
{
    private String prefix = "system/category";
	
	@Autowired
	private ICategoryService categoryService;
	
	@RequiresPermissions("system:category:view")
	@GetMapping()
	public String category()
	{
	    return prefix + "/category";
	}
	
	/**
	 * 查询商品分类列表
	 */
	@RequiresPermissions("system:category:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Category category)
	{
		startPage();
        List<Category> list = categoryService.selectCategoryList(category);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商品分类列表
	 */
	@RequiresPermissions("system:category:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Category category)
    {
    	List<Category> list = categoryService.selectCategoryList(category);
        ExcelUtil<Category> util = new ExcelUtil<Category>(Category.class);
        return util.exportExcel(list, "category");
    }
	
	/**
	 * 新增商品分类
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品分类
	 */
	@RequiresPermissions("system:category:add")
	@Log(title = "商品分类", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Category category)
	{		
		category.setCreateTime(new Date());
		return toAjax(categoryService.insertCategory(category));
	}

	/**
	 * 修改商品分类
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Category category = categoryService.selectCategoryById(id);
		mmap.put("category", category);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品分类
	 */
	@RequiresPermissions("system:category:edit")
	@Log(title = "商品分类", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Category category)
	{
		category.setCreateTime(new Date());
		return toAjax(categoryService.updateCategory(category));
	}
	
	/**
	 * 删除商品分类
	 */
	@RequiresPermissions("system:category:remove")
	@Log(title = "商品分类", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(categoryService.deleteCategoryByIds(ids));
	}
	
}
