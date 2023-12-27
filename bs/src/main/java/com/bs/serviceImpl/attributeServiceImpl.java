package com.bs.serviceImpl;


import com.bs.mapper.BaseMapper;
import com.bs.mapper.attributeMapper;
import com.bs.pojo.attribute;

import com.bs.service.attributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Transactional
@Service
public class attributeServiceImpl extends BaseServiceImpl<attribute> implements attributeService {

    @Autowired
    private attributeMapper mapper;

    @Override
    public BaseMapper<attribute> getBaseMapper(){
        return mapper;
    }

    @Override
    public List<attribute> selectAttributeByCategoryId(int categoryId) {
        try {
            return mapper.selectAttributeByCategoryId(categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addAttribute(int categoryId, String attributeName) {
        try {
            mapper.addAttribute(categoryId,attributeName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAttribute(int addributeId) {
        try {
            mapper.deleteAttribute(addributeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
