package com.bs.mapper;

import com.bs.pojo.address;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;


@Mapper
public interface addressMapper extends BaseMapper<address> {

    @Select("select * from address where userId=#{userId}")
    List<address> selectUserAddress(int userId) throws SQLException;//查询用户的收货地址

    @Insert("Insert into address (userId,contact,telephone,address) values(#{userId},#{contact},#{telephone},#{address})")
    void addAddress(address address) throws SQLException;//添加收获地址

    @Delete("delete from address where id=#{id}")
    void deleteAddress(int id) throws SQLException;//删除收获地址

    @Update("update address set contact=#{contact},telephone=#{telephone},address=#{address} where id=#{id}")
    void updateAddress(address address) throws SQLException;//修改收获地址
}
