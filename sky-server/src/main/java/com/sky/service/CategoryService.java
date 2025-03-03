package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {

    /**
     * 新增分类
     */
    void save(CategoryDTO categoryDTO);

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult categoryPageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 分类启用禁用
     * @param status
     * @param id
     * @return
     */
    void startOrStop(Integer status, long id);

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    List<Category> list(Integer type);

    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    void deleteById(long id);

    /**
     * 修改分类
     * @param categoryDTO
     * @return
     */
    void update(CategoryDTO categoryDTO);
}
