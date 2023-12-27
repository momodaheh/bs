package com.bs.controller;

import com.bs.pojo.category;
import com.bs.service.categoryService;
import com.bs.util.ResultCode;
import com.bs.util.ResultEntity;
import org.apache.ibatis.annotations.Insert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*" , maxAge=3600)
@RestController
@RequestMapping("category")
public class categoryController {

    @Resource
    private categoryService service;

    //根据brandid查询category
    @RequestMapping("selectCategoryByBrandId")
    public ResultEntity<List<category>> selectCategoryByBrandId(@RequestBody String brandId){

        int id= Integer.parseInt(brandId);
        List<category> categoryList=service.selectCategoryByBrandId(id);
        return ResultEntity.infor(ResultCode.SUCCESS,categoryList);
    }

    //删除某品牌的某个分类
    @RequestMapping("deletebrand_Category")
    public ResultEntity deletebrand_Category(@RequestBody Map<String, Object> data){
        int brandId = (int) data.get("brandId");
        int categoryId = (int) data.get("categoryId");
        service.deletebrand_Category(brandId,categoryId);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    //查询所有分类
    @RequestMapping("selectCategorys")
    public ResultEntity<List<category>> selectCategorys(){
        List<category> categoryList =service.selectCategory();
        return ResultEntity.infor(ResultCode.SUCCESS,categoryList);
    }

    //添加品牌下的分类
    @RequestMapping("addbradn_Category")
    public ResultEntity addbradn_Category(@RequestBody Map<String, Object> data){
        int brandId = (int) data.get("brandId");
        int categoryId = (int) data.get("categoryId");
        service.addbradn_Category(brandId,categoryId);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    //添加分类
    @RequestMapping("addcategory")
    public ResultEntity addcategory(@RequestBody String categoryName){
        service.addcategory(categoryName);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    //删除分类
    @RequestMapping("deletecategory")
    public ResultEntity deletecategory(@RequestBody String categoryId){
        int id =Integer.parseInt(categoryId);
        service.deleteCategory(id);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }
}
