package com.bs.controller;

import com.bs.pojo.address;
import com.bs.pojo.goodsInfo;
import com.bs.pojo.order;
import com.bs.service.orderService;
import com.bs.service.goodsInfoService;
import com.bs.util.ResultCode;
import com.bs.util.ResultEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*" , maxAge=3600)
@RestController
@RequestMapping("order")
public class orderController {
    @Resource
    private orderService service;

    @Resource
    private goodsInfoService goodsInfoService;

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
        service.deleteOrder(orderId);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    @RequestMapping("selectOrderInfo")
    public ResultEntity<Map<String, Object>> selectOrderInfo(@RequestBody Map<String, Object> obj){
        Map<String, Object> orderInfo =service.selectOrderInfo(obj);
        return ResultEntity.infor(ResultCode.SUCCESS,orderInfo);
    }

    @RequestMapping("addOrder")
    public ResultEntity addOrder(@RequestBody Map<String, Object> obj){

        // 生成订单号
        long timestamp = System.currentTimeMillis() / 1000L;
        Date date = new Date(timestamp * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String datetimePart = sdf.format(date);
        int randomPart = (int) (Math.random() * 900) + 100; // [100, 999]
        String orderId = datetimePart + randomPart ;
        // 将订单号添加到Map对象中
        obj.put("id", orderId);
        Integer goodInfoId = (Integer) obj.get("goodInfoId");
        Integer amount = (Integer) obj.get("amount");
        goodsInfo goodsInfo=goodsInfoService.selectById(goodInfoId);
        if(amount>goodsInfo.getNumber()){
            return ResultEntity.infor(ResultCode.ERROR);
        }else {
            service.addOrder(obj);
            goodsInfoService.updateInfoNumber(goodInfoId,goodsInfo.getNumber()-amount);
            return ResultEntity.infor(ResultCode.SUCCESS);
        }

    }

    @RequestMapping("selectOrdersStateOne")
    public ResultEntity<List<Map<String, Object>>> selectOrdersStateOne(){
        List<Map<String, Object>> nopayList =service.selectOrdersStateOne();
        return ResultEntity.infor(ResultCode.SUCCESS,nopayList);
    }

    @RequestMapping("fahuo")
    public ResultEntity fahuo(@RequestBody Map<String, Object> data){
        String id = (String) data.get("id");
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
        service.Shouhuo(orderId);
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

    @RequestMapping("selectOrdresById")
    public ResultEntity<List<Map<String, Object>>> selectOrdresById(@RequestBody String id){
        List<Map<String, Object>> nopayList =service.selectOrdresById(id);
        return ResultEntity.infor(ResultCode.SUCCESS,nopayList);
    }

    @RequestMapping("selectOrdresStateOneById")
    public ResultEntity<List<Map<String, Object>>> selectOrdresStateOneById(@RequestBody String id){
        List<Map<String, Object>> nopayList =service.selectOrdresStateOneById(id);
        return ResultEntity.infor(ResultCode.SUCCESS,nopayList);
    }
}
