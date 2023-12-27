package com.bs.service;

import java.util.List;

public interface BaseService<E> {
    Integer insertobject(E obj); 	//增加

    Integer updateobject(E obj);	//修改

    Integer deleteobject(Integer id);	//删除

    List<E> queryAll(); //全查

    List<E> queryPage(Integer offset,Integer size); //分页查询

    Integer queryCount(); //统计

    E queryObject(Integer id); //单查
}
