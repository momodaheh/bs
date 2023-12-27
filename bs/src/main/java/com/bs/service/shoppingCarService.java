package com.bs.service;


import com.bs.pojo.shoppingCar;

import java.util.List;
import java.util.Map;

public interface shoppingCarService extends BaseService<shoppingCar>{
    List<Map<String,Object>> selectShoppingCarById(int id);
    void deleteShoppingCar(int id);
    void addShoppingCar(shoppingCar car);
    shoppingCar selectOne(int goodInfo);
    void updateShoppingCatAmount(int amount,int CarId);
}
