package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Employee;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetMealMapper setMealMapper;

    /**
     * 新增分类
     * @param
     */
    @Override
    public void save(CategoryDTO categoryDTO) {

        Category category = new Category();

        //对象属性拷贝
        BeanUtils.copyProperties(categoryDTO, category);

        //设置状态，1正常 0锁定
        category.setStatus(StatusConstant.ENABLE);

        //设置当前创建时间和修改时间
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        //设置当前创建记录人id和修改人id
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());

        //向数据库中插入数据 insert
        categoryMapper.insert(category);

    }

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @Override
    public PageResult categoryPageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {

        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());

        Page<Category> page =  categoryMapper.pageQuery(categoryPageQueryDTO);

//        long total = page.getTotal();
//        List<Category> records = page.getResult();
//        return new PageResult(total,records);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 分类启用禁用
     * @param status
     * @param id
     * @return
     */
    @Override
    public void startOrStop(Integer status, long id) {
        Category category = Category.builder()
                .status(status)
                .id(id)
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();

        categoryMapper.update(category);
    }

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    @Override
    public List<Category> list(Integer type) {
        return categoryMapper.list(type);
    }

    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @Override
    public void deleteById(long id) {
        Integer count = dishMapper.countByCategoryId(id);

        //查询当前分类是否关联了菜品，如果关联了就抛出业务异常
        if(count > 0){
            //当前分类下有菜品，不能删除
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }

        count = setMealMapper.countByCategoryId(id);
        //查询当前分类是否关联了套餐，如果关联了就抛出业务异常
        if(count > 0){
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
        }

        //删除分类数据
        categoryMapper.deleteById(id);
    }

    /**
     * 修改分类
     * @param categoryDTO
     * @return
     */
    @Override
    public void update(CategoryDTO categoryDTO) {

        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);

        category.setStatus(StatusConstant.ENABLE);
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.update(category);

    }
}
