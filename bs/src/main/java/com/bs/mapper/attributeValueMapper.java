package com.bs.mapper;


import com.bs.pojo.attributeValue;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface attributeValueMapper extends BaseMapper<attributeValue> {

    @Select("select * from attribute_value where attribute_id=#{attribute_id}")
    List<attributeValue> selectAttributeValueById(int attribute_id) throws SQLException;

    @Insert("insert into attribute_value(attribute_id,attribute_value) values(#{attribute_id},#{attributeValue})")
    void addAttributeValue(int attribute_id,String attributeValue) throws SQLException;


    @Delete("delete from attribute_value where id=#{id}")
    void deleteAttributeValue(int id) throws SQLException;
}
