package com.bs.controller;


import com.bs.pojo.address;
import com.bs.pojo.goods;
import com.bs.service.goodsService;
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
@RequestMapping("goods")
public class goodsController {
    @Resource
    private goodsService service;

    @RequestMapping("addGoods")
    public ResultEntity addGoods(@RequestBody goods goods ){

        service.addGoods(goods);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    @RequestMapping("selectGoods")
    public ResultEntity<List<Map<String, Object>>> selectGoods(){
        List<Map<String,Object>> goodsList=service.selectGoods();
        return ResultEntity.infor(ResultCode.SUCCESS,goodsList);
    }

    @RequestMapping("deleteGood")
    public ResultEntity deleteGood(@RequestBody String goodid ){
        int id=Integer.parseInt(goodid);
        service.deleteGood(id);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    @RequestMapping("selectGoodsByCategory")
    public ResultEntity<List<Map<String, Object>>> selectGoodsByCategory(@RequestBody String categoryId){
        int id =Integer.parseInt(categoryId);
        List<Map<String,Object>> goodsList=service.selectGoodsByCategory(id);
        return ResultEntity.infor(ResultCode.SUCCESS,goodsList);
    }

    @RequestMapping("selectGoodsPhone")
    public ResultEntity<List<Map<String, Object>>> selectGoodsPhone(){
        List<Map<String,Object>> goodsList=service.selectGoodsPhone();
        return ResultEntity.infor(ResultCode.SUCCESS,goodsList);
    }

    @RequestMapping("selectGoodsCom")
    public ResultEntity<List<Map<String, Object>>> selectGoodsCom(){
        List<Map<String,Object>> goodsList=service.selectGoodsCom();
        return ResultEntity.infor(ResultCode.SUCCESS,goodsList);
    }


    @RequestMapping("selectGoodsByName")
    public ResultEntity<List<Map<String, Object>>> selectGoodsByName(@RequestBody String name){
        List<Map<String,Object>> goodsList=service.selectGoodsByName(name);
        return ResultEntity.infor(ResultCode.SUCCESS,goodsList);
    }

    @RequestMapping("selectGoodsById")
    public ResultEntity<Map<String,Object>> selectGoodsById(@RequestBody String goodId){
        int id=Integer.parseInt(goodId);
        Map<String,Object> good=service.selectGoodsById(id);
        return ResultEntity.infor(ResultCode.SUCCESS,good);
    }
}
