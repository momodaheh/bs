package com.bs.controller;

import com.bs.pojo.brand;
import com.bs.service.brandService;
import com.bs.util.ResultCode;
import com.bs.util.ResultEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*" , maxAge=3600)
@RestController
@RequestMapping("brand")
public class brandController {

    @Resource
    private brandService service;

    //查询所有的品牌
    @RequestMapping("selectBrands")
    public ResultEntity<List<brand>> selectBrands(){
        List<brand> brandList=service.SelectBrands();
        return ResultEntity.infor(ResultCode.SUCCESS,brandList);
    }

    //添加品牌
    @RequestMapping("addBrand")
    public ResultEntity addBrand(@RequestBody String brandName){
        service.addBrand(brandName);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    //删除品牌
    @RequestMapping("deleteBrand")
    public ResultEntity deleteBrand(@RequestBody String brandId){
        int id = Integer.parseInt(brandId);
        service.deleteBrands(id);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }
}
