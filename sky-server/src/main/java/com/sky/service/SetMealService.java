package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetMealService {

    /**
     * 新增套餐
     * @param setmealDTO
     * @return
     */
    void saveWithDish(SetmealDTO setmealDTO);

    /**
     * 套餐分页查询
     * @param
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 批量删除套餐
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 修改套餐
     * @param setmealDTO
     * @return
     */
    void update(SetmealDTO setmealDTO);

    /**
     * 根据id查询套餐，用于回显数据
     * @param id
     * @return
     */
    SetmealVO getByIdWithDish(long id);


    /**
     * 套餐起售停售
     * @param id
     * @return
     */
    void startOrStop(long id, Integer status);

    /**
     * 条件查询
     * @param
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据套餐id查询包含的菜品列表
     * @param id
     * @return
     */
    List<DishItemVO> getDishtemById(Long id);
}
