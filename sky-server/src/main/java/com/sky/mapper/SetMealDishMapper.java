package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface SetMealDishMapper {


    /**
     * 根据菜品id查询对应的套餐id
     * @param
     * @return
     */
    List<Long> getSetMealIdsByDishIds(List<Long> dishIds);


    /**
     *  保存套餐和菜品的关联关系
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * 删除套餐菜品关系表中的数据
     * @param setMealId
     */
    @Delete("delete from setmeal_dish where setmeal_id = #{setMealId}")
    void deleteBySetMealId(Long setMealId);

    /**
     * 根据套餐id查询套餐和菜品的关联关系
     * @param
     * @return
     */
    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> getBySetMealId(long setmealId);

}
