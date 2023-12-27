package com.bs.service;

import com.bs.pojo.address;

import java.util.List;

public interface addressService extends BaseService<address>{
    List<address> selectUserAddress(int userId);//查询用户的收货地址
    void addAddress(address address);//添加收获地址
    void deleteAddress(int id);//删除收获地址
    void updateAddress(address address);//修改收获地址
}
