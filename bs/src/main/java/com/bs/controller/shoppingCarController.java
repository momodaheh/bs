package com.bs.controller;


import com.bs.pojo.shoppingCar;
import com.bs.service.shoppingCarService;
import com.bs.util.ResultCode;
import com.bs.util.ResultEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*" , maxAge=3600)
@RestController
@RequestMapping("shoppingCar")
public class shoppingCarController {
    @Resource
    private shoppingCarService service;

    @RequestMapping("selectShoppingCarById")
    public ResultEntity<List<Map<String, Object>>> selectShoppingCarById(@RequestBody String userId){
        int id =Integer.parseInt(userId);
        List<Map<String,Object>> goodsList=service.selectShoppingCarById(id);
        return ResultEntity.infor(ResultCode.SUCCESS,goodsList);
    }

    @RequestMapping("deleteShoppingCar")
    public ResultEntity deleteShoppingCar(@RequestBody String carId){
        int id =Integer.parseInt(carId);
        service.deleteShoppingCar(id);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    @RequestMapping("addShoppingCar")
    public ResultEntity addShoppingCar(@RequestBody shoppingCar shoppingCar){
        shoppingCar car=service.selectOne(shoppingCar.getGoodInfoId());
        if(car==null){
            service.addShoppingCar(shoppingCar);
        }else {
            int amount=car.getAmount()+shoppingCar.getAmount();
            service.updateShoppingCatAmount(amount,car.getId());
        }
        return ResultEntity.infor(ResultCode.SUCCESS);
    }
}
