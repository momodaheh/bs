package com.bs.service;

import com.bs.pojo.address;
import com.bs.pojo.goods;

import java.util.List;
import java.util.Map;

public interface goodsService extends BaseService<goods>{
    void addGoods(goods good);
    List<Map<String,Object>> selectGoods();
    void deleteGood(int id);
    List<Map<String,Object>> selectGoodsPhone();
    List<Map<String,Object>> selectGoodsCom();
    List<Map<String,Object>> selectGoodsByCategory(int id);
    List<Map<String,Object>> selectGoodsByName(String name);

    Map<String,Object> selectGoodsById(int id);
}
