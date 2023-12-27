package com.bs.controller;


import com.bs.pojo.address;
import com.bs.pojo.goodsInfo;
import com.bs.service.goodsInfoService;
import com.bs.util.ResultCode;
import com.bs.util.ResultEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*" , maxAge=3600)
@RestController
@RequestMapping("goodsInfo")
public class goodsInfoController {

    @Resource
    private goodsInfoService service;

    @RequestMapping("selectGoodsInfoById")
    public ResultEntity<List<goodsInfo>> selectGoodsInfoById(@RequestBody String goodId){
        int id =Integer.parseInt(goodId);
        List<goodsInfo> goodsInfoList =service.selectGoodsInfoById(id);
        return ResultEntity.infor(ResultCode.SUCCESS,goodsInfoList);
    }

    @RequestMapping("deleteGoodsInfo")
    public ResultEntity deleteGoodsInfo(@RequestBody String goodId){
        int id =Integer.parseInt(goodId);
        service.deleteGoodsInfo(id);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    @RequestMapping("addGoodsInfo")
    public ResultEntity addGoodsInfo(@RequestBody goodsInfo goodsInfo){
        service.addGoodsInfo(goodsInfo);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    @RequestMapping("updateGoodsInfo")
    public ResultEntity updateGoodsInfo(@RequestBody goodsInfo goodsInfo){
        service.updateGoodsInfo(goodsInfo);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }
}
