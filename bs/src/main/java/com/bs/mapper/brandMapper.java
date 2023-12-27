package com.bs.mapper;

import com.bs.pojo.brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface brandMapper extends BaseMapper<brand> {
    @Select("select * from brand")
    List<brand> selectBrands() throws SQLException;//查询所有品牌

    @Insert("Insert into brand (brand) values (#{brandName})")
    void  addBrand(String brandName) throws SQLException;//添加品牌

    @Delete("Delete from brand where id=#{brandId}")
    void deleteBrand(int brandId) throws SQLException;//删除品牌
}
