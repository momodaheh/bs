package com.bs.mapper;


import com.bs.pojo.goods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface goodsMapper extends BaseMapper<goods>{

    @Insert("insert into goods (goodsName,brandId,categoryId,imgShow1,imgShow2,imgShow3,imgDetail,isDel)values(#{goodsName},#{brandId},#{categoryId},#{imgShow1},#{imgShow2},#{imgShow3},#{imgDetail},0)")
    void addGoods(goods good) throws SQLException;

    @Select("select g.*,b.brand,c.category_name from goods g \n" +
            "\tjoin brand b on g.brandId=b.id \n" +
            "\tjoin category c on g.categoryId = c.id\n" +
            "\twhere g.isDel=0")
    List<Map<String,Object>> selectGoods() throws SQLException;

    @Update("update goods set isDel=1 where id=#{id}")
    void deleteGood(int id) throws SQLException;

    @Select("select g.*,b.brand,c.category_name from goods g \n" +
            "\tjoin brand b on g.brandId=b.id \n" +
            "\tjoin category c on g.categoryId = c.id\n" +
            "\twhere g.isDel=0 and categoryId=#{id} ")
    List<Map<String,Object>> selectGoodsByCategory(int id) throws SQLException;

    @Select("SELECT g.*, b.brand, c.category_name, MIN(gi.price) as minprice\n" +
            "FROM goods g\n" +
            "JOIN brand b ON g.brandId = b.id\n" +
            "JOIN category c ON g.categoryId = c.id\n" +
            "JOIN good_info gi ON g.id = gi.goodsId\n" +
            "WHERE g.isDel = 0 AND categoryId = 1\n" +
            "GROUP BY g.id")
    List<Map<String,Object>> selectGoodsPhone() throws SQLException;

    @Select("SELECT g.*, b.brand, c.category_name, MIN(gi.price) as minprice\n" +
            "FROM goods g\n" +
            "JOIN brand b ON g.brandId = b.id\n" +
            "JOIN category c ON g.categoryId = c.id\n" +
            "JOIN good_info gi ON g.id = gi.goodsId\n" +
            "WHERE g.isDel = 0 AND categoryId = 2\n" +
            "GROUP BY g.id")
    List<Map<String,Object>> selectGoodsCom()throws SQLException;

    @Select("SELECT g.*, b.brand, c.category_name, MIN(gi.price) as minprice\n" +
            "FROM goods g\n" +
            "JOIN brand b ON g.brandId = b.id\n" +
            "JOIN category c ON g.categoryId = c.id\n" +
            "JOIN good_info gi ON g.id = gi.goodsId\n" +
            "WHERE g.isDel = 0 AND categoryId = 3\n" +
            "GROUP BY g.id")
    List<Map<String,Object>> selectGoodsWatch() throws SQLException;
    @Select("select g.*,b.brand,c.category_name ,MIN(gi.price) as minprice from goods g \n" +
            "join brand b on g.brandId=b.id \n" +
            "join category c on g.categoryId = c.id\n" +
            "join good_info gi on g.id=gi.goodsId\n" +
            "where g.isDel=0 and goodsName like CONCAT('%', #{name},'%') \n" +
            "GROUP BY g.id")
    List<Map<String,Object>> selectGoodsByName(String name) throws SQLException;

    @Select("select g.*,b.brand,c.category_name from goods g \n" +
            "\tjoin brand b on g.brandId=b.id \n" +
            "\tjoin category c on g.categoryId = c.id\n" +
            "\twhere g.id=#{id} ")
    Map<String,Object> selectGoodsById(int id) throws SQLException;

    @Select("SELECT g.*, b.brand, c.category_name, MIN(gi.price) as minprice\n" +
            "FROM goods g\n" +
            "JOIN brand b ON g.brandId = b.id\n" +
            "JOIN category c ON g.categoryId = c.id\n" +
            "JOIN good_info gi ON g.id = gi.goodsId\n" +
            "WHERE g.isDel = 0 AND c.id = #{categoryId} AND b.id=#{BrandId}\n" +
            "GROUP BY g.id")
    List<Map<String,Object>> selectByBrandCotegory(int BrandId,int categoryId)throws SQLException;
}
