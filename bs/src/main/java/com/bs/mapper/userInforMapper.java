package com.bs.mapper;


import com.bs.pojo.userInfor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;


@Mapper
public interface userInforMapper extends BaseMapper<userInfor>{
    @Select("select * from user_infor where id=#{id}")
    userInfor selectUserInfor(int id) throws SQLException;

    @Update("update user_infor set telephone=#{telephone},sex=#{sex},nickName=#{nickName},email=#{email} where id=#{id}")
    void updateUserInfor(userInfor obj) throws SQLException;

    @Insert("insert into user_infor (id) values(#{id})")
    void addUserInfo(int id)throws SQLException;
}
