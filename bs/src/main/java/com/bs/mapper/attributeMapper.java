package com.bs.mapper;


import com.bs.pojo.attribute;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface attributeMapper extends BaseMapper<attribute>{
    @Select("select * from attribute_key where category_id=#{categoryId}")
    List<attribute> selectAttributeByCategoryId(int categoryId) throws SQLException;

    @Insert("insert into attribute_key (category_id,attribute_name) values(#{categoryId},#{attributeName})")
    void addAttribute(int categoryId,String attributeName) throws SQLException;

    @Delete("delete from attribute_key where id=#{id}")
    void deleteAttribute(int id) throws SQLException;
}
