package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类管理
 */
@RestController("adminCategoryController")
@RequestMapping("/admin/category")
@Slf4j
@Api(tags = "分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     * @return
     */
    @PostMapping
    public Result save(@RequestBody CategoryDTO categoryDTO){
        log.info("新增分类：{}",categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> categoryPageQuery(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分类分页查询：{}",categoryPageQueryDTO);
        PageResult pageResult = categoryService.categoryPageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 分类启用禁用
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status,long id){
        log.info("分类启用禁用:{},{}",status,id);
        categoryService.startOrStop(status,id);
        return Result.success();
    }

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    @GetMapping("/list")
    public Result<List<Category>> list(Integer type){
        log.info("根据类型查询分类：{}",type);
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }

    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    public Result delete(long id){
        log.info("根据id删除分类:{}",id);
        categoryService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改分类
     * @param categoryDTO
     * @return
     */
    @PutMapping
    public Result update(@RequestBody CategoryDTO categoryDTO){
        log.info("修改分类：{}",categoryDTO);
        categoryService.update(categoryDTO);
        return Result.success();
    }

}
