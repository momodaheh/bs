package com.bs.service;


import com.bs.pojo.category;

import java.util.List;

public interface categoryService extends BaseService<category>{
    List<category> selectCategoryByBrandId(int brandId);//根据品牌来查看分类
    void deletebrand_Category(int brandId,int categoryId);//删除某品牌下的某个分类
    List<category> selectCategory();//查询所有分类
    void addbradn_Category(int brandId,int categoryId);//添加某品牌的分类
    void addcategory(String name);
    void deleteCategory(int categoryId);//删除某分类
}
