package com.bs.serviceImpl;

import com.bs.mapper.BaseMapper;
import com.bs.mapper.attributeValueMapper;
import com.bs.pojo.attributeValue;
import com.bs.service.attributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;


@Transactional
@Service
public class attributeValueServiceImpl extends BaseServiceImpl<attributeValue> implements attributeValueService {
    @Autowired
    private attributeValueMapper mapper;

    @Override
    public BaseMapper<attributeValue> getBaseMapper(){
        return mapper;
    }

    @Override
    public List<attributeValue> selectAttributeValueById(int attribute_id) {
        try {
            return mapper.selectAttributeValueById(attribute_id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addAttributeValue(int attribute_id, String attributeValue) {
        try {
            mapper.addAttributeValue(attribute_id,attributeValue);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void deleteAttributeValue(int id) {
        try {
            mapper.deleteAttributeValue(id);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
