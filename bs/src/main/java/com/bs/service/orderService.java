package com.bs.service;


import com.bs.pojo.goods;
import com.bs.pojo.order;

import java.util.List;
import java.util.Map;

public interface orderService extends BaseService<order>{
    order selectOrderbyId(String id);
    List<Map<String,Object>> selectOrders();
    void addOrder(Map<String,Object> obj);
    void orderPay(String id,String payId);
    void deleteOrder(String id);
    List<Map<String,Object>> selectNopay(int id);
    Map<String,Object> selectOrderInfo(Map<String,Object> obj);
    goods selectgood(int id);
    List<Map<String,Object>> selectOrdersStateOne();
    void fahuo(String id,String logisticsId );

    List<Map<String,Object>> selectNoReceipt(int id);

    List<Map<String,Object>> selectAll(int id);

    List<Map<String,Object>> selectDailyPrice();

    List<Map<String,Object>> selectPriceByCategory();

    List<Map<String,Object>> selectGoodsByPriceTop();

    void Shouhuo(String id);

    List<Map<String,Object>> selectFinsh(int id);

    List<Map<String,Object>> noFa(int id);

    List<Map<String,Object>> selectOrdresById(String id);

    List<Map<String,Object>> selectOrdresStateOneById(String id);
}
