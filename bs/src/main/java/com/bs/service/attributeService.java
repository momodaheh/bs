package com.bs.service;


import com.bs.pojo.attribute;

import java.util.List;

public interface attributeService extends BaseService<attribute>{
    List<attribute> selectAttributeByCategoryId(int categoryId);//查询某个分类下的属性
    void addAttribute(int categoryId,String attributeName);//添加分类下的属性
    void deleteAttribute(int addributeId);//删除分类下的属性

}
