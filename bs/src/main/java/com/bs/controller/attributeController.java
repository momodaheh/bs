package com.bs.controller;


import com.bs.pojo.attribute;
import com.bs.service.attributeService;
import com.bs.util.ResultCode;
import com.bs.util.ResultEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*" , maxAge=3600)
@RestController
@RequestMapping("attribute")
public class attributeController {
    @Resource
    private attributeService service;

    @RequestMapping("selectAttributeByCategoryId")
    public ResultEntity<List<attribute>>  selectAttributeByCategoryId(@RequestBody String categoryId){
        int id =Integer.parseInt(categoryId);
        List<attribute> attributeList =service.selectAttributeByCategoryId(id);
        return ResultEntity.infor(ResultCode.SUCCESS,attributeList);
    }

    @RequestMapping("addAttribute")
    public ResultEntity addAttribute(@RequestBody Map<String, Object> data){
        int categoryId = (int) data.get("categoryId");
        String attributeName = (String) data.get("attributeName");
        service.addAttribute(categoryId,attributeName);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }


    @RequestMapping("deleteAttribute")
    public ResultEntity deleteAttribute(@RequestBody String addributeId){
        int id =Integer.parseInt(addributeId);
        service.deleteAttribute(id);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }
}
