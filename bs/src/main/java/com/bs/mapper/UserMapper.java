package com.bs.mapper;

import com.bs.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where userName=#{userName} AND password=#{password}")
    User loginAdmin(String userName, String password) throws SQLException;//管理员登录

    @Select("select * from user where userName=#{userName} AND password=#{password}")
    User loginCon(String userName, String password) throws SQLException;//用户登录

    @Select("select u.*,ui.* from user u join user_infor ui on u.id=ui.id where isDel=0")
    List<Map<String,Object>> selectUsers() throws SQLException;

    @Update("update user set isDel=1 where id=#{id}")
    void deleteUser(int id) throws SQLException;

    @Update("update user set password='123456' where id=#{id}")
    void resetPassword(int id) throws SQLException;

    @Select("select u.*,ui.* from user u join user_infor ui on u.id=ui.id where isDel=0 and userName like CONCAT('%', #{name},'%')")
    List<Map<String,Object>> selectUsersByuserName(String name)throws SQLException;

    @Select("select * from user where userName=#{userName}")
    User selectRegisterUserName(String userName)throws SQLException;

    @Insert("insert into user (userName,password,level,isDel)values(#{userName},#{password},1,0)")
    void Register(User user)throws SQLException;


    @Update("update user set password=#{password} where id=#{userId}")
    void ResetPassworSelf(int userId,String password)throws SQLException;

    @Select("select * from user where id=#{id}")
    User selectAUser(int id)throws SQLException;
}
