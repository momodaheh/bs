package com.bs.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.bs.mapper.BaseMapper;
import com.bs.service.BaseService;

public abstract class BaseServiceImpl<E> implements BaseService<E> {
	
	public abstract BaseMapper<E> getBaseMapper();
	
	@Override
	public Integer insertobject(E obj){
		try {
			return getBaseMapper().insertobject(obj);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public Integer updateobject(E obj) {
		try {
			return getBaseMapper().updateobject(obj);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public Integer deleteobject(Integer id) {
		try {
			return getBaseMapper().deleteobject(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public List<E> queryAll() {
		try {
			return getBaseMapper().queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<E> queryPage(Integer offset, Integer size) {
		try {
			return getBaseMapper().queryPage(offset, size);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Integer queryCount()  {
		try {
			return getBaseMapper().queryCount();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public E queryObject(Integer id) {
		try {
			return getBaseMapper().queryObject(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
