package com.bs.service;


import com.bs.pojo.userInfor;

public interface userInforService extends BaseService<userInfor> {
    userInfor selectUserInfor(int id);//查询用户基本信息
    void updateUserInfor(userInfor obj);
    //修改用户的基本信息

    void addUserInfo(int id);

}
