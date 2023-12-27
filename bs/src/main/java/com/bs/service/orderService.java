package com.bs.service;


import com.bs.pojo.goods;
import com.bs.pojo.order;

import java.util.List;
import java.util.Map;

public interface orderService extends BaseService<order>{
    order selectOrderbyId(int id);
    List<Map<String,Object>> selectOrders();
    void addOrder(Map<String,Object> obj);
    void orderPay(int id,String payId);
    void deleteOrder(int id);
    List<Map<String,Object>> selectNopay(int id);
    Map<String,Object> selectOrderInfo(Map<String,Object> obj);
    goods selectgood(int id);
    List<Map<String,Object>> selectOrdersStateOne();
    void fahuo(int id,String logisticsId );

    List<Map<String,Object>> selectNoReceipt(int id);

    List<Map<String,Object>> selectAll(int id);

    List<Map<String,Object>> selectDailyPrice();

    List<Map<String,Object>> selectPriceByCategory();

    List<Map<String,Object>> selectGoodsByPriceTop();

    void Shouhuo(int id);

    List<Map<String,Object>> selectFinsh(int id);

    List<Map<String,Object>> noFa(int id);
}
