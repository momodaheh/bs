package com.bs.controller;

import com.bs.pojo.address;
import com.bs.pojo.order;
import com.bs.service.orderService;
import com.bs.util.ResultCode;
import com.bs.util.ResultEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*" , maxAge=3600)
@RestController
@RequestMapping("order")
public class orderController {
    @Resource
    private orderService service;

    @RequestMapping("selectNopay")
    public ResultEntity<List<Map<String, Object>>> selectNopay(@RequestBody String userId){
        int id =Integer.parseInt(userId);
        List<Map<String, Object>> nopayList =service.selectNopay(id);
        return ResultEntity.infor(ResultCode.SUCCESS,nopayList);
    }

    @RequestMapping("selectOrders")
    public ResultEntity<List<Map<String, Object>>> selectOrders(){
        List<Map<String, Object>> nopayList =service.selectOrders();
        return ResultEntity.infor(ResultCode.SUCCESS,nopayList);
    }

    @RequestMapping("deleteOrder")
    public ResultEntity deleteOrder(@RequestBody String orderId){
        int id =Integer.parseInt(orderId);
        service.deleteOrder(id);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    @RequestMapping("selectOrderInfo")
    public ResultEntity<Map<String, Object>> selectOrderInfo(@RequestBody Map<String, Object> obj){
        Map<String, Object> orderInfo =service.selectOrderInfo(obj);
        return ResultEntity.infor(ResultCode.SUCCESS,orderInfo);
    }

    @RequestMapping("addOrder")
    public ResultEntity deleteOrder(@RequestBody Map<String, Object> obj){

        service.addOrder(obj);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    @RequestMapping("selectOrdersStateOne")
    public ResultEntity<List<Map<String, Object>>> selectOrdersStateOne(){
        List<Map<String, Object>> nopayList =service.selectOrdersStateOne();
        return ResultEntity.infor(ResultCode.SUCCESS,nopayList);
    }

    @RequestMapping("fahuo")
    public ResultEntity fahuo(@RequestBody Map<String, Object> data){
        int id = (int) data.get("id");
        String logisticsId = (String) data.get("logisticsId");
        service.fahuo(id,logisticsId);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    @RequestMapping("selectNoReceipt")
    public ResultEntity<List<Map<String, Object>>> selectNoReceipt(@RequestBody String userId){
        int id=Integer.parseInt(userId);
        List<Map<String, Object>> nopayList =service.selectNoReceipt(id);
        return ResultEntity.infor(ResultCode.SUCCESS,nopayList);
    }

    @RequestMapping("selectAll")
    public ResultEntity<List<Map<String, Object>>> selectAll(@RequestBody String userId){
        int id=Integer.parseInt(userId);
        List<Map<String, Object>> nopayList =service.selectAll(id);
        return ResultEntity.infor(ResultCode.SUCCESS,nopayList);
    }

    @RequestMapping("selectDailyPrice")
    public ResultEntity<List<Map<String, Object>>> selectDailyPrice(){

        List<Map<String, Object>> nopayList =service.selectDailyPrice();
        return ResultEntity.infor(ResultCode.SUCCESS,nopayList);
    }

    @RequestMapping("selectPriceByCategory")
    public ResultEntity<List<Map<String, Object>>> selectPriceByCategory(){

        List<Map<String, Object>> nopayList =service.selectPriceByCategory();
        return ResultEntity.infor(ResultCode.SUCCESS,nopayList);
    }

    @RequestMapping("selectGoodsByPriceTop")
    public ResultEntity<List<Map<String, Object>>> selectGoodsByPriceTop(){

        List<Map<String, Object>> nopayList =service.selectGoodsByPriceTop();
        return ResultEntity.infor(ResultCode.SUCCESS,nopayList);
    }

    @RequestMapping("Shouhuo")
    public ResultEntity Shouhuo(@RequestBody String orderId){
        int id=Integer.parseInt(orderId);
        service.Shouhuo(id);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    @RequestMapping("selectFinsh")
    public ResultEntity<List<Map<String, Object>>> selectFinsh(@RequestBody String userId){
        int id=Integer.parseInt(userId);
        List<Map<String, Object>> nopayList =service.selectFinsh(id);
        return ResultEntity.infor(ResultCode.SUCCESS,nopayList);
    }

    @RequestMapping("noFa")
    public ResultEntity<List<Map<String, Object>>> noFa(@RequestBody String userId){
        int id=Integer.parseInt(userId);
        List<Map<String, Object>> nopayList =service.noFa(id);
        return ResultEntity.infor(ResultCode.SUCCESS,nopayList);
    }
}
