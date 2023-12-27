package com.bs.mapper;


import com.bs.pojo.category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface categoryMapper extends BaseMapper<category>{

    @Select("SELECT c.id,c.category_name from brand_category bc join category c on bc.categoryId =c.id join brand b on bc.brandId=b.id\n" +
            "WHERE bc.brandId=#{id}")
    List<category> selectCategoryByBrandId(int id) throws SQLException;

    @Delete("delete from brand_category where brandId=#{brandId} and categoryId=#{categoryId}")
    void deletebrand_Category(int brandId,int categoryId) throws SQLException;

    @Select("select * from category")
     List<category> selectCategory() throws SQLException;

    @Delete("insert into brand_category (brandId,categoryId) values (#{brandId},#{categoryId})")
    void addbrand_Category(int brandId,int categoryId) throws SQLException;


    @Select("insert into category (category_name) values(#{name})")
    void addcategory(String name) throws SQLException;

    @Select("delete from category where id=#{categoryId}")
    void deletecategory(int categoryId) throws SQLException;
}
