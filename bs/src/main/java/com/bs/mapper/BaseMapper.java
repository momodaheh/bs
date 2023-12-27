package com.bs.mapper;

import java.sql.SQLException;
import java.util.List;

public interface BaseMapper<E> {
    Integer insertobject(E obj) throws SQLException; 	//增加

    Integer updateobject(E obj) throws SQLException;	//修改

    Integer deleteobject(Integer id) throws SQLException;	//删除

    List<E> queryAll() throws SQLException; //全查

    List<E> queryPage(Integer offset,Integer size) throws SQLException; //分页查询


    Integer queryCount() throws SQLException; //统计

    E queryObject(Integer id) throws SQLException; //单查
}
