package com.bs.service;

import com.bs.pojo.User;

import java.util.List;
import java.util.Map;

public interface userService extends BaseService<User> {
    User loginAdmin(String userName, String password);
    User loginCon(String userName, String password);
    List<Map<String,Object>> selectUsers();
    void deleteUser(int id);
    void resetPassword(int id);
    List<Map<String,Object>> selectUsersByuserName(String name);
    User selectRegisterUserName(String userName);
    void Register(User user);
    void ResetPassworSelf(int userId,String password);
    User selectAUser(int id);
}
