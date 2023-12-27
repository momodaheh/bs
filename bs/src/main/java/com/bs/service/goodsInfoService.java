package com.bs.service;


import com.bs.pojo.goodsInfo;

import java.util.List;

public interface goodsInfoService extends BaseService<goodsInfo>{
     List<goodsInfo> selectGoodsInfoById(int id);
     void deleteGoodsInfo(int id);
     void updateGoodsInfo(goodsInfo goodsInfo);
     void addGoodsInfo(goodsInfo goodsInfo);
}
