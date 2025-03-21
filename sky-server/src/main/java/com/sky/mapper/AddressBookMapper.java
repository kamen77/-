package com.sky.mapper;

import com.sky.entity.AddressBook;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressBookMapper {

    /**
     * 条件查询
     * @param addressBook
     * @return
     */
    List<AddressBook> list(AddressBook addressBook);

    /**
     * 新增地址
     * @param addressBook
     * @return
     */
    @Insert("insert into address_book " +
            "(user_id, consignee, sex, phone, province_code, province_name, city_code, " +
            "city_name, district_code, district_name, detail, label) VALUES " +
            "(#{userId},#{consignee},#{sex},#{phone},#{provinceCode},#{provinceName},#{cityCode},#{cityName}" +
            ",#{districtCode},#{districtName},#{detail},#{label})")
    void save(AddressBook addressBook);
}
