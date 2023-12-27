package com.bs.mapper;


import com.bs.pojo.shoppingCar;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface shoppingCarMapper extends BaseMapper<shoppingCar> {

    @Select("select s.*,g.*,gi.* from shoppingcar s join goods g on g.id=s.goodId\n" +
            "join good_info gi on gi.id=s.goodInfoId\n" +
            "where s.userId=#{id}")
    List<Map<String,Object>> selectShoppingCarById(int id) throws SQLException;

    @Delete("delete from shoppingcar where id=#{id} ")
    void deleteShoppingCar(int id) throws SQLException;

    @Insert("insert into shoppingcar (userId,goodInfoId,amount,goodId) values(#{userId},#{goodInfoId},#{amount},#{goodId})")
    void addShoppingCar(shoppingCar car) throws SQLException;

    @Select("select * from shoppingcar where goodInfoId=#{goodInfo}")
    shoppingCar selectOne(int goodInfo) throws SQLException;

    @Update("update shoppingcar set amount=#{amount} where id=#{CarId}")
    void updateShoppingCatAmount(int amount,int CarId) throws SQLException;
}
