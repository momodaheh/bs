package com.bs.controller;

import com.bs.pojo.attributeValue;
import com.bs.service.attributeValueService;
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
@RequestMapping("attributeValue")
public class attributeValueController {
    @Resource
    private attributeValueService service;

    @RequestMapping("selectAttributeValueById")
    public ResultEntity<List<attributeValue>>  selectAttributeValueById(@RequestBody String attribute_id){
        int id=Integer.parseInt(attribute_id);
        List<attributeValue> attributeValueList=service.selectAttributeValueById(id);
        return ResultEntity.infor(ResultCode.SUCCESS,attributeValueList);
    }

    @RequestMapping("addAttributeValue")
    public ResultEntity addAttributeValue(@RequestBody Map<String, Object> data){
        int attribute_id = (int) data.get("attribute_id");
        String attributeValue = (String) data.get("attributeValue");
        service.addAttributeValue(attribute_id,attributeValue);
        return ResultEntity.infor(ResultCode.SUCCESS);

    }

    @RequestMapping("deleteAttributeValue")
    public ResultEntity deleteAttributeValue(@RequestBody String data){
        int id=Integer.parseInt(data);
        service.deleteAttributeValue(id);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }
}
