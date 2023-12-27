package com.bs.serviceImpl;

import com.bs.mapper.BaseMapper;

import com.bs.mapper.shoppingCarMapper;

import com.bs.pojo.shoppingCar;

import com.bs.service.shoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class shoppingCarServiceImpl extends BaseServiceImpl<shoppingCar> implements shoppingCarService {

    @Autowired
    private shoppingCarMapper mapper;

    @Override
    public BaseMapper<shoppingCar> getBaseMapper(){
        return mapper;
    }

    @Override
    public List<Map<String, Object>> selectShoppingCarById(int id) {
        try {
            return mapper.selectShoppingCarById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteShoppingCar(int id) {
        try {
             mapper.deleteShoppingCar(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addShoppingCar(shoppingCar car) {
        try {
            mapper.addShoppingCar(car);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public shoppingCar selectOne(int goodInfo) {
        try {
            return mapper.selectOne(goodInfo);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateShoppingCatAmount(int amount,int CarId) {
        try {
            mapper.updateShoppingCatAmount(amount,CarId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
