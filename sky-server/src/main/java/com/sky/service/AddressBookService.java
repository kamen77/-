package com.sky.service;

import com.sky.entity.AddressBook;

import java.util.List;

public interface AddressBookService {

    /**
     * 查询当前登录用户的所有地址信息
     *
     * @return
     */
    List<AddressBook> list(AddressBook addressBook);


    /**
     * 新增地址
     * @param addressBook
     * @return
     */
    void save(AddressBook addressBook);

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    AddressBook getById(long id);

    /**
     * 根据id修改地址
     * @param
     * @return
     */
    void update(AddressBook addressBook);
}
