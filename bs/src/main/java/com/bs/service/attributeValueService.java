package com.bs.service;


import com.bs.pojo.attributeValue;

import java.util.List;

public interface attributeValueService extends BaseService<attributeValue> {
    List<attributeValue> selectAttributeValueById(int attribute_id);//根据属性的id拿属性值
    void addAttributeValue(int attribute_id,String attributeValue);//添加属性值
    void deleteAttributeValue(int id);//删除属性值
}
